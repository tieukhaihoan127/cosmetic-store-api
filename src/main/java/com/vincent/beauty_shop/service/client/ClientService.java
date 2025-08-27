package com.vincent.beauty_shop.service.client;

import com.vincent.beauty_shop.entity.Client;
import com.vincent.beauty_shop.entity.ClientAddress;
import com.vincent.beauty_shop.exception.BadRequestException;
import com.vincent.beauty_shop.request.client.ClientAddressCreateRequest;
import com.vincent.beauty_shop.request.client.ClientAddressUpdateRequest;
import com.vincent.beauty_shop.request.client.ClientCreateRequest;
import com.vincent.beauty_shop.request.client.ClientUpdateRequest;
import com.vincent.beauty_shop.request.wishlist.WishlistRequest;
import com.vincent.beauty_shop.response.wishlist.WishlistDTO;

import java.util.List;

public interface ClientService {
    List<Client> getAllClients();

    List<WishlistDTO> getAllProductsFromWishlist(Long id);

    Client createClient(ClientCreateRequest clientRequest);

    Client getClientById(Long id);

    Client updateClient(Long id, ClientUpdateRequest clientRequest);

    ClientAddress createClientAddress(Long id, ClientAddressCreateRequest request);

    ClientAddress updateClientAddress(Long id, ClientAddressUpdateRequest request);

    void deleteClientAddress(Long id);

    List<ClientAddress> getAllClientAddress(Long id);

    void deleteClient(Long id);

    WishlistDTO addProductToWishlist(WishlistRequest request) throws BadRequestException;

    void removeProductFromWishlist(WishlistRequest request);
}
