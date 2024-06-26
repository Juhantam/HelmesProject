package com.helmes.helmesbackend.appdomain.personworksector.creation;

import com.helmes.helmesbackend.appdomain.person.Person;
import com.helmes.helmesbackend.appdomain.person.creation.CreatePerson;
import com.helmes.helmesbackend.appdomain.personworksector.PersonWorkSectorsInfo;
import com.helmes.helmesbackend.appdomain.personworksector.SavePersonWorkSectorsInfo;
import com.helmes.helmesbackend.appdomain.personworksector.validation.PersonWorkSectorsInfoValidator;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreatePersonWorkSectorsInfo {
    private final CreatePerson createPerson;
    private final CreatePersonWorkSectors createPersonWorkSectors;
    private final SavePersonWorkSectorsInfo savePersonWorkSectorsInfo;
    private final PersonWorkSectorsInfoValidator personWorkSectorsInfoValidator;

    public PersonWorkSectorsInfo.Id execute(Request request) {
        PersonWorkSectorsInfoCreationDetails creationDetails = request.getCreationDetails();
        personWorkSectorsInfoValidator.validateSave(creationDetails)
                                      .orThrow();
        Person.Id personId =
                createPerson.execute(CreatePerson.Request.of(creationDetails.getPersonName()));
        createPersonWorkSectors.execute(CreatePersonWorkSectors.Request.of(creationDetails.getWorkSectorIds(), personId));
        return PersonWorkSectorsInfo.Id.of(savePersonWorkSectorsInfo.execute(SavePersonWorkSectorsInfo.Request.of(PersonWorkSectorsInfo.Id.of(null), personId, creationDetails.getIsAcceptTermsOfService())));
    }

    @Value(staticConstructor = "of")
    public static class Request {
        PersonWorkSectorsInfoCreationDetails creationDetails;
    }
}
