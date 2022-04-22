package spring.advQuerying;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import spring.advQuerying.model.entity.AgeRestriction;
import spring.advQuerying.model.entity.Author;
import spring.advQuerying.model.entity.Book;
import spring.advQuerying.service.AuthorService;
import spring.advQuerying.service.BookService;
import spring.advQuerying.service.CategoryService;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

@Component
public class CommandLineRunnerImpl implements CommandLineRunner {

    private final CategoryService categoryService;
    private final AuthorService authorService;
    private final BookService bookService;

    public CommandLineRunnerImpl(CategoryService categoryService, AuthorService authorService, BookService bookService) {
        this.categoryService = categoryService;
        this.authorService = authorService;
        this.bookService = bookService;
    }

    @Override
    public void run(String... args) throws Exception {
//        seedData();//Uploading Database
//        getAllBooksByAgeRestriction();//1.	Books Titles by Age Restriction
//        getAllBooksByEditionTypeAndCopies();//2.	Golden Books
//        getAllBooksByPriceLowerThanOrGreaterThan();//3.	Books by Price
//        getAllBooksByReleasedDate();//4.	Not Released Books
//        getAllBooksByReleaseDateBefore();//5.	Books Released Before Date
//        getAllAuthorsByFirstNameEndingWith();//6.	Authors Search
//        getAllBooksByTitleContaining();//7.	Books Search
//        getAllBooksByAuthorLastNamePart();//8.	Book Titles Search
//        getCountBooksByTitleCountLetters();//9.	Count Books
//        getAllAuthorsByTotalBookCopies();//10.	Total Book Copies
//        getBookInfoByTitle();//11.	Reduced Book
//        increaseBookCopies();//12.	* Increase Book Copies
//        removeBooksByCopiesLowerThan();//13.	* Remove Books
//        getCountBooksByAuthor();//14.	* Stored Procedure
    }

    private void getCountBooksByAuthor() {
        System.out.println("Enter Author's Name");
        Scanner scanner = new Scanner(System.in);
        String[] splitData = scanner.nextLine().split("\\s+");
        String firstName = splitData[0];
        String lastName = splitData[1];
        System.out.println(this.bookService.getCountByAuthor(firstName, lastName));
    }

    private void removeBooksByCopiesLowerThan() {
        System.out.println("Enter Number of Copies");
        Scanner scanner = new Scanner(System.in);
        int count = Integer.parseInt(scanner.nextLine());
        System.out.println(this.bookService.removeByCopiesLowerThan(count));
    }

    private void increaseBookCopies() {
        System.out.println("Enter Date And Copies");
        Scanner scanner = new Scanner(System.in);
        String[] dateData = scanner.nextLine().split("\\s+");
        int copies = Integer.parseInt(scanner.nextLine());
        int month = getMonthAsInt(dateData);
        LocalDate date = LocalDate.of(Integer.parseInt(dateData[2]), month, Integer.parseInt(dateData[0]));
        int countBooks = this.bookService.increaseCopies(copies, date);
        System.out.println(copies * countBooks);

    }

    private int getMonthAsInt(String[] dateData) {
        int month = 0;
        switch (dateData[1]) {
            case "Jan":
                month = 1;
                break;
            case "Feb":
                month = 2;
                break;
            case "Mar":
                month = 3;
                break;
            case "Apr":
                month = 4;
                break;
            case "May":
                month = 5;
                break;
            case "Jun":
                month = 6;
                break;
            case "Jul":
                month = 7;
                break;
            case "Aug":
                month = 8;
                break;
            case "Sep":
                month = 9;
                break;
            case "Oct":
                month = 10;
                break;
            case "Nov":
                month = 11;
                break;
            case "Dec":
                month = 12;
                break;
        }
        return month;
    }

    private void getBookInfoByTitle() {
        System.out.println("Enter Book Title");
        Scanner scanner = new Scanner(System.in);
        String title = scanner.nextLine();
        List<String[]> bookByTitle = this.bookService.getBookByTitle(title);
        bookByTitle.forEach(s -> System.out.printf("%s %s %s %s%n", s[0], s[1], s[2], s[3]));
    }

    private void getAllAuthorsByTotalBookCopies() {
        List<Author> allOrderByBooksCopies = this.authorService.findAll();
        allOrderByBooksCopies
                .stream()
                .sorted((a1, a2) -> Integer.compare(a2.getBooks().stream().mapToInt(Book::getCopies).sum(),
                        a1.getBooks().stream().mapToInt(Book::getCopies).sum()))
                .forEach(a -> System.out.printf("%s %s - %d%n",
                a.getFirstName(), a.getLastName(), a.getBooks().stream().mapToInt(Book::getCopies).sum()));
    }

