package io.github.nichetoolkit.jts.parser;

import tools.jackson.databind.JsonNode;
import io.github.nichetoolkit.jts.JtsParser;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.MultiPoint;

import static io.github.nichetoolkit.jts.JtsGeojson.COORDINATES;

/**
 * <code>MultiPointParser</code>
 * <p>The multi point parser class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.jts.JtsParser
 * @since Jdk17
 */
public class MultiPointParser extends JtsParser<MultiPoint> {

    /**
     * <code>MultiPointParser</code>
     * <p>Instantiates a new multi point parser.</p>
     * @param geometryFactory {@link org.locationtech.jts.geom.GeometryFactory} <p>The geometry factory parameter is <code>GeometryFactory</code> type.</p>
     * @see org.locationtech.jts.geom.GeometryFactory
     */
    public MultiPointParser(GeometryFactory geometryFactory) {
        super(geometryFactory);
    }

    /**
     * <code>parseMultiPoint</code>
     * <p>The parse multi point method.</p>
     * @param root {@link tools.jackson.databind.JsonNode} <p>The root parameter is <code>JsonNode</code> type.</p>
     * @return {@link org.locationtech.jts.geom.MultiPoint} <p>The parse multi point return object is <code>MultiPoint</code> type.</p>
     * @see tools.jackson.databind.JsonNode
     * @see org.locationtech.jts.geom.MultiPoint
     */
    public MultiPoint parseMultiPoint(JsonNode root) {
        Coordinate[] coordinates = PointParser.parseCoordinates(root.get(COORDINATES));
        return geometryFactory.createMultiPointFromCoords(coordinates);
    }

    @Override
    public MultiPoint parse(JsonNode node) {
        return parseMultiPoint(node);
    }
}
