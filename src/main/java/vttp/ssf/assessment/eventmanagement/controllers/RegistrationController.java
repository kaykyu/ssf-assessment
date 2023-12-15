package vttp.ssf.assessment.eventmanagement.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;
import vttp.ssf.assessment.eventmanagement.models.Participant;
import vttp.ssf.assessment.eventmanagement.services.EventService;

@Controller
@RequestMapping(path = "/events/register")
public class RegistrationController {

    @Autowired
    EventService svc;

    @GetMapping(path = "/{id}")
    public String register(@PathVariable("id") String eventId, Model model) {

        model.addAttribute("event", svc.getEvent(eventId));
        model.addAttribute("participant", new Participant());

        return "view1";
    }

    @PostMapping(path = "/{id}")
    public String processRegistration(@PathVariable("id") String eventId, Model model,
            @Valid @ModelAttribute("participant") Participant part, BindingResult binding) {

        model.addAttribute("event", svc.getEvent(eventId));

        if (binding.hasErrors()) {
            if (part.getBirthday() != null && !svc.isAbove21(part)) {
                model.addAttribute("ageError", "Must be 21 and above to register");
            }
            return "view1";
        } else if (part.getBirthday() != null && !svc.isAbove21(part)) {
            model.addAttribute("ageError", "Must be 21 and above to register");
            return "view1";
        }

        if (!svc.haveSpace(eventId, part.getTickets())) {
            model.addAttribute("exceedPax", "Your request for tickets exceeded the event size.");
            return "view3";
        }

        return "view2";
    }
}
