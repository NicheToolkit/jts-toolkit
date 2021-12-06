package io.github.nichetoolkit.jts.error.shape;

import io.github.nichetoolkit.jts.error.JtsErrorStatus;
import io.github.nichetoolkit.rest.RestStatus;
import io.github.nichetoolkit.rest.error.natives.ResourceErrorException;

/**
 * <p>ShapeReaderDataStoreErrorException</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public class DataStoreErrorException extends ResourceErrorException {
    public DataStoreErrorException() {
        super(JtsErrorStatus.SHAPE_DATA_STORE_ERROR);
    }

    public DataStoreErrorException(RestStatus status) {
        super(status);
    }

    public DataStoreErrorException(String message) {
        super(JtsErrorStatus.SHAPE_DATA_STORE_ERROR,message);
    }

    public DataStoreErrorException(RestStatus status, String message) {
        super(status,message);
    }

    public DataStoreErrorException get() {
        return new DataStoreErrorException();
    }

    public String name() {
        return "Shape data store error exception";
    }
}
