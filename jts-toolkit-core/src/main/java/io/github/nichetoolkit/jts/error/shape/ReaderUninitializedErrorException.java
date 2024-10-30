package io.github.nichetoolkit.jts.error.shape;

import io.github.nichetoolkit.jts.error.JtsErrorStatus;
import io.github.nichetoolkit.rest.error.natives.ResourceErrorException;

/**
 * <code>ReaderUninitializedErrorException</code>
 * <p>The reader uninitialized error exception class.</p>
 * @see  io.github.nichetoolkit.rest.error.natives.ResourceErrorException
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
public class ReaderUninitializedErrorException extends ResourceErrorException {
    /**
     * <code>ReaderUninitializedErrorException</code>
     * <p>Instantiates a new reader uninitialized error exception.</p>
     */
    public ReaderUninitializedErrorException() {
        super(JtsErrorStatus.SHAPE_READER_UNINITIALIZED_ERROR);
    }

    /**
     * <code>ReaderUninitializedErrorException</code>
     * <p>Instantiates a new reader uninitialized error exception.</p>
     * @param message {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @see  java.lang.String
     */
    public ReaderUninitializedErrorException(String message) {
        super(JtsErrorStatus.SHAPE_READER_UNINITIALIZED_ERROR, message);
    }

    @Override
    public ReaderUninitializedErrorException get() {
        return new ReaderUninitializedErrorException();
    }

    @Override
    public String name() {
        return "Shape params uninitialized error exception";
    }
}
