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
 * <code>Shapefile</code>
 * <p>The shapefile class.</p>
 * @see  java.io.Serializable
 * @see  lombok.Getter
 * @see  lombok.Setter
 * @see  java.lang.SuppressWarnings
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
@Getter
@Setter
@SuppressWarnings("unused")
public abstract class Shapefile implements Serializable {
    /**
     * <code>THE_GEOM</code>
     * {@link java.lang.String} <p>The constant <code>THE_GEOM</code> field.</p>
     * @see  java.lang.String
     */
    public static final String THE_GEOM = "the_geom";
    /**
     * <code>featureId</code>
     * {@link java.lang.String} <p>The <code>featureId</code> field.</p>
     * @see  java.lang.String
     */
    protected String featureId;
    /**
     * <code>typeName</code>
     * {@link java.lang.String} <p>The <code>typeName</code> field.</p>
     * @see  java.lang.String
     */
    protected String typeName;
    /**
     * <code>geometry</code>
     * {@link org.locationtech.jts.geom.Geometry} <p>The <code>geometry</code> field.</p>
     * @see  org.locationtech.jts.geom.Geometry
     * @see  com.fasterxml.jackson.databind.annotation.JsonDeserialize
     * @see  com.fasterxml.jackson.databind.annotation.JsonSerialize
     */
    @JsonDeserialize(using = GeometryDeserializer.class)
    @JsonSerialize(using = GeometrySerializer.class)
    protected Geometry geometry;
    /**
     * <code>geometries</code>
     * {@link org.geotools.geometry.jts.Geometries} <p>The <code>geometries</code> field.</p>
     * @see  org.geotools.geometry.jts.Geometries
     */
    protected Geometries geometries;
    /**
     * <code>properties</code>
     * {@link java.util.Map} <p>The <code>properties</code> field.</p>
     * @see  java.util.Map
     */
    protected Map<String, Object> properties;

    /**
     * <code>Shapefile</code>
     * <p>Instantiates a new shapefile.</p>
     */
    public Shapefile() {
    }

    /**
     * <code>Shapefile</code>
     * <p>Instantiates a new shapefile.</p>
     * @param featureId {@link java.lang.String} <p>The feature id parameter is <code>String</code> type.</p>
     * @see  java.lang.String
     */
    public Shapefile(String featureId) {
        this.featureId = featureId;
    }

    /**
     * <code>Shapefile</code>
     * <p>Instantiates a new shapefile.</p>
     * @param geometry {@link org.locationtech.jts.geom.Geometry} <p>The geometry parameter is <code>Geometry</code> type.</p>
     * @see  org.locationtech.jts.geom.Geometry
     */
    public Shapefile(Geometry geometry) {
        this.geometry = geometry;
        this.geometries = Geometries.get(geometry);
    }

    /**
     * <code>Shapefile</code>
     * <p>Instantiates a new shapefile.</p>
     * @param featureId {@link java.lang.String} <p>The feature id parameter is <code>String</code> type.</p>
     * @param geometry {@link org.locationtech.jts.geom.Geometry} <p>The geometry parameter is <code>Geometry</code> type.</p>
     * @see  java.lang.String
     * @see  org.locationtech.jts.geom.Geometry
     */
    public Shapefile(String featureId, Geometry geometry) {
        this.featureId = featureId;
        this.geometry = geometry;
        this.geometries = Geometries.get(geometry);
    }

    /**
     * <code>setGeometry</code>
     * <p>The set geometry setter method.</p>
     * @param geometry {@link org.locationtech.jts.geom.Geometry} <p>The geometry parameter is <code>Geometry</code> type.</p>
     * @see  org.locationtech.jts.geom.Geometry
     */
    public void setGeometry(Geometry geometry) {
        this.geometry = geometry;
        this.geometries = Geometries.get(geometry);
    }

    /**
     * <code>addProperties</code>
     * <p>The add properties method.</p>
     * @param name {@link java.lang.String} <p>The name parameter is <code>String</code> type.</p>
     * @param property {@link java.lang.Object} <p>The property parameter is <code>Object</code> type.</p>
     * @see  java.lang.String
     * @see  java.lang.Object
     */
    public void addProperties(String name, Object property) {
        if (GeneralUtils.isEmpty(this.properties)) {
            this.properties = new HashMap<>();
        }
        this.properties.putIfAbsent(name, property);
    }
}
