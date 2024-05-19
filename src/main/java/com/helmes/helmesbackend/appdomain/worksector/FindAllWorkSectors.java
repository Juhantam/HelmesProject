package com.helmes.helmesbackend.appdomain.worksector;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@RequiredArgsConstructor
public class FindAllWorkSectors {
    private final FindWorkSectors findWorkSectors;

    public Set<WorkSector> execute() {
        return findWorkSectors.execute();
    }
}
