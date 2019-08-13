package net.springapp.service;

import net.springapp.model.barbershop.Client;

import java.util.Optional;

public interface ClientService {
    Optional<Client> findClient(String userAccountName);

    void addMoneyToBalance(int money, String userName);
}
