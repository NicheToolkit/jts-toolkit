package io.github.nichetoolkit.jts.error;

import io.github.nichetoolkit.rest.RestStatus;
import io.github.nichetoolkit.rest.util.I18nUtils;
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
    JTS_ERROR(10700, "It has encountered a jts related error"),
    /**
     * <code>JTS_PARSE_ERROR</code>
     * {@link io.github.nichetoolkit.jts.error.JtsErrorStatus} <p>The <code>JTS_PARSE_ERROR</code> field.</p>
     */
    JTS_PARSE_ERROR(10701, "It has encountered a jts data parsing error"),
    /**
     * <code>JTS_BOX_INVALID_ERROR</code>
     * {@link io.github.nichetoolkit.jts.error.JtsErrorStatus} <p>The <code>JTS_BOX_INVALID_ERROR</code> field.</p>
     */
    JTS_BOX_INVALID_ERROR(10702, "The rang of jts box is invalid"),

    /**
     * <code>SHAPE_PARAMS_ERROR</code>
     * {@link io.github.nichetoolkit.jts.error.JtsErrorStatus} <p>The <code>SHAPE_PARAMS_ERROR</code> field.</p>
     */
    SHAPE_PARAMS_ERROR(10711, "It has encountered a shape param error"),
    /**
     * <code>SHAPE_DATA_STORE_ERROR</code>
     * {@link io.github.nichetoolkit.jts.error.JtsErrorStatus} <p>The <code>SHAPE_DATA_STORE_ERROR</code> field.</p>
     */
    SHAPE_DATA_STORE_ERROR(10712, "It has encountered a shape data store error"),
    /**
     * <code>SHAPE_FEATURES_ERROR</code>
     * {@link io.github.nichetoolkit.jts.error.JtsErrorStatus} <p>The <code>SHAPE_FEATURES_ERROR</code> field.</p>
     */
    SHAPE_FEATURES_ERROR(10713, "It has encountered a shape features error"),
    /**
     * <code>SHAPE_PARAMS_UNINITIALIZED_ERROR</code>
     * {@link io.github.nichetoolkit.jts.error.JtsErrorStatus} <p>The <code>SHAPE_PARAMS_UNINITIALIZED_ERROR</code> field.</p>
     */
    SHAPE_PARAMS_UNINITIALIZED_ERROR(10714, "It has encountered a shape params uninitialized error"),
    /**
     * <code>SHAPE_DATA_STORE_UNINITIALIZED_ERROR</code>
     * {@link io.github.nichetoolkit.jts.error.JtsErrorStatus} <p>The <code>SHAPE_DATA_STORE_UNINITIALIZED_ERROR</code> field.</p>
     */
    SHAPE_DATA_STORE_UNINITIALIZED_ERROR(10715, "It has encountered a shape data store uninitialized error"),
    /**
     * <code>SHAPE_FEATURES_UNINITIALIZED_ERROR</code>
     * {@link io.github.nichetoolkit.jts.error.JtsErrorStatus} <p>The <code>SHAPE_FEATURES_UNINITIALIZED_ERROR</code> field.</p>
     */
    SHAPE_FEATURES_UNINITIALIZED_ERROR(10716, "It has encountered a shape features uninitialized error"),
    /**
     * <code>SHAPE_READER_UNINITIALIZED_ERROR</code>
     * {@link io.github.nichetoolkit.jts.error.JtsErrorStatus} <p>The <code>SHAPE_READER_UNINITIALIZED_ERROR</code> field.</p>
     */
    SHAPE_READER_UNINITIALIZED_ERROR(10717, "It has encountered a shape reader uninitialized error"),
    /**
     * <code>SHAPE_WRITER_UNINITIALIZED_ERROR</code>
     * {@link io.github.nichetoolkit.jts.error.JtsErrorStatus} <p>The <code>SHAPE_WRITER_UNINITIALIZED_ERROR</code> field.</p>
     */
    SHAPE_WRITER_UNINITIALIZED_ERROR(10718, "It has encountered a shape writer uninitialized error"),

    /**
     * <code>SHAPE_READER_PARAMS_ERROR</code>
     * {@link io.github.nichetoolkit.jts.error.JtsErrorStatus} <p>The <code>SHAPE_READER_PARAMS_ERROR</code> field.</p>
     */
    SHAPE_READER_PARAMS_ERROR(10719, "It has encountered a params error of shape file reader"),
    /**
     * <code>SHAPE_READER_DATA_STORE_ERROR</code>
     * {@link io.github.nichetoolkit.jts.error.JtsErrorStatus} <p>The <code>SHAPE_READER_DATA_STORE_ERROR</code> field.</p>
     */
    SHAPE_READER_DATA_STORE_ERROR(10720, "It has encountered a data store error of shape file reader"),
    /**
     * <code>SHAPE_READER_FEATURES_ERROR</code>
     * {@link io.github.nichetoolkit.jts.error.JtsErrorStatus} <p>The <code>SHAPE_READER_FEATURES_ERROR</code> field.</p>
     */
    SHAPE_READER_FEATURES_ERROR(10721, "It has encountered a features error of shape file reader"),

    /**
     * <code>SHAPE_WRITER_PARAMS_ERROR</code>
     * {@link io.github.nichetoolkit.jts.error.JtsErrorStatus} <p>The <code>SHAPE_WRITER_PARAMS_ERROR</code> field.</p>
     */
    SHAPE_WRITER_PARAMS_ERROR(10722, "It has encountered a params error of shape file writer"),
    /**
     * <code>SHAPE_WRITER_DATA_STORE_ERROR</code>
     * {@link io.github.nichetoolkit.jts.error.JtsErrorStatus} <p>The <code>SHAPE_WRITER_DATA_STORE_ERROR</code> field.</p>
     */
    SHAPE_WRITER_DATA_STORE_ERROR(10723, "It has encountered a data store error of shape file writer"),
    /**
     * <code>SHAPE_WRITER_FEATURES_ERROR</code>
     * {@link io.github.nichetoolkit.jts.error.JtsErrorStatus} <p>The <code>SHAPE_WRITER_FEATURES_ERROR</code> field.</p>
     */
    SHAPE_WRITER_FEATURES_ERROR(10724, "It has encountered a features error of shape file writer"),
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

    @Override
    public String getMessage() {
        return I18nUtils.message(name(), this.message);
    }
}
