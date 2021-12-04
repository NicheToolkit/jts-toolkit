package io.github.nichetoolkit.jts;


import org.locationtech.jts.geom.GeometryFactory;

public class JtsGeojson {
    public static final String POINT = "Point";
    public static final String LINE_STRING = "LineString";
    public static final String POLYGON = "Polygon";

    public static final String MULTI_POINT = "MultiPoint";
    public static final String MULTI_LINE_STRING = "MultiLineString";
    public static final String MULTI_POLYGON = "MultiPolygon";
    
    public static final String GEOMETRY_COLLECTION = "GeometryCollection";
    
    public static final String TYPE = "type";
    
    public static final String GEOMETRIES = "geometries";

    public static final String COORDINATES = "coordinates";

    public static final GeometryFactory GEOMETRY_FACTORY = new GeometryFactory();
}
