package domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
//@DiscriminatorValue("cd")
public class CD extends Product {
    private String artist;

    protected CD(){}

    public CD(int productNumber, String name, String description, double price, String artist) {
        super(productNumber, name, description, price);
        this.artist = artist;
    }
}
