package com.tonde.simplonapp.services;

import com.tonde.simplonapp.model.Participant;
import com.tonde.simplonapp.repository.ParticipantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ApiServiceImpl implements ApiService{

    private final ParticipantRepository repository;


    @Override
    public String addParticipant(@Validated Participant participant) {

          Optional<Participant> participantOptional = repository.findByEmailOrTelephone(participant.getEmail(), participant.getTelephone());
          if (participantOptional.isPresent()){
                return  "Ce participant existe deja";

          }
          repository.save(participant);

          return "ok";
    }

    @Override
    public List<Participant> getAllParticipants() {

        return repository.findAll();
    }

    @Override
    public Participant getParticipantById(Integer id) {
        return  repository.findById(id).orElseThrow();
    }

    @Override
    public String updateParticipant(Participant participant) {
        Optional<Participant>  participantOptional = repository.findById(participant.getId());
        if (participantOptional.isPresent()){
            participantOptional.get().setNom(participant.getNom());
            participantOptional.get().setPrenom(participant.getPrenom());
            participantOptional.get().setEmail(participant.getEmail());
            participantOptional.get().setTelephone(participant.getTelephone());
            repository.save(participantOptional.get());
            return "ok";
        }
        else {
            return "Aucun participant trouvé";
        }
    }

    @Override
    public String deleteParticipant(Integer id) {
        Optional<Participant> participant = repository.findById(id);
        String status;
        if (participant.isPresent()){
            repository.delete(participant.get());
            status = "ok";
        }
        else {

            status = "Aucun participant trouvé";
        }
        return status;


    }
}
