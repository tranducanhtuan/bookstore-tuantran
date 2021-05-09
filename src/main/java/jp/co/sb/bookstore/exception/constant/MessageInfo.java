package jp.co.sb.bookstore.exception.constant;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author andy - tuantda.uit@gmail.com
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MessageInfo implements Serializable {

    private String code;
    private String message;

}