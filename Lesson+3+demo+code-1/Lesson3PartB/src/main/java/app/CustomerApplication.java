package app;

import java.util.List;
import java.util.Optional;

import domain.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import repositories.BookRepository;

@SpringBootApplication
@EnableJpaRepositories("repositories")
@EntityScan("domain")
public class CustomerApplication implements CommandLineRunner {

    @Autowired
    BookRepository bookrepository;

    public static void main(String[] args) {
        SpringApplication.run(CustomerApplication.class, args);
    }

    public void displayBooks(List<Book> books) {
        System.out.println("----------"+books);
        for (Book book : books) {
            System.out.println("--------------------------------------");
            System.out.println("Book title: "+book.getTitle());
            System.out.println("Book ISBN: "+book.getISBN());
            System.out.println("Book author: "+book.getAuthor());
            System.out.println("Book price: "+book.getPrice());
        }
    }

    @Override
    public void run(String... args) throws Exception {
        bookrepository.save(new Book("2", "ISBN2", "Auth2", 12000));
        bookrepository.save(new Book("3", "ISBN3", "Auth3", 1000));
        bookrepository.save(new Book("4", "ISBN4", "Auth4", 100000));

        displayBooks(bookrepository.findAll());

        Optional<Book> b = bookrepository.findById(1);
        Book book = b.get();
        book.setTitle("Book Title");
        bookrepository.save(book);

        bookrepository.deleteById(2);

        displayBooks(bookrepository.findAll());

        displayBooks(bookrepository.findByISBN("ISBN4"));

        displayBooks(bookrepository.findByISBNAndAuthor("ISBN2","Auth2"));

    }
}
