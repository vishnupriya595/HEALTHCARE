package com.example.HealthCare.Service;

import com.example.HealthCare.DTO.*;
import com.example.HealthCare.Model.*;
import com.example.HealthCare.Repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor

public class AdminService {
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;
    private final AppointmentRepository appointmentRepository;
    private final NurseRepository nurseRepository;
    private final UserRepository userRepository;
    private final PrescriptionRepository prescriptionRepository;
    private final BillingRepository billingRepository;
    private final PasswordEncoder passwordEncoder;

    public Map<String, Object> getDashboardSummary() {
        Map<String, Object> summary = new HashMap<>();

        long totalDoctors = doctorRepository.count();
        long totalPatients = patientRepository.count();
        long totalNurses = nurseRepository.count();

        LocalDate today = LocalDate.now(); // ✅ keep as LocalDate
        long activeAppointmentsToday = appointmentRepository.countByAppointmentDate(today);

        summary.put("totalPatients", totalPatients);
        summary.put("totalDoctors", totalDoctors);
        summary.put("totalNurses", totalNurses);
        summary.put("activeAppointmentsToday", activeAppointmentsToday);

        return summary;
    }


    public Doctor addDoctor(DoctorRequestDTO dto) {
        Users user = Users.builder()
                .username(dto.getDoctorUsername())
                .password(passwordEncoder.encode(dto.getDoctorPassword()))
                .role(Users.Role.DOCTOR)
                .build();
        userRepository.save(user);

        Doctor doctor = Doctor.builder()
                .name(dto.getDoctorName())
                .specialization(dto.getSpecialization())
                .experience(dto.getExperience())
                .users(user) // relation mapping
                .build();

        return doctorRepository.save(doctor);
    }

    // Get all doctors
    public List<DoctorResponseDTO> getAllDoctors() {
        return doctorRepository.findAll()
                .stream()
                .map(doc -> DoctorResponseDTO.builder()
                        .doctorId(doc.getDoctorId())
                        .doctorName(doc.getName())
                        .specialization(doc.getSpecialization())
                        .experience(doc.getExperience())
                        .build())
                .collect(Collectors.toList());
    }

    // Get doctor by ID
    public DoctorResponseDTO getDoctorById(Long id) {
        Doctor doc = doctorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Doctor not found with id: " + id));

        return DoctorResponseDTO.builder()
                .doctorId(doc.getDoctorId())
                .doctorName(doc.getName())
                .specialization(doc.getSpecialization())
                .experience(doc.getExperience())
                .build();
    }

    // Update doctor
    public DoctorResponseDTO updateDoctor(Long id, DoctorRequestDTO dto) {
        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Doctor not found with id: " + id));

        doctor.setName(dto.getDoctorName());
        doctor.setSpecialization(dto.getSpecialization());
        doctor.setExperience(dto.getExperience());

        doctorRepository.save(doctor);

        return DoctorResponseDTO.builder()
                .doctorId(doctor.getDoctorId())
                .doctorName(doctor.getName())
                .specialization(doctor.getSpecialization())
                .experience(doctor.getExperience())
                .build();
    }


    // Delete doctor
    public void deleteDoctor(Long id) {
        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Doctor not found with id: " + id));
        userRepository.delete(doctor.getUsers());
        doctorRepository.delete(doctor);
    }

    // add patient
    public Patient addPatient(PatientRequestDTO dto) {
        Users user = Users.builder()
                .username(dto.getPatientUsername())
                .password(passwordEncoder.encode(dto.getPatientPassword()))
                .role(Users.Role.PATIENT)
                .build();
        userRepository.save(user);

        Patient patient = Patient.builder()
                .name(dto.getName())
                .age(dto.getAge())
                .gender(dto.getGender())
                .address(dto.getAddress())
                .phone(dto.getPhone())
                .disease(dto.getDisease())
                .users(user) // relation mapping
                .build();

        return patientRepository.save(patient);
    }
    // Get all patients
    public List<PatientResponseDTO> getAllPatients() {
        return patientRepository.findAll()
                .stream()
                .map(pat -> PatientResponseDTO.builder()
                        .patientId(pat.getPatientId())
                        .name(pat.getName())
                        .age(pat.getAge())
                        .gender(pat.getGender())
                        .address(pat.getAddress())
                        .phone(pat.getPhone())
                        .disease(pat.getDisease())
                        .build())
                .collect(Collectors.toList());
    }

