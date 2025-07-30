package com.lab8.Lab8PartB;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Lab8PartBApplication {

	public static void main(String[] args) {
		SpringApplication.run(Lab8PartBApplication.class, args);
	}

}
//package com.lab8.Lab8PartA;
//
//import com.lab8.Lab8PartA.domain.Book;
//import com.lab8.Lab8PartA.domain.Books;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.web.client.RestTemplate;
//
//import java.util.List;
//
//@SpringBootApplication
//public class Lab8PartAApplication implements CommandLineRunner {
//    private RestTemplate restTemplate = new RestTemplate();
//    public static void main(String[] args) {
//        SpringApplication.run(Lab8PartAApplication.class, args);
//    }
//    @Override
//    public void run(String... args) throws Exception {
//        String serverUrl = "http://localhost:8080/books";
//        //Add book
//        restTemplate.postForLocation(serverUrl, new Book("Jav", "Bao", "3", 10000));
//        restTemplate.postForLocation(serverUrl, new Book("Java", "Bao", "4", 103000));
//        restTemplate.postForLocation(serverUrl, new Book("PHP", "Thai", "5", 10));
//
//        //Search Book by author name
//        Books books = restTemplate.getForObject(serverUrl+"/search/{authorName}", Books.class, "Thai");
//        System.out.println(books);
//
//        //Get book by isbn
//        Book book1 = restTemplate.getForObject(serverUrl+"/{isbn}", Book.class, "1");
//        System.out.println(book1);
//
//        //Update book
//        book1.setAuthor("Bao");
//        restTemplate.put(serverUrl+"/{isbn}", book1 ,book1.getIsbn());
//
//        //Search Book by author name
//        Books books1 = restTemplate.getForObject(serverUrl+"/search/{authorName}", Books.class, "Bao");
//        System.out.println(books1);
//
//        //Get all books
//        Books allBooks = restTemplate.getForObject(serverUrl, Books.class);
//        System.out.println(allBooks);
//
//    }
//
//
//
//
//}
