package io.github.nichetoolkit.jts.shape.simple;

import io.github.nichetoolkit.jts.shape.Shapefile;
import org.locationtech.jts.geom.Geometry;

/**
 * <code>SimpleShapefile</code>
 * <p>The simple shapefile class.</p>
 * @see  io.github.nichetoolkit.jts.shape.Shapefile
 * @see  java.lang.SuppressWarnings
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
@SuppressWarnings("unused")
public class SimpleShapefile extends Shapefile {

    /**
     * <code>SimpleShapefile</code>
     * <p>Instantiates a new simple shapefile.</p>
     */
    public SimpleShapefile() {
    }

    /**
     * <code>SimpleShapefile</code>
     * <p>Instantiates a new simple shapefile.</p>
     * @param featureId {@link java.lang.String} <p>The feature id parameter is <code>String</code> type.</p>
     * @see  java.lang.String
     */
    public SimpleShapefile(String featureId) {
        super(featureId);
    }

    /**
     * <code>SimpleShapefile</code>
     * <p>Instantiates a new simple shapefile.</p>
     * @param geometry {@link org.locationtech.jts.geom.Geometry} <p>The geometry parameter is <code>Geometry</code> type.</p>
     * @see  org.locationtech.jts.geom.Geometry
     */
    public SimpleShapefile(Geometry geometry) {
        super(geometry);
    }

    /**
     * <code>SimpleShapefile</code>
     * <p>Instantiates a new simple shapefile.</p>
     * @param featureId {@link java.lang.String} <p>The feature id parameter is <code>String</code> type.</p>
     * @param geometry {@link org.locationtech.jts.geom.Geometry} <p>The geometry parameter is <code>Geometry</code> type.</p>
     * @see  java.lang.String
     * @see  org.locationtech.jts.geom.Geometry
     */
    public SimpleShapefile(String featureId, Geometry geometry) {
        super(featureId, geometry);
    }
}
