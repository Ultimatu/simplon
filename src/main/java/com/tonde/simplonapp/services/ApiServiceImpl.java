package com.tonde.simplonapp.services;

import com.tonde.simplonapp.model.Participant;
import com.tonde.simplonapp.repository.ParticipantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Optional;


@Service
public class ApiServiceImpl implements ApiService{

    @Autowired
    private ParticipantRepository repository;

    @Override
    public ResponseBody addParticipant(@Validated Participant participant) {
        //verifier si le participant existe deja
      Optional<Participant> participantOptional = repository.findByEmailOrTelephone(participant.getEmail(), participant.getTelephone());
        if (participantOptional.isPresent()){
            return (ResponseBody) ResponseEntity.ok().body("Ce participant existe deja");

        }
        return null;
    }

    @Override
    public ResponseBody getAllParticipants() {
        return null;
    }

    @Override
    public ResponseBody getParticipantById(Integer id) {
        return null;
    }

    @Override
    public ResponseBody updateParticipant(Participant participant) {
        return null;
    }

    @Override
    public ResponseBody deleteParticipant(Integer id) {
        return null;
    }
}
