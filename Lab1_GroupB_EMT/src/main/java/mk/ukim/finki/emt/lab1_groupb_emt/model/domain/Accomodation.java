package mk.ukim.finki.emt.lab1_groupb_emt.model.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import mk.ukim.finki.emt.lab1_groupb_emt.model.enums.Category;
import mk.ukim.finki.emt.lab1_groupb_emt.model.enums.Condition;

import java.util.ArrayList;
import java.util.List;

//id (Long), createdAt (LocalDateTime), updatedAt (LocalDateTime), name (String), category (enum), host (Host) и numRooms (Integer).

@Entity
@Getter
@Setter
@Table(name = "accommodations")
@NamedEntityGraphs({
    @NamedEntityGraph(
        name = "Accommodation.withHostAndCountry",
        attributeNodes = {
            @NamedAttributeNode(value = "host", subgraph = "host-country")
        },
        subgraphs = {
            @NamedSubgraph(
                name = "host-country",
                attributeNodes = @NamedAttributeNode("country")
            )
        }
    ),
    @NamedEntityGraph(
        name = "Accommodation.withHost",
        attributeNodes = @NamedAttributeNode("host")
    )
})
public class Accomodation extends BaseAuditableEntity{
    private String name;
    @Enumerated(EnumType.STRING)
    private Category category;
    @Enumerated(EnumType.STRING)
    private Condition condition;
    private Boolean occupied;
    @ManyToOne
    @JoinColumn(name = "host_id")
    private Host host;
    private Integer numRooms;
    @OneToMany(mappedBy = "accomodation", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Review> reviews = new ArrayList<>();

    public Accomodation() {
    }

    public Accomodation(String name, Category category, Condition condition, Host host, Integer numRooms, Boolean occupied) {
        this.name = name;
        this.category = category;
        this.condition = condition;
        this.host = host;
        this.numRooms = numRooms;
        this.occupied = occupied;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Condition getCondition() {
        return condition;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }

    public Boolean getOccupied() {
        return occupied;
    }

    public void setOccupied(Boolean occupied) {
        this.occupied = occupied;
    }

    public Host getHost() {
        return host;
    }

    public void setHost(Host host) {
        this.host = host;
    }

    public Integer getNumRooms() {
        return numRooms;
    }

    public void setNumRooms(Integer numRooms) {
        this.numRooms = numRooms;
    }
}
