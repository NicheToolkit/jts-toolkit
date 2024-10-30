package io.github.nichetoolkit.jts.error;

import io.github.nichetoolkit.rest.RestStatus;
import io.github.nichetoolkit.rest.error.natives.ParseErrorException;

/**
 * <p>JtsParseException</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
@SuppressWarnings("unused")
public class JtsParseException extends ParseErrorException {

    public JtsParseException() {
        super(JtsErrorStatus.JTS_PARSE_ERROR);
    }

    public JtsParseException(RestStatus status) {
        super(status);
    }

    public JtsParseException(RestStatus status, String message) {
        super(status, message);
    }

    public JtsParseException(String resource, String message) {
        super(JtsErrorStatus.JTS_PARSE_ERROR, resource, message);
    }

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
