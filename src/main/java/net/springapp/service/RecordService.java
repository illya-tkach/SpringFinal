package net.springapp.service;

import net.springapp.model.barbershop.Record;

import java.time.LocalDate;
import java.util.List;

public interface RecordService {

    List<Record> getAllRecords();

    List<Record> getAllRecordsByDate(LocalDate date);

    void save (Record record);
}
