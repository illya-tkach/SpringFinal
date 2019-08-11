package net.springapp.model.barbershop;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "barber")
public class Barber {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "firstname")
    private String firstName;

    @Column(name = "lastname")
    private String lastName;

    @Column(name = "photo")
    private String photoUrl;

    @JsonIgnore
    @ManyToMany
    @JoinTable (name = "barber_service", joinColumns = @JoinColumn(name = "barber_id"),
            inverseJoinColumns = @JoinColumn(name = "service_id"))
    private Set<Service> services;

    @ManyToOne
    @JoinColumn(name = "level_id", nullable = false)
    private BarberLevel barberLevel;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "barber")
    private Set<Record> records;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Set<Service> getServices() {
        return services;
    }

    public void setServices(Set<Service> services) {
        this.services = services;
    }

    public BarberLevel getBarberLevel() {
        return barberLevel;
    }

    public void setBarberLevel(BarberLevel barberLevel) {
        this.barberLevel = barberLevel;
    }

    public Set<Record> getRecords() {
        return records;
    }

    public void setRecords(Set<Record> records) {
        this.records = records;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }
}
