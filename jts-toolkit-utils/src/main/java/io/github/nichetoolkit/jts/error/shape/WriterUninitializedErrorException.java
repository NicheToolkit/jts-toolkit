package io.github.nichetoolkit.jts.error.shape;

import io.github.nichetoolkit.jts.error.JtsErrorStatus;
import io.github.nichetoolkit.rest.error.natives.ResourceErrorException;

/**
 * <p>ShapeWriterParamsErrorException</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
@SuppressWarnings("unused")
public class WriterUninitializedErrorException extends ResourceErrorException {
    public WriterUninitializedErrorException() {
        super(JtsErrorStatus.SHAPE_WRITER_UNINITIALIZED_ERROR);
    }

    public WriterUninitializedErrorException(String message) {
        super(JtsErrorStatus.SHAPE_WRITER_UNINITIALIZED_ERROR,message);
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
