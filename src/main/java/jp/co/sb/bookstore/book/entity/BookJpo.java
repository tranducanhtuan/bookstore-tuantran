package jp.co.sb.bookstore.book.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import jp.co.sb.bookstore.author.entity.AuthorJpo;
import jp.co.sb.bookstore.base.domain.model.BaseJpo;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author andy - tuantda.uit@gmail.com
 */
@Data
@NoArgsConstructor
@Entity
@Table(name = "book")
public class BookJpo extends BaseJpo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "book_id", unique = true, nullable = false, updatable = false)
    private Integer bookId;

    @Column(name = "book_title", length = 100, nullable = false)
    private String bookTitle;

    @ManyToOne
    @JoinColumn(name = "author_id", referencedColumnName = "author_id")
    private AuthorJpo authorJpo = new AuthorJpo();

}
