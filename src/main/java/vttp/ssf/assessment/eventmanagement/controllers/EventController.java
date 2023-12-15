package vttp.ssf.assessment.eventmanagement.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import vttp.ssf.assessment.eventmanagement.services.EventService;

@Controller
@RequestMapping(path = "/events")
public class EventController {

	@Autowired
	EventService svc;

	@GetMapping(path = "/listing")
	public String displayEvents(Model model) {

		model.addAttribute("events", svc.displayEvents());
		return "view0";
	}
}
