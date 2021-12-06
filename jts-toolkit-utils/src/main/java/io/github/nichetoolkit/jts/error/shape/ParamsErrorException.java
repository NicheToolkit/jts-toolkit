package io.github.nichetoolkit.jts.error.shape;

import io.github.nichetoolkit.jts.error.JtsErrorStatus;
import io.github.nichetoolkit.rest.RestStatus;
import io.github.nichetoolkit.rest.error.natives.ResourceErrorException;

/**
 * <p>ShapeReaderParamsErrorException</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public class ParamsErrorException extends ResourceErrorException {
    public ParamsErrorException() {
        super(JtsErrorStatus.SHAPE_PARAMS_ERROR);
    }

    public ParamsErrorException(RestStatus status) {
        super(status);
    }

    public ParamsErrorException(String message) {
        super(JtsErrorStatus.SHAPE_PARAMS_ERROR,message);
    }

    public ParamsErrorException(RestStatus status, String message) {
        super(status,message);
    }

    public ParamsErrorException get() {
        return new ParamsErrorException();
    }

    public String name() {
        return "Shape params error exception";
    }
}
