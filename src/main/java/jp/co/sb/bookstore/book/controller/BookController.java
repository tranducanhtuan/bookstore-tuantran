package jp.co.sb.bookstore.book.controller;

import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import jp.co.sb.bookstore.base.dto.JsonResponse;
import jp.co.sb.bookstore.base.validate.OnCreate;
import jp.co.sb.bookstore.base.validate.OnUpdate;
import jp.co.sb.bookstore.book.dto.BookDto;
import jp.co.sb.bookstore.book.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author andy - tuantda.uit@gmail.com
 */
@RestController
@RequestMapping("/book")
@Validated
public class BookController {

    @Autowired
    private BookService bookService;

    /**
     * Create Books
     *
     * @param bookDtoList
     * @return JsonResponse<Boolean>
     */
    @PostMapping
    @Validated(OnCreate.class)
    public JsonResponse<Boolean> createBooks(@RequestBody @NotEmpty List<@Valid BookDto> bookDtoList) {
        return new JsonResponse<>(bookService.createBooks(bookDtoList));
    }

    /**
     * Find Books by conditions
     *
     * @param bookTitle
     * @param authorName
     * @return JsonResponse<List<BookDto>>
     */
    @GetMapping
    public JsonResponse<List<BookDto>> findBooks(
            @RequestParam(value = "bookTitle", required = false, defaultValue = "") String bookTitle,
            @RequestParam(value = "authorName", required = false, defaultValue = "") String authorName) {
        return new JsonResponse<>(bookService.findBooks(bookTitle, authorName));
    }

    /**
     * Update Books
     *
     * @param fromBookDtoList
     * @return JsonResponse<Boolean>
     */
    @PutMapping
    @Validated(OnUpdate.class)
    public JsonResponse<Boolean> updateBooks(@RequestBody @NotEmpty List<@Valid BookDto> fromBookDtoList) {
        return new JsonResponse<>(bookService.updateBooks(fromBookDtoList));
    }

    /**
     * Delete Books
     *
     * @param authorIdList
     * @return true if remove success
     */
    @DeleteMapping
    public JsonResponse<Boolean> deleteBooks(@RequestBody @NotEmpty List<@NotNull Integer> authorIdList) {
        return new JsonResponse<>(bookService.deleteBooks(authorIdList));
    }

}