    // Get patient by ID
    public PatientResponseDTO getPatientById(Long id) {
        Patient pat = patientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Patient not found with id: " + id));

        return PatientResponseDTO.builder()
                .patientId(pat.getPatientId())
                .name(pat.getName())
                .age(pat.getAge())
                .gender(pat.getGender())
                .address(pat.getAddress())
                .phone(pat.getPhone())
                .disease(pat.getDisease())
                .build();
    }

    // Update patient
    public PatientResponseDTO updatePatient(Long id, PatientRequestDTO dto) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Patient not found with id: " + id));

        patient.setName(dto.getName());
        patient.setAge(dto.getAge());
        patient.setGender(dto.getGender());
        patient.setAddress(dto.getAddress());
        patient.setPhone(dto.getPhone());
        patient.setDisease(dto.getDisease());

        patientRepository.save(patient);

        return PatientResponseDTO.builder()
                .patientId(patient.getPatientId())
                .name(patient.getName())
                .age(patient.getAge())
                .gender(patient.getGender())
                .address(patient.getAddress())
                .phone(patient.getPhone())
                .disease(patient.getDisease())
                .build();
    }

    // Delete patient
    public void deletePatient(Long id) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Patient not found with id: " + id));

        patientRepository.delete(patient);
    }

    // Get all nurses
    public List<NurseResponseDTO> getAllNurses() {
        return nurseRepository.findAll()
                .stream()
                .map(nurse -> NurseResponseDTO.builder()
                        .nurseId(nurse.getNurseId())
                        .nurseName(nurse.getNurseName())
                        .shift(nurse.getShift())
                        .qualification(nurse.getQualification())
                        .build())
                .collect(Collectors.toList());
    }

    // Add nurse
    public Nurse addNurse(NurseRequestDTO dto) {
        Users user = Users.builder()
                .username(dto.getNurseUsername())
                .password(passwordEncoder.encode(dto.getNursePassword()))
                .role(Users.Role.NURSE)
                .build();
        userRepository.save(user);

        Nurse nurse = Nurse.builder()
                .nurseName(dto.getNurseName())
                .shift(dto.getShift())
                .qualification(dto.getQualification())
                .users(user) // relation mapping
                .build();

        return nurseRepository.save(nurse);
    }

    // Get nurse by ID
    public NurseResponseDTO getNurseById(Long id) {
        Nurse nurse = nurseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Nurse not found with id: " + id));

        return NurseResponseDTO.builder()
                .nurseId(nurse.getNurseId())
                .nurseName(nurse.getNurseName())
                .shift(nurse.getShift())
                .qualification(nurse.getQualification())
                .build();
    }

    // Update nurse
    public NurseResponseDTO updateNurse(Long id, NurseRequestDTO dto) {
        Nurse nurse = nurseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Nurse not found with id: " + id));

        nurse.setNurseName(dto.getNurseName());
        nurse.setShift(dto.getShift());
        nurse.setQualification(dto.getQualification());

        nurseRepository.save(nurse);

        return NurseResponseDTO.builder()
                .nurseId(nurse.getNurseId())
                .nurseName(nurse.getNurseName())
                .shift(nurse.getShift())
                .qualification(nurse.getQualification())
                .build();
    }

    // Delete nurse
    public void deleteNurse(Long id) {
        Nurse nurse = nurseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Nurse not found with id: " + id));

        nurseRepository.delete(nurse);
    }

    // View appointments by date
    public List<AppointmentResponseDTO> getAppointmentsByDate(LocalDate date) { // ✅ param LocalDate
        return appointmentRepository.findByAppointmentDate(date)
                .stream()
                .map(app -> AppointmentResponseDTO.builder()
                        .appointmentId(app.getAppointmentId())
                        .appointmentDate(app.getAppointmentDate()) // still LocalDate in DTO
                        .status(app.getStatus().name())
                        .doctorId(app.getDoctor().getDoctorId())
                        .doctorName(app.getDoctor().getName())
                        .patientId(app.getPatient().getPatientId())
                        .patientName(app.getPatient().getName())
                        .build())
                .collect(Collectors.toList());
    }
    // Reschedule appointment
    public AppointmentResponseDTO rescheduleAppointment(Long id, LocalDate newDate) { // ✅ param LocalDate
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Appointment not found with id: " + id));

        appointment.setAppointmentDate(newDate); // ✅ LocalDate
        appointment.setStatus(Appointment.Status.RESCHEDULED);

        appointmentRepository.save(appointment);

        return AppointmentResponseDTO.builder()
                .appointmentId(appointment.getAppointmentId())
                .appointmentDate(appointment.getAppointmentDate())
                .status(appointment.getStatus().name())
                .doctorId(appointment.getDoctor().getDoctorId())
                .doctorName(appointment.getDoctor().getName())
                .patientId(appointment.getPatient().getPatientId())
                .patientName(appointment.getPatient().getName())
                .build();
    }

    // Cancel appointment
    public void cancelAppointment(Long id) {
        Appointment appointment = appointmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Appointment not found with id: " + id));

        appointment.setStatus(Appointment.Status.CANCELLED); // use Enum directly
        appointmentRepository.save(appointment);
    }

    public PatientSummaryDTO getPatientSummary(Long patientId) {
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new RuntimeException("Patient not found"));

        // Appointments
        // Appointments
        List<AppointmentDTO> appointments = appointmentRepository.findByPatient_PatientId(patientId)
                .stream()
                .map(app -> AppointmentDTO.builder()
                        .appointmentDate(app.getAppointmentDate())
                        .status(app.getStatus())
                        .doctorId(app.getDoctor() != null ? app.getDoctor().getDoctorId() : null)
                        .nurseId(app.getNurse() != null ? app.getNurse().getNurseId() : null)
                        .patientId(app.getPatient() != null ? app.getPatient().getPatientId() : null)  // ✅ FIXED
                        .build())
                .collect(Collectors.toList());


        // Prescriptions
        List<PrescriptionDTO> prescriptions = prescriptionRepository.findByPatient_PatientId(patientId)
                .stream()
                .map(pr -> PrescriptionDTO.builder()
                        .medicineName(pr.getMedicineName())
                        .dosage(pr.getDosage())
                        .instructions(pr.getInstructions())
                        .doctorId(pr.getDoctor() != null ? pr.getDoctor().getDoctorId() : null)  // ✅ doctorId from prescription
                        .build())
                .collect(Collectors.toList());

        // Billings
        List<BillingDTO> billings = billingRepository.findByPatient_PatientId(patientId)
                .stream()
                .map(bill -> BillingDTO.builder()
                        .amount(bill.getAmount())
                        .paymentStatus(bill.getPaymentStatus())
                        .paymentDate(bill.getPaymentDate())
                        .build())
                .collect(Collectors.toList());

        // Doctor info (latest appointment doctor)
        Doctor doctor = appointments.isEmpty() ? null :
                appointmentRepository.findByPatient_PatientId(patientId)
                        .stream().findFirst().get().getDoctor();

        DoctorResponseDTO doctorDTO = doctor != null ? DoctorResponseDTO.builder()
                .doctorId(doctor.getDoctorId())
                .doctorName(doctor.getName())
                .specialization(doctor.getSpecialization())
                .experience(doctor.getExperience())
                .build() : null;

        // Nurse info (latest appointment nurse)
        Nurse nurse = appointments.isEmpty() ? null :
                appointmentRepository.findByPatient_PatientId(patientId)
                        .stream().findFirst().get().getNurse();

        NurseResponseDTO nurseDTO = nurse != null ? NurseResponseDTO.builder()
                .nurseId(nurse.getNurseId())
                .nurseName(nurse.getNurseName())
                .shift(nurse.getShift())
                .qualification(nurse.getQualification())   // ✅ Added qualification
                .build() : null;

        return PatientSummaryDTO.builder()
                .patientId(patient.getPatientId())
                .name(patient.getName())
                .age(patient.getAge())
                .gender(patient.getGender())
                .address(patient.getAddress())
                .phone(patient.getPhone())
                .disease(patient.getDisease())
                .doctor(doctorDTO)
                .nurse(nurseDTO)
                .appointments(appointments)
                .prescriptions(prescriptions)
                .billings(billings)
                .build();
    }

    public DoctorSummaryDTO getDoctorSummary(Long doctorId) {
        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new RuntimeException("Doctor not found"));

        // Patients handled by this doctor (through appointments)
        List<PatientResponseDTO> patients = appointmentRepository.findByDoctor_DoctorId(doctorId)
                .stream()
                .map(Appointment::getPatient)
                .distinct()
                .map(patient -> PatientResponseDTO.builder()
                        .patientId(patient.getPatientId())
                        .name(patient.getName())
                        .age(patient.getAge())
                        .gender(patient.getGender())
                        .address(patient.getAddress())
                        .phone(patient.getPhone())
                        .disease(patient.getDisease())
                        .build())
                .collect(Collectors.toList());

        return DoctorSummaryDTO.builder()
                .doctorId(doctor.getDoctorId())
                .doctorName(doctor.getName())
                .specialization(doctor.getSpecialization())
                .experience(doctor.getExperience())
                .patients(patients)
                .build();
    }










}
