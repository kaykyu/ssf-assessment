package vttp.ssf.assessment.eventmanagement.repositories;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.stereotype.Repository;

import jakarta.annotation.Resource;
import vttp.ssf.assessment.eventmanagement.models.Event;

@Repository
public class RedisRepository {

	@Resource(name = "redisEvents")
	private HashOperations<String, String, Event> eventHashOps;

	public void saveRecord(Event event) {
		eventHashOps.put("events", event.getEventId().toString(), event);
	}

	public Integer getNumberOfEvents() {
		return eventHashOps.entries("events").keySet().size();
	}

	public Event getEvent(Integer index) {
		return eventHashOps.get("events", index.toString());
	}

	public List<Event> getEventsList() {

		List<Event> list = new ArrayList<>();
		for (int i = 0; i < getNumberOfEvents(); i++) {
			list.add(eventHashOps.get("events", String.valueOf(i + 1)));
		}
		return list;
	}
}
