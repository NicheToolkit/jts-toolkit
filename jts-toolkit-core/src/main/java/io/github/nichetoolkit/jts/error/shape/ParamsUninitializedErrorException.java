package io.github.nichetoolkit.jts.error.shape;

import io.github.nichetoolkit.jts.error.JtsErrorStatus;
import io.github.nichetoolkit.rest.error.natives.ResourceErrorException;

/**
 * <code>ParamsUninitializedErrorException</code>
 * <p>The params uninitialized error exception class.</p>
 * @see  io.github.nichetoolkit.rest.error.natives.ResourceErrorException
 * @see  java.lang.SuppressWarnings
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
@SuppressWarnings("unused")
public class ParamsUninitializedErrorException extends ResourceErrorException {
    /**
     * <code>ParamsUninitializedErrorException</code>
     * <p>Instantiates a new params uninitialized error exception.</p>
     */
    public ParamsUninitializedErrorException() {
        super(JtsErrorStatus.SHAPE_PARAMS_UNINITIALIZED_ERROR);
    }

    /**
     * <code>ParamsUninitializedErrorException</code>
     * <p>Instantiates a new params uninitialized error exception.</p>
     * @param message {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @see  java.lang.String
     */
    public ParamsUninitializedErrorException(String message) {
        super(JtsErrorStatus.SHAPE_PARAMS_UNINITIALIZED_ERROR, message);
    }

    @Override
    public ParamsUninitializedErrorException get() {
        return new ParamsUninitializedErrorException();
    }

    @Override
    public String name() {
        return "Shape params uninitialized error exception";
    }
}
