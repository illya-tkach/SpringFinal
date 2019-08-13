package net.springapp.repository;

import net.springapp.model.User;
import net.springapp.model.barbershop.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ClientRepository extends JpaRepository<Client,Long> {

    @Query("select c from Client c where c.userAccount.userName = :userName")
    Client getClientByUserAccountId(@Param("userName") String userName);

    @Modifying
    @Query("update Client c set c.balance = c.balance + :money where c.userAccount = (select u from User u WHERE u.userName = :userName)")
    void addMoneyToBalance(@Param("money") int money, @Param("userName") String userName);


    @Modifying
    @Query(value = "UPDATE client set client.balance = client.balance - (select cost from( SELECT barberlevel.coefficient*service.cost AS cost FROM barber JOIN service JOIN barber_service ON barber_service.barber_id = barber.id AND service.id = barber_service.service_id JOIN barberlevel ON barber.level_id = barberlevel.id WHERE barber.id = ? AND service.id = ?) as resultTable) where client.useraccount_id = ?;",
            nativeQuery = true)
    void writeOffMoney(long barberId, long serviceId, long userId);

    Client findByUserAccount(User userAccount);
}
