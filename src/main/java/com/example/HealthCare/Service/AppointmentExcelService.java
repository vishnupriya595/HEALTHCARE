package com.example.HealthCare.Service;

import com.example.HealthCare.Model.*;
import com.example.HealthCare.Repository.*;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class AppointmentExcelService {

    private final AppointmentRepository appointmentRepository;
    private final BillingRepository billingRepository;
    private final PatientRepository patientRepository;
    private final PrescriptionRepository prescriptionRepository;
    private final DoctorRepository doctorRepository;

    public void importData(MultipartFile file) throws IOException {
        try (XSSFWorkbook workbook = new XSSFWorkbook(file.getInputStream())) {
            XSSFSheet sheet = workbook.getSheetAt(0);

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {  // skip header
                Row row = sheet.getRow(i);
                if (row == null) continue;

                Long doctorId = (long) row.getCell(0).getNumericCellValue();
                Long patientId = (long) row.getCell(1).getNumericCellValue();
                LocalDate appointmentDate = row.getCell(2).getLocalDateTimeCellValue().toLocalDate();
                Appointment.Status status = Appointment.Status.valueOf(row.getCell(3).getStringCellValue());
                String medicineName = row.getCell(4).getStringCellValue();
                String dosage = row.getCell(5).getStringCellValue();
                String instructions = row.getCell(6).getStringCellValue();
                Double amount = row.getCell(7).getNumericCellValue();
                Billing.PaymentStatus paymentStatus = Billing.PaymentStatus.valueOf(row.getCell(8).getStringCellValue());
                LocalDate paymentDate = row.getCell(9) != null ? row.getCell(9).getLocalDateTimeCellValue().toLocalDate() : null;

                Patient patient = patientRepository.findById(patientId).orElseThrow();
                Doctor doctor = doctorRepository.findById(doctorId).orElseThrow();

                Appointment appointment = Appointment.builder()
                        .appointmentDate(appointmentDate)
                        .status(status)
                        .patient(patient)
                        .doctor(doctor)
                        .build();
                appointmentRepository.save(appointment);

                Prescription prescription = Prescription.builder()
                        .appointment(appointment)
                        .doctor(doctor)
                        .patient(patient)
                        .medicineName(medicineName)
                        .dosage(dosage)
                        .instructions(instructions)
                        .build();
                prescriptionRepository.save(prescription);


                Billing billing = Billing.builder()
                        .appointment(appointment)
                        .patient(patient)
                        .amount(amount)
                        .paymentStatus(paymentStatus)
                        .paymentDate(paymentDate)
                        .build();
                billingRepository.save(billing);


                appointment.setPrescription(prescription);
                appointment.setBill(billing);
                appointmentRepository.save(appointment);
            }
        }
    }

}
