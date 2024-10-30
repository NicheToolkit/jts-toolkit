package io.github.nichetoolkit.jts;

import io.github.nichetoolkit.jts.enums.SpatialType;
import org.locationtech.jts.geom.Geometry;
import org.springframework.lang.NonNull;

/**
 * <code>JtsBuilder</code>
 * <p>The jts builder class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
public class JtsBuilder {

    /**
     * <code>build</code>
     * <p>The build method.</p>
     * @param alias {@link java.lang.String} <p>The alias parameter is <code>String</code> type.</p>
     * @param geometry {@link org.locationtech.jts.geom.Geometry} <p>The geometry parameter is <code>Geometry</code> type.</p>
     * @param spatialType {@link io.github.nichetoolkit.jts.enums.SpatialType} <p>The spatial type parameter is <code>SpatialType</code> type.</p>
     * @see  java.lang.String
     * @see  org.springframework.lang.NonNull
     * @see  org.locationtech.jts.geom.Geometry
     * @see  io.github.nichetoolkit.jts.enums.SpatialType
     * @see  java.lang.StringBuilder
     * @return  {@link java.lang.StringBuilder} <p>The build return object is <code>StringBuilder</code> type.</p>
     */
    public static StringBuilder build(@NonNull String alias, @NonNull Geometry geometry, SpatialType spatialType) {
        StringBuilder geometryBuilder = new StringBuilder("AND ");
        switch (spatialType) {
            case TOUCHES:
                geometryBuilder.append("st_touches(st_geometryfromtext('").append(geometry.toText()).append("'), ").append(alias).append(")");
                break;
            case OVERLAPS:
                geometryBuilder.append("st_overlaps(st_geometryfromtext('").append(geometry.toText()).append("'), ").append(alias).append(")");
                break;
            case CONTAINS:
                geometryBuilder.append("st_contains(st_geometryfromtext('").append(geometry.toText()).append("'), ").append(alias).append(")");
                break;
            case INTERSECTS:
                geometryBuilder.append("st_intersects(st_geometryfromtext('").append(geometry.toText()).append("'), ").append(alias).append(")");
                break;
        }
        return geometryBuilder;
    }

}
