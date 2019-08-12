package net.springapp.dto;
import net.springapp.model.barbershop.BarberLevel;

public class BarberDTO {

    public int id;

    public String firstName;

    public String lastName;

    public String photoUrl;

    public BarberLevel barberLevel;

    public void setId(int id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public void setBarberLevel(BarberLevel barberLevel) {
        this.barberLevel = barberLevel;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public BarberLevel getBarberLevel() {
        return barberLevel;
    }

    public static BarberDTO.Builder newBuilder() {
        return new BarberDTO().new Builder();
    }

    public class Builder {

        private Builder() {
            // private constructor
        }

        public BarberDTO.Builder setId(int barberId) {
            BarberDTO.this.id = barberId;

            return this;
        }

        public BarberDTO.Builder setFirstName(String firstName) {
            BarberDTO.this.firstName = firstName;

            return this;
        }

        public BarberDTO.Builder setLastName(String lastName) {
            BarberDTO.this.lastName = lastName;

            return this;
        }

        public BarberDTO.Builder setPhotoUrl(String photoUrl) {
            BarberDTO.this.photoUrl = photoUrl;

            return this;
        }

        public BarberDTO.Builder setBarberlLevel(BarberLevel barberLevel) {
            BarberDTO.this.barberLevel = barberLevel;

            return this;
        }


        public BarberDTO build() {
            return BarberDTO.this;
        }

    }
}
