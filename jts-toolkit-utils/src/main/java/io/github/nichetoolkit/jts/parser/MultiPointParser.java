package io.github.nichetoolkit.jts.parser;

import com.fasterxml.jackson.databind.JsonNode;
import io.github.nichetoolkit.jts.JtsParser;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.MultiPoint;

import static io.github.nichetoolkit.jts.JtsGeojson.COORDINATES;

/**
 * <p>MultiPointParser</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public class MultiPointParser extends JtsParser<MultiPoint> {

    public MultiPointParser(GeometryFactory geometryFactory) {
        super(geometryFactory);
    }

    public MultiPoint parseMultiPoint(JsonNode root) {
        Coordinate[] coordinates = PointParser.parseCoordinates(root.get(COORDINATES));
        return geometryFactory.createMultiPointFromCoords(coordinates);
    }

    @Override
    public MultiPoint parse(JsonNode node) {
        return parseMultiPoint(node);
    }
}
