package io.github.nichetoolkit.jts;

import io.github.nichetoolkit.jts.error.JtsBoxInvalidException;
import io.github.nichetoolkit.jts.error.JtsParseException;
import io.github.nichetoolkit.rest.util.common.GeneralUtils;
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
 * <p>JtsHelper</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public class JtsHelper {

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

    public static String parseGeojson(JtsBox box) throws JtsParseException {
        String wkt = parseWkt(box);
        Geometry geometry = parseGeometry(wkt);
        return parseGeojson(geometry);
    }

    public static JtsBox parseBox(String wktString) throws JtsParseException {
        if (GeneralUtils.isNotEmpty(wktString)) {
            try {
                WKTReader reader = new WKTReader();
                Polygon polygon = (Polygon) reader.read(wktString);
                return parseBox(polygon);
            } catch (ParseException exception) {
                String message = "wkt parse error，wkt = " + wktString;
                throw new JtsParseException("parse box",message,exception);
            }
        }
        return null;
    }

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

    public static String parseGeojson(Geometry geometry) throws JtsParseException {
        if (GeneralUtils.isNotEmpty(geometry)) {
            try {
                StringWriter writer = new StringWriter();
                GeometryJSON geometryJSON = new GeometryJSON(10);
                geometryJSON.write(geometry, writer);
                return writer.toString();
            } catch (IOException exception) {
                String message = "geometry parse error，geometry = " + geometry.toText();
                throw new JtsParseException("parse geojson",message,exception);
            }
        }
        return null;
    }

    public static Geometry parseGeometry(byte[] WkbBytes) throws JtsParseException {
        if (GeneralUtils.isNotEmpty(WkbBytes)) {
            try {
                WKBReader reader = new WKBReader();
                return reader.read(WkbBytes);
            } catch (ParseException exception) {
                String message = "wkb parse error，wkbBytes = " + new String(WkbBytes);
                throw new JtsParseException("parse geometry",message,exception);
            }
        }
        return null;
    }

    public static String parseWkt(byte[] wkbBytes) throws JtsParseException {
        if (GeneralUtils.isNotEmpty(wkbBytes)) {
            try {
                WKTWriter writer = new WKTWriter();
                WKBReader reader = new WKBReader();
                return writer.write(reader.read(wkbBytes));
            } catch (ParseException exception) {
                String message = "wkb parse error，wkb = " + new String(wkbBytes);
                throw new JtsParseException("parse wkt",message,exception);
            }
        }
        return null;
    }

    public static byte[] parseWkb(Geometry geometry) throws JtsParseException {
        if (GeneralUtils.isNotEmpty(geometry)) {
            try {
                WKBWriter writer = new WKBWriter();
                return writer.write(geometry);
            } catch (Exception exception) {
                String message = "geometry parse error，geometry = " + geometry.toText();
                throw new JtsParseException("parse wkb",message,exception);
            }
        }
        return null;
    }

    public static String parseWkt(Geometry geometry) throws JtsParseException {
        if (GeneralUtils.isNotEmpty(geometry)) {
            try {
                WKTWriter writer = new WKTWriter();
                return writer.write(geometry);
            } catch (Exception exception) {
                String message = "geometry parse error，geometry = " + geometry.toText();
                throw new JtsParseException("parse wkt",message,exception);
            }
        }
        return null;
    }

    public static Geometry parseGeometry(String wktString) throws JtsParseException {
        if (GeneralUtils.isNotEmpty(wktString)) {
            try {
                WKTReader reader = new WKTReader();
                return reader.read(wktString);
            } catch (ParseException exception) {
                String message = "wkt parse error，wkt = " + wktString;
                throw new JtsParseException("parse geometry",message,exception);
            }
        }
        return null;
    }

    public static byte[] parseWkb(String wktString) throws JtsParseException {
        if (GeneralUtils.isNotEmpty(wktString)) {
            try {
                WKTReader reader = new WKTReader();
                WKBWriter writer = new WKBWriter();
                return writer.write(reader.read(wktString));
            } catch (ParseException exception) {
                String message = "wkt parse error，wkt = " + wktString;
                throw new JtsParseException("parse geometry", message,exception);
            }
        }
        return null;
    }

    public static Geometry parseGeojson(String geojson) throws JtsParseException {
        if (GeneralUtils.isNotEmpty(geojson)) {
            try {
                GeometryJSON geometryJSON = new GeometryJSON();
                Reader reader = new StringReader(geojson);
                return geometryJSON.read(reader);
            } catch (IOException exception) {
                String message = "geoJson parse error，geoJson = " + geojson;
                throw new JtsParseException("parse geoJson",message,exception);
            }
        }
        return null;
    }
}
