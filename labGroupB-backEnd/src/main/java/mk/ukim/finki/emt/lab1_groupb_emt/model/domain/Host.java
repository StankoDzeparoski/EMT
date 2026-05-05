package mk.ukim.finki.emt.lab1_groupb_emt.model.domain;

//За секој домаќин пак се чуваат податоците: id (Long), createdAt (LocalDateTime),
// updatedAt (LocalDateTime), name (String), surname (String) и country (Country).

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "hosts")
public class Host extends BaseAuditableEntity {
    private String name;
    private String surname;
    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;

    public Host() {
    }

    public Host(String name, String surname, Country country) {
        this.name = name;
        this.surname = surname;
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }
}
