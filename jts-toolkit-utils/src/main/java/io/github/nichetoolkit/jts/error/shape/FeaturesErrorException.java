package io.github.nichetoolkit.jts.error.shape;

import io.github.nichetoolkit.jts.error.JtsErrorStatus;
import io.github.nichetoolkit.rest.RestStatus;
import io.github.nichetoolkit.rest.error.natives.ResourceErrorException;

/**
 * <p>ShapeReaderFeaturesErrorException</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
@SuppressWarnings("unused")
public class FeaturesErrorException extends ResourceErrorException {
    public FeaturesErrorException() {
        super(JtsErrorStatus.SHAPE_FEATURES_ERROR);
    }

    public FeaturesErrorException(RestStatus status) {
        super(status);
    }

    public FeaturesErrorException(String message) {
        super(JtsErrorStatus.SHAPE_FEATURES_ERROR,message);
    }

    public FeaturesErrorException(RestStatus status, String message) {
        super(status,message);
    }

    @Override
    public FeaturesErrorException get() {
        return new FeaturesErrorException();
    }

    @Override
    public String name() {
        return "Shape features error exception";
    }
}
