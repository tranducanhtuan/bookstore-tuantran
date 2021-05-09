package jp.co.sb.bookstore.base.dto;

import java.io.Serializable;
import jp.co.sb.bookstore.exception.constant.MessageCode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.aspectj.bridge.Message;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;

/**
 * @author andy - tuantda.uit@gmail.com
 */
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class JsonResponse<T> implements Serializable {

    private String status;
    private String message;

    private T data;

    private String code;

    public JsonResponse(T data) {
        this.status = String.valueOf(HttpStatus.OK.value()); // 200
        this.message = MessageCode.COMMON_INFO_200.getMsg();
        this.code = MessageCode.COMMON_INFO_200.getCode();
        this.data = data;
    }

}
