package jp.co.sb.bookstore.exception.constant;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author andy - tuantda.uit@gmail.com
 */
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public enum MessageCode {

    COMMON_INFO_001("common.info.001", "Parameter is invalid: %s"),
    COMMON_INFO_002("common.info.002", "Data already exists."),
    COMMON_INFO_003("common.info.003", "Data does not exist."),
    COMMON_INFO_200("common.info.200", "Success"),

    COMMON_ERROR_001("common.error.001", "Can not create data."),
    COMMON_ERROR_002("common.error.002", "Can not update data."),
    COMMON_ERROR_003("common.error.003", "Can not delete data."),
    COMMON_ERROR_011("common.error.011", "There is no data to create."),
    COMMON_ERROR_012("common.error.012", "There is no data to update."),
    COMMON_ERROR_013("common.error.013", "There is no data to delete."),
    COMMON_ERROR_014("common.error.014", "Cannot delete it because the related constraint exists."),

    COMMON_ERROR_400("common.error.400", "Bad request"),
    COMMON_ERROR_401("common.error.401", "UnAuthorized"),
    COMMON_ERROR_404("common.error.404", "The requested URL does not exist. Please check your URL and try again."),

    COMMON_ERROR_500("common.error.500", "Internal Server Error");

    private String code;
    private String msg;

    MessageCode(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
