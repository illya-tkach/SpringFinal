package net.springapp.model.barbershop;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "barberlevel")
public class BarberLevel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name", unique = true)
    private String levelName;

    @Column (name="coefficient", unique = true)
    private double serviceCostCoef;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "barberLevel")
    private List<Barber> barbers;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    public double getServiceCostCoef() {
        return serviceCostCoef;
    }

    public void setServiceCostCoef(double serviceCostCoef) {
        this.serviceCostCoef = serviceCostCoef;
    }

    public List<Barber> getBarbers() {
        return barbers;
    }

    public void setBarbers(List<Barber> barbers) {
        this.barbers = barbers;
    }
}
