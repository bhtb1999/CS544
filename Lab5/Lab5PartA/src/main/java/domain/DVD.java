package domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
//@DiscriminatorValue("dvd")
public class DVD extends Product {
    private String genre;

    protected DVD(){}

    public DVD(int productNumber, String name, String description, double price, String genre) {
        super(productNumber, name, description, price);
        this.genre = genre;
    }
}
