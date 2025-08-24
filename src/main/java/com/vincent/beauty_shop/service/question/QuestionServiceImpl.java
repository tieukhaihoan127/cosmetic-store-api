package com.vincent.beauty_shop.service.question;

import com.vincent.beauty_shop.entity.Answer;
import com.vincent.beauty_shop.entity.Client;
import com.vincent.beauty_shop.entity.Product;
import com.vincent.beauty_shop.entity.Question;
import com.vincent.beauty_shop.exception.ResourceNotFoundException;
import com.vincent.beauty_shop.repository.ClientRepository;
import com.vincent.beauty_shop.repository.ProductRepository;
import com.vincent.beauty_shop.repository.QuestionRepository;
import com.vincent.beauty_shop.request.question.QuestionCreateRequest;
import com.vincent.beauty_shop.response.answer.AnswerDTO;
import com.vincent.beauty_shop.response.question.QuestionDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class QuestionServiceImpl implements QuestionService{

    private QuestionRepository questionRepository;
    private ProductRepository productRepository;
    private ClientRepository clientRepository;

    public QuestionServiceImpl(QuestionRepository questionRepository, ProductRepository productRepository, ClientRepository clientRepository) {
        this.questionRepository = questionRepository;
        this.productRepository = productRepository;
        this.clientRepository = clientRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<QuestionDTO> getAllQuestions() {
        return questionRepository.findAll().stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    @Override
    public QuestionDTO createQuestion(QuestionCreateRequest request) {
        Product product = productRepository.findById(request.getProductId()).orElseThrow(() -> new ResourceNotFoundException("Product not found with id " + request.getProductId()));
        Client client = clientRepository.findById(request.getClientId()).orElseThrow(() -> new ResourceNotFoundException("Client not found with id " + request.getClientId()));

        Question question = Question.builder().question(request.getQuestion()).product(product).client(client).timestamp(LocalDateTime.now()).build();
        Question saved = questionRepository.save(question);
        return mapToDTO(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public QuestionDTO getQuestionById(Long id) {
        Question question = questionRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Question not found with id " + id));
        return mapToDTO(question);
    }

    @Override
    public void deleteQuestion(Long id) {
        Question question = questionRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Question not found with id " + id));
        questionRepository.delete(question);
    }

    private QuestionDTO mapToDTO(Question question) {
        Answer answer = question.getAnswer();

        AnswerDTO answerDTO = null;
        if (answer != null) {
            answerDTO = AnswerDTO.builder()
                    .id(answer.getId())
                    .answer(answer.getAnswer())
                    .timestamp(answer.getTimestamp())
                    .build();
        }

        return QuestionDTO.builder()
                .id(question.getId())
                .question(question.getQuestion())
                .timestamp(question.getTimestamp())
                .productName(question.getProduct() != null ? question.getProduct().getTitle() : null)
                .clientName(question.getClient() != null ? question.getClient().getFirstName() : null)
                .answer(answerDTO)
                .build();
    }

}
