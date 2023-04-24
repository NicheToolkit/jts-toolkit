package io.github.nichetoolkit.jts;

import io.github.nichetoolkit.jts.error.JtsBoxInvalidException;
import io.github.nichetoolkit.jts.error.JtsParseException;
import lombok.extern.slf4j.Slf4j;
import org.locationtech.jts.geom.Geometry;

/**
 * <p>JtsUtils</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
@Slf4j
@SuppressWarnings("unused")
public class JtsUtils {

    public static String parseWkt(JtsBox box) {
        try {
            return JtsHelper.parseWkt(box);
        } catch (JtsBoxInvalidException exception) {
            log.error("It is failed during parsing box to wkt!", exception);
            exception.printStackTrace();
        }
        return null;
    }

    public static String parseGeojson(JtsBox box) {
        try {
            return JtsHelper.parseGeojson(box);
        } catch (JtsParseException exception) {
            log.error("It is failed during parsing box to geojson!", exception);
            exception.printStackTrace();
        }
        return null;
    }

    public static JtsBox parseBox(String wktString) {
        try {
            return JtsHelper.parseBox(wktString);
        } catch (JtsParseException exception) {
            log.error("It is failed during parsing wkt to box!", exception);
            exception.printStackTrace();
        }
        return null;
    }

    public static JtsBox parseBox(Geometry geometry) {
        try {
            return JtsHelper.parseBox(geometry);
        } catch (JtsParseException exception) {
            log.error("It is failed during parsing geometry to box!", exception);
            exception.printStackTrace();
        }
        return null;
    }

    public static String parseGeojson(Geometry geometry) {
        try {
            return JtsHelper.parseGeojson(geometry);
        } catch (JtsParseException exception) {
            log.error("It is failed during parsing geometry to geojson!", exception);
            exception.printStackTrace();
        }
        return null;
    }

    public static Geometry parseGeometry(byte[] wkbBytes) {
        try {
            return JtsHelper.parseGeometry(wkbBytes);
        } catch (JtsParseException exception) {
            log.error("It is failed during parsing wkb to geometry!", exception);
            exception.printStackTrace();
        }
        return null;
    }

    public static String parseWkt(byte[] wkbBytes) {
        try {
            return JtsHelper.parseWkt(wkbBytes);
        } catch (JtsParseException exception) {
            log.error("It is failed during parsing wkb to wkt!", exception);
            exception.printStackTrace();
        }
        return null;
    }

    public static byte[] parseWkb(Geometry geometry) {
        try {
            return JtsHelper.parseWkb(geometry);
        } catch (JtsParseException exception) {
            log.error("It is failed during parsing geometry to wkb!", exception);
            exception.printStackTrace();
        }
        return null;
    }

    public static String parseWkt(Geometry geometry) {
        try {
            return JtsHelper.parseWkt(geometry);
        } catch (JtsParseException exception) {
            log.error("It is failed during parsing geometry to wkt!", exception);
            exception.printStackTrace();
        }
        return null;
    }

    public static Geometry parseGeometry(String wktString) {
        try {
            return JtsHelper.parseGeometry(wktString);
        } catch (JtsParseException exception) {
            log.error("It is failed during parsing wkt to geometry!", exception);
            exception.printStackTrace();
        }
        return null;
    }

    public static byte[] parseWkb(String wktString) {
        try {
            return JtsHelper.parseWkb(wktString);
        } catch (JtsParseException exception) {
            log.error("It is failed during parsing wkt to wkb!", exception);
            exception.printStackTrace();
        }
        return null;
    }

    public static Geometry parseGeojson(String geojson) {
        try {
            return JtsHelper.parseGeojson(geojson);
        } catch (JtsParseException exception) {
            log.error("It is failed during parsing geojson to geometry!", exception);
            exception.printStackTrace();
        }
        return null;
    }
}
