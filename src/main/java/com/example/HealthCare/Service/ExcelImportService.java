package com.example.HealthCare.Service;

import com.example.HealthCare.DTO.*;
import com.example.HealthCare.Model.*;
import com.example.HealthCare.Repository.*;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ExcelImportService {

    private final UserRepository usersRepository;
    private final DoctorRepository doctorRepository;
    private final NurseRepository nurseRepository;
    private final PatientRepository patientRepository;
    private final AppointmentRepository appointmentRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void importExcel(MultipartFile file, String type) {
        try (InputStream inputStream = file.getInputStream();
             Workbook workbook = WorkbookFactory.create(inputStream)) {

            Sheet sheet = workbook.getSheetAt(0);

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row == null) continue;

                if ("doctor".equalsIgnoreCase(type)) {
                    DoctorRequestDTO dto = DoctorRequestDTO.builder()
                            .doctorName(row.getCell(0).getStringCellValue())
                            .specialization(row.getCell(1).getStringCellValue())
                            .experience((int) row.getCell(2).getNumericCellValue())
//                            .dEmail(row.getCell(3).getStringCellValue())
//                            .dPhone(row.getCell(4).getStringCellValue())
                            .doctorUsername(row.getCell(3).getStringCellValue())
                            .doctorPassword(row.getCell(4).getStringCellValue())
                            .doctorRole("DOCTOR")
                            .build();

                    saveDoctor(dto);
                } else if ("nurse".equalsIgnoreCase(type)) {
                    NurseRequestDTO dto = NurseRequestDTO.builder()
                            .nurseName(row.getCell(0).getStringCellValue())
                            .shift(row.getCell(1).getStringCellValue())
                            .qualification(row.getCell(2).getStringCellValue())
//                            .nEmail(row.getCell(3).getStringCellValue())
//                            .nPhone(row.getCell(4).getStringCellValue())
                            .nurseUsername(row.getCell(3).getStringCellValue())
                            .nursePassword(row.getCell(4).getStringCellValue())
                            .nurseRole("NURSE")
                            .build();

                    saveNurse(dto);
                } else if ("patient".equalsIgnoreCase(type)) {
                    PatientRequestDTO dto = PatientRequestDTO.builder()
                            .name(row.getCell(0).getStringCellValue())
                            .age((int) row.getCell(1).getNumericCellValue())
                            .gender(row.getCell(2).getStringCellValue())
                            .address(row.getCell(3).getStringCellValue())
                            .phone(row.getCell(4).getStringCellValue())
                            .disease(row.getCell(5).getStringCellValue())
//                            .pEmail(row.getCell(6).getStringCellValue())
                            .patientUsername(row.getCell(6).getStringCellValue())
                            .patientPassword(row.getCell(7).getStringCellValue())
                            .patientRole("PATIENT")
                            .build();

                    savePatient(dto);
                }
                else if ("appointment".equalsIgnoreCase(type)) {
                    AppointmentDTO dto =AppointmentDTO.builder()
                            .appointmentDate(row.getCell(0).getLocalDateTimeCellValue().toLocalDate())
                            .status(Appointment.Status.valueOf(row.getCell(1).getStringCellValue().toUpperCase()))
                            .doctorId((long) row.getCell(2).getNumericCellValue())
                            .patientId((long) row.getCell(3).getNumericCellValue())
                            .build();
                    saveAppointment(dto);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to import Excel: " + e.getMessage());
        }
    }

    private void saveDoctor(DoctorRequestDTO dto) {
        Users user = Users.builder()
                .username(dto.getDoctorUsername())
                .password(passwordEncoder.encode(dto.getDoctorPassword()))
                .role(Users.Role.DOCTOR)
                .build();
        usersRepository.save(user);

        Doctor doctor = Doctor.builder()
                .name(dto.getDoctorName())
                .specialization(dto.getSpecialization())
                .experience(dto.getExperience())
                .users(user)
                .build();
        doctorRepository.save(doctor);
    }

    private void saveNurse(NurseRequestDTO dto) {
        Users user = Users.builder()
                .username(dto.getNurseUsername())
                .password(passwordEncoder.encode(dto.getNursePassword()))
                .role(Users.Role.NURSE)
                .build();
        usersRepository.save(user);

        Nurse nurse = Nurse.builder()
                .nurseName(dto.getNurseName())
                .shift(dto.getShift())
                .qualification(dto.getQualification())
                .users(user)
                .build();
        nurseRepository.save(nurse);
    }

    private void savePatient(PatientRequestDTO dto) {
        Users user = Users.builder()
                .username(dto.getPatientUsername())
                .password(passwordEncoder.encode(dto.getPatientPassword()))
                .role(Users.Role.PATIENT)
                .build();
        usersRepository.save(user);

        Patient patient = Patient.builder()
                .name(dto.getName())
                .age(dto.getAge())
                .gender(dto.getGender())
                .address(dto.getAddress())
                .phone(dto.getPhone())
                .disease(dto.getDisease())
                .users(user)
                .build();
        patientRepository.save(patient);
    }
    private void saveAppointment(AppointmentDTO dto) {

        Doctor doctor = doctorRepository.findById(dto.getDoctorId())
                .orElseThrow(() -> new RuntimeException("Doctor not found with id " + dto.getDoctorId()));

        Patient patient = patientRepository.findById(dto.getPatientId())
                .orElseThrow(() -> new RuntimeException("Patient not found with id " + dto.getPatientId()));


        // Build appointment entity
        Appointment appointment = Appointment.builder()
                .appointmentDate(dto.getAppointmentDate())
                .status(dto.getStatus()) // already enum
                .doctor(doctor)
                .patient(patient)
//                .nurse(nurse)
                .build();

        // Save appointment
        appointmentRepository.save(appointment);
    }

}
