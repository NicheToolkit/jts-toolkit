package io.github.nichetoolkit.jts.shape;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.github.nichetoolkit.jts.serialization.GeometryDeserializer;
import io.github.nichetoolkit.jts.serialization.GeometrySerializer;
import io.github.nichetoolkit.rest.util.GeneralUtils;
import lombok.Getter;
import lombok.Setter;
import org.geotools.geometry.jts.Geometries;
import org.locationtech.jts.geom.Geometry;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>Shapefile</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
@Getter
@Setter
@SuppressWarnings("unused")
public abstract class Shapefile implements Serializable {
    public static final String THE_GEOM = "the_geom";
    protected String featureId;
    protected String typeName;
    @JsonDeserialize(using = GeometryDeserializer.class)
    @JsonSerialize(using = GeometrySerializer.class)
    protected Geometry geometry;
    protected Geometries geometries;
    protected Map<String, Object> properties;

    public Shapefile() {
    }

    public Shapefile(String featureId) {
        this.featureId = featureId;
    }

    public Shapefile(Geometry geometry) {
        this.geometry = geometry;
        this.geometries = Geometries.get(geometry);
    }

    public Shapefile(String featureId, Geometry geometry) {
        this.featureId = featureId;
        this.geometry = geometry;
        this.geometries = Geometries.get(geometry);
    }

    public void setGeometry(Geometry geometry) {
        this.geometry = geometry;
        this.geometries = Geometries.get(geometry);
    }

    public void addProperties(String name, Object property) {
        if (GeneralUtils.isEmpty(this.properties)) {
            this.properties = new HashMap<>();
        }
        this.properties.putIfAbsent(name, property);
    }
}
