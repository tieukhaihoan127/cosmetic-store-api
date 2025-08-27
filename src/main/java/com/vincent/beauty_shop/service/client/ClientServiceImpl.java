package com.vincent.beauty_shop.service.client;

import com.vincent.beauty_shop.entity.Client;
import com.vincent.beauty_shop.entity.ClientAddress;
import com.vincent.beauty_shop.entity.Product;
import com.vincent.beauty_shop.entity.Wishlist;
import com.vincent.beauty_shop.exception.ResourceNotFoundException;
import com.vincent.beauty_shop.exception.BadRequestException;
import com.vincent.beauty_shop.mapper.AddressMapper;
import com.vincent.beauty_shop.mapper.ClientMapper;
import com.vincent.beauty_shop.repository.ClientAddressRepository;
import com.vincent.beauty_shop.repository.ClientRepository;
import com.vincent.beauty_shop.repository.ProductRepository;
import com.vincent.beauty_shop.repository.WishlistRepository;
import com.vincent.beauty_shop.request.client.ClientAddressCreateRequest;
import com.vincent.beauty_shop.request.client.ClientAddressUpdateRequest;
import com.vincent.beauty_shop.request.client.ClientCreateRequest;
import com.vincent.beauty_shop.request.client.ClientUpdateRequest;
import com.vincent.beauty_shop.request.wishlist.WishlistRequest;
import com.vincent.beauty_shop.response.wishlist.WishlistDTO;
import com.vincent.beauty_shop.util.ProductUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ClientServiceImpl implements ClientService{
    private ClientRepository clientRepository;
    private ClientAddressRepository clientAddressRepository;
    private ProductRepository productRepository;
    private WishlistRepository wishlistRepository;
    private ClientMapper clientMapper;
    private AddressMapper addressMapper;

    public ClientServiceImpl(ClientRepository clientRepository, ClientMapper clientMapper, ClientAddressRepository clientAddressRepository, AddressMapper addressMapper, ProductRepository productRepository, WishlistRepository wishlistRepository) {
        this.clientRepository = clientRepository;
        this.clientAddressRepository = clientAddressRepository;
        this.productRepository = productRepository;
        this.wishlistRepository = wishlistRepository;
        this.clientMapper = clientMapper;
        this.addressMapper = addressMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    @Override
    public List<WishlistDTO> getAllProductsFromWishlist(Long id) {
        List<Product> products = wishlistRepository.findProductsByClientId(id);

        if(products.isEmpty()) {
            return Collections.emptyList();
        }

        return products.stream().map(this::mapToWishlistDTO).collect(Collectors.toList());
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
    public ClientAddress createClientAddress(Long id, ClientAddressCreateRequest request) {
        Client client = clientRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Client not found with id " + id));
        ClientAddress address = ClientAddress.builder().title(request.getTitle()).firstName(request.getFirstName()).lastName(request.getLastName()).phone(request.getPhone()).city(request.getCity()).district(request.getDistrict()).ward(request.getWard()).address(request.getAddress()).defaultAddress(request.isDefaultAddress()).client(client).build();
        return clientAddressRepository.save(address);
    }

    @Override
    public List<ClientAddress> getAllClientAddress(Long id) {
        if (!clientRepository.existsById(id)) {
            throw new ResourceNotFoundException("Client not found with id " + id);
        }

        return clientAddressRepository.findByClientIdOrderByIdDesc(id);
    }

    @Override
    public ClientAddress updateClientAddress(Long id, ClientAddressUpdateRequest request) {
        ClientAddress clientAddress = clientAddressRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Address not found with id " + id));
        addressMapper.updateClientAddressFromDto(request, clientAddress);
        return clientAddressRepository.save(clientAddress);
    }

    @Override
    public void deleteClientAddress(Long id) {
        ClientAddress clientAddress = clientAddressRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Address not found with id " + id));
        clientAddressRepository.delete(clientAddress);
    }

    @Override
    public void deleteClient(Long id) {
        Client client = clientRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Client not found with id " + id));
        client.setDeleted(true);
        clientRepository.save(client);
    }

    @Override
    public WishlistDTO addProductToWishlist(WishlistRequest request) throws BadRequestException {
        Client client = clientRepository.findById(request.getClientId()).orElseThrow(() -> new ResourceNotFoundException("Client not found with id " + request.getClientId()));

        Product product = productRepository.findById(request.getProductId()).orElseThrow(() -> new ResourceNotFoundException("Product not found with id " + request.getProductId()));

        if (wishlistRepository.existsByClientIdAndProductId(request.getClientId(), request.getProductId())) {
            throw new BadRequestException("Product already exists in wishlist");
        }

        Wishlist wishlist = Wishlist.builder().client(client).product(product).build();
        wishlistRepository.save(wishlist);

        double price = ProductUtil.calculateProductMinPrice(product);
        WishlistDTO wishlistDTO = WishlistDTO.builder().productTitle(product.getTitle()).price(price).build();

        return wishlistDTO;
    }

    @Override
    public void removeProductFromWishlist(WishlistRequest request) {
        Wishlist wishlist = wishlistRepository.findByClientIdAndProductId(request.getClientId(), request.getProductId()).orElseThrow(() -> new ResourceNotFoundException(
                "Wishlist not found for clientId " + request.getClientId() + " and productId " + request.getProductId()
        ));;

        wishlistRepository.delete(wishlist);
    }

    private WishlistDTO mapToWishlistDTO(Product product) {
        double price = ProductUtil.calculateProductMinPrice(product);
        WishlistDTO wishlistDTO = WishlistDTO.builder().productTitle(product.getTitle()).price(price).build();
        return wishlistDTO;
    }
}
