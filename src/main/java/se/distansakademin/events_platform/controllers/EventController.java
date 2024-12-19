package se.distansakademin.events_platform.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import se.distansakademin.events_platform.models.Event;
import se.distansakademin.events_platform.services.EventService;

import java.util.List;

@Controller
public class EventController {

    @Autowired
    private EventService service;

    @GetMapping("/events")
    public String getEventsHome(Model model) {
        List<Event> allEvents = service.getAllEvents();
        model.addAttribute("events", allEvents);
        return "event-list-view";
    }

    @PostMapping("/seed-events")
    public String postSeedEvents() {
        service.seedInitialEvents();
        return "redirect:/events"; // GET: /events
    }


    @PostMapping("/events")
    public String postSaveEvent(@ModelAttribute Event event) {
        service.saveEvent(event);
        return "redirect:/events"; // GET: /events
    }

    @GetMapping("/events/{id}") // http://localhost:8080/events/4 -> id = 4
    public String getSingleEvent(@PathVariable("id") long id, Model model) {
        Event event = service.getSingleEvent(id);
        model.addAttribute("event", event);
        return "single-event-view";
    }

    @PostMapping("/events/{id}")
    public String postDeleteEvent(@PathVariable("id") long id){
        service.deleteEvent(id);
        return "redirect:/events";
    }



}
