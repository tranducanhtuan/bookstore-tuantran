package jp.co.sb.bookstore.author.service.impl;

import java.util.Map;
import jp.co.sb.bookstore.author.dto.AuthorDto;
import jp.co.sb.bookstore.author.entity.AuthorJpo;
import jp.co.sb.bookstore.author.mapper.AuthorMapper;
import jp.co.sb.bookstore.author.service.AuthorService;
import jp.co.sb.bookstore.author.repository.AuthorRepository;
import jp.co.sb.bookstore.book.repository.BookRepository;
import jp.co.sb.bookstore.exception.constant.MessageCode;
import jp.co.sb.bookstore.exception.dto.BsException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

/**
 * @author andy - tuantda.uit@gmail.com
 */
@Service
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private BookRepository bookRepository;

    /**
     * Create Authors
     *
     * @param authorDtoList
     * @return true if success
     */
    @Override
    @Transactional
    public boolean createAuthors(List<AuthorDto> authorDtoList) {
        List<String> nameList = authorDtoList.stream().map(AuthorDto::getAuthorName).collect(Collectors.toList());
        if (authorRepository.existsByAuthorNameIn(nameList)) {
            throw new BsException(MessageCode.COMMON_INFO_002);
        }

        List<AuthorJpo> resultList = authorRepository.saveAll(AuthorMapper.toJpoList(authorDtoList));
        if (resultList.size() < authorDtoList.size()) {
            throw new BsException(MessageCode.COMMON_ERROR_011);
        }

        return true;
    }

    /**
     * Find Authors
     *
     * @param authorName
     * @return List<BookDto>
     */
    public List<AuthorDto> findAuthors(String authorName) {
        return AuthorMapper.toDtoList(authorRepository.findByAuthorNameContainingIgnoreCase(authorName));
    }

    /**
     * Update Authors
     *
     * @param fromAuthorDtoList
     * @return true if update success
     */
    @Override
    @Transactional
    public boolean updateAuthors(List<AuthorDto> fromAuthorDtoList) {
        List<Integer> fromAuthorIdList = fromAuthorDtoList.stream().map(AuthorDto::getAuthorId).collect(Collectors.toList());
        List<AuthorJpo> toAuthorJpoList = authorRepository.findByAuthorIdIn(fromAuthorIdList);

        if (CollectionUtils.isEmpty(toAuthorJpoList) || toAuthorJpoList.size() < fromAuthorDtoList.size()) {
            throw new BsException(MessageCode.COMMON_INFO_003);
        }

        prepareUpdateData(fromAuthorDtoList, toAuthorJpoList);

        List<AuthorJpo> resultList = authorRepository.saveAll(toAuthorJpoList);
        if (resultList.size() < fromAuthorDtoList.size()) {
            throw new BsException(MessageCode.COMMON_ERROR_012);
        }

        return true;
    }

    /**
     * Delete Authors
     *
     * @param authorIdList
     * @return true if remove success
     */
    @Override
    @Transactional
    public boolean deleteAuthors(List<Integer> authorIdList){
        if (authorRepository.countByAuthorIdIn(authorIdList) != authorIdList.size()) {
            throw new BsException(MessageCode.COMMON_INFO_003);
        }

        if (bookRepository.existsByAuthorJpo_AuthorIdIn(authorIdList)) {
            throw new BsException(MessageCode.COMMON_ERROR_014);
        }

        authorRepository.deleteAllByAuthorIdIn(authorIdList);
        return true;
    }

    /**
     * Prepare data for updating Authors
     *
     * @param sourceList
     * @param targetList
     */
    private void prepareUpdateData(List<AuthorDto> sourceList, List<AuthorJpo> targetList) {
        Map<Integer, AuthorDto> fromAuthorMap = sourceList.stream().collect(
                Collectors.toMap(
                        AuthorDto::getAuthorId
                        , authorDto -> authorDto
                        , (a, b) -> b
                )
        );

        targetList.stream().forEach(toAuthorJpo -> {
            AuthorDto apiTemp = fromAuthorMap.get(toAuthorJpo.getAuthorId());
            BeanUtils.copyProperties(apiTemp, toAuthorJpo);
        });
    }

}
