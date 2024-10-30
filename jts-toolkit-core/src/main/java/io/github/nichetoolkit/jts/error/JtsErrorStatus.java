package io.github.nichetoolkit.jts.error;

import io.github.nichetoolkit.rest.RestStatus;
import lombok.Getter;

/**
 * <p>JtsErrorStatus</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
@Getter
public enum JtsErrorStatus implements RestStatus {

    /** jts */
    JTS_ERROR(10700, "it has encountered a error"),
    JTS_PARSE_ERROR(10701, "it has encountered a data parsing error"),
    JTS_BOX_INVALID_ERROR(10702, "the rang of box is invalid"),

    /** shape */
    SHAPE_PARAMS_ERROR(10711, "it has encountered a param error"),
    SHAPE_DATA_STORE_ERROR(10712, "it has encountered a data store error"),
    SHAPE_FEATURES_ERROR(10713, "it has encountered a features error"),
    SHAPE_PARAMS_UNINITIALIZED_ERROR(10714, "it has encountered a params uninitialized error"),
    SHAPE_DATA_STORE_UNINITIALIZED_ERROR(10715, "it has encountered a data store uninitialized error"),
    SHAPE_FEATURES_UNINITIALIZED_ERROR(10716, "it has encountered a features uninitialized error"),
    SHAPE_READER_UNINITIALIZED_ERROR(10717, "it has encountered a reader uninitialized error"),
    SHAPE_WRITER_UNINITIALIZED_ERROR(10718, "it has encountered a writer uninitialized error"),

    SHAPE_READER_PARAMS_ERROR(10719, "it has encountered a params error of shape file reader"),
    SHAPE_READER_DATA_STORE_ERROR(10720, "it has encountered a data store error of shape file reader"),
    SHAPE_READER_FEATURES_ERROR(10721, "it has encountered a features error of shape file reader"),

    SHAPE_WRITER_PARAMS_ERROR(10722, "it has encountered a params error of shape file writer"),
    SHAPE_WRITER_DATA_STORE_ERROR(10723, "it has encountered a data store error of shape file writer"),
    SHAPE_WRITER_FEATURES_ERROR(10724, "it has encountered a features error of shape file writer"),


    ;

    private final Integer status;
    private final String message;

    JtsErrorStatus(Integer status, String message) {
        this.status = status;
        this.message = message;
    }
}
