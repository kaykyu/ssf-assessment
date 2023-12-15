package vttp.ssf.assessment.eventmanagement.services;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vttp.ssf.assessment.eventmanagement.models.Event;
import vttp.ssf.assessment.eventmanagement.models.Participant;
import vttp.ssf.assessment.eventmanagement.repositories.RedisRepository;

@Service
public class EventService {

    @Autowired
    RedisRepository repo;

    public List<Event> displayEvents() {
        return repo.getEventsList();
    }

    public Event getEvent(String id) {
        return repo.getEvent(Integer.parseInt(id));
    }

    public Boolean haveSpace(String eventId, Integer tix) {

        Event event = repo.getEvent(Integer.parseInt(eventId));
        int space = event.getEventSize() - event.getParticipants();
        if (space < tix) {
            return false;
        }

        event.setParticipants(event.getParticipants() + tix);
        repo.saveRecord(event);
        return true;
    }

    public Boolean isAbove21(Participant part) {

        LocalDate birthDate = LocalDate.ofInstant(part.getBirthday().toInstant(), ZoneId.systemDefault());
        Period diff = Period.between(birthDate, LocalDate.now());
        int age = diff.getYears();

        if (age < 21) {
            return false;
        }
        return true;
    }

}