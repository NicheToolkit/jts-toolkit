package io.github.nichetoolkit.jts.error.shape;

import io.github.nichetoolkit.jts.error.JtsErrorStatus;
import io.github.nichetoolkit.rest.RestStatus;
import io.github.nichetoolkit.rest.error.natives.ResourceErrorException;

/**
 * <code>ParamsErrorException</code>
 * <p>The params error exception class.</p>
 * @see  io.github.nichetoolkit.rest.error.natives.ResourceErrorException
 * @see  java.lang.SuppressWarnings
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
@SuppressWarnings("unused")
public class ParamsErrorException extends ResourceErrorException {
    /**
     * <code>ParamsErrorException</code>
     * <p>Instantiates a new params error exception.</p>
     */
    public ParamsErrorException() {
        super(JtsErrorStatus.SHAPE_PARAMS_ERROR);
    }

    /**
     * <code>ParamsErrorException</code>
     * <p>Instantiates a new params error exception.</p>
     * @param status {@link io.github.nichetoolkit.rest.RestStatus} <p>The status parameter is <code>RestStatus</code> type.</p>
     * @see  io.github.nichetoolkit.rest.RestStatus
     */
    public ParamsErrorException(RestStatus status) {
        super(status);
    }

    /**
     * <code>ParamsErrorException</code>
     * <p>Instantiates a new params error exception.</p>
     * @param message {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @see  java.lang.String
     */
    public ParamsErrorException(String message) {
        super(JtsErrorStatus.SHAPE_PARAMS_ERROR, message);
    }

    /**
     * <code>ParamsErrorException</code>
     * <p>Instantiates a new params error exception.</p>
     * @param status {@link io.github.nichetoolkit.rest.RestStatus} <p>The status parameter is <code>RestStatus</code> type.</p>
     * @param message {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @see  io.github.nichetoolkit.rest.RestStatus
     * @see  java.lang.String
     */
    public ParamsErrorException(RestStatus status, String message) {
        super(status, message);
    }

    @Override
    public ParamsErrorException get() {
        return new ParamsErrorException();
    }

    @Override
    public String name() {
        return "Shape params error exception";
    }
}
