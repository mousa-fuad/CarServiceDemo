package com.carservice.service;

import com.carservice.model.Appointment;
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
class AppointmentsServiceTest {

    @Autowired
    @Mock
    AppointmentsService appointmentsService;
    Appointment appointment;

    @BeforeClass
    public void init() {
        appointment = new Appointment();
        appointment.setLocation("testLocation");
        appointmentsService = mock(AppointmentsService.class);
    }

    @Test
    void findAll() {
        Appointment appointment1 = new Appointment();
        appointment1.setLocation("testLocation1");

        Appointment appointment2 = new Appointment();
        appointment2.setLocation("testLocation2");


        List<Appointment> appointments = Arrays.asList(appointment1, appointment2);

        when(appointmentsService.findAll()).thenReturn(appointments);

        // when
        List<Appointment> result = appointmentsService.findAll();

        // then
        assertThat(result.size()).isEqualTo(2);

        assertThat(result.get(0).getId())
                .isEqualTo(appointment1.getId());

        assertThat(result.get(1).getId())
                .isEqualTo(appointment2.getId());

    }

    @Test
    void findById() {
        init();
        when(appointmentsService.create(any(Appointment.class))).thenReturn(appointment);
        when(appointmentsService.findById(appointment.getId())).thenReturn(appointment);

        appointmentsService.create(appointment);

        Appointment result = appointmentsService.findById(appointment.getId());

        assertThat(result.getId())
                .isEqualTo(appointment.getId());
    }

    @Test
    void create() {
        init();
        when(appointmentsService.create(any(Appointment.class))).thenReturn(appointment);
        when(appointmentsService.findById(appointment.getId())).thenReturn(appointment);

        appointmentsService.create(appointment);

        Appointment result = appointmentsService.findById(appointment.getId());

        assertThat(result.getId())
                .isEqualTo(appointment.getId());
    }

    @Test
    void update() {
        init();
        when(appointmentsService.update(appointment.getId(), appointment)).thenReturn(appointment);
        when(appointmentsService.findById(appointment.getId())).thenReturn(appointment);

        appointmentsService.update(appointment.getId(), appointment);

        Appointment result = appointmentsService.findById(appointment.getId());

        assertThat(result.getId())
                .isEqualTo(appointment.getId());
    }

    @Test
    void delete() {
        init();
        when(appointmentsService.create(any(Appointment.class))).thenReturn(appointment);
        when(appointmentsService.delete(appointment.getId())).thenReturn(appointment);

        appointmentsService.create(appointment);
        Appointment result = appointmentsService.delete(appointment.getId());

        assertThat(result.getId())
                .isEqualTo(appointment.getId());
    }
}

