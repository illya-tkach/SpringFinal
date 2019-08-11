package net.springapp.repository;

import net.springapp.model.barbershop.Service;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceRepository extends JpaRepository<Service,Long> {
}
