package io.github.nichetoolkit.jts.error.shape;

import io.github.nichetoolkit.jts.error.JtsErrorStatus;
import io.github.nichetoolkit.rest.error.natives.ResourceErrorException;

/**
 * <p>ShapeWriterDataStoreErrorException</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public class FeaturesUninitializedErrorException extends ResourceErrorException {
    public FeaturesUninitializedErrorException() {
        super(JtsErrorStatus.SHAPE_FEATURES_UNINITIALIZED_ERROR);
    }

    public FeaturesUninitializedErrorException(String message) {
        super(JtsErrorStatus.SHAPE_FEATURES_UNINITIALIZED_ERROR,message);
    }

    public FeaturesUninitializedErrorException get() {
        return new FeaturesUninitializedErrorException();
    }

    public String name() {
        return "Shape features uninitialized error exception";
    }
}
