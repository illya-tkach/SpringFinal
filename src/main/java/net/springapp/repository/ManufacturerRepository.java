package net.springapp.repository;

import net.springapp.model.Manufacturer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ManufacturerRepository extends JpaRepository<Manufacturer,Long> {

    Manufacturer findByName(String name);
}
