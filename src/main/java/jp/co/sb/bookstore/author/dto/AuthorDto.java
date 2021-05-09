package jp.co.sb.bookstore.author.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;
import jp.co.sb.bookstore.base.validate.OnCreate;
import jp.co.sb.bookstore.base.validate.OnUpdate;
import lombok.Data;

/**
 * @author andy - tuantda.uit@gmail.com
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class AuthorDto {

    @NotNull(groups = OnUpdate.class)
    private Integer authorId;

    @NotBlank(groups = {OnCreate.class, OnUpdate.class})
    @Size(max = 100)
    private String authorName;

}
