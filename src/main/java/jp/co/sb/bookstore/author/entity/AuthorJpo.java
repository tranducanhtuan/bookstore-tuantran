package jp.co.sb.bookstore.author.entity;

import javax.persistence.*;
import jp.co.sb.bookstore.base.domain.model.BaseJpo;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author andy - tuantda.uit@gmail.com
 */
@Data
@NoArgsConstructor
@Entity
@Table(name = "author")
public class AuthorJpo extends BaseJpo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "author_id", unique = true, nullable = false, updatable = false)
    private Integer authorId;

    @Column(name = "author_name", length = 100, nullable = false)
    private String authorName;

}
