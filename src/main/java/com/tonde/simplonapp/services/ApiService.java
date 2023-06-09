package com.tonde.simplonapp.services;

import com.tonde.simplonapp.model.Participant;
import java.util.List;

public interface ApiService {

    public String addParticipant(Participant participant);

    public List<Participant> getAllParticipants();

    public Participant getParticipantById(Integer id);

    public String updateParticipant(Participant participant);

    public String deleteParticipant(Integer id);


}