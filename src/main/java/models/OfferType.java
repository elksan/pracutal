package models;

import javax.persistence.*;
import java.util.List;

/**
 * Created by maguirre on 4/13/16.
 */
@Entity
public class OfferType {

    @Id
    @Column(name="id_offer_type", columnDefinition = "serial")
    private Integer id;

    @Column(name="description")
    private String description;

    @Column(name="name_type")
    private String name;

    @OneToMany(fetch= FetchType.LAZY, mappedBy="id")
    private List<Offer> offers;

    public OfferType() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Offer> getOffers() {
        return offers;
    }

    public void setOffers(List<Offer> offers) {
        this.offers = offers;
    }

    @Override
    public String toString() {
        return "OfferType{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", name='" + name + '\'' +
                ", offers=" + (offers != null ? offers.size() : "0") + '\'' +
                '}';
    }
}
