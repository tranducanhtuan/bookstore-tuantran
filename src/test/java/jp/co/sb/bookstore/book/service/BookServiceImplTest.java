package jp.co.sb.bookstore.book.service;

import jp.co.sb.bookstore.book.repository.BookRepository;
import jp.co.sb.bookstore.book.service.impl.BookServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

/**
 * @author andy - tuantda.uit@gmail.com
 */
//@RunWith(PowerMockRunner.class)
//@PrepareForTest({})
@RunWith(MockitoJUnitRunner.class)
public class BookServiceImplTest {

    @InjectMocks
    private BookServiceImpl bookServiceImpl;

    @Mock
    private BookRepository bookRepository;

    @Before
    public void setMockOutput() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreateBooks_failed() {
        // TODO i will write unit tests later since not having much time
    }

}