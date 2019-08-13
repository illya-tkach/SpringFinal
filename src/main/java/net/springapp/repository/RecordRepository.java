package net.springapp.repository;

import net.springapp.model.barbershop.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface RecordRepository extends JpaRepository<Record,Long> {


    List<Record> findByLocalDateOrderByLocalTimeAsc(LocalDate date);

    List<Record> findDistinctByStatus (ServiceStatus status);

    @Query("select b.barberLevel.serviceCostCoef*sr.serviceCost from Barber b inner join b.services sr where sr.id = :serviceId AND b.id = :barberId")
    int calculateCost(@Param("barberId") long barberId, @Param("serviceId") long serviceId);

    @Modifying
    @Query(value = "UPDATE Record SET status = :setStatus, service = :service, client = :client where status = :status and localDate = :localDate and localTime = :localTime and barber = :barber")
    void rezerv(@Param("setStatus") ServiceStatus setStatus, @Param("service") Service service, @Param("client") Client client, @Param("status") ServiceStatus status, @Param("localDate") LocalDate localDate, @Param("localTime") LocalTime localTime, @Param("barber") Barber barber);
}
