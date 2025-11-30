package io.github.nichetoolkit.jts.error.shape;

import io.github.nichetoolkit.jts.error.JtsErrorStatus;
import io.github.nichetoolkit.rest.error.natives.ResourceErrorException;

/**
 * <code>WriterUninitializedErrorException</code>
 * <p>The writer uninitialized error exception class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.rest.error.natives.ResourceErrorException
 * @see java.lang.SuppressWarnings
 * @since Jdk17
 */
@SuppressWarnings("unused")
public class WriterUninitializedErrorException extends ResourceErrorException {
    /**
     * <code>WriterUninitializedErrorException</code>
     * <p>Instantiates a new writer uninitialized error exception.</p>
     */
    public WriterUninitializedErrorException() {
        super(JtsErrorStatus.SHAPE_WRITER_UNINITIALIZED_ERROR);
    }

    /**
     * <code>WriterUninitializedErrorException</code>
     * <p>Instantiates a new writer uninitialized error exception.</p>
     * @param message {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @see java.lang.String
     */
    public WriterUninitializedErrorException(String message) {
        super(JtsErrorStatus.SHAPE_WRITER_UNINITIALIZED_ERROR, message);
    }

    @Override
    public WriterUninitializedErrorException get() {
        return new WriterUninitializedErrorException();
    }

    @Override
    public String name() {
        return "Shape params uninitialized error exception";
    }
}
