package net.springapp.service;

import net.springapp.model.barbershop.Record;
import net.springapp.model.barbershop.ServiceStatus;
import org.springframework.dao.DataIntegrityViolationException;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface RecordService {

    List<Record> getAllRecords();

    List<LocalTime> getAllRecordsTimeByDate(LocalDate date);

    void save (Record record);

    List<LocalDate> findDateByStatus (ServiceStatus status);

    int calculateCost(long barberId, long serviceId);

    void payAndSaveRecord(long barberId, long serviceId, String dateAndTime, String userName) throws DataIntegrityViolationException;
}
