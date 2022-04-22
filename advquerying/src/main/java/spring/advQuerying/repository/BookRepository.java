package spring.advQuerying.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import spring.advQuerying.model.entity.AgeRestriction;
import spring.advQuerying.model.entity.Book;
import spring.advQuerying.model.entity.EditionType;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;


@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findAllByAgeRestriction(AgeRestriction ageRestriction);

    List<Book> findAllByEditionTypeAndCopiesLessThan(EditionType editionType, int copies);

    List<Book> findAllByPriceLessThanOrPriceGreaterThan(BigDecimal lowerPrice, BigDecimal greaterPrice);

    List<Book> findAllByReleaseDateLessThanOrReleaseDateGreaterThan(LocalDate startDate, LocalDate endDate);

    List<Book> findAllByReleaseDateLessThan(LocalDate date);

    List<Book> findAllByTitleContaining(String part);

    List<Book> findAllByAuthorLastNameStartingWith(String part);

    @Query(value = "SELECT count(b.id) FROM Book AS b\n" +
            "WHERE char_length(b.title) > :countLetters")
    long getCountBooksByTitleCountLetters(@Param(value = "countLetters") long countLetters);

    @Query(value = "SELECT title, editionType, ageRestriction, price FROM Book\n" +
            "WHERE title = :titleBook")
    List<String[]> getBookByTitle(@Param(value = "titleBook") String titleBook);

    @Modifying
    @Transactional
    @Query(value = "UPDATE Book SET copies = copies + :copiesToAdd " +
            "WHERE releaseDate > :releaseDateToAdd")
    int increaseCopies(@Param(value = "copiesToAdd") int copiesToAdd,
                        @Param(value = "releaseDateToAdd") LocalDate date);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM Book WHERE copies < :copiesCount")
    int removeByCopiesLowerThan(@Param(value = "copiesCount") int copiesCount);

    @Query(value = "CALL udp_get_count_books_by_author(:firstName, :lastName)", nativeQuery = true)
    int getCountByAuthor(@Param(value = "firstName") String firstName,
                         @Param(value = "lastName") String lastName);
}
