package com.example.sanction_service.controller;

import com.example.sanction_service.batch.SanctionService;
import com.example.sanction_service.model.SanctionEntity;
import com.example.sanction_service.service.UserVerificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sanctions")
public class UserController {
    @Autowired
    private SanctionService sanctionService;
    @Autowired
    private UserVerificationService userVerificationService;


//        @Autowired
//        private SpecialtyService specialtyService;

    @PostMapping("/import")
    public ResponseEntity<String> importSanctions(@RequestBody List<SanctionEntity> sanctionEntity) {
        try {
            sanctionService.saveList(sanctionEntity);
            return ResponseEntity.ok("Sanctions imported successfully.");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error importing sanctions.");
        }
    }

    @GetMapping("/verify")
    public ResponseEntity<String> verifyUser(@RequestParam String name, @RequestParam String surname){
        return ResponseEntity.ok(userVerificationService.verifyUser(name, surname));
    }
}