    private void getCountBooksByTitleCountLetters() {
        System.out.println("Enter Title Count Letters");
        Scanner scanner = new Scanner(System.in);
        long countLetters = Long.parseLong(scanner.nextLine());
        long countBooksByTitleCountLetters = this.bookService.getCountBooksByTitleCountLetters(countLetters);
        System.out.printf("There are %d books with longer title than %d symbols%n",
                countBooksByTitleCountLetters, countLetters);
    }

    private void getAllBooksByAuthorLastNamePart() {
        System.out.println("Enter Part Last Name");
        Scanner scanner = new Scanner(System.in);
        String part = scanner.nextLine();
        List<Book> allByAuthorLastNamePart = this.bookService.findAllByAuthorLastNameStartingWith(part);
        allByAuthorLastNamePart
                .forEach(b -> System.out.printf("%s (%s %s)%n",
                        b.getTitle(), b.getAuthor().getFirstName(), b.getAuthor().getLastName()));
    }

    private void getAllBooksByTitleContaining() {
        System.out.println("Enter String");
        Scanner scanner = new Scanner(System.in);
        String part = scanner.nextLine();
        List<Book> allByTitleContaining = this.bookService.findAllByTitleContaining(part);
        allByTitleContaining.forEach(b -> System.out.printf("%s%n", b.getTitle()));
    }

    private void getAllAuthorsByFirstNameEndingWith() {
        System.out.println("Enter String");
        Scanner scanner = new Scanner(System.in);
        String part = scanner.nextLine();
        List<Author> allByFirstNameEndingWith = this.authorService.findAllByFirstNameEndingWith(part);
        allByFirstNameEndingWith.forEach(a -> System.out.printf("%s %s%n", a.getFirstName(), a.getLastName()));
    }

    private void getAllBooksByReleaseDateBefore() {
        System.out.println("Enter Date");
        Scanner scanner = new Scanner(System.in);
        String[] dateData = scanner.nextLine().split("-");
        int day = Integer.parseInt(dateData[0]);
        int month = Integer.parseInt(dateData[1]);
        int year = Integer.parseInt(dateData[2]);
        List<Book> allByReleaseDateBefore = this.bookService.findAllByReleaseDateBefore(LocalDate.of(year, month, day));
        allByReleaseDateBefore.forEach(b -> System.out.printf("%s %s %.2f%n",
                b.getTitle(), b.getEditionType(), b.getPrice()));
    }

    private void getAllBooksByReleasedDate() {
        System.out.println("Enter Year");
        Scanner scanner = new Scanner(System.in);
        int year = Integer.parseInt(scanner.nextLine());
        List<Book> allByReleasedDate = this.bookService
                .findAllByReleasedDate(LocalDate.of(year, 1, 1), LocalDate.of(year, 12, 31));
        allByReleasedDate.forEach(b -> System.out.printf("%s%n", b.getTitle()));
    }

    private void getAllBooksByPriceLowerThanOrGreaterThan() {
        List<Book> allByPriceLowerThanOrPriceGreaterThan = this.bookService.findAllByPriceLowerThanOrPriceGreaterThan();
        allByPriceLowerThanOrPriceGreaterThan.forEach(b -> System.out.printf("%s - $%.2f%n", b.getTitle(), b.getPrice()));
    }

    private void getAllBooksByEditionTypeAndCopies() {
        List<Book> allByEditionTypeAndCopies = this.bookService.findAllByEditionTypeAndCopies();
        allByEditionTypeAndCopies.forEach(b -> System.out.printf("%s%n", b.getTitle()));
    }

    private void getAllBooksByAgeRestriction() {
        System.out.println("Enter Age Restriction");
        Scanner scanner = new Scanner(System.in);
        String ageRestriction = scanner.nextLine();
        String ageRestrictionToUpper = ageRestriction.toUpperCase();
        List<Book> allBooksByAgeRestriction = this.bookService
                .findAllByAgeRestriction(AgeRestriction.valueOf(ageRestrictionToUpper));
        allBooksByAgeRestriction.forEach(b -> System.out.printf("%s%n", b.getTitle()));
    }

    private void seedData() throws IOException {
        categoryService.seedCategories();
        authorService.seedAuthors();
        bookService.seedBooks();
    }
}
