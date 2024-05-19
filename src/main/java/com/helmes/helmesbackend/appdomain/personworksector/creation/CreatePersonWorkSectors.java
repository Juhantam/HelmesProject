package com.helmes.helmesbackend.appdomain.personworksector.creation;

import com.helmes.helmesbackend.appdomain.person.Person;
import com.helmes.helmesbackend.appdomain.personworksector.SavePersonWorkSectors;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.stereotype.Component;

import java.util.Set;

import static com.helmes.helmesbackend.appdomain.personworksector.PersonWorkSector.toPersonWorkSectors;

@Component
@RequiredArgsConstructor
public class CreatePersonWorkSectors {
    private final SavePersonWorkSectors savePersonWorkSectors;

    public void execute(Request request) {
        savePersonWorkSectors.execute(SavePersonWorkSectors.Request.of(toPersonWorkSectors(request.getWorkSectorIds(), request.getPersonId())));
    }

    @Value(staticConstructor = "of")
    public static class Request {
        Set<Long> workSectorIds;
        Person.Id personId;
    }
}
