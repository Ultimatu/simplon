package com.tonde.simplonapp.controller;


import com.tonde.simplonapp.services.ApiServiceImpl;
import lombok.RequiredArgsConstructor;
import com.tonde.simplonapp.model.Participant;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
@RequestMapping("/api/v1/part")
@RequiredArgsConstructor
public class ApiController {

    private final ApiServiceImpl apiService;

    @GetMapping("/all/{token}")
    public ResponseEntity<List<Participant>> getAllParticipants(@PathVariable String token) {

        if (token == null || !token.equals("1234")) {
            return ResponseEntity.badRequest().build();
        }
        
        
        return ResponseEntity.ok().body(apiService.getAllParticipants());
    }

    @GetMapping("/{id}/{token}")
    public ResponseEntity<Participant> getParticipantById(@PathVariable Integer id, @PathVariable String token) {

        if (token == null || !token.equals("1234")) {
            return ResponseEntity.badRequest().build();
        }
        if (apiService.getParticipantById(id) == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(apiService.getParticipantById(id));
    }


    @PutMapping("/update/{token}")
    public ResponseEntity<String> updateParticipant(@PathVariable String token, @Validated Participant participant) {
        if (token == null || !token.equals("1234")) {
            return ResponseEntity.badRequest().build();
        }
        if (apiService.getParticipantById(participant.getId()) == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(apiService.addParticipant(participant));
    }


    @DeleteMapping("/delete/{id}/{token}")
    public ResponseEntity<String> deleteParticipant(@PathVariable Integer id, @PathVariable String token) {

        if (token == null || !token.equals("1234")) {
            return ResponseEntity.badRequest().build();
        }
        if (apiService.getParticipantById(id) == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().body(apiService.deleteParticipant(id));
    }

    @PostMapping("/add/{token}")
    public ResponseEntity<String> addParticipant(@PathVariable String token, @Validated Participant participant) {

        if (token == null || !token.equals("1234")) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok().body(apiService.addParticipant(participant));
    }





    

}
