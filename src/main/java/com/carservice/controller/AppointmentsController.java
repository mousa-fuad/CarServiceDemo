package com.carservice.controller;

import com.carservice.model.Appointment;
import com.carservice.service.AppointmentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/appointments")
public class AppointmentsController {

    @Autowired
    private final AppointmentsService service;

    public AppointmentsController(AppointmentsService service) {
        this.service = service;
    }

    @GetMapping
    public List<Appointment> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Appointment findById(@PathVariable int id) {
        return service.findById(id);
    }

    @PostMapping
    public Appointment create(@RequestBody Appointment appointment) {
        service.create(appointment);
        return service.findById(appointment.getId());
    }

    @PutMapping(value = "/{id}")
    public Appointment update(@PathVariable int id, @RequestBody Appointment appointment) {
        Appointment record = service.findById(id);
        service.update(id, appointment);
        return record;
    }

    @DeleteMapping(path = {"/{id}"})
    public Appointment delete(@PathVariable("id") int id) {
        Appointment appointment = service.findById(id);
        service.delete(id);
        return appointment;
    }

}
