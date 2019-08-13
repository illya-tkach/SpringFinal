package net.springapp.repository;

import net.springapp.model.barbershop.Barber;
import net.springapp.model.barbershop.ServiceStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface BarberRepository extends JpaRepository<Barber,Long> {
    @Query("select b from Barber b inner join b.services sr where sr.id = :serviceId")
    List<Barber> findBarbersByService(@Param("serviceId") long serviceId);

    @Query("select b from Barber b inner join b.records rec where rec.localDate = :localDate and rec.localTime = :localTime and rec.status = :status")
    List<Barber> findBarberByDateAndTime(@Param("localDate") LocalDate localDate, @Param("localTime") LocalTime localTime, @Param("status") ServiceStatus status);
}
