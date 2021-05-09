package jp.co.sb.bookstore.author.service;

import jp.co.sb.bookstore.author.repository.AuthorRepository;
import jp.co.sb.bookstore.author.service.impl.AuthorServiceImpl;
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
public class AuthorServiceImplTest {

    @InjectMocks
    private AuthorServiceImpl authorServiceImpl;

    @Mock
    private AuthorRepository authorRepository;

    @Before
    public void setMockOutput() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testCreateAuthors_failed() {
        // TODO i will write unit tests later since not having much time
    }

}