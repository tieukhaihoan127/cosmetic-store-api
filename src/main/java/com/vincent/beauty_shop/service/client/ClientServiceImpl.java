package com.vincent.beauty_shop.service.client;

import com.vincent.beauty_shop.entity.Client;
import com.vincent.beauty_shop.entity.ClientAddress;
import com.vincent.beauty_shop.exception.ResourceNotFoundException;
import com.vincent.beauty_shop.mapper.AddressMapper;
import com.vincent.beauty_shop.mapper.ClientMapper;
import com.vincent.beauty_shop.repository.ClientAddressRepository;
import com.vincent.beauty_shop.repository.ClientRepository;
import com.vincent.beauty_shop.request.client.ClientAddressCreateRequest;
import com.vincent.beauty_shop.request.client.ClientAddressUpdateRequest;
import com.vincent.beauty_shop.request.client.ClientCreateRequest;
import com.vincent.beauty_shop.request.client.ClientUpdateRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ClientServiceImpl implements ClientService{
    private ClientRepository clientRepository;
    private ClientAddressRepository clientAddressRepository;
    private ClientMapper clientMapper;
    private AddressMapper addressMapper;

    public ClientServiceImpl(ClientRepository clientRepository, ClientMapper clientMapper, ClientAddressRepository clientAddressRepository, AddressMapper addressMapper) {
        this.clientRepository = clientRepository;
        this.clientAddressRepository = clientAddressRepository;
        this.clientMapper = clientMapper;
        this.addressMapper = addressMapper;
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
}
