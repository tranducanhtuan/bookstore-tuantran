package jp.co.sb.bookstore.author.controller;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import jp.co.sb.bookstore.author.dto.AuthorDto;
import jp.co.sb.bookstore.author.service.AuthorService;
import jp.co.sb.bookstore.base.dto.JsonResponse;
import jp.co.sb.bookstore.base.validate.OnCreate;
import jp.co.sb.bookstore.base.validate.OnUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author andy - tuantda.uit@gmail.com
 */
@RestController
@RequestMapping("/author")
@Validated
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    /**
     * Create Authors
     *
     * @param authorDtoList
     * @return JsonResponse<Boolean>
     */
    @PostMapping
    @Validated(OnCreate.class)
    public JsonResponse<Boolean> createAuthors(@RequestBody @NotEmpty List<@Valid AuthorDto> authorDtoList) {
        return new JsonResponse<>(authorService.createAuthors(authorDtoList));
    }

    /**
     * Find Authors by conditions
     *
     * @param authorName
     * @return JsonResponse<List<AuthorDto>>
     */
    @GetMapping
    public JsonResponse<List<AuthorDto>> findAuthors(
            @RequestParam(value = "authorName", required = false, defaultValue = "") String authorName) {
        return new JsonResponse<>(authorService.findAuthors(authorName));
    }

    /**
     * Update Authors
     *
     * @param fromAuthorDtoList
     * @return JsonResponse<Boolean>
     */
    @PutMapping
    @Validated(OnUpdate.class)
    public JsonResponse<Boolean> updateAuthors(@RequestBody @NotEmpty List<@Valid AuthorDto> fromAuthorDtoList) {
        return new JsonResponse<>(authorService.updateAuthors(fromAuthorDtoList));
    }

    /**
     * Delete Authors
     *
     * @param authorIdList
     * @return true if remove success
     */
    @DeleteMapping
    public JsonResponse<Boolean> deleteAuthors(@RequestBody @NotEmpty List<@NotNull Integer> authorIdList) {
        return new JsonResponse<>(authorService.deleteAuthors(authorIdList));
    }

}
