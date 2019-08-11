package net.springapp.repository;

import net.springapp.model.barbershop.Record;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface RecordRepository extends JpaRepository<Record,Long> {
    List<Record> findByLocalDateOrderByLocalTimeAsc(LocalDate date);
}
