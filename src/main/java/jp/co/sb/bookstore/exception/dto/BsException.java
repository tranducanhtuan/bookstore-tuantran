package jp.co.sb.bookstore.exception.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.Objects;
import jp.co.sb.bookstore.exception.constant.MessageCode;
import jp.co.sb.bookstore.exception.constant.MessageInfo;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author andy - tuantda.uit@gmail.com
 */
@JsonIgnoreProperties({"cause", "stackTrace"})
@Getter
public class BsException extends RuntimeException {

    /**
     * The logger
     */
    private static final Logger log = LoggerFactory.getLogger(BsException.class);
    protected String status; // HTTP status
    protected Object[] parameters;
    protected MessageInfo messageInfo;

    /**
     * The constructor
     */
    public BsException(MessageCode messageCode) {
        this(messageCode, null);
    }

    /**
     * The constructor
     *
     * @param parameters
     */
    public BsException(MessageCode messageCode, Object... parameters) {
        this(null, messageCode, parameters);
    }

    /**
     * The constructor
     *
     * @param cause
     * @param parameters
     */
    protected BsException(Throwable cause, MessageCode messageCode, Object... parameters) {
        super(messageCode.getCode(), cause);

        this.status = messageCode.getCode();
        this.parameters = parameters;
        this.messageInfo = buildMessageInfo(messageCode, parameters);
    }

    /**
     * Return the message information that get from MessageSource
     *
     * @return The content of message
     */
    protected MessageInfo buildMessageInfo(MessageCode messageCode, Object... parameters) {
        MessageInfo info = new MessageInfo();
        try {
            info.setCode(messageCode.getCode());
            info.setMessage(String.format(messageCode.getMsg(), parameters));
        } catch (Exception e) {
            log.error(e.getMessage());
        }

        return info;
    }

    /**
     * Returns the detail message string of this throwable.
     *
     * @return the detail message string of this {@code Throwable} instance (which may be {@code
     * null}).
     */
    @Override
    public String getMessage() {
        if (Objects.isNull(messageInfo)) {
            return super.getMessage();
        }
        return messageInfo.getMessage();
    }
}
