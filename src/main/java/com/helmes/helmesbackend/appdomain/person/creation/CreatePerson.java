package com.helmes.helmesbackend.appdomain.person.creation;

import com.helmes.helmesbackend.appdomain.person.Person;
import com.helmes.helmesbackend.appdomain.person.SavePerson;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreatePerson {
    private final SavePerson savePerson;

    public Person.Id execute(Request request) {
        return Person.Id.of(savePerson.execute(SavePerson.Request.of(Person.Id.of(null), request.getPersonName())));
    }

    @Value(staticConstructor = "of")
    public static class Request {
        String personName;
    }
}
