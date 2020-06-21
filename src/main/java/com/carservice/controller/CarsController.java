package com.carservice.controller;

import com.carservice.model.Car;
import com.carservice.repository.CarsRepository;
import com.carservice.service.CarsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping({"/cars"})
public class CarsController {

    @Autowired
    private CarsService service;

    public CarsController(CarsService service) {
        this.service = service;
    }

    @GetMapping
    public List<Car> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Car findById(@PathVariable int id) {
        return service.findById(id);
    }

    @PostMapping
    public Car create(@RequestBody Car car) {
        service.create(car);
        return service.findById(car.getId());
    }

    @PutMapping(value = "/{id}")
    public Car update(@PathVariable int id, @RequestBody Car car) {
        Car record = service.findById(id);
        record.setModel(car.getModel());
        record.setPrice(car.getPrice());
        service.create(car);
        return record;
    }

    @DeleteMapping(path = {"/{id}"})
    public Car delete(@PathVariable("id") int id) {
        Car car = service.findById(id);
        service.delete(id);
        return car;
    }
}
