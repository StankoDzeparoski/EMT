package mk.ukim.finki.emt.lab1_groupb_emt.model.domain;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.*;

//За секоја земја се чуваат податоците: id (Long), name (String) и continent (String).
@Entity
@Getter
@Setter
@Table(name = "countries")
public class Country extends BaseAuditableEntity{
    private String name;
    private String continent;

    public Country() {
    }

    public Country(String name, String continent) {
        this.name = name;
        this.continent = continent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }
}
