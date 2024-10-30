package io.github.nichetoolkit.jts;


import org.locationtech.jts.geom.GeometryFactory;

/**
 * <code>JtsGeojson</code>
 * <p>The jts geojson class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
public class JtsGeojson {
    /**
     * <code>POINT</code>
     * {@link java.lang.String} <p>The constant <code>POINT</code> field.</p>
     * @see  java.lang.String
     */
    public static final String POINT = "Point";
    /**
     * <code>LINE_STRING</code>
     * {@link java.lang.String} <p>The constant <code>LINE_STRING</code> field.</p>
     * @see  java.lang.String
     */
    public static final String LINE_STRING = "LineString";
    /**
     * <code>POLYGON</code>
     * {@link java.lang.String} <p>The constant <code>POLYGON</code> field.</p>
     * @see  java.lang.String
     */
    public static final String POLYGON = "Polygon";

    /**
     * <code>MULTI_POINT</code>
     * {@link java.lang.String} <p>The constant <code>MULTI_POINT</code> field.</p>
     * @see  java.lang.String
     */
    public static final String MULTI_POINT = "MultiPoint";
    /**
     * <code>MULTI_LINE_STRING</code>
     * {@link java.lang.String} <p>The constant <code>MULTI_LINE_STRING</code> field.</p>
     * @see  java.lang.String
     */
    public static final String MULTI_LINE_STRING = "MultiLineString";
    /**
     * <code>MULTI_POLYGON</code>
     * {@link java.lang.String} <p>The constant <code>MULTI_POLYGON</code> field.</p>
     * @see  java.lang.String
     */
    public static final String MULTI_POLYGON = "MultiPolygon";

    /**
     * <code>GEOMETRY_COLLECTION</code>
     * {@link java.lang.String} <p>The constant <code>GEOMETRY_COLLECTION</code> field.</p>
     * @see  java.lang.String
     */
    public static final String GEOMETRY_COLLECTION = "GeometryCollection";

    /**
     * <code>TYPE</code>
     * {@link java.lang.String} <p>The constant <code>TYPE</code> field.</p>
     * @see  java.lang.String
     */
    public static final String TYPE = "type";

    /**
     * <code>GEOMETRIES</code>
     * {@link java.lang.String} <p>The constant <code>GEOMETRIES</code> field.</p>
     * @see  java.lang.String
     */
    public static final String GEOMETRIES = "geometries";

    /**
     * <code>COORDINATES</code>
     * {@link java.lang.String} <p>The constant <code>COORDINATES</code> field.</p>
     * @see  java.lang.String
     */
    public static final String COORDINATES = "coordinates";

    /**
     * <code>GEOMETRY_FACTORY</code>
     * {@link org.locationtech.jts.geom.GeometryFactory} <p>The constant <code>GEOMETRY_FACTORY</code> field.</p>
     * @see  org.locationtech.jts.geom.GeometryFactory
     */
    public static final GeometryFactory GEOMETRY_FACTORY = new GeometryFactory();
}
