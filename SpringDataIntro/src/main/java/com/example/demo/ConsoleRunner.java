package com.example.demo;

import com.example.demo.models.*;
import com.example.demo.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;


@Component
public class ConsoleRunner implements CommandLineRunner {

    private AuthorService authorService;
    private BookService bookService;
    private SeedService seedService;
    private FillService fillService;
    private UserService userService;

    @Autowired
    public ConsoleRunner(AuthorService authorService,
                         BookService bookService,
                         SeedService seedService,
                         FillService fillService,
                         UserService userService) {
        this.authorService = authorService;
        this.bookService = bookService;
        this.seedService = seedService;
        this.fillService = fillService;
        this.userService = userService;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {
//        seedService.seedAll();//Seeding for First problem
//        getAllBooksAfterYear();//Solution for First problem
//        getAllAuthorsWithAtLeastOneBookAndReleaseDateBefore();//Solution for First problem
//        getAllAuthorsSortedByBooksSize();//Solution for First problem
//        getAllBooksFromAuthor();//Solution for First problem
//        fillService.fill();//Filling for Second problem
//        getUsersByEmail();//Solution for Second problem
//        removeInactiveUsers();//Solution for Second problem
    }

    private void removeInactiveUsers() {
        System.out.println("Enter Date and Time:");
        Scanner scanner = new Scanner(System.in);
        LocalDateTime dateTime = LocalDateTime.parse(scanner.nextLine());
        List<User> users = userService.findByLastLogInBeforeAndSetToDeleted(dateTime);
        users.forEach(u -> System.out.printf("%s%n", u.getUsername()));
        userService.deleteUsers();
    }

    private void getUsersByEmail() {
        System.out.println("Enter provider:");
        Scanner scanner = new Scanner(System.in);
        String provider = scanner.nextLine();
        List<User> users = userService.findByEmail(provider);
        if (users.size() != 0) {
            users.forEach(u -> System.out.printf("%s %s%n", u.getUsername(), u.getEmail()));
        } else {
            System.out.printf("No users found with email domain %s%n", provider);
        }
    }

    private void getAllBooksFromAuthor() {
        List<Book> books = bookService.getByAuthor();
        books.forEach(b -> System.out.printf("%s %s %d%n", b.getTitle(), b.getReleaseDate(), b.getCopies()));
    }

    private void getAllAuthorsSortedByBooksSize() {
        List<Author> authorsByBooksSize = authorService.findAuthorsByBooksCountDesc();
        authorsByBooksSize.forEach(a -> System.out.printf("%s %s %d%n", a.getFirstName(), a.getLastName(), a.getBooks().size()));
    }

    private void getAllAuthorsWithAtLeastOneBookAndReleaseDateBefore() {
        List<Author> authors = authorService.findAuthorsByBooksReleaseDateBefore();
        authors.forEach(a -> System.out.printf("%s %s%n", a.getFirstName(), a.getLastName()));
    }

    private void getAllBooksAfterYear() {
        List<Book> books = bookService.getByYearAfter();
        books.forEach(b -> System.out.printf("%s%n", b.getTitle()));
        System.out.println(books.size());
    }
}
