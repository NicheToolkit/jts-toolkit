package io.github.nichetoolkit.jts.error.shape;

import io.github.nichetoolkit.jts.error.JtsErrorStatus;
import io.github.nichetoolkit.rest.error.natives.ResourceErrorException;

/**
 * <p>ShapeWriterParamsErrorException</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public class ParamsUninitializedErrorException extends ResourceErrorException {
    public ParamsUninitializedErrorException() {
        super(JtsErrorStatus.SHAPE_PARAMS_UNINITIALIZED_ERROR);
    }

    public ParamsUninitializedErrorException(String message) {
        super(JtsErrorStatus.SHAPE_PARAMS_UNINITIALIZED_ERROR,message);
    }

    public ParamsUninitializedErrorException get() {
        return new ParamsUninitializedErrorException();
    }

    public String name() {
        return "Shape params uninitialized error exception";
    }
}
