package com.tonde.simplonapp.services;


import com.tonde.simplonapp.model.Participant;
import com.tonde.simplonapp.repository.ParticipantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ParticipantServiceImpl implements ParticipantService{

    @Autowired // injecter le repository
    private ParticipantRepository repository;

    /**
    * Cette méthode permet d'ajouter un participant
    * @param participant
    * @return String
     */
    @Override
    public String addParticipant(Participant participant) {
        //verifier si le participant existe
        Optional<Participant> participantOptional = repository.findByEmailOrTelephone(participant.getEmail(), participant.getTelephone());
        if (participantOptional.isPresent()){
            return "Il existe déjà un participant avec cet email/telephone";
        }
        //ajouter le participant
        repository.save(participant);
        return "ok";
    }


    /**
     * Cette méthode retourne la liste de tous les participants
     * @return List<Participant>
     */
    @Override
    public Page<Participant> getAllParticipants(Pageable pageable) {

        return repository.findAll(pageable);
    }

    /**
     * Cette méthode retourne un participant par son id
     * @param id
     * @return Participant
     */
    @Override
    public Participant getParticipantById(Integer id) {
        boolean participantOptional = repository.existsById(id);
        if (participantOptional){
            Participant optionalParticipant = repository.findById(id).get();
            return optionalParticipant;
        }
        return null;
    }

    /**
     * Cette méthode permet de mettre à jour un participant
     * @param participant
     * @return  Participant
     */
    @Override
    public Participant updateParticipant(Participant participant) {
        Optional<Participant> optionalParticipant = repository.findById(participant.getId());
        if (optionalParticipant.isPresent()) {
            Participant existingParticipant = optionalParticipant.get();

            // Vérifier si l'email est associé à un autre compte
            Optional<Participant> existingEmailParticipant = repository.findByEmailAndIdNot(participant.getEmail(), participant.getId());
            if (existingEmailParticipant.isPresent()) {
                return null; // Renvoyer null avec un message d'erreur spécifique
            }

            // Vérifier si le téléphone est associé à un autre compte
            Optional<Participant> existingPhoneParticipant = repository.findByTelephoneAndIdNot(participant.getTelephone(), participant.getId());
            if (existingPhoneParticipant.isPresent()) {
                return null; // Renvoyer null avec un message d'erreur spécifique
            }

            // Mettre à jour les informations du participant
            existingParticipant.setNom(participant.getNom());
            existingParticipant.setPrenom(participant.getPrenom());
            existingParticipant.setEmail(participant.getEmail());
            existingParticipant.setTelephone(participant.getTelephone());

            repository.save(existingParticipant);
            return existingParticipant;
        }
        return null;
    }


    /**
     * Cette méthode permet de supprimer un participant
     * @param id
     */
    @Override
    public String deleteParticipant(Integer id) {
        boolean participantOptional = repository.existsById(id);
        if (participantOptional){
            Participant optionalParticipant = repository.findById(id).get();
            repository.delete(optionalParticipant);
            return "ok";
        }
        return "utilisateur non trouvé";
    }
}
