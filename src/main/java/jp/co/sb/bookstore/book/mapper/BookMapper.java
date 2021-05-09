package jp.co.sb.bookstore.book.mapper;

import java.util.List;
import java.util.stream.Collectors;
import jp.co.sb.bookstore.book.dto.BookDto;
import jp.co.sb.bookstore.book.entity.BookJpo;
import org.springframework.beans.BeanUtils;

/**
 * @author andy - tuantda.uit@gmail.com
 */
public class BookMapper {

    /**
     * convert To Jpo List
     *
     * @param bookDtoList
     * @return Jpo List
     */
    public static List<BookJpo> toJpoList(List<BookDto> bookDtoList) {
        return bookDtoList
                .stream()
                .map(bookDto -> {
                    BookJpo bookJpo = new BookJpo();
                    BeanUtils.copyProperties(bookDto, bookJpo);
                    BeanUtils.copyProperties(bookDto, bookJpo.getAuthorJpo());
                    return bookJpo;
                })
                .collect(Collectors.toList());
    }

    /**
     * convert To Dto List
     *
     * @param bookJpoList
     * @return Dto List
     */
    public static List<BookDto> toDtoList(List<BookJpo> bookJpoList) {
        return bookJpoList
                .stream()
                .map(jpo -> {
                    BookDto dto = new BookDto();
                    BeanUtils.copyProperties(jpo, dto);
                    BeanUtils.copyProperties(jpo.getAuthorJpo(), dto);
                    return dto;
                })
                .collect(Collectors.toList());
    }

}
