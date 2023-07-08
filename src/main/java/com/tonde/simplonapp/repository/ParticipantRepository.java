package com.tonde.simplonapp.repository;

import com.tonde.simplonapp.model.Participant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ParticipantRepository extends JpaRepository<Participant, Integer> {

    //verifier si le participant existe par email
    Optional<Participant> findByEmail(String email);

    //verifier si le participant existe par email ou telephone
    Optional<Participant> findByEmailOrTelephone(String email, String telephone);

    //reinterpretation de la methode findAll pour retourner une page de participants
    @Override
    Page<Participant> findAll(Pageable pageable);

    //verifier si le participant existe par email  et id
    Optional<Participant> findByEmailAndIdNot(String email, Integer id);


    Optional<Participant> findByTelephoneAndIdNot(String telephone, Integer id);

}
