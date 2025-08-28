package com.example.HealthCare.Service;

import com.example.HealthCare.Model.*;
import com.example.HealthCare.Repository.*;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDate;
import java.util.UUID;

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

                String doctorIdStr = getCellValueAsString(row.getCell(0));
                String patientIdStr = getCellValueAsString(row.getCell(1));
                String appointmentDateStr = getCellValueAsString(row.getCell(2));
                String statusStr = getCellValueAsString(row.getCell(3));
                String medicineName = getCellValueAsString(row.getCell(4));
                String dosage = getCellValueAsString(row.getCell(5));
                String instructions = getCellValueAsString(row.getCell(6));
                String amountStr = getCellValueAsString(row.getCell(7));
                String paymentStatusStr = getCellValueAsString(row.getCell(8));
                String paymentDateStr = getCellValueAsString(row.getCell(9));

                UUID doctorId = UUID.fromString(doctorIdStr);
                UUID patientId = UUID.fromString(patientIdStr);
                LocalDate appointmentDate = LocalDate.parse(appointmentDateStr);
                Appointment.Status status = Appointment.Status.valueOf(statusStr);
                Double amount = amountStr != null ? Double.valueOf(amountStr) : null;
                Billing.PaymentStatus paymentStatus = Billing.PaymentStatus.valueOf(paymentStatusStr);
                LocalDate paymentDate = (paymentDateStr != null && !paymentDateStr.isEmpty())
                        ? LocalDate.parse(paymentDateStr)
                        : null;

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

    private String getCellValueAsString(Cell cell) {
        if (cell == null) return null;
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue().trim();
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    return cell.getLocalDateTimeCellValue().toLocalDate().toString();
                }
                // Avoid scientific notation for integers
                double d = cell.getNumericCellValue();
                if (d == (long) d) {
                    return String.valueOf((long) d);
                } else {
                    return String.valueOf(d);
                }
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case FORMULA:
                return cell.getCellFormula();
            case BLANK:
            default:
                return null;
        }
    }

}

