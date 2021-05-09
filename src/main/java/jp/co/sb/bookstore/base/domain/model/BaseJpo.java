package jp.co.sb.bookstore.base.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import jp.co.sb.bookstore.base.constant.TemporaryConstant;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@MappedSuperclass
public abstract class BaseJpo<T> implements Serializable {

    @JsonIgnore
    @Column(name = "created_by", nullable = false, updatable = false, length = 100)
    protected String createdBy;

    @JsonIgnore
    @Column(name = "created_dt", nullable = false, updatable = false)
    protected Timestamp createdDate;

    @JsonIgnore
    @Column(name = "updated_by", length = 100, insertable = false)
    protected String updatedBy;

    @JsonIgnore
    @Column(name = "updated_dt", insertable = false)
    protected Timestamp updatedDate;

    /**
     * The handler for INSERT event
     */
    @PrePersist
    public void onPrePersist() {
        Timestamp now = new Timestamp(System.currentTimeMillis());
        setCreatedBy(TemporaryConstant.DEFAULT_USER_NAME);
        setCreatedDate(now);
    }

    /**
     * The handler for UPDATE event
     */
    @PreUpdate
    public void onPreUpdate() {
        setUpdatedBy(TemporaryConstant.DEFAULT_USER_NAME);
        setUpdatedDate(new Timestamp(System.currentTimeMillis()));
    }

}
