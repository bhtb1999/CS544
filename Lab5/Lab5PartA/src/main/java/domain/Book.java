package domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
//@DiscriminatorValue("book")
public class Book extends Product {
    private String isbn;

    protected Book(){}

    public Book(int productNumber, String name, String description, double price, String isbn) {
        super(productNumber, name, description, price);
        this.isbn = isbn;
    }
}
