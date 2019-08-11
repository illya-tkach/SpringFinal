package net.springapp.repository;

import net.springapp.model.barbershop.Barber;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BarberRepository extends JpaRepository<Barber,Long> {
}
