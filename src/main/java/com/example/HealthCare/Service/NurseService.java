package com.example.HealthCare.Service;

import com.example.HealthCare.DTO.Request.DoctorProfileReqDTO;
import com.example.HealthCare.DTO.Request.NurseProfileReqDTO;
import com.example.HealthCare.Model.Doctor;
import com.example.HealthCare.Model.Nurse;
import com.example.HealthCare.Model.Users;
import com.example.HealthCare.Repository.DoctorRepository;
import com.example.HealthCare.Repository.NurseRepository;
import com.example.HealthCare.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class NurseService {

    @Autowired
    private NurseRepository nurseRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DoctorRepository doctorRepository;


    public String updateNurseProfile(UUID userId, NurseProfileReqDTO req) {
        Users user = userRepository.findById(userId).get();
        Nurse nurse = nurseRepository.findByUsers(user);
        nurse.setEmail(req.getEmail());
        nurse.setPhoneNumber(req.getPhoneNumber());
        nurseRepository.save(nurse);
        return "Nurse Profile updated successfully";
    }
}
