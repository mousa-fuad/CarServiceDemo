package com.carservice.service;

import com.carservice.repository.CarsRepository;
import com.carservice.model.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CarsService {
    @Autowired
    private CarsRepository repository;

    CarsService(CarsRepository repository) {
        this.repository = repository;
    }


    public List<Car> findAll() {
        return repository.findAll();
    }


    public Car findById(int id) {
        return repository.findById(id).get();
    }

    public Car create(Car car) {
        repository.save(car);
        return repository.findById(car.getId()).get();
    }


    public Car update(int id, Car car) {
        Car record = repository.findById(id).get();
        record.setModel(car.getModel());
        record.setPrice(car.getPrice());
        repository.save(car);
        return record;
    }


    public Car delete(int id) {
        Car car = repository.findById(id).get();
        repository.deleteById(id);
        return car;
    }
}
