package io.github.nichetoolkit.jts;

import io.github.nichetoolkit.jts.error.JtsBoxInvalidException;
import io.github.nichetoolkit.jts.error.JtsParseException;
import io.github.nichetoolkit.rest.util.GeneralUtils;
import lombok.extern.slf4j.Slf4j;
import org.locationtech.jts.geom.Geometry;

/**
 * <code>JtsUtils</code>
 * <p>The jts utils class.</p>
 * @see  lombok.extern.slf4j.Slf4j
 * @see  java.lang.SuppressWarnings
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
@Slf4j
@SuppressWarnings("unused")
public class JtsUtils {

    /**
     * <code>parseWkt</code>
     * <p>The parse wkt method.</p>
     * @param box {@link io.github.nichetoolkit.jts.JtsBox} <p>The box parameter is <code>JtsBox</code> type.</p>
     * @see  io.github.nichetoolkit.jts.JtsBox
     * @see  java.lang.String
     * @return  {@link java.lang.String} <p>The parse wkt return object is <code>String</code> type.</p>
     */
    public static String parseWkt(JtsBox box) {
        try {
            return JtsHelper.parseWkt(box);
        } catch (JtsBoxInvalidException exception) {
            log.error("It is failed during parsing box to wkt!", exception);
            GeneralUtils.printStackTrace(log,exception,false);
        }
        return null;
    }

    /**
     * <code>parseGeojson</code>
     * <p>The parse geojson method.</p>
     * @param box {@link io.github.nichetoolkit.jts.JtsBox} <p>The box parameter is <code>JtsBox</code> type.</p>
     * @see  io.github.nichetoolkit.jts.JtsBox
     * @see  java.lang.String
     * @return  {@link java.lang.String} <p>The parse geojson return object is <code>String</code> type.</p>
     */
    public static String parseGeojson(JtsBox box) {
        try {
            return JtsHelper.parseGeojson(box);
        } catch (JtsParseException exception) {
            log.error("It is failed during parsing box to geojson!", exception);
            GeneralUtils.printStackTrace(log,exception,false);
        }
        return null;
    }

    /**
     * <code>parseBox</code>
     * <p>The parse box method.</p>
     * @param wktString {@link java.lang.String} <p>The wkt string parameter is <code>String</code> type.</p>
     * @see  java.lang.String
     * @see  io.github.nichetoolkit.jts.JtsBox
     * @return  {@link io.github.nichetoolkit.jts.JtsBox} <p>The parse box return object is <code>JtsBox</code> type.</p>
     */
    public static JtsBox parseBox(String wktString) {
        try {
            return JtsHelper.parseBox(wktString);
        } catch (JtsParseException exception) {
            log.error("It is failed during parsing wkt to box!", exception);
            GeneralUtils.printStackTrace(log,exception,false);
        }
        return null;
    }

    /**
     * <code>parseBox</code>
     * <p>The parse box method.</p>
     * @param geometry {@link org.locationtech.jts.geom.Geometry} <p>The geometry parameter is <code>Geometry</code> type.</p>
     * @see  org.locationtech.jts.geom.Geometry
     * @see  io.github.nichetoolkit.jts.JtsBox
     * @return  {@link io.github.nichetoolkit.jts.JtsBox} <p>The parse box return object is <code>JtsBox</code> type.</p>
     */
    public static JtsBox parseBox(Geometry geometry) {
        try {
            return JtsHelper.parseBox(geometry);
        } catch (JtsParseException exception) {
            log.error("It is failed during parsing geometry to box!", exception);
            GeneralUtils.printStackTrace(log,exception,false);
        }
        return null;
    }

    /**
     * <code>parseGeojson</code>
     * <p>The parse geojson method.</p>
     * @param geometry {@link org.locationtech.jts.geom.Geometry} <p>The geometry parameter is <code>Geometry</code> type.</p>
     * @see  org.locationtech.jts.geom.Geometry
     * @see  java.lang.String
     * @return  {@link java.lang.String} <p>The parse geojson return object is <code>String</code> type.</p>
     */
    public static String parseGeojson(Geometry geometry) {
        try {
            return JtsHelper.parseGeojson(geometry);
        } catch (JtsParseException exception) {
            log.error("It is failed during parsing geometry to geojson!", exception);
            GeneralUtils.printStackTrace(log,exception,false);
        }
        return null;
    }

    /**
     * <code>parseGeometry</code>
     * <p>The parse geometry method.</p>
     * @param wkbBytes byte <p>The wkb bytes parameter is <code>byte</code> type.</p>
     * @return  {@link org.locationtech.jts.geom.Geometry} <p>The parse geometry return object is <code>Geometry</code> type.</p>
     * @see  org.locationtech.jts.geom.Geometry
     */
    public static Geometry parseGeometry(byte[] wkbBytes) {
        try {
            return JtsHelper.parseGeometry(wkbBytes);
        } catch (JtsParseException exception) {
            log.error("It is failed during parsing wkb to geometry!", exception);
            GeneralUtils.printStackTrace(log,exception,false);
        }
        return null;
    }

    /**
     * <code>parseWkt</code>
     * <p>The parse wkt method.</p>
     * @param wkbBytes byte <p>The wkb bytes parameter is <code>byte</code> type.</p>
     * @return  {@link java.lang.String} <p>The parse wkt return object is <code>String</code> type.</p>
     * @see  java.lang.String
     */
    public static String parseWkt(byte[] wkbBytes) {
        try {
            return JtsHelper.parseWkt(wkbBytes);
        } catch (JtsParseException exception) {
            log.error("It is failed during parsing wkb to wkt!", exception);
            GeneralUtils.printStackTrace(log,exception,false);
        }
        return null;
    }

    /**
     * <code>parseWkb</code>
     * <p>The parse wkb method.</p>
     * @param geometry {@link org.locationtech.jts.geom.Geometry} <p>The geometry parameter is <code>Geometry</code> type.</p>
     * @see  org.locationtech.jts.geom.Geometry
     * @return byte <p>The parse wkb return object is <code>byte</code> type.</p>
     */
    public static byte[] parseWkb(Geometry geometry) {
        try {
            return JtsHelper.parseWkb(geometry);
        } catch (JtsParseException exception) {
            log.error("It is failed during parsing geometry to wkb!", exception);
            GeneralUtils.printStackTrace(log,exception,false);
        }
        return null;
    }

    /**
     * <code>parseWkt</code>
     * <p>The parse wkt method.</p>
     * @param geometry {@link org.locationtech.jts.geom.Geometry} <p>The geometry parameter is <code>Geometry</code> type.</p>
     * @see  org.locationtech.jts.geom.Geometry
     * @see  java.lang.String
     * @return  {@link java.lang.String} <p>The parse wkt return object is <code>String</code> type.</p>
     */
    public static String parseWkt(Geometry geometry) {
        try {
            return JtsHelper.parseWkt(geometry);
        } catch (JtsParseException exception) {
            log.error("It is failed during parsing geometry to wkt!", exception);
            GeneralUtils.printStackTrace(log,exception,false);
        }
        return null;
    }

    /**
     * <code>parseGeometry</code>
     * <p>The parse geometry method.</p>
     * @param wktString {@link java.lang.String} <p>The wkt string parameter is <code>String</code> type.</p>
     * @see  java.lang.String
     * @see  org.locationtech.jts.geom.Geometry
     * @return  {@link org.locationtech.jts.geom.Geometry} <p>The parse geometry return object is <code>Geometry</code> type.</p>
     */
    public static Geometry parseGeometry(String wktString) {
        try {
            return JtsHelper.parseGeometry(wktString);
        } catch (JtsParseException exception) {
            log.error("It is failed during parsing wkt to geometry!", exception);
            GeneralUtils.printStackTrace(log,exception,false);
        }
        return null;
    }

    /**
     * <code>parseWkb</code>
     * <p>The parse wkb method.</p>
     * @param wktString {@link java.lang.String} <p>The wkt string parameter is <code>String</code> type.</p>
     * @see  java.lang.String
     * @return byte <p>The parse wkb return object is <code>byte</code> type.</p>
     */
    public static byte[] parseWkb(String wktString) {
        try {
            return JtsHelper.parseWkb(wktString);
        } catch (JtsParseException exception) {
            log.error("It is failed during parsing wkt to wkb!", exception);
            GeneralUtils.printStackTrace(log,exception,false);
        }
        return null;
    }

    /**
     * <code>parseGeojson</code>
     * <p>The parse geojson method.</p>
     * @param geojson {@link java.lang.String} <p>The geojson parameter is <code>String</code> type.</p>
     * @see  java.lang.String
     * @see  org.locationtech.jts.geom.Geometry
     * @return  {@link org.locationtech.jts.geom.Geometry} <p>The parse geojson return object is <code>Geometry</code> type.</p>
     */
    public static Geometry parseGeojson(String geojson) {
        try {
            return JtsHelper.parseGeojson(geojson);
        } catch (JtsParseException exception) {
            log.error("It is failed during parsing geojson to geometry!", exception);
            GeneralUtils.printStackTrace(log,exception,false);
        }
        return null;
    }
}
