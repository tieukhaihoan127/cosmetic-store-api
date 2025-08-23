package com.vincent.beauty_shop.service.client;

import com.vincent.beauty_shop.entity.Client;
import com.vincent.beauty_shop.request.client.ClientCreateRequest;
import com.vincent.beauty_shop.request.client.ClientUpdateRequest;

import java.util.List;

public interface ClientService {
    List<Client> getAllClients();

    Client createClient(ClientCreateRequest clientRequest);

    Client getClientById(Long id);

    Client updateClient(Long id, ClientUpdateRequest clientRequest);

    void deleteClient(Long id);
}
