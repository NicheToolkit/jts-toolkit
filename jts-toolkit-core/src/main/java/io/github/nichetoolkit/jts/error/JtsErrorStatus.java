package io.github.nichetoolkit.jts.error;

import io.github.nichetoolkit.rest.RestStatus;
import lombok.Getter;

/**
 * <code>JtsErrorStatus</code>
 * <p>The jts error status enumeration.</p>
 * @see  io.github.nichetoolkit.rest.RestStatus
 * @see  lombok.Getter
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
@Getter
public enum JtsErrorStatus implements RestStatus {

    /**
     * <code>JTS_ERROR</code>
     * {@link io.github.nichetoolkit.jts.error.JtsErrorStatus} <p>The <code>JTS_ERROR</code> field.</p>
     */
    JTS_ERROR(10700, "it has encountered a error"),
    /**
     * <code>JTS_PARSE_ERROR</code>
     * {@link io.github.nichetoolkit.jts.error.JtsErrorStatus} <p>The <code>JTS_PARSE_ERROR</code> field.</p>
     */
    JTS_PARSE_ERROR(10701, "it has encountered a data parsing error"),
    /**
     * <code>JTS_BOX_INVALID_ERROR</code>
     * {@link io.github.nichetoolkit.jts.error.JtsErrorStatus} <p>The <code>JTS_BOX_INVALID_ERROR</code> field.</p>
     */
    JTS_BOX_INVALID_ERROR(10702, "the rang of box is invalid"),

    /**
     * <code>SHAPE_PARAMS_ERROR</code>
     * {@link io.github.nichetoolkit.jts.error.JtsErrorStatus} <p>The <code>SHAPE_PARAMS_ERROR</code> field.</p>
     */
    SHAPE_PARAMS_ERROR(10711, "it has encountered a param error"),
    /**
     * <code>SHAPE_DATA_STORE_ERROR</code>
     * {@link io.github.nichetoolkit.jts.error.JtsErrorStatus} <p>The <code>SHAPE_DATA_STORE_ERROR</code> field.</p>
     */
    SHAPE_DATA_STORE_ERROR(10712, "it has encountered a data store error"),
    /**
     * <code>SHAPE_FEATURES_ERROR</code>
     * {@link io.github.nichetoolkit.jts.error.JtsErrorStatus} <p>The <code>SHAPE_FEATURES_ERROR</code> field.</p>
     */
    SHAPE_FEATURES_ERROR(10713, "it has encountered a features error"),
    /**
     * <code>SHAPE_PARAMS_UNINITIALIZED_ERROR</code>
     * {@link io.github.nichetoolkit.jts.error.JtsErrorStatus} <p>The <code>SHAPE_PARAMS_UNINITIALIZED_ERROR</code> field.</p>
     */
    SHAPE_PARAMS_UNINITIALIZED_ERROR(10714, "it has encountered a params uninitialized error"),
    /**
     * <code>SHAPE_DATA_STORE_UNINITIALIZED_ERROR</code>
     * {@link io.github.nichetoolkit.jts.error.JtsErrorStatus} <p>The <code>SHAPE_DATA_STORE_UNINITIALIZED_ERROR</code> field.</p>
     */
    SHAPE_DATA_STORE_UNINITIALIZED_ERROR(10715, "it has encountered a data store uninitialized error"),
    /**
     * <code>SHAPE_FEATURES_UNINITIALIZED_ERROR</code>
     * {@link io.github.nichetoolkit.jts.error.JtsErrorStatus} <p>The <code>SHAPE_FEATURES_UNINITIALIZED_ERROR</code> field.</p>
     */
    SHAPE_FEATURES_UNINITIALIZED_ERROR(10716, "it has encountered a features uninitialized error"),
    /**
     * <code>SHAPE_READER_UNINITIALIZED_ERROR</code>
     * {@link io.github.nichetoolkit.jts.error.JtsErrorStatus} <p>The <code>SHAPE_READER_UNINITIALIZED_ERROR</code> field.</p>
     */
    SHAPE_READER_UNINITIALIZED_ERROR(10717, "it has encountered a reader uninitialized error"),
    /**
     * <code>SHAPE_WRITER_UNINITIALIZED_ERROR</code>
     * {@link io.github.nichetoolkit.jts.error.JtsErrorStatus} <p>The <code>SHAPE_WRITER_UNINITIALIZED_ERROR</code> field.</p>
     */
    SHAPE_WRITER_UNINITIALIZED_ERROR(10718, "it has encountered a writer uninitialized error"),

    /**
     * <code>SHAPE_READER_PARAMS_ERROR</code>
     * {@link io.github.nichetoolkit.jts.error.JtsErrorStatus} <p>The <code>SHAPE_READER_PARAMS_ERROR</code> field.</p>
     */
    SHAPE_READER_PARAMS_ERROR(10719, "it has encountered a params error of shape file reader"),
    /**
     * <code>SHAPE_READER_DATA_STORE_ERROR</code>
     * {@link io.github.nichetoolkit.jts.error.JtsErrorStatus} <p>The <code>SHAPE_READER_DATA_STORE_ERROR</code> field.</p>
     */
    SHAPE_READER_DATA_STORE_ERROR(10720, "it has encountered a data store error of shape file reader"),
    /**
     * <code>SHAPE_READER_FEATURES_ERROR</code>
     * {@link io.github.nichetoolkit.jts.error.JtsErrorStatus} <p>The <code>SHAPE_READER_FEATURES_ERROR</code> field.</p>
     */
    SHAPE_READER_FEATURES_ERROR(10721, "it has encountered a features error of shape file reader"),

    /**
     * <code>SHAPE_WRITER_PARAMS_ERROR</code>
     * {@link io.github.nichetoolkit.jts.error.JtsErrorStatus} <p>The <code>SHAPE_WRITER_PARAMS_ERROR</code> field.</p>
     */
    SHAPE_WRITER_PARAMS_ERROR(10722, "it has encountered a params error of shape file writer"),
    /**
     * <code>SHAPE_WRITER_DATA_STORE_ERROR</code>
     * {@link io.github.nichetoolkit.jts.error.JtsErrorStatus} <p>The <code>SHAPE_WRITER_DATA_STORE_ERROR</code> field.</p>
     */
    SHAPE_WRITER_DATA_STORE_ERROR(10723, "it has encountered a data store error of shape file writer"),
    /**
     * <code>SHAPE_WRITER_FEATURES_ERROR</code>
     * {@link io.github.nichetoolkit.jts.error.JtsErrorStatus} <p>The <code>SHAPE_WRITER_FEATURES_ERROR</code> field.</p>
     */
    SHAPE_WRITER_FEATURES_ERROR(10724, "it has encountered a features error of shape file writer"),


    ;

    /**
     * <code>status</code>
     * {@link java.lang.Integer} <p>The <code>status</code> field.</p>
     * @see  java.lang.Integer
     */
    private final Integer status;
    /**
     * <code>message</code>
     * {@link java.lang.String} <p>The <code>message</code> field.</p>
     * @see  java.lang.String
     */
    private final String message;

    /**
     * <code>JtsErrorStatus</code>
     * <p>Instantiates a new jts error status.</p>
     * @param status {@link java.lang.Integer} <p>The status parameter is <code>Integer</code> type.</p>
     * @param message {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @see  java.lang.Integer
     * @see  java.lang.String
     */
    JtsErrorStatus(Integer status, String message) {
        this.status = status;
        this.message = message;
    }
}
