package com.helmes.helmesbackend.web.worksector;

import com.helmes.helmesbackend.appdomain.worksector.FindAllWorkSectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("rest/api/work-sector")
public class WorkSectorController {
    private final FindAllWorkSectors findAllWorkSectors;

    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping
    public Set<WorkSectorDto> findAllWorkSectors() {
        return findAllWorkSectors.execute()
                                 .stream()
                                 .map(WorkSectorDto::of)
                                 .collect(Collectors.toSet());
    }

}
