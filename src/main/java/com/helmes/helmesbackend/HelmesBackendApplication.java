package com.helmes.helmesbackend;

import com.helmes.helmesbackend.adapter.database.worksector.WorkSectorEntity;
import com.helmes.helmesbackend.adapter.database.worksector.WorkSectorEntityRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Set;

@SpringBootApplication
public class HelmesBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(HelmesBackendApplication.class, args);
    }

    @Bean
    //For the sake of simplicity I am adding the work sectors here, but ofc in a real application I would use a liquibase script
    public CommandLineRunner createWorkSectors(WorkSectorEntityRepository repository) {
        return (args) -> {
            Long manufacturingId = repository.save(buildWorkSector("Manufacturing"))
                                             .getId();
            saveManufacturingSectors(repository, manufacturingId);
            Long otherId = repository.save(buildWorkSector("Other"))
                                             .getId();
            saveOtherSectors(repository, otherId);
            Long serviceId = repository.save(buildWorkSector("Service"))
                                     .getId();
            saveServiceSectors(repository, serviceId);
        };
    }

    private void saveManufacturingSectors(WorkSectorEntityRepository repository, Long manufacturingId) {
        repository.saveAll(Set.of(buildWorkSector("Construction materials", manufacturingId),
                                  buildWorkSector("Electronics and Optics", manufacturingId)));
        Long foodAndBeverageId = repository.save(buildWorkSector("Food and Beverage", manufacturingId))
                                           .getId();
        repository.saveAll(Set.of(buildWorkSector("Bakery & confectionery products", foodAndBeverageId),
                                  buildWorkSector("Beverages", foodAndBeverageId),
                                  buildWorkSector("Fish & fish products", foodAndBeverageId),
                                  buildWorkSector("Meat & meat products", foodAndBeverageId),
                                  buildWorkSector("Milk & dairy products", foodAndBeverageId),
                                  buildWorkSector("Other", foodAndBeverageId),
                                  buildWorkSector("Sweets & snack food", foodAndBeverageId)));
        Long furnitureId = repository.save(buildWorkSector("Furniture", manufacturingId))
                                     .getId();
        repository.saveAll(Set.of(buildWorkSector("Bathroom/Sauna", furnitureId),
                                  buildWorkSector("Bedroom", furnitureId),
                                  buildWorkSector("Children's room", furnitureId),
                                  buildWorkSector("Kitchen", furnitureId),
                                  buildWorkSector("Living room", furnitureId),
                                  buildWorkSector("Office", furnitureId),
                                  buildWorkSector("Other (Furniture)", furnitureId),
                                  buildWorkSector("Outdoor", furnitureId),
                                  buildWorkSector("Project furniture", furnitureId)));
        Long machineryId = repository.save(buildWorkSector("Machinery", manufacturingId))
                                     .getId();
        repository.saveAll(Set.of(buildWorkSector("Machinery components", machineryId),
                                  buildWorkSector("Machinery equipment/tools", machineryId),
                                  buildWorkSector("Manufacture of machinery", machineryId),
                                  buildWorkSector("Metal structures", machineryId),
                                  buildWorkSector("Other", machineryId),
                                  buildWorkSector("Repair and maintenance service", machineryId)));
        Long maritimeId = repository.save(buildWorkSector("Maritime", machineryId))
                                    .getId();
        repository.saveAll(Set.of(buildWorkSector("Aluminum and steel workboats", maritimeId),
                                  buildWorkSector("Boat/Yacht building", maritimeId),
                                  buildWorkSector("Ship repair and conversion", maritimeId)));
        Long metalworkingId = repository.save(buildWorkSector("Metalworking", manufacturingId))
                                        .getId();
        repository.saveAll(Set.of(buildWorkSector("Construction of metal structures", metalworkingId),
                                  buildWorkSector("Houses and buildings", metalworkingId),
                                  buildWorkSector("Metal products", metalworkingId)));
        Long metalWorksId = repository.save(buildWorkSector("Metal works", metalworkingId))
                                      .getId();
        repository.saveAll(Set.of(buildWorkSector("CNC-machining", metalWorksId),
                                  buildWorkSector("Forgings, Fasteners", metalWorksId),
                                  buildWorkSector("Gas, Plasma, Laser cutting", metalWorksId),
                                  buildWorkSector("MIG, TIG, Aluminum welding", metalWorksId)));
        Long plasticAndRubberId = repository.save(buildWorkSector("Plastic and Rubber", manufacturingId))
                                            .getId();
        repository.saveAll(Set.of(buildWorkSector("Packaging", plasticAndRubberId),
                                  buildWorkSector("Plastic goods", plasticAndRubberId),
                                  buildWorkSector("Plastic profiles", plasticAndRubberId)));
        Long plasticProcessingId = repository.save(buildWorkSector("Plastic processing technology", plasticAndRubberId))
                                             .getId();
        repository.saveAll(Set.of(buildWorkSector("Blowing", plasticProcessingId),
                                  buildWorkSector("Moulding", plasticProcessingId),
                                  buildWorkSector("Plastics welding and processing", plasticProcessingId)));
        Long printingId = repository.save(buildWorkSector("Printing", manufacturingId))
                                    .getId();
        repository.saveAll(Set.of(buildWorkSector("Advertising", printingId),
                                  buildWorkSector("Book/Periodicals printing", printingId),
                                  buildWorkSector("Labelling and packaging printing", printingId)));
        Long textileAndClothingId = repository.save(buildWorkSector("Textile and Clothing",
                                                                    manufacturingId))
                                              .getId();
        repository.saveAll(Set.of(buildWorkSector("Clothing", textileAndClothingId),
                                  buildWorkSector("Textile", textileAndClothingId)));
        Long woodId = repository.save(buildWorkSector("Wood", manufacturingId))
                                .getId();
        repository.saveAll(Set.of(buildWorkSector("Other (Wood)", woodId),
                                  buildWorkSector("Wooden building materials", woodId),
                                  buildWorkSector("Wooden houses", woodId)));
    }

    private void saveOtherSectors(WorkSectorEntityRepository repository, Long otherId) {
        repository.saveAll(Set.of(buildWorkSector("Creative industries", otherId),
                                  buildWorkSector("Energy technology", otherId),
                                  buildWorkSector("Environment", otherId)));
    }

    private void saveServiceSectors(WorkSectorEntityRepository repository, Long serviceId) {
        repository.saveAll(Set.of(buildWorkSector("Business services", serviceId),
                                  buildWorkSector("Engineering", serviceId),
                                  buildWorkSector("Tourism", serviceId),
                                  buildWorkSector("Translation services", serviceId)));
        Long itId = repository.save(buildWorkSector("Information Technology and Telecommunications", serviceId))
                              .getId();
        repository.saveAll(Set.of(buildWorkSector("Data processing, Web portals, E-marketing", itId),
                                  buildWorkSector("Programming, Consultancy", itId),
                                  buildWorkSector("Software, Hardware", itId),
                                  buildWorkSector("Telecommunications", itId)));
        Long transportAndLogisticsId = repository.save(buildWorkSector("Transport and Logistics", serviceId))
                                                 .getId();
        repository.saveAll(Set.of(buildWorkSector("Air", transportAndLogisticsId),
                                  buildWorkSector("Rail", transportAndLogisticsId),
                                  buildWorkSector("Road", transportAndLogisticsId),
                                  buildWorkSector("Water", transportAndLogisticsId)));
    }

    private WorkSectorEntity buildWorkSector(String name) {
        return WorkSectorEntity.builder()
                               .name(name)
                               .build();
    }

    private WorkSectorEntity buildWorkSector(String name, Long parentSectorId) {
        return WorkSectorEntity.builder()
                               .name(name)
                               .parentSectorId(parentSectorId)
                               .build();
    }

}
