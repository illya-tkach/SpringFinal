package net.springapp.service;

import net.springapp.model.barbershop.Client;

import java.util.Optional;

public interface ClientService {
    Optional<Client> findClient(int userAccountId);

    void addMoneyToBalance(int money, int userAccountId);
}
