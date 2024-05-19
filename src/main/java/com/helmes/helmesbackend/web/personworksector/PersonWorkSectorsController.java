package com.helmes.helmesbackend.web.personworksector;

import com.helmes.helmesbackend.appdomain.personworksector.creation.CreatePersonWorkSectorsInfo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping
    public PersonWorkSectorsCreationResponseDto createPersonWorkSectors(@RequestBody PersonWorkSectorsCreationDto personWorkSectorsCreationDto) {
        var creationDetails = personWorkSectorsCreationDto.toDomain(personWorkSectorsCreationDto);

        var response = createPersonWorkSectorsInfo.execute(CreatePersonWorkSectorsInfo.Request.of(creationDetails));

        return PersonWorkSectorsCreationResponseDto.of(response.getValue());
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping
    public String createPersonWorkSectors(@RequestParam String personName) {
        log.warn("Get request received");
        return personName;
    }

}
