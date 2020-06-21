package com.carservice.service;

import com.carservice.model.Car;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
class CarsServiceTest {

    @Autowired
    @Mock
    CarsService carsService;
    Car car;

    @BeforeClass
    public void init() {
        car = new Car();
        car.setModel("testModel");
        car.setPrice(1);
        carsService = mock(CarsService.class);
    }

    @Test
    void findAll() {
        Car car1 = new Car();
        car1.setModel("testModel1");
        car1.setPrice(1);
        Car car2 = new Car();
        car2.setModel("testModel2");
        car2.setPrice(1);

        List<Car> cars = Arrays.asList(car1, car2);

        when(carsService.findAll()).thenReturn(cars);

        // when
        List<Car> result = carsService.findAll();

        // then
        assertThat(result.size()).isEqualTo(2);

        assertThat(result.get(0).getId())
                .isEqualTo(car1.getId());

        assertThat(result.get(1).getId())
                .isEqualTo(car2.getId());

    }

    @Test
    void findById() {
        init();
        when(carsService.create(any(Car.class))).thenReturn(car);
        when(carsService.findById(car.getId())).thenReturn(car);

        carsService.create(car);

        Car result = carsService.findById(car.getId());

        assertThat(result.getId())
                .isEqualTo(car.getId());
    }

    @Test
    void create() {
        init();
        when(carsService.create(any(Car.class))).thenReturn(car);
        when(carsService.findById(car.getId())).thenReturn(car);

        carsService.create(car);

        Car result = carsService.findById(car.getId());

        assertThat(result.getId())
                .isEqualTo(car.getId());
    }

    @Test
    void update() {
        init();
        when(carsService.update(car.getId(), car)).thenReturn(car);
        when(carsService.findById(car.getId())).thenReturn(car);

        carsService.update(car.getId(), car);

        Car result = carsService.findById(car.getId());

        assertThat(result.getId())
                .isEqualTo(car.getId());
    }

    @Test
    void delete() {
        init();
        when(carsService.create(any(Car.class))).thenReturn(car);
        when(carsService.delete(car.getId())).thenReturn(car);

        carsService.create(car);
        Car result = carsService.delete(car.getId());

        assertThat(result.getId())
                .isEqualTo(car.getId());
    }
}

