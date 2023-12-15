package vttp.ssf.assessment.eventmanagement.services;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import jakarta.json.JsonValue;
import vttp.ssf.assessment.eventmanagement.models.Event;

@Service
public class DatabaseService {
    
    public List<Event> readFile(String fileName) {

        try (FileInputStream fis = new FileInputStream(fileName)) {

            JsonReader jReader = Json.createReader(fis);
            JsonArray jArray = jReader.readArray();
            List<Event> events = new ArrayList<>();

            for (JsonValue jValue : jArray) {
                JsonObject jObject = jValue.asJsonObject();

                events.add(new Event(
                        jObject.getInt("eventId"),
                        jObject.getString("eventName"),
                        jObject.getInt("eventSize"),
                        msToDate(Long.valueOf(jObject.get("eventDate").toString())),
                        jObject.getInt("participants")));
            }

            return events;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
    
    public String msToDate(Long date) {

        Date result = new Date(date);

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyy");
        return sdf.format(result);
    }
}
