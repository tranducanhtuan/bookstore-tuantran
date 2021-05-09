package jp.co.sb.bookstore.author.mapper;

import java.util.List;
import java.util.stream.Collectors;
import jp.co.sb.bookstore.author.dto.AuthorDto;
import jp.co.sb.bookstore.author.entity.AuthorJpo;
import org.springframework.beans.BeanUtils;

/**
 * @author andy - tuantda.uit@gmail.com
 */
public class AuthorMapper {

    /**
     * convert To Jpo List
     *
     * @param authorDtoList
     * @return Jpo List
     */
    public static List<AuthorJpo> toJpoList(List<AuthorDto> authorDtoList) {
        return authorDtoList
                .stream()
                .map(dto -> {
                    AuthorJpo jpo = new AuthorJpo();
                    BeanUtils.copyProperties(dto, jpo);
                    return jpo;
                })
                .collect(Collectors.toList());
    }

    /**
     * convert To Dto List
     *
     * @param authorJpoList
     * @return Dto List
     */
    public static List<AuthorDto> toDtoList(List<AuthorJpo> authorJpoList) {
        return authorJpoList
                .stream()
                .map(jpo -> {
                    AuthorDto dto = new AuthorDto();
                    BeanUtils.copyProperties(jpo, dto);
                    return dto;
                })
                .collect(Collectors.toList());
    }

}
