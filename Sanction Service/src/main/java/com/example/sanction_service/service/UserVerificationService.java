package com.example.sanction_service.service;

import com.example.sanction_service.model.SanctionEntity;
import com.example.sanction_service.repository.SanctionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserVerificationService {

    @Autowired
    private SanctionRepository sanctionRepository;

    public String verifyUser(String name, String surname) {
        if (name == null || surname == null || name.isEmpty() || surname.isEmpty()) {
            return "Full name is required for verification.";
        }

        Optional<SanctionEntity> sanctionEntity = sanctionRepository.findByNameContainingIgnoreCase(name);
        if (sanctionEntity.isPresent() && sanctionEntity.get().getSurname().equalsIgnoreCase(surname)) {
            return "User is sanctioned.";
        } else {
            return "User is not sanctioned.";
        }
    }
}
