package io.github.nichetoolkit.jts.shape.simple;

import io.github.nichetoolkit.jts.shape.Shapefile;
import org.locationtech.jts.geom.Geometry;

/**
 * <p>SimpleShapefile</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
@SuppressWarnings("unused")
public class SimpleShapefile extends Shapefile {

    public SimpleShapefile() {
    }

    public SimpleShapefile(String featureId) {
        super(featureId);
    }

    public SimpleShapefile(Geometry geometry) {
        super(geometry);
    }

    public SimpleShapefile(String featureId, Geometry geometry) {
        super(featureId, geometry);
    }
}
