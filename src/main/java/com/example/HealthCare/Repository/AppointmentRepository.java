package com.example.HealthCare.Repository;

import com.example.HealthCare.Model.Appointment;
import com.example.HealthCare.Model.Doctor;
import com.example.HealthCare.Model.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository

public interface AppointmentRepository extends JpaRepository<Appointment, UUID> {

    @Query("SELECT COUNT(a) FROM Appointment a WHERE a.appointmentDate = :date")
    long countByAppointmentDate(@Param("date") LocalDate date);

    List<Appointment> findByAppointmentDate(LocalDate appointmentDate);

    List<Appointment> findByPatient_PatientId(UUID patientId);

    List<Appointment> findByDoctor_DoctorId(UUID doctorId);

    // Fetch upcoming appointments for a patient
    @Query("SELECT a FROM Appointment a WHERE a.patient.patientId = :patientId AND a.appointmentDate >= :date ORDER BY a.appointmentDate ASC")
    List<Appointment> findUpcomingAppointments(UUID patientId, LocalDate date);

    @Query("select distinct a.patient from Appointment a where a.doctor.doctorId = :doctorId")
    List<Patient> findDistinctPatientsByDoctorId(@Param("doctorId") UUID doctorId);

    boolean existsByDoctor_DoctorIdAndPatient_PatientId(UUID doctorId, UUID patientId);

    List<Appointment> findByDoctorDoctorIdAndAppointmentDateAfter(UUID doctorId, LocalDate now);

    Optional<Appointment> findByDoctorDoctorIdAndAppointmentId(UUID doctorId, UUID appointmentId);

    @Query("SELECT a FROM Appointment a WHERE a.patient.patientId = :patientId AND a.doctor.doctorId = :doctorId ORDER BY a.appointmentDate DESC")
    List<Appointment> findByDoctorIdAndPatientId(@Param("doctorId") UUID doctorId, @Param("patientId") UUID patientId);
}



