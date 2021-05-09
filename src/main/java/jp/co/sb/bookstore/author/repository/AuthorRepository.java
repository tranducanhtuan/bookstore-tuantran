package jp.co.sb.bookstore.author.repository;

import java.util.Set;
import jp.co.sb.bookstore.author.entity.AuthorJpo;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author andy - tuantda.uit@gmail.com
 */
@Repository
public interface AuthorRepository extends JpaRepository<AuthorJpo, Integer> {

    List<AuthorJpo> findByAuthorNameContainingIgnoreCase(String name);
    List<AuthorJpo> findByAuthorIdIn(List<Integer> authorIdList);
    long countByAuthorIdIn(List<Integer> authorIdList);
    long countByAuthorIdIn(Set<Integer> authorIdList);
    boolean existsByAuthorNameIn(List<String> nameList);
    void deleteAllByAuthorIdIn(List<Integer> authorIdList);

}
