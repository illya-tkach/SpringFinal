package net.springapp.service.impl;

import net.springapp.model.barbershop.Client;
import net.springapp.repository.ClientRepository;
import net.springapp.service.ClientService;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    ClientRepository clientRepository;

    @Override
    public Optional<Client> findClient(String userAccountName) {
       return Optional.of(clientRepository.getClientByUserAccountId(userAccountName));
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public void addMoneyToBalance(int money, String userName) {
        clientRepository.addMoneyToBalance(money, userName);
    }
}
