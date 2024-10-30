package io.github.nichetoolkit.jts.error.shape;

import io.github.nichetoolkit.jts.error.JtsErrorStatus;
import io.github.nichetoolkit.rest.error.natives.ResourceErrorException;

/**
 * <code>DataStoreUninitializedErrorException</code>
 * <p>The data store uninitialized error exception class.</p>
 * @see  io.github.nichetoolkit.rest.error.natives.ResourceErrorException
 * @see  java.lang.SuppressWarnings
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
@SuppressWarnings("unused")
public class DataStoreUninitializedErrorException extends ResourceErrorException {
    /**
     * <code>DataStoreUninitializedErrorException</code>
     * <p>Instantiates a new data store uninitialized error exception.</p>
     */
    public DataStoreUninitializedErrorException() {
        super(JtsErrorStatus.SHAPE_DATA_STORE_UNINITIALIZED_ERROR);
    }


    /**
     * <code>DataStoreUninitializedErrorException</code>
     * <p>Instantiates a new data store uninitialized error exception.</p>
     * @param message {@link java.lang.String} <p>The message parameter is <code>String</code> type.</p>
     * @see  java.lang.String
     */
    public DataStoreUninitializedErrorException(String message) {
        super(JtsErrorStatus.SHAPE_DATA_STORE_UNINITIALIZED_ERROR, message);
    }

    @Override
    public DataStoreUninitializedErrorException get() {
        return new DataStoreUninitializedErrorException();
    }

    @Override
    public String name() {
        return "Shape data store uninitialized error exception";
    }
}
