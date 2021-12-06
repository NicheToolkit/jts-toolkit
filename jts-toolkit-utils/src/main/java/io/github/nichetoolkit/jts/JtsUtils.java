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
public class JtsUtils {

    public static String parseWkt(JtsBox box) {
        try {
            return JtsHelper.parseWkt(box);
        } catch (JtsBoxInvalidException exception) {
            exception.printStackTrace();
            log.error(exception.getMessage());
        }
        return null;
    }

    public static String parseGeojson(JtsBox box) {
        try {
            return JtsHelper.parseGeojson(box);
        } catch (JtsParseException exception) {
            exception.printStackTrace();
            log.error(exception.getMessage());
        }
        return null;
    }

    public static JtsBox parseBox(String WktString) {
        try {
            return JtsHelper.parseBox(WktString);
        } catch (JtsParseException exception) {
            exception.printStackTrace();
            log.error(exception.getMessage());
        }
        return null;
    }

    public static JtsBox parseBox(Geometry geometry) {
        try {
            return JtsHelper.parseBox(geometry);
        } catch (JtsParseException exception) {
            exception.printStackTrace();
            log.error(exception.getMessage());
        }
        return null;
    }

    public static String parseGeojson(Geometry geometry) {
        try {
            return JtsHelper.parseGeojson(geometry);
        } catch (JtsParseException exception) {
            exception.printStackTrace();
            log.error(exception.getMessage());
        }
        return null;
    }

    public static Geometry parseGeometry(byte[] WkbBytes) {
        try {
            return JtsHelper.parseGeometry(WkbBytes);
        } catch (JtsParseException exception) {
            exception.printStackTrace();
            log.error(exception.getMessage());
        }
        return null;
    }

    public static String parseWkt(byte[] WkbBytes) {
        try {
            return JtsHelper.parseWkt(WkbBytes);
        } catch (JtsParseException exception) {
            exception.printStackTrace();
            log.error(exception.getMessage());
        }
        return null;
    }

    public static byte[] parseWkb(Geometry geometry) {
        try {
            return JtsHelper.parseWkb(geometry);
        } catch (JtsParseException exception) {
            exception.printStackTrace();
            log.error(exception.getMessage());
        }
        return null;
    }

    public static String parseWkt(Geometry geometry) {
        try {
            return JtsHelper.parseWkt(geometry);
        } catch (JtsParseException exception) {
            exception.printStackTrace();
            log.error(exception.getMessage());
        }
        return null;
    }

    public static Geometry parseGeometry(String WktString) {
        try {
            return JtsHelper.parseGeometry(WktString);
        } catch (JtsParseException exception) {
            exception.printStackTrace();
            log.error(exception.getMessage());
        }
        return null;
    }

    public static byte[] parseWkb(String WktString) {
        try {
            return JtsHelper.parseWkb(WktString);
        } catch (JtsParseException exception) {
            exception.printStackTrace();
            log.error(exception.getMessage());
        }
        return null;
    }

    public static Geometry parseGeojson(String geojson) {
        try {
            return JtsHelper.parseGeojson(geojson);
        } catch (JtsParseException exception) {
            exception.printStackTrace();
            log.error(exception.getMessage());
        }
        return null;
    }
}
