package com.carservice.repository;

import com.carservice.model.Appointment;
import com.carservice.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface AppointmentsRepository extends JpaRepository<Appointment, Integer> {

}
