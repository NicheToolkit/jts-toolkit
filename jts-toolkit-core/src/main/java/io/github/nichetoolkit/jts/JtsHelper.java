package io.github.nichetoolkit.jts;

import io.github.nichetoolkit.jts.error.JtsBoxInvalidException;
import io.github.nichetoolkit.jts.error.JtsParseException;
import io.github.nichetoolkit.rest.util.GeneralUtils;
import org.geotools.geojson.geom.GeometryJSON;
import org.geotools.geometry.jts.Geometries;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.Polygon;
import org.locationtech.jts.io.*;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;

/**
 * <code>JtsHelper</code>
 * <p>The jts helper class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
public class JtsHelper {

    /**
     * <code>parseWkt</code>
     * <p>The parse wkt method.</p>
     * @param box {@link io.github.nichetoolkit.jts.JtsBox} <p>The box parameter is <code>JtsBox</code> type.</p>
     * @see  io.github.nichetoolkit.jts.JtsBox
     * @see  java.lang.String
     * @see  io.github.nichetoolkit.jts.error.JtsBoxInvalidException
     * @return  {@link java.lang.String} <p>The parse wkt return object is <code>String</code> type.</p>
     * @throws JtsBoxInvalidException {@link io.github.nichetoolkit.jts.error.JtsBoxInvalidException} <p>The jts box invalid exception is <code>JtsBoxInvalidException</code> type.</p>
     */
    public static String parseWkt(JtsBox box) throws JtsBoxInvalidException {
        if (GeneralUtils.isNotEmpty(box)) {
            box.verify();
            return "POLYGON ((" +
                    box.getMaxX() + " " + box.getMaxY() + ", " +
                    box.getMinX() + " " + box.getMaxY() + ", " +
                    box.getMinX() + " " + box.getMinY() + ", " +
                    box.getMaxX() + " " + box.getMinY() + ", " +
                    box.getMaxX() + " " + box.getMaxY() + "))";
        }
        return null;
    }

    /**
     * <code>parseGeojson</code>
     * <p>The parse geojson method.</p>
     * @param box {@link io.github.nichetoolkit.jts.JtsBox} <p>The box parameter is <code>JtsBox</code> type.</p>
     * @see  io.github.nichetoolkit.jts.JtsBox
     * @see  java.lang.String
     * @see  io.github.nichetoolkit.jts.error.JtsParseException
     * @return  {@link java.lang.String} <p>The parse geojson return object is <code>String</code> type.</p>
     * @throws JtsParseException {@link io.github.nichetoolkit.jts.error.JtsParseException} <p>The jts parse exception is <code>JtsParseException</code> type.</p>
     */
    public static String parseGeojson(JtsBox box) throws JtsParseException {
        String wkt = parseWkt(box);
        Geometry geometry = parseGeometry(wkt);
        return parseGeojson(geometry);
    }

    /**
     * <code>parseBox</code>
     * <p>The parse box method.</p>
     * @param wktString {@link java.lang.String} <p>The wkt string parameter is <code>String</code> type.</p>
     * @see  java.lang.String
     * @see  io.github.nichetoolkit.jts.JtsBox
     * @see  io.github.nichetoolkit.jts.error.JtsParseException
     * @return  {@link io.github.nichetoolkit.jts.JtsBox} <p>The parse box return object is <code>JtsBox</code> type.</p>
     * @throws JtsParseException {@link io.github.nichetoolkit.jts.error.JtsParseException} <p>The jts parse exception is <code>JtsParseException</code> type.</p>
     */
    public static JtsBox parseBox(String wktString) throws JtsParseException {
        if (GeneralUtils.isNotEmpty(wktString)) {
            try {
                WKTReader reader = new WKTReader();
                Polygon polygon = (Polygon) reader.read(wktString);
                return parseBox(polygon);
            } catch (ParseException exception) {
                throw new JtsParseException("wkt parse to box", exception.getMessage());
            }
        }
        return null;
    }

    /**
     * <code>parseBox</code>
     * <p>The parse box method.</p>
     * @param geometry {@link org.locationtech.jts.geom.Geometry} <p>The geometry parameter is <code>Geometry</code> type.</p>
     * @see  org.locationtech.jts.geom.Geometry
     * @see  io.github.nichetoolkit.jts.JtsBox
     * @see  io.github.nichetoolkit.jts.error.JtsBoxInvalidException
     * @return  {@link io.github.nichetoolkit.jts.JtsBox} <p>The parse box return object is <code>JtsBox</code> type.</p>
     * @throws JtsBoxInvalidException {@link io.github.nichetoolkit.jts.error.JtsBoxInvalidException} <p>The jts box invalid exception is <code>JtsBoxInvalidException</code> type.</p>
     */
    public static JtsBox parseBox(Geometry geometry) throws JtsBoxInvalidException {
        if (GeneralUtils.isNotEmpty(geometry)) {
            Geometries geoType = Geometries.get(geometry);
            if (Geometries.POLYGON.equals(geoType)) {
                double minX = geometry.getEnvelopeInternal().getMinX();
                double minY = geometry.getEnvelopeInternal().getMinY();
                double maxX = geometry.getEnvelopeInternal().getMaxX();
                double maxY = geometry.getEnvelopeInternal().getMaxY();
                JtsBox box = new JtsBox(minX, minY, maxX, maxY);
                box.verify();
                return box;
            }
        }
        return null;
    }

    /**
     * <code>parseGeojson</code>
     * <p>The parse geojson method.</p>
     * @param geometry {@link org.locationtech.jts.geom.Geometry} <p>The geometry parameter is <code>Geometry</code> type.</p>
     * @see  org.locationtech.jts.geom.Geometry
     * @see  java.lang.String
     * @see  io.github.nichetoolkit.jts.error.JtsParseException
     * @return  {@link java.lang.String} <p>The parse geojson return object is <code>String</code> type.</p>
     * @throws JtsParseException {@link io.github.nichetoolkit.jts.error.JtsParseException} <p>The jts parse exception is <code>JtsParseException</code> type.</p>
     */
    public static String parseGeojson(Geometry geometry) throws JtsParseException {
        if (GeneralUtils.isNotEmpty(geometry)) {
            try {
                StringWriter writer = new StringWriter();
                GeometryJSON geometryJSON = new GeometryJSON(10);
                geometryJSON.write(geometry, writer);
                return writer.toString();
            } catch (IOException exception) {
                throw new JtsParseException("geometry parse to geojson", exception.getMessage());
            }
        }
        return null;
    }

    /**
     * <code>parseGeometry</code>
     * <p>The parse geometry method.</p>
     * @param wkbBytes byte <p>The wkb bytes parameter is <code>byte</code> type.</p>
     * @return  {@link org.locationtech.jts.geom.Geometry} <p>The parse geometry return object is <code>Geometry</code> type.</p>
     * @see  org.locationtech.jts.geom.Geometry
     * @see  io.github.nichetoolkit.jts.error.JtsParseException
     * @throws JtsParseException {@link io.github.nichetoolkit.jts.error.JtsParseException} <p>The jts parse exception is <code>JtsParseException</code> type.</p>
     */
    public static Geometry parseGeometry(byte[] wkbBytes) throws JtsParseException {
        if (GeneralUtils.isNotEmpty(wkbBytes)) {
            try {
                WKBReader reader = new WKBReader();
                return reader.read(wkbBytes);
            } catch (ParseException exception) {
                throw new JtsParseException("wkb parse to geometry", exception.getMessage());
            }
        }
        return null;
    }

    /**
     * <code>parseWkt</code>
     * <p>The parse wkt method.</p>
     * @param wkbBytes byte <p>The wkb bytes parameter is <code>byte</code> type.</p>
     * @return  {@link java.lang.String} <p>The parse wkt return object is <code>String</code> type.</p>
     * @see  java.lang.String
     * @see  io.github.nichetoolkit.jts.error.JtsParseException
     * @throws JtsParseException {@link io.github.nichetoolkit.jts.error.JtsParseException} <p>The jts parse exception is <code>JtsParseException</code> type.</p>
     */
    public static String parseWkt(byte[] wkbBytes) throws JtsParseException {
        if (GeneralUtils.isNotEmpty(wkbBytes)) {
            try {
                WKTWriter writer = new WKTWriter();
                WKBReader reader = new WKBReader();
                return writer.write(reader.read(wkbBytes));
            } catch (ParseException exception) {
                throw new JtsParseException("wkb parse to wkt", exception.getMessage());
            }
        }
        return null;
    }

    /**
     * <code>parseWkb</code>
     * <p>The parse wkb method.</p>
     * @param geometry {@link org.locationtech.jts.geom.Geometry} <p>The geometry parameter is <code>Geometry</code> type.</p>
     * @see  org.locationtech.jts.geom.Geometry
     * @see  io.github.nichetoolkit.jts.error.JtsParseException
     * @return byte <p>The parse wkb return object is <code>byte</code> type.</p>
     * @throws JtsParseException {@link io.github.nichetoolkit.jts.error.JtsParseException} <p>The jts parse exception is <code>JtsParseException</code> type.</p>
     */
    public static byte[] parseWkb(Geometry geometry) throws JtsParseException {
        if (GeneralUtils.isNotEmpty(geometry)) {
            try {
                WKBWriter writer = new WKBWriter();
                return writer.write(geometry);
            } catch (Exception exception) {
                throw new JtsParseException("geometry parse to wkb", exception.getMessage());
            }
        }
        return null;
    }

    /**
     * <code>parseWkt</code>
     * <p>The parse wkt method.</p>
     * @param geometry {@link org.locationtech.jts.geom.Geometry} <p>The geometry parameter is <code>Geometry</code> type.</p>
     * @see  org.locationtech.jts.geom.Geometry
     * @see  java.lang.String
     * @see  io.github.nichetoolkit.jts.error.JtsParseException
     * @return  {@link java.lang.String} <p>The parse wkt return object is <code>String</code> type.</p>
     * @throws JtsParseException {@link io.github.nichetoolkit.jts.error.JtsParseException} <p>The jts parse exception is <code>JtsParseException</code> type.</p>
     */
    public static String parseWkt(Geometry geometry) throws JtsParseException {
        if (GeneralUtils.isNotEmpty(geometry)) {
            try {
                WKTWriter writer = new WKTWriter();
                return writer.write(geometry);
            } catch (Exception exception) {
                throw new JtsParseException("geometry parse to wkt", exception.getMessage());
            }
        }
        return null;
    }

    /**
     * <code>parseGeometry</code>
     * <p>The parse geometry method.</p>
     * @param wktString {@link java.lang.String} <p>The wkt string parameter is <code>String</code> type.</p>
     * @see  java.lang.String
     * @see  org.locationtech.jts.geom.Geometry
     * @see  io.github.nichetoolkit.jts.error.JtsParseException
     * @return  {@link org.locationtech.jts.geom.Geometry} <p>The parse geometry return object is <code>Geometry</code> type.</p>
     * @throws JtsParseException {@link io.github.nichetoolkit.jts.error.JtsParseException} <p>The jts parse exception is <code>JtsParseException</code> type.</p>
     */
    public static Geometry parseGeometry(String wktString) throws JtsParseException {
        if (GeneralUtils.isNotEmpty(wktString)) {
            try {
                WKTReader reader = new WKTReader();
                return reader.read(wktString);
            } catch (ParseException exception) {
                throw new JtsParseException("wkt parse to geometry", exception.getMessage());
            }
        }
        return null;
    }

    /**
     * <code>parseWkb</code>
     * <p>The parse wkb method.</p>
     * @param wktString {@link java.lang.String} <p>The wkt string parameter is <code>String</code> type.</p>
     * @see  java.lang.String
     * @see  io.github.nichetoolkit.jts.error.JtsParseException
     * @return byte <p>The parse wkb return object is <code>byte</code> type.</p>
     * @throws JtsParseException {@link io.github.nichetoolkit.jts.error.JtsParseException} <p>The jts parse exception is <code>JtsParseException</code> type.</p>
     */
    public static byte[] parseWkb(String wktString) throws JtsParseException {
        if (GeneralUtils.isNotEmpty(wktString)) {
            try {
                WKTReader reader = new WKTReader();
                WKBWriter writer = new WKBWriter();
                return writer.write(reader.read(wktString));
            } catch (ParseException exception) {
                throw new JtsParseException("wkt parse to wkb", exception.getMessage());
            }
        }
        return null;
    }

    /**
     * <code>parseGeojson</code>
     * <p>The parse geojson method.</p>
     * @param geojson {@link java.lang.String} <p>The geojson parameter is <code>String</code> type.</p>
     * @see  java.lang.String
     * @see  org.locationtech.jts.geom.Geometry
     * @see  io.github.nichetoolkit.jts.error.JtsParseException
     * @return  {@link org.locationtech.jts.geom.Geometry} <p>The parse geojson return object is <code>Geometry</code> type.</p>
     * @throws JtsParseException {@link io.github.nichetoolkit.jts.error.JtsParseException} <p>The jts parse exception is <code>JtsParseException</code> type.</p>
     */
    public static Geometry parseGeojson(String geojson) throws JtsParseException {
        if (GeneralUtils.isNotEmpty(geojson)) {
            try {
                GeometryJSON geometryJSON = new GeometryJSON();
                Reader reader = new StringReader(geojson);
                return geometryJSON.read(reader);
            } catch (IOException exception) {
                throw new JtsParseException("geojson parse to geometry", exception.getMessage());
            }
        }
        return null;
    }
}
