package jp.co.sb.bookstore.book.service;

import java.util.List;
import jp.co.sb.bookstore.book.dto.BookDto;

/**
 * @author andy - tuantda.uit@gmail.com
 */
public interface BookService {

    /**
     * Create Books
     *
     * @param bookDtoList
     * @return true if create success
     */
    boolean createBooks(List<BookDto> bookDtoList);

    /**
     * Find Books by conditions
     *
     * @param bookTitle
     * @param authorName
     * @return List<BookDto>
     */
    List<BookDto> findBooks(String bookTitle, String authorName);

    /**
     * Update Books
     *
     * @param fromBookDtoList
     * @return true if update success
     */
    boolean updateBooks(List<BookDto> fromBookDtoList);

    /**
     * Delete Books
     *
     * @param authorIdList
     * @return true if remove success
     */
    boolean deleteBooks(List<Integer> authorIdList);

}
