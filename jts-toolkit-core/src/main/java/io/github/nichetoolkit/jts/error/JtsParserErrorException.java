package io.github.nichetoolkit.jts.error;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.github.nichetoolkit.rest.RestStatus;

/**
 * <code>JtsParserErrorException</code>
 * <p>The jts parser error exception class.</p>
 * @see  com.fasterxml.jackson.core.JsonProcessingException
 * @see  io.github.nichetoolkit.rest.RestStatus
 * @see  java.lang.SuppressWarnings
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
@SuppressWarnings("unused")
public class JtsParserErrorException extends JsonProcessingException implements RestStatus {
    /**
     * <code>ERROR_STATUS</code>
     * {@link java.lang.Integer} <p>The constant <code>ERROR_STATUS</code> field.</p>
     * @see  java.lang.Integer
     */
    public static final Integer ERROR_STATUS = 10700;
    /**
     * <code>ERROR_MESSAGE</code>
     * {@link java.lang.String} <p>The constant <code>ERROR_MESSAGE</code> field.</p>
     * @see  java.lang.String
     */
    public static final String ERROR_MESSAGE = "Jts相关数据解析错误";

    /**
     * <code>status</code>
     * {@link java.lang.Integer} <p>The <code>status</code> field.</p>
     * @see  java.lang.Integer
     */
    protected final Integer status;

    /**
     * <code>JtsParserErrorException</code>
     * <p>Instantiates a new jts parser error exception.</p>
     */
    public JtsParserErrorException() {
        super(ERROR_MESSAGE);
        this.status = ERROR_STATUS;
    }

    /**
     * <code>JtsParserErrorException</code>
     * <p>Instantiates a new jts parser error exception.</p>
     * @param message {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @see  java.lang.String
     */
    public JtsParserErrorException(String message) {
        super(message);
        this.status = ERROR_STATUS;
    }

    /**
     * <code>JtsParserErrorException</code>
     * <p>Instantiates a new jts parser error exception.</p>
     * @param status {@link java.lang.Integer} <p>The status parameter is <code>Integer</code> type.</p>
     * @param message {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @see  java.lang.Integer
     * @see  java.lang.String
     */
    public JtsParserErrorException(Integer status, String message) {
        super(message);
        this.status = ERROR_STATUS;
    }

    /**
     * <code>JtsParserErrorException</code>
     * <p>Instantiates a new jts parser error exception.</p>
     * @param message {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @param status {@link io.github.nichetoolkit.rest.RestStatus} <p>The status parameter is <code>RestStatus</code> type.</p>
     * @see  java.lang.String
     * @see  io.github.nichetoolkit.rest.RestStatus
     */
    public JtsParserErrorException(String message, RestStatus status) {
        super(message);
        this.status = status.getStatus();
    }

    /**
     * <code>JtsParserErrorException</code>
     * <p>Instantiates a new jts parser error exception.</p>
     * @param message {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @param cause {@link java.lang.Throwable} <p>The cause parameter is <code>Throwable</code> type.</p>
     * @see  java.lang.String
     * @see  java.lang.Throwable
     */
    public JtsParserErrorException(String message, Throwable cause) {
        super(message, cause);
        this.status = ERROR_STATUS;
    }

    /**
     * <code>JtsParserErrorException</code>
     * <p>Instantiates a new jts parser error exception.</p>
     * @param message {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @param status {@link io.github.nichetoolkit.rest.RestStatus} <p>The status parameter is <code>RestStatus</code> type.</p>
     * @param cause {@link java.lang.Throwable} <p>The cause parameter is <code>Throwable</code> type.</p>
     * @see  java.lang.String
     * @see  io.github.nichetoolkit.rest.RestStatus
     * @see  java.lang.Throwable
     */
    public JtsParserErrorException(String message, RestStatus status, Throwable cause) {
        super(message, cause);
        this.status = status.getStatus();
    }

    /**
     * <code>JtsParserErrorException</code>
     * <p>Instantiates a new jts parser error exception.</p>
     * @param status {@link java.lang.Integer} <p>The status parameter is <code>Integer</code> type.</p>
     * @param message {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @param cause {@link java.lang.Throwable} <p>The cause parameter is <code>Throwable</code> type.</p>
     * @see  java.lang.Integer
     * @see  java.lang.String
     * @see  java.lang.Throwable
     */
    public JtsParserErrorException(Integer status, String message, Throwable cause) {
        super(message, cause);
        this.status = status;
    }

    /**
     * <code>get</code>
     * <p>The get method.</p>
     * @return  {@link io.github.nichetoolkit.jts.error.JtsParserErrorException} <p>The get return object is <code>JtsParserErrorException</code> type.</p>
     */
    public JtsParserErrorException get() {
        return new JtsParserErrorException();
    }

    @Override
    public String name() {
        return "jts parse exception";
    }

    @Override
    public Integer getStatus() {
        return this.status;
    }
}
