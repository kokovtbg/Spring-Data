package spring.advQuerying.service;


import spring.advQuerying.model.entity.AgeRestriction;
import spring.advQuerying.model.entity.Book;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public interface BookService {
    void seedBooks() throws IOException;

    List<Book> findAllByAgeRestriction(AgeRestriction ageRestriction);

    List<Book> findAllByEditionTypeAndCopies();

    List<Book> findAllByPriceLowerThanOrPriceGreaterThan();

    List<Book> findAllByReleasedDate(LocalDate startDate, LocalDate endDate);

    List<Book> findAllByReleaseDateBefore(LocalDate date);

    List<Book> findAllByTitleContaining(String part);

    List<Book> findAllByAuthorLastNameStartingWith(String part);

    long getCountBooksByTitleCountLetters(long count);

    List<String[]> getBookByTitle(String titleBook);

    int increaseCopies(int copies, LocalDate date);

    int removeByCopiesLowerThan(int copiesCount);

    int getCountByAuthor(String firstName, String lastName);
}
