package jp.co.sb.bookstore.book.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;
import jp.co.sb.bookstore.author.dto.AuthorDto;
import jp.co.sb.bookstore.author.entity.AuthorJpo;
import jp.co.sb.bookstore.base.validate.OnCreate;
import jp.co.sb.bookstore.base.validate.OnUpdate;
import lombok.Data;

/**
 * @author andy - tuantda.uit@gmail.com
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class BookDto {

    @NotNull(groups = OnUpdate.class)
    private Integer bookId;

    @NotBlank(groups = {OnCreate.class, OnUpdate.class})
    @Size(max = 100)
    private String bookTitle;

    @NotNull(groups = {OnCreate.class, OnUpdate.class})
    private Integer authorId;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String authorName;

}
