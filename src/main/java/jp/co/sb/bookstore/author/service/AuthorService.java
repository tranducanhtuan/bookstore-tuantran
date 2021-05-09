package jp.co.sb.bookstore.author.service;

import jp.co.sb.bookstore.author.dto.AuthorDto;

import java.util.List;

/**
 * @author andy - tuantda.uit@gmail.com
 */
public interface AuthorService {

    /**
     * Create Authors
     *
     * @param authorDtoList
     * @return true if create success
     */
    boolean createAuthors(List<AuthorDto> authorDtoList);

    /**
     * Find Authors by conditions
     *
     * @param authorName
     * @return List<AuthorDto>
     */
    List<AuthorDto> findAuthors(String authorName);

    /**
     * Update Authors
     *
     * @param fromAuthorDtoList
     * @return true if update success
     */
    boolean updateAuthors(List<AuthorDto> fromAuthorDtoList);

    /**
     * Delete Authors
     *
     * @param authorIdList
     * @return true if remove success
     */
    boolean deleteAuthors(List<Integer> authorIdList);

}
