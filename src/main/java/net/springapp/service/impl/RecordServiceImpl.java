package net.springapp.service.impl;

import net.springapp.model.User;
import net.springapp.model.barbershop.Barber;
import net.springapp.model.barbershop.Client;
import net.springapp.model.barbershop.Record;
import net.springapp.model.barbershop.ServiceStatus;
import net.springapp.repository.*;
import net.springapp.service.RecordService;
import net.springapp.service.ServiceUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RecordServiceImpl implements RecordService {

    @Autowired
    RecordRepository recordRepository;

    @Autowired
    ClientRepository clientRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ServiceRepository serviceRepository;

    @Autowired
    BarberRepository barberRepository;

    @Override
    public void remove(long id) {
        recordRepository.delete(id);
    }

    @Override
    public Record getRecordById(long id) {
        return recordRepository.findOne(id);
    }

    @Override
    public List<Record> getAllRecords() {
        return recordRepository.findAll(orderByDateAndTime());
    }
    private Sort orderByDateAndTime() {
        return new Sort(Sort.Direction.ASC, "localDate")
                .and(new Sort(Sort.Direction.ASC, "localTime"));
    }

    @Override
    public List<LocalTime> getAllRecordsTimeByDate(LocalDate date) {
        return recordRepository.findByLocalDateOrderByLocalTimeAsc(date).stream().map(Record::getLocalTime).collect(Collectors.toList());
    }

    @Override
    public void save(Record record) {
        recordRepository.save(record);
    }

    @Override
    public List<LocalDate> findDateByStatus(ServiceStatus status) {
        return recordRepository.findDistinctByStatus(status).stream().map(Record::getLocalDate).collect(Collectors.toList());
    }

    @Override
    public int calculateCost(long barberId, long serviceId) {
        return recordRepository.calculateCost(barberId, serviceId);
    }

    @Transactional
    @Override
    public void payAndSaveRecord(long barberId, long serviceId, String dateAndTime, String userName) throws DataIntegrityViolationException {

        LocalDateTime localDateTime = ServiceUtil.convertToDateAndTime(dateAndTime, "_", "MM/dd/yyyy");

        LocalDate localDate = localDateTime.toLocalDate();

        LocalTime localTime = localDateTime.toLocalTime();


        User user = userRepository.findByUserName(userName);

        clientRepository.writeOffMoney(barberId, serviceId, user.getId());

        net.springapp.model.barbershop.Service service = serviceRepository.findOne(serviceId);

        Barber barber = barberRepository.findOne(barberId);

        Client client = clientRepository.findByUserAccount(user);

        recordRepository.rezerv(ServiceStatus.RESERVED, service, client, ServiceStatus.NOT_RESERVED, localDate, localTime, barber);

    }
}
