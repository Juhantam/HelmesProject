package com.helmes.helmesbackend.appdomain.personworksector;

import com.helmes.helmesbackend.appdomain.person.Person;
import com.helmes.helmesbackend.appdomain.person.SavePerson;
import com.helmes.helmesbackend.appdomain.personworksector.creation.CreatePersonWorkSectors;
import com.helmes.helmesbackend.appdomain.personworksector.validation.PersonWorkSectorsInfoValidator;
import com.helmes.helmesbackend.appdomain.worksector.WorkSector;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ChangePersonWorkSectorsInfo {
    private final PersonWorkSectorsInfoValidator personWorkSectorsInfoValidator;
    private final GetPersonWorkSectorsInfo getPersonWorkSectorsInfo;
    private final SavePerson savePerson;
    private final SavePersonWorkSectorsInfo savePersonWorkSectorsInfo;
    private final GetPersonWorkSectors getPersonWorksSectors;
    private final CreatePersonWorkSectors createPersonWorkSectors;
    private final DeletePersonWorkSectors deletePersonWorkSectors;

    public PersonWorkSectorsInfo.Id execute(Request request) {
        PersonWorkSectorsInfoUpdateDetails updateDetails = request.getUpdateDetails();
        personWorkSectorsInfoValidator.validateUpdate(updateDetails)
                                      .orThrow();
        var personWorkSectorsInfo =
                getPersonWorkSectorsInfo.execute(GetPersonWorkSectorsInfo.Request.of(updateDetails.getPersonWorkSectorsInfoId()
                                                                                                  .getValue()));
        Person.Id personId = personWorkSectorsInfo.getPerson()
                                                  .getId();
        savePerson.execute(SavePerson.Request.of(personId, updateDetails.getPersonName()));
        Set<PersonWorkSector> currentPersonWorkSectors =
                getPersonWorksSectors.execute(GetPersonWorkSectors.Request.of(personId.getValue()));
        Set<Long> currentWorkSectorIds =
                currentPersonWorkSectors
                                     .stream()
                                     .map(PersonWorkSector::getWorkSectorId)
                                     .map(WorkSector.Id::getValue)
                                     .collect(Collectors.toSet());
        Set<Long> newWorkSectorIds = updateDetails.getWorkSectorIds();
        Set<Long> personWorkSectorsToBeCreated = newWorkSectorIds
                                                              .stream()
                                                              .filter(sectorId -> !currentWorkSectorIds.contains(sectorId))
                                                              .collect(Collectors.toSet());
        Set<PersonWorkSector.Id> personWorkSectorsToBeDeleted = currentPersonWorkSectors
                .stream()
                .filter(personWorkSector -> !newWorkSectorIds.contains(personWorkSector.getWorkSectorId().getValue()))
                .map(PersonWorkSector::getId)
                .collect(Collectors.toSet());

        createPersonWorkSectors.execute(CreatePersonWorkSectors.Request.of(personWorkSectorsToBeCreated, personId));
        deletePersonWorkSectors.execute(DeletePersonWorkSectors.Request.of(personWorkSectorsToBeDeleted));
        return PersonWorkSectorsInfo.Id.of(
                savePersonWorkSectorsInfo.execute(SavePersonWorkSectorsInfo.Request.of(
                        updateDetails.getPersonWorkSectorsInfoId(), personId, updateDetails.getIsAcceptTermsOfService())));
    }

    @Value(staticConstructor = "of")
    public static class Request {
        PersonWorkSectorsInfoUpdateDetails updateDetails;
    }
}
