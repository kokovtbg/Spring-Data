package spring.advQuerying.service.impl;

import org.springframework.stereotype.Service;
import spring.advQuerying.model.entity.*;
import spring.advQuerying.repository.BookRepository;
import spring.advQuerying.service.AuthorService;
import spring.advQuerying.service.BookService;
import spring.advQuerying.service.CategoryService;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    private static final String BOOKS_FILE_PATH = "src\\main\\resources\\files\\books.txt";

    private final BookRepository bookRepository;
    private final AuthorService authorService;
    private final CategoryService categoryService;

    public BookServiceImpl(BookRepository bookRepository, AuthorService authorService, CategoryService categoryService) {
        this.bookRepository = bookRepository;
        this.authorService = authorService;
        this.categoryService = categoryService;
    }

    @Override
    public void seedBooks() throws IOException {
        if (bookRepository.count() > 0) {
            return;
        }

        Files
                .readAllLines(Path.of(BOOKS_FILE_PATH))
                .forEach(row -> {
                    String[] bookInfo = row.split("\\s+");

                    Book book = createBookFromInfo(bookInfo);

                    bookRepository.save(book);
                });
    }

    @Override
    public List<Book> findAllByAgeRestriction(AgeRestriction ageRestriction) {
        return this.bookRepository.findAllByAgeRestriction(ageRestriction);
    }

    @Override
    public List<Book> findAllByEditionTypeAndCopies() {
        return this.bookRepository.findAllByEditionTypeAndCopiesLessThan(EditionType.valueOf("GOLD"), 5000);
    }

    @Override
    public List<Book> findAllByPriceLowerThanOrPriceGreaterThan() {
        return this.bookRepository.findAllByPriceLessThanOrPriceGreaterThan(BigDecimal.valueOf(5), BigDecimal.valueOf(40));
    }

    @Override
    public List<Book> findAllByReleasedDate(LocalDate startDate, LocalDate endDate) {
        return this.bookRepository.findAllByReleaseDateLessThanOrReleaseDateGreaterThan(startDate, endDate);
    }

    @Override
    public List<Book> findAllByReleaseDateBefore(LocalDate date) {
        return this.bookRepository.findAllByReleaseDateLessThan(date);
    }

    @Override
    public List<Book> findAllByTitleContaining(String part) {
        return this.bookRepository.findAllByTitleContaining(part);
    }

    @Override
    public List<Book> findAllByAuthorLastNameStartingWith(String part) {
        return this.bookRepository.findAllByAuthorLastNameStartingWith(part);
    }

    @Override
    public long getCountBooksByTitleCountLetters(long count) {
        return this.bookRepository.getCountBooksByTitleCountLetters(count);
    }

    @Override
    public List<String[]> getBookByTitle(String titleBook) {
        return this.bookRepository.getBookByTitle(titleBook);
    }

    @Override
    public int increaseCopies(int copies, LocalDate date) {
        return this.bookRepository.increaseCopies(copies, date);
    }

    @Override
    public int removeByCopiesLowerThan(int copiesCount) {
        return this.bookRepository.removeByCopiesLowerThan(copiesCount);
    }

    @Override
    public int getCountByAuthor(String firstName, String lastName) {
        return this.bookRepository.getCountByAuthor(firstName, lastName);
    }

    private Book createBookFromInfo(String[] bookInfo) {
        EditionType editionType = EditionType.values()[Integer.parseInt(bookInfo[0])];
        LocalDate releaseDate = LocalDate
                .parse(bookInfo[1], DateTimeFormatter.ofPattern("d/M/yyyy"));
        Integer copies = Integer.parseInt(bookInfo[2]);
        BigDecimal price = new BigDecimal(bookInfo[3]);
        AgeRestriction ageRestriction = AgeRestriction
                .values()[Integer.parseInt(bookInfo[4])];
        String title = Arrays.stream(bookInfo)
                .skip(5)
                .collect(Collectors.joining(" "));

        Author author = authorService.getRandomAuthor();
        Set<Category> categories = categoryService
                .getRandomCategories();

        return new Book(editionType, releaseDate, copies, price, ageRestriction, title, author, categories);
    }
}
