package io.github.nichetoolkit.jts.error;

import io.github.nichetoolkit.rest.RestStatus;
import io.github.nichetoolkit.rest.error.natives.ParseErrorException;

/**
 * <code>JtsParseException</code>
 * <p>The jts parse exception class.</p>
 * @see  io.github.nichetoolkit.rest.error.natives.ParseErrorException
 * @see  java.lang.SuppressWarnings
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
@SuppressWarnings("unused")
public class JtsParseException extends ParseErrorException {

    /**
     * <code>JtsParseException</code>
     * <p>Instantiates a new jts parse exception.</p>
     */
    public JtsParseException() {
        super(JtsErrorStatus.JTS_PARSE_ERROR);
    }

    /**
     * <code>JtsParseException</code>
     * <p>Instantiates a new jts parse exception.</p>
     * @param status {@link io.github.nichetoolkit.rest.RestStatus} <p>The status parameter is <code>RestStatus</code> type.</p>
     * @see  io.github.nichetoolkit.rest.RestStatus
     */
    public JtsParseException(RestStatus status) {
        super(status);
    }

    /**
     * <code>JtsParseException</code>
     * <p>Instantiates a new jts parse exception.</p>
     * @param status {@link io.github.nichetoolkit.rest.RestStatus} <p>The status parameter is <code>RestStatus</code> type.</p>
     * @param message {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @see  io.github.nichetoolkit.rest.RestStatus
     * @see  java.lang.String
     */
    public JtsParseException(RestStatus status, String message) {
        super(status, message);
    }

    /**
     * <code>JtsParseException</code>
     * <p>Instantiates a new jts parse exception.</p>
     * @param resource {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param message {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @see  java.lang.String
     */
    public JtsParseException(String resource, String message) {
        super(JtsErrorStatus.JTS_PARSE_ERROR, resource, message);
    }

    /**
     * <code>JtsParseException</code>
     * <p>Instantiates a new jts parse exception.</p>
     * @param resource {@link java.lang.String} <p>The resource parameter is <code>String</code> type.</p>
     * @param message {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @param cause {@link java.lang.Throwable} <p>The cause parameter is <code>Throwable</code> type.</p>
     * @see  java.lang.String
     * @see  java.lang.Throwable
     */
    public JtsParseException(String resource, String message, Throwable cause) {
        super(JtsErrorStatus.JTS_PARSE_ERROR, resource, message, cause);
    }

    @Override
    public JtsParseException get() {
        return new JtsParseException();
    }

    @Override
    public String name() {
        return "jts parse exception";
    }

}
