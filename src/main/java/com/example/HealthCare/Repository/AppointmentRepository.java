package com.example.HealthCare.Repository;

import com.example.HealthCare.Model.Appointment;
import com.example.HealthCare.Model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository

public interface AppointmentRepository extends JpaRepository<Appointment,Long> {

    @Query("SELECT COUNT(a) FROM Appointment a WHERE a.appointmentDate = :date")
    long countByAppointmentDate(@Param("date") LocalDate date);

    List<Appointment> findByAppointmentDate(LocalDate appointmentDate);

    List<Appointment> findByPatient_PatientId(Long patientId);

    List<Appointment> findByDoctor_DoctorId(Long doctorId);

//    @Query("SELECT COUNT(a) FROM Appointment a WHERE a.doctor.doctorId = :doctorId AND a.appointmentDate = :today")
//    int countTodayAppointments(@Param("doctorId") Long doctorId, @Param("today") LocalDate today);

    // Fetch upcoming appointments for a patient
    @Query("SELECT a FROM Appointment a WHERE a.patient.patientId = :patientId AND a.appointmentDate >= :date ORDER BY a.appointmentDate ASC")
    List<Appointment> findUpcomingAppointments(Long patientId, LocalDate date);

    @Query("select distinct a.patient from Appointment a where a.doctor.doctorId = :doctorId")
    List<Patient> findDistinctPatientsByDoctorId(@Param("doctorId") Long doctorId);

    boolean existsByDoctor_DoctorIdAndPatient_PatientId(Long doctorId, Long patientId);

    List<Appointment> findByDoctorDoctorIdAndAppointmentDateAfter(Long doctorId, LocalDate now);

    Optional<Appointment> findByDoctorDoctorIdAndAppointmentId(Long doctorId, Long appointmentId);


}



