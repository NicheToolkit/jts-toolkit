package io.github.nichetoolkit.jts.error.shape;

import io.github.nichetoolkit.jts.error.JtsErrorStatus;
import io.github.nichetoolkit.rest.error.natives.ResourceErrorException;

/**
 * <code>FeaturesUninitializedErrorException</code>
 * <p>The features uninitialized error exception class.</p>
 * @see  io.github.nichetoolkit.rest.error.natives.ResourceErrorException
 * @see  java.lang.SuppressWarnings
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
@SuppressWarnings("unused")
public class FeaturesUninitializedErrorException extends ResourceErrorException {
    /**
     * <code>FeaturesUninitializedErrorException</code>
     * <p>Instantiates a new features uninitialized error exception.</p>
     */
    public FeaturesUninitializedErrorException() {
        super(JtsErrorStatus.SHAPE_FEATURES_UNINITIALIZED_ERROR);
    }

    /**
     * <code>FeaturesUninitializedErrorException</code>
     * <p>Instantiates a new features uninitialized error exception.</p>
     * @param message {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @see  java.lang.String
     */
    public FeaturesUninitializedErrorException(String message) {
        super(JtsErrorStatus.SHAPE_FEATURES_UNINITIALIZED_ERROR, message);
    }

    @Override
    public FeaturesUninitializedErrorException get() {
        return new FeaturesUninitializedErrorException();
    }

    @Override
    public String name() {
        return "Shape features uninitialized error exception";
    }
}
