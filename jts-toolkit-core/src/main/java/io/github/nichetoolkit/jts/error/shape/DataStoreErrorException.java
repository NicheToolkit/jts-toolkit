package io.github.nichetoolkit.jts.error.shape;

import io.github.nichetoolkit.jts.error.JtsErrorStatus;
import io.github.nichetoolkit.rest.RestStatus;
import io.github.nichetoolkit.rest.error.natives.ResourceErrorException;

/**
 * <code>DataStoreErrorException</code>
 * <p>The data store error exception class.</p>
 * @see  io.github.nichetoolkit.rest.error.natives.ResourceErrorException
 * @see  java.lang.SuppressWarnings
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
@SuppressWarnings("unused")
public class DataStoreErrorException extends ResourceErrorException {
    /**
     * <code>DataStoreErrorException</code>
     * <p>Instantiates a new data store error exception.</p>
     */
    public DataStoreErrorException() {
        super(JtsErrorStatus.SHAPE_DATA_STORE_ERROR);
    }

    /**
     * <code>DataStoreErrorException</code>
     * <p>Instantiates a new data store error exception.</p>
     * @param status {@link io.github.nichetoolkit.rest.RestStatus} <p>The status parameter is <code>RestStatus</code> type.</p>
     * @see  io.github.nichetoolkit.rest.RestStatus
     */
    public DataStoreErrorException(RestStatus status) {
        super(status);
    }

    /**
     * <code>DataStoreErrorException</code>
     * <p>Instantiates a new data store error exception.</p>
     * @param message {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @see  java.lang.String
     */
    public DataStoreErrorException(String message) {
        super(JtsErrorStatus.SHAPE_DATA_STORE_ERROR, message);
    }

    /**
     * <code>DataStoreErrorException</code>
     * <p>Instantiates a new data store error exception.</p>
     * @param status {@link io.github.nichetoolkit.rest.RestStatus} <p>The status parameter is <code>RestStatus</code> type.</p>
     * @param message {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @see  io.github.nichetoolkit.rest.RestStatus
     * @see  java.lang.String
     */
    public DataStoreErrorException(RestStatus status, String message) {
        super(status, message);
    }

    @Override
    public DataStoreErrorException get() {
        return new DataStoreErrorException();
    }

    @Override
    public String name() {
        return "Shape data store error exception";
    }
}
