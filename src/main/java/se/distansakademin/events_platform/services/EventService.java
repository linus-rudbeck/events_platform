package se.distansakademin.events_platform.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.distansakademin.events_platform.models.Event;
import se.distansakademin.events_platform.repositories.EventRepository;

import java.util.List;
import java.util.Optional;

@Service
public class EventService {

    @Autowired
    private EventRepository repository;

    public List<Event> getAllEvents(){
        return repository.findAll();
    }

    public void saveEvent(Event event){
        repository.save(event);
    }

    public void seedInitialEvents(){
        long totalEventCount = repository.count();

        if (totalEventCount > 0){
            return;
        }

        Event lucia = new Event("Lucia", "2024-12-13");
        Event christmas = new Event("Christmas", "2024-12-24");
        Event newYear = new Event("New year", "2024-12-31");

        repository.save(lucia);
        repository.save(christmas);
        repository.save(newYear);
    }

    public Event getSingleEvent(long id) {
        Optional<Event> eventOptional = repository.findById(id);

        if(eventOptional.isEmpty()){
            return null;
        }

        return eventOptional.get();
    }

    public void deleteEvent(long id) {
        repository.deleteById(id);
    }
}
