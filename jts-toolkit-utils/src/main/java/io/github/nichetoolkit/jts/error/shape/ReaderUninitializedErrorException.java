package io.github.nichetoolkit.jts.error.shape;

import io.github.nichetoolkit.jts.error.JtsErrorStatus;
import io.github.nichetoolkit.rest.error.natives.ResourceErrorException;

/**
 * <p>ShapeWriterParamsErrorException</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public class ReaderUninitializedErrorException extends ResourceErrorException {
    public ReaderUninitializedErrorException() {
        super(JtsErrorStatus.SHAPE_READER_UNINITIALIZED_ERROR);
    }

    public ReaderUninitializedErrorException(String message) {
        super(JtsErrorStatus.SHAPE_READER_UNINITIALIZED_ERROR,message);
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
