package io.github.nichetoolkit.jts.error.shape;

import io.github.nichetoolkit.jts.error.JtsErrorStatus;
import io.github.nichetoolkit.rest.error.natives.ResourceErrorException;

/**
 * <p>ShapeWriterDataStoreErrorException</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public class DataStoreUninitializedErrorException extends ResourceErrorException {
    public DataStoreUninitializedErrorException() {
        super(JtsErrorStatus.SHAPE_DATA_STORE_UNINITIALIZED_ERROR);
    }

    public DataStoreUninitializedErrorException(String message) {
        super(JtsErrorStatus.SHAPE_DATA_STORE_UNINITIALIZED_ERROR,message);
    }

    public DataStoreUninitializedErrorException get() {
        return new DataStoreUninitializedErrorException();
    }

    public String name() {
        return "Shape data store uninitialized error exception";
    }
}
