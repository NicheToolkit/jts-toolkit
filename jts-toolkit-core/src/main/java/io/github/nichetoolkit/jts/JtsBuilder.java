package io.github.nichetoolkit.jts;

import io.github.nichetoolkit.jts.enums.SpatialType;
import org.locationtech.jts.geom.Geometry;
import org.springframework.lang.NonNull;

/**
 * <p>JtsBuilder</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public class JtsBuilder {

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
