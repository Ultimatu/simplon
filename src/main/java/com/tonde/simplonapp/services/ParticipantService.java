package com.tonde.simplonapp.services;


import com.tonde.simplonapp.model.Participant;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


@Service
public interface ParticipantService {

    public String addParticipant(Participant participant);

    public Page<Participant> getAllParticipants(Pageable pageable);

    public Participant getParticipantById(Integer id);

    public Participant updateParticipant(Participant participant);

    public String deleteParticipant(Integer id);




}
