package io.github.nichetoolkit.jts.error;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.github.nichetoolkit.rest.RestStatus;

/**
 * <p>JtsSerializeErrorException</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public class JtsParserErrorException extends JsonProcessingException implements RestStatus {
    public static final Integer ERROR_STATUS = 10700;
    public static final String ERROR_MESSAGE = "Jts相关数据解析错误";

    protected Integer status;

    public JtsParserErrorException() {
        super(ERROR_MESSAGE);
        this.status = ERROR_STATUS;
    }

    public JtsParserErrorException(String message) {
        super(message);
        this.status = ERROR_STATUS;
    }

    public JtsParserErrorException(Integer status, String message) {
        super(message);
        this.status = ERROR_STATUS;
    }

    public JtsParserErrorException(String message, RestStatus status) {
        super(message);
        this.status = status.getStatus();
    }

    public JtsParserErrorException(String message, Throwable cause) {
        super(message,cause);
        this.status = ERROR_STATUS;
    }

    public JtsParserErrorException(String message, RestStatus status, Throwable cause) {
        super(message,cause);
        this.status = status.getStatus();
    }

    public JtsParserErrorException(Integer status, String message, Throwable cause) {
        super(message, cause);
        this.status = status;
    }

    public JtsParserErrorException get() {
        return new JtsParserErrorException();
    }

    public String name() {
        return "jts parse exception";
    }

    @Override
    public Integer getStatus() {
        return this.status;
    }
}
