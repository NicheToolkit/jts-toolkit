package io.github.nichetoolkit.jts.shape;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import io.github.nichetoolkit.jts.serialization.GeometryDeserializer;
import io.github.nichetoolkit.jts.serialization.GeometrySerializer;
import io.github.nichetoolkit.rest.util.GeneralUtils;
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

    public String getFeatureId() {
        return featureId;
    }

    public void setFeatureId(String featureId) {
        this.featureId = featureId;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public Geometry getGeometry() {
        return geometry;
    }

    public void setGeometry(Geometry geometry) {
        this.geometry = geometry;
        this.geometries = Geometries.get(geometry);
    }

    public Geometries getGeometries() {
        return geometries;
    }

    public void setGeometries(Geometries geometries) {
        this.geometries = geometries;
    }

    public Map<String, Object> getProperties() {
        return properties;
    }

    public void setProperties(Map<String, Object> properties) {
        this.properties = properties;
    }

    public void addProperties(String name, Object property) {
        if (GeneralUtils.isEmpty(this.properties)) {
            this.properties = new HashMap<>();
        }
        this.properties.putIfAbsent(name, property);
    }
}
