package com.carservice.service;

import com.carservice.model.Appointment;
import com.carservice.repository.AppointmentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentsService {
    @Autowired
    private AppointmentsRepository repository;

    public List<Appointment> findAll() {
        return repository.findAll();
    }


    public Appointment findById(int id) {
        return repository.findById(id).get();
    }

    public Appointment create(Appointment appointment) {
        repository.save(appointment);
        return repository.findById(appointment.getId()).get();
    }

    public Appointment update(int id, Appointment appointment) {
        Appointment record = repository.findById(id).get();
        repository.save(appointment);
        return record;
    }

    public Appointment delete(int id) {
        Appointment appointment = repository.findById(id).get();
        repository.deleteById(id);
        return appointment;
    }
}
