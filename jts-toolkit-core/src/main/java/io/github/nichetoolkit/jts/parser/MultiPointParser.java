package io.github.nichetoolkit.jts.parser;

import com.fasterxml.jackson.databind.JsonNode;
import io.github.nichetoolkit.jts.JtsParser;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.MultiPoint;

import static io.github.nichetoolkit.jts.JtsGeojson.COORDINATES;

/**
 * <code>MultiPointParser</code>
 * <p>The multi point parser class.</p>
 * @see  io.github.nichetoolkit.jts.JtsParser
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
public class MultiPointParser extends JtsParser<MultiPoint> {

    /**
     * <code>MultiPointParser</code>
     * <p>Instantiates a new multi point parser.</p>
     * @param geometryFactory {@link org.locationtech.jts.geom.GeometryFactory} <p>The geometry factory parameter is <code>GeometryFactory</code> type.</p>
     * @see  org.locationtech.jts.geom.GeometryFactory
     */
    public MultiPointParser(GeometryFactory geometryFactory) {
        super(geometryFactory);
    }

    /**
     * <code>parseMultiPoint</code>
     * <p>The parse multi point method.</p>
     * @param root {@link com.fasterxml.jackson.databind.JsonNode} <p>The root parameter is <code>JsonNode</code> type.</p>
     * @see  com.fasterxml.jackson.databind.JsonNode
     * @see  org.locationtech.jts.geom.MultiPoint
     * @return  {@link org.locationtech.jts.geom.MultiPoint} <p>The parse multi point return object is <code>MultiPoint</code> type.</p>
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
