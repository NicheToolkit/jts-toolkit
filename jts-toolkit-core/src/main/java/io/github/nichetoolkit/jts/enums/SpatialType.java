package io.github.nichetoolkit.jts.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.github.nichetoolkit.rest.RestValue;
import org.springframework.lang.NonNull;

import java.util.Optional;

/**
 * <p>SpatialType</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public enum SpatialType implements RestValue<Integer,String> {
    INTERSECTS(1,"相交"),
    CONTAINS(2,"包含"),
    OVERLAPS(3,"重叠"),
    TOUCHES(4,"接触"),
    COVERS(5,"覆盖"),
            ;

    private final Integer key;
    private final String value;

    SpatialType(Integer key, String value) {
        this.key = key;
        this.value = value;
    }

    @JsonValue
    public Integer getKey() {
        return this.key;
    }

    public String getValue() {
        return this.value;
    }

    @JsonCreator
    public static SpatialType parseKey(@NonNull Integer key) {
        SpatialType typeEnum = RestValue.parseKey(SpatialType.class, key);
        return Optional.ofNullable(typeEnum).orElse(SpatialType.INTERSECTS);
    }

    public static SpatialType parseValue(@NonNull String value) {
        SpatialType typeEnum = RestValue.parseValue(SpatialType.class, value);
        return Optional.ofNullable(typeEnum).orElse(SpatialType.INTERSECTS);
    }
}
