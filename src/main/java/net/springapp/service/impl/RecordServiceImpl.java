package net.springapp.service.impl;

import net.springapp.model.barbershop.Record;
import net.springapp.repository.RecordRepository;
import net.springapp.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class RecordServiceImpl implements RecordService {

    @Autowired
    RecordRepository recordRepository;

    @Override
    public List<Record> getAllRecords() {
        return recordRepository.findAll();
    }

    @Override
    public List<Record> getAllRecordsByDate(LocalDate date) {
        return recordRepository.findByLocalDateOrderByLocalTimeAsc(date);
    }

    @Override
    public void save(Record record) {
        recordRepository.save(record);
    }
}
