package jp.co.sb.bookstore.book.service.impl;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import jp.co.sb.bookstore.author.entity.AuthorJpo;
import jp.co.sb.bookstore.author.repository.AuthorRepository;
import jp.co.sb.bookstore.book.dto.BookDto;
import jp.co.sb.bookstore.book.entity.BookJpo;
import jp.co.sb.bookstore.book.mapper.BookMapper;
import jp.co.sb.bookstore.book.repository.BookRepository;
import jp.co.sb.bookstore.book.service.BookService;
import jp.co.sb.bookstore.exception.constant.MessageCode;
import jp.co.sb.bookstore.exception.dto.BsException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

/**
 * @author andy - tuantda.uit@gmail.com
 */
@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    /**
     * Create Books
     *
     * @param bookDtoList
     * @return true if success
     */
    @Override
    @Transactional
    public boolean createBooks(List<BookDto> bookDtoList) {
        List<String> bookTitleList = bookDtoList.stream().map(BookDto::getBookTitle).collect(Collectors.toList());
        if (bookRepository.existsByBookTitleIn(bookTitleList)) {
            throw new BsException(MessageCode.COMMON_INFO_002);
        }

        if (!isValidAuthorIdList(bookDtoList)) {
            throw new BsException(MessageCode.COMMON_INFO_003);
        }

        List<BookJpo> resultList = bookRepository.saveAll(BookMapper.toJpoList(bookDtoList));
        if (resultList.size() < bookDtoList.size()) {
            throw new BsException(MessageCode.COMMON_ERROR_011);
        }

        return true;
    }

    /**
     * Find Books
     *
     * @param bookTitle
     * @param authorName
     * @return List<BookDto>
     */
    public List<BookDto> findBooks(String bookTitle, String authorName) {
        return BookMapper.toDtoList(bookRepository.findByBookTitleContainingIgnoreCaseAndAuthorJpo_AuthorNameContainingIgnoreCase(bookTitle, authorName));
    }

    /**
     * Update Books
     *
     * @param fromBookDtoList
     * @return true if update success
     */
    @Override
    @Transactional
    public boolean updateBooks(List<BookDto> fromBookDtoList) {
        List<Integer> fromBookIdList = fromBookDtoList.stream().map(BookDto::getBookId).collect(Collectors.toList());
        List<BookJpo> toBookJpoList = bookRepository.findByBookIdIn(fromBookIdList);

        if (CollectionUtils.isEmpty(toBookJpoList) || toBookJpoList.size() < fromBookDtoList.size()) {
            throw new BsException(MessageCode.COMMON_INFO_003);
        }

        if (!isValidAuthorIdList(fromBookDtoList)) {
            throw new BsException(MessageCode.COMMON_INFO_003);
        }

        prepareUpdateData(fromBookDtoList, toBookJpoList);

        List<BookJpo> resultList = bookRepository.saveAll(toBookJpoList);
        if (resultList.size() < fromBookDtoList.size()) {
            throw new BsException(MessageCode.COMMON_ERROR_012);
        }

        return true;
    }

    /**
     * Delete Books
     *
     * @param bookIdList
     * @return true if remove success
     */
    @Override
    @Transactional
    public boolean deleteBooks(List<Integer> bookIdList){

        if (bookRepository.countByBookIdIn(bookIdList) != bookIdList.size()) {
            throw new BsException(MessageCode.COMMON_INFO_003);
        }

        bookRepository.deleteAllByBookIdIn(bookIdList);
        return true;
    }

    private boolean isValidAuthorIdList(List<BookDto> bookDtoList) {
        Set<Integer> authorIdSet = bookDtoList.stream().map(BookDto::getAuthorId).collect(Collectors.toSet());
        long count = authorRepository.countByAuthorIdIn(authorIdSet);
        return count == authorIdSet.size();
    }

    /**
     * Prepare data for updating Books
     *
     * @param sourceList
     * @param targetList
     */
    private void prepareUpdateData(List<BookDto> sourceList, List<BookJpo> targetList) {
        Map<Integer, BookDto> fromBookDtoMap = sourceList.stream().collect(
                Collectors.toMap(
                        BookDto::getBookId
                        , bookDto -> bookDto
                        , (a, b) -> b
                )
        );

        targetList.stream().forEach(toBookJpo -> {
            BookDto bookDto = fromBookDtoMap.get(toBookJpo.getBookId());
            BeanUtils.copyProperties(bookDto, toBookJpo);

            if (!bookDto.getAuthorId().equals(toBookJpo.getAuthorJpo().getAuthorId())) {
                Optional<AuthorJpo> newAuthorJpoOpt = authorRepository.findById(bookDto.getAuthorId());
                if (newAuthorJpoOpt.isPresent()) {
                    toBookJpo.setAuthorJpo(newAuthorJpoOpt.get());
                }
            }
        });
    }

}
