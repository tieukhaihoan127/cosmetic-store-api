package com.vincent.beauty_shop.service.client;

import com.vincent.beauty_shop.entity.Client;
import com.vincent.beauty_shop.entity.ClientAddress;
import com.vincent.beauty_shop.request.client.ClientAddressCreateRequest;
import com.vincent.beauty_shop.request.client.ClientAddressUpdateRequest;
import com.vincent.beauty_shop.request.client.ClientCreateRequest;
import com.vincent.beauty_shop.request.client.ClientUpdateRequest;

import java.util.List;

public interface ClientService {
    List<Client> getAllClients();

    Client createClient(ClientCreateRequest clientRequest);

    Client getClientById(Long id);

    Client updateClient(Long id, ClientUpdateRequest clientRequest);

    ClientAddress createClientAddress(Long id, ClientAddressCreateRequest request);

    ClientAddress updateClientAddress(Long id, ClientAddressUpdateRequest request);

    void deleteClientAddress(Long id);

    List<ClientAddress> getAllClientAddress(Long id);

    void deleteClient(Long id);
}
