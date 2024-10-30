package io.github.nichetoolkit.jts.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.github.nichetoolkit.rest.RestValue;
import org.springframework.lang.NonNull;

import java.util.Optional;

/**
 * <code>SpatialType</code>
 * <p>The spatial type enumeration.</p>
 * @see  io.github.nichetoolkit.rest.RestValue
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
public enum SpatialType implements RestValue<Integer,String> {
    /**
     * <code>INTERSECTS</code>
     * <p>The intersects spatial type field.</p>
     */
    INTERSECTS(1,"相交"),
    /**
     * <code>CONTAINS</code>
     * <p>The contains spatial type field.</p>
     */
    CONTAINS(2,"包含"),
    /**
     * <code>OVERLAPS</code>
     * <p>The overlaps spatial type field.</p>
     */
    OVERLAPS(3,"重叠"),
    /**
     * <code>TOUCHES</code>
     * <p>The touches spatial type field.</p>
     */
    TOUCHES(4,"接触"),
    /**
     * <code>COVERS</code>
     * <p>The covers spatial type field.</p>
     */
    COVERS(5,"覆盖"),
            ;

    /**
     * <code>key</code>
     * {@link java.lang.Integer} <p>The <code>key</code> field.</p>
     * @see  java.lang.Integer
     */
    private final Integer key;
    /**
     * <code>value</code>
     * {@link java.lang.String} <p>The <code>value</code> field.</p>
     * @see  java.lang.String
     */
    private final String value;

    /**
     * <code>SpatialType</code>
     * <p>Instantiates a new spatial type.</p>
     * @param key {@link java.lang.Integer} <p>The key parameter is <code>Integer</code> type.</p>
     * @param value {@link java.lang.String} <p>The value parameter is <code>String</code> type.</p>
     * @see  java.lang.Integer
     * @see  java.lang.String
     */
    SpatialType(Integer key, String value) {
        this.key = key;
        this.value = value;
    }

    @JsonValue
    @Override
    public Integer getKey() {
        return this.key;
    }

    @Override
    public String getValue() {
        return this.value;
    }

    /**
     * <code>parseKey</code>
     * <p>The parse key method.</p>
     * @param key {@link java.lang.Integer} <p>The key parameter is <code>Integer</code> type.</p>
     * @see  java.lang.Integer
     * @see  org.springframework.lang.NonNull
     * @see  com.fasterxml.jackson.annotation.JsonCreator
     * @return  {@link io.github.nichetoolkit.jts.enums.SpatialType} <p>The parse key return object is <code>SpatialType</code> type.</p>
     */
    @JsonCreator
    public static SpatialType parseKey(@NonNull Integer key) {
        SpatialType typeEnum = RestValue.parseKey(SpatialType.class, key);
        return Optional.ofNullable(typeEnum).orElse(SpatialType.INTERSECTS);
    }

    /**
     * <code>parseValue</code>
     * <p>The parse value method.</p>
     * @param value {@link java.lang.String} <p>The value parameter is <code>String</code> type.</p>
     * @see  java.lang.String
     * @see  org.springframework.lang.NonNull
     * @return  {@link io.github.nichetoolkit.jts.enums.SpatialType} <p>The parse value return object is <code>SpatialType</code> type.</p>
     */
    public static SpatialType parseValue(@NonNull String value) {
        SpatialType typeEnum = RestValue.parseValue(SpatialType.class, value);
        return Optional.ofNullable(typeEnum).orElse(SpatialType.INTERSECTS);
    }
}
