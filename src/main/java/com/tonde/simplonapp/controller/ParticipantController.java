package com.tonde.simplonapp.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.tonde.simplonapp.model.Participant;
import com.tonde.simplonapp.services.ParticipantServiceImpl;


@Controller
@RequestMapping("/")
public class ParticipantController {

    @Autowired
    private ParticipantServiceImpl service;

    @GetMapping("")
    public String index(Model model){
        return "index";
    }


    @GetMapping("add")
    public String ajouterParticipant(Model model, Participant participant){
        model.addAttribute("participant", participant);
        return "participant/add";
    }

    @GetMapping("list")
    public String list(Model model, @RequestParam(defaultValue = "0") int page){
        PageRequest pageable = PageRequest.of(page , 10);
        Page<Participant> participant1 = service.getAllParticipants(pageable);
        if (!participant1.isEmpty()){
            model.addAttribute("participants", participant1.getContent());
            model.addAttribute("currentPage", page);
            model.addAttribute("totalPages", participant1.getTotalPages());
        }
        else {
            model.addAttribute("videtrue", "Aucun participant trouvé");
        }
        return "participant/list";
    }

    @GetMapping("edit/{id}")
    public String update(@PathVariable("id") int id, Model model){
        Optional<Participant> participantOptional = Optional.ofNullable(service.getParticipantById(id));
        if (participantOptional.isPresent()){
            model.addAttribute("participant", participantOptional.get());
            return "participant/edit";
        }
        return "redirect:/list";
    }

    @GetMapping("delete/{id}")
    public String delete(@PathVariable("id") int id, Model model){
        System.out.println("here");
        Optional<Participant> participantOptional = Optional.ofNullable(service.getParticipantById(id));
        if (participantOptional.isPresent()){
            model.addAttribute("participant", participantOptional.get());
            return "participant/delete";
        }

        return "redirect:/list";
    }

    @PostMapping("add")
    public String add(Model model, Participant participant){
        String message = service.addParticipant(participant);
        if (message.equals("ok")){
            model.addAttribute("added", "Participant ajouté avec succès");
            return list(model, 0);
        }
        model.addAttribute("erreur", message);
        model.addAttribute("participant", participant);
        return "participant/add";
    }


    @PostMapping("update")
    public String update(Model model, Participant participant){
        Participant participantOptional = service.updateParticipant(participant);
        if (participantOptional != null){
            model.addAttribute("edited", "Participant modifié avec succès");
            return list(model, 0);
        }
        model.addAttribute("participant", participant);
        model.addAttribute("error", "email/téléphone associé à un autre participant");
        return "participant/edit";
    }

    @PostMapping("delete/{id}")
    public String delete(Model model, Participant participant, @PathVariable("id") int id){
        String participantOptional = service.deleteParticipant(id);
        if (participantOptional.equals("ok")){
            model.addAttribute("deleted", "Participant supprimé avec succès");
            return list(model, 0);
        }
        return "redirect:/list";
    }


}
