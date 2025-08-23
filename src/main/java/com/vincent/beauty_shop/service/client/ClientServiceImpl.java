package com.vincent.beauty_shop.service.client;

import com.vincent.beauty_shop.entity.Client;
import com.vincent.beauty_shop.exception.ResourceNotFoundException;
import com.vincent.beauty_shop.mapper.ClientMapper;
import com.vincent.beauty_shop.repository.ClientRepository;
import com.vincent.beauty_shop.request.client.ClientCreateRequest;
import com.vincent.beauty_shop.request.client.ClientUpdateRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ClientServiceImpl implements ClientService{
    private ClientRepository clientRepository;
    private ClientMapper clientMapper;

    public ClientServiceImpl(ClientRepository clientRepository, ClientMapper clientMapper) {
        this.clientRepository = clientRepository;
        this.clientMapper = clientMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    @Override
    public Client createClient(ClientCreateRequest clientRequest) {
        Client client = Client.builder().firstName(clientRequest.getFirstName()).lastName(clientRequest.getLastName()).email(clientRequest.getEmail()).phone(clientRequest.getPhone()).status("Active").deleted(false).loyaltyPoint(0.0).build();
        return clientRepository.save(client);
    }

    @Override
    @Transactional(readOnly = true)
    public Client getClientById(Long id) {
        return clientRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Client not found with id " + id));
    }

    @Override
    public Client updateClient(Long id, ClientUpdateRequest clientRequest) {
        Client client = clientRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Client not found with id " + id));
        clientMapper.updateClientFromDto(clientRequest, client);
        return clientRepository.save(client);
    }

    @Override
    public void deleteClient(Long id) {
        Client client = clientRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Client not found with id " + id));
        client.setDeleted(true);
        clientRepository.save(client);
    }
}
