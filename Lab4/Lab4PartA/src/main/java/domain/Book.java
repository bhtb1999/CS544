package domain;


import jakarta.persistence.*;

@Entity
public class Book {

    @Id
    @GeneratedValue
    private int id;
    private String title;
    private String ISBN;
    private String author;
    @ManyToOne
    @JoinColumn(name = "p_id",nullable = false)
    // below is more correct
//    @JoinTable(
//            name = "book_publisher",
//            joinColumns = @JoinColumn(name = "book_id"),
//            inverseJoinColumns = @JoinColumn(name = "publisher_id")
//    )
    private Publisher publisher;

    protected Book() {
    }

    public Book(String title, String ISBN, String author) {
        super();
        this.title = title;
        this.ISBN = ISBN;
        this.author = author;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }
}


