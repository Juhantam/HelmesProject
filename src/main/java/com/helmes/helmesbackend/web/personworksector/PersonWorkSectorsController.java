package com.helmes.helmesbackend.web.personworksector;

import com.helmes.helmesbackend.appdomain.personworksector.AquirePersonWorkSectorsInfo;
import com.helmes.helmesbackend.appdomain.personworksector.ChangePersonWorkSectorsInfo;
import com.helmes.helmesbackend.appdomain.personworksector.PersonWorkSectorsInfo;
import com.helmes.helmesbackend.appdomain.personworksector.creation.CreatePersonWorkSectorsInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("rest/api/person-work-sectors-info")
public class PersonWorkSectorsController {
    private final CreatePersonWorkSectorsInfo createPersonWorkSectorsInfo;
    private final AquirePersonWorkSectorsInfo aquirePersonWorkSectorsInfo;
    private final ChangePersonWorkSectorsInfo updatePersonWorkSectorsInfo;

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping
    public PersonWorkSectorsInfoModificationResponseDto createPersonWorkSectors(@RequestBody PersonWorkSectorsCreationDto personWorkSectorsCreationDto) {
        var creationDetails = personWorkSectorsCreationDto.toDomain(personWorkSectorsCreationDto);

        var response =
                createPersonWorkSectorsInfo.execute(CreatePersonWorkSectorsInfo.Request.of(creationDetails));

        return PersonWorkSectorsInfoModificationResponseDto.of(response.getValue());
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @PutMapping
    public PersonWorkSectorsInfoModificationResponseDto updatePersonWorkSectors(@RequestBody PersonWorkSectorsUpdateDto personWorkSectorsUpdateDto) {
        var updateDetails = personWorkSectorsUpdateDto.toDomain(personWorkSectorsUpdateDto);

        var response =
                updatePersonWorkSectorsInfo.execute(ChangePersonWorkSectorsInfo.Request.of(updateDetails));

        return PersonWorkSectorsInfoModificationResponseDto.of(response.getValue());
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping
    public PersonWorkSectorsInfoDto getPersonWorkSectorsInfo(@RequestParam Long personWorksSectorsInfoId) {
        return PersonWorkSectorsInfoDto.of(aquirePersonWorkSectorsInfo.execute(AquirePersonWorkSectorsInfo.Request.of(PersonWorkSectorsInfo.Id.of(personWorksSectorsInfoId))));
    }

}
