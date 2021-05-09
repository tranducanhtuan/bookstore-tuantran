package jp.co.sb.bookstore.book.repository;

import java.util.List;
import jp.co.sb.bookstore.book.entity.BookJpo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author andy - tuantda.uit@gmail.com
 */
@Repository
public interface BookRepository extends JpaRepository<BookJpo, Integer> {

    List<BookJpo> findByBookTitleContainingIgnoreCaseAndAuthorJpo_AuthorNameContainingIgnoreCase(String bookTitle, String authorName);
    List<BookJpo> findByBookIdIn(List<Integer> bookIdList);
    long countByBookIdIn(List<Integer> bookIdList);
    boolean existsByBookTitleIn(List<String> bookTitleList);
    boolean existsByAuthorJpo_AuthorIdIn(List<Integer> authorIdList);
    int deleteAllByBookIdIn(List<Integer> bookIdList);

}
