package io.github.nichetoolkit.jts.error.shape;

import io.github.nichetoolkit.jts.error.JtsErrorStatus;
import io.github.nichetoolkit.rest.RestStatus;
import io.github.nichetoolkit.rest.error.natives.ResourceErrorException;

/**
 * <code>FeaturesErrorException</code>
 * <p>The features error exception class.</p>
 * @see  io.github.nichetoolkit.rest.error.natives.ResourceErrorException
 * @see  java.lang.SuppressWarnings
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
@SuppressWarnings("unused")
public class FeaturesErrorException extends ResourceErrorException {
    /**
     * <code>FeaturesErrorException</code>
     * <p>Instantiates a new features error exception.</p>
     */
    public FeaturesErrorException() {
        super(JtsErrorStatus.SHAPE_FEATURES_ERROR);
    }

    /**
     * <code>FeaturesErrorException</code>
     * <p>Instantiates a new features error exception.</p>
     * @param status {@link io.github.nichetoolkit.rest.RestStatus} <p>The status parameter is <code>RestStatus</code> type.</p>
     * @see  io.github.nichetoolkit.rest.RestStatus
     */
    public FeaturesErrorException(RestStatus status) {
        super(status);
    }

    /**
     * <code>FeaturesErrorException</code>
     * <p>Instantiates a new features error exception.</p>
     * @param message {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @see  java.lang.String
     */
    public FeaturesErrorException(String message) {
        super(JtsErrorStatus.SHAPE_FEATURES_ERROR, message);
    }

    /**
     * <code>FeaturesErrorException</code>
     * <p>Instantiates a new features error exception.</p>
     * @param status {@link io.github.nichetoolkit.rest.RestStatus} <p>The status parameter is <code>RestStatus</code> type.</p>
     * @param message {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @see  io.github.nichetoolkit.rest.RestStatus
     * @see  java.lang.String
     */
    public FeaturesErrorException(RestStatus status, String message) {
        super(status, message);
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
