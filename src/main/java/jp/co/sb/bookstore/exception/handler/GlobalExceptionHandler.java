package jp.co.sb.bookstore.exception.handler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import jp.co.sb.bookstore.base.dto.JsonResponse;
import jp.co.sb.bookstore.exception.constant.MessageCode;
import jp.co.sb.bookstore.exception.constant.MessageInfo;
import jp.co.sb.bookstore.exception.dto.BsException;
import org.hibernate.validator.internal.engine.path.PathImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * @author andy - tuantda.uit@gmail.com
 */
@EnableWebMvc
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    /** The log */
    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    /** The HTTP headers */
    private static final HttpHeaders headers = new HttpHeaders();

    /**
     * The logic to catch all service invalid arguments exception when validating
     *
     * @param request
     * @param ex
     * @return ResponseEntity
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity handleInvalidArgumentsException(WebRequest request, ConstraintViolationException ex) {
        String fieldName = getFirstViolationFieldName(ex);

        BsException kmsException = new BsException(MessageCode.COMMON_INFO_001, fieldName);
        JsonResponse<Object> response = getJsonResponseWithNoData(kmsException.getMessageInfo());

        this.printLog(request, ex);
        return super.handleExceptionInternal(kmsException, response, headers, HttpStatus.OK, request);
    }

    /**
     * Get first violation field name
     *
     * @param ex
     * @return one violation fieldName
     */
    private String getFirstViolationFieldName(ConstraintViolationException ex) {
        Set<ConstraintViolation<?>> violationSet = ex.getConstraintViolations();
        List<String> fieldNameList = new ArrayList<>();

        for (ConstraintViolation<?> violation: violationSet) {
            PathImpl pathImpl = (PathImpl) violation.getPropertyPath();
            String fieldName = pathImpl.getLeafNode().getName();
            fieldNameList.add(fieldName);
        }

        Collections.sort(fieldNameList);
        return fieldNameList.get(0);
    }

    /**
     * The logic to catch all service error
     *
     * @param request
     * @param ex
     * @return ResponseEntity
     */
    @ExceptionHandler(BsException.class)
    public ResponseEntity<Object> serviceRuntimeException(WebRequest request, BsException ex) {
        JsonResponse<Object> response = getJsonResponseWithNoData(ex.getMessageInfo());

        this.printLog(request, ex);
        return super.handleExceptionInternal(ex, response, headers, HttpStatus.OK, request);
    }

    /**
     * The logic to catch all internal error
     *
     * @param request
     * @param ex
     * @return ResponseEntity
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> internalServerError(WebRequest request, Exception ex) {
        BsException kmsException = new BsException(MessageCode.COMMON_ERROR_500);
        JsonResponse<Object> response = getJsonResponseWithNoData(kmsException.getMessageInfo());

        this.printLog(request, ex);
        return handleExceptionInternal(kmsException, response, headers, HttpStatus.OK, request);
    }

    /**
     * The logic to catch all no handler error
     *
     * @param ex
     * @param headers
     * @param status
     * @param request
     * @return ResponseEntity
     */
    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body,
            HttpHeaders headers, HttpStatus status, WebRequest request) {

        if (status == HttpStatus.BAD_REQUEST) {
            BsException kmsException = new BsException(MessageCode.COMMON_ERROR_400);
            JsonResponse<Object> resp = getJsonResponseWithNoData(kmsException.getMessageInfo());
            resp.setStatus(String.valueOf(HttpStatus.BAD_REQUEST.value()));
            body = resp;
            status = HttpStatus.OK;
        }

        else if (status == HttpStatus.NOT_FOUND) {
            BsException kmsException = new BsException(MessageCode.COMMON_ERROR_404);
            JsonResponse<Object> resp = getJsonResponseWithNoData(kmsException.getMessageInfo());
            resp.setStatus(String.valueOf(HttpStatus.NOT_FOUND.value()));
            body = resp;
            status = HttpStatus.OK;
        }

        this.printLog(request, ex);

        return super.handleExceptionInternal(ex, body, headers, status, request);
    }

    /**
     * Print the error log
     *
     * @param request
     * @param e
     */
    private void printLog(WebRequest request, Exception e) {
        log.error("GlobalExceptionHandler error :: msg -> {} :: class -> {}", request.getContextPath(), e.getMessage(), e.getClass(), e);
    }

    /**
     * Get the JsonResponse when occur the error
     *
     * @param messageInfo
     * @return The JsonResponse Object
     */
    private JsonResponse<Object> getJsonResponseWithNoData(MessageInfo messageInfo) {
        JsonResponse<Object> response = new JsonResponse<>();
        response.setCode(messageInfo.getCode());
        response.setMessage(messageInfo.getMessage());
        return response;
    }
}