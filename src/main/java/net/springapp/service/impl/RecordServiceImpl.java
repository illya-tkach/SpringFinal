package net.springapp.service.impl;

import net.springapp.model.User;
import net.springapp.model.barbershop.Barber;
import net.springapp.model.barbershop.Client;
import net.springapp.model.barbershop.Record;
import net.springapp.model.barbershop.ServiceStatus;
import net.springapp.repository.*;
import net.springapp.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
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
    public List<Record> getAllRecords() {
        return recordRepository.findAll();
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
        String[] dateAndTimeString = dateAndTime.split(" ");
        String date = dateAndTimeString[0];
        String time = dateAndTimeString[1];
        DateTimeFormatter dateformatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        //convert String to LocalDate
        LocalDate localDate = LocalDate.parse(date, dateformatter);
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
        LocalTime localTime = LocalTime.parse(time, timeFormatter);

        User user = userRepository.findByUserName(userName);

        clientRepository.writeOffMoney(barberId, serviceId, user.getId());

        net.springapp.model.barbershop.Service service = serviceRepository.findOne(serviceId);

        Barber barber = barberRepository.findOne(barberId);

        Client client = clientRepository.findByUserAccount(user);

        recordRepository.rezerv(ServiceStatus.RESERVED, service, client, ServiceStatus.NOT_RESERVED, localDate, localTime, barber);

    }
}
