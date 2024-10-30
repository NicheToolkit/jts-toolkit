package io.github.nichetoolkit.jts.parser;

import com.fasterxml.jackson.databind.JsonNode;
import io.github.nichetoolkit.jts.JtsParser;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.LinearRing;
import org.locationtech.jts.geom.Polygon;

import static io.github.nichetoolkit.jts.JtsGeojson.COORDINATES;

/**
 * <code>PolygonParser</code>
 * <p>The polygon parser class.</p>
 * @see  io.github.nichetoolkit.jts.JtsParser
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
public class PolygonParser extends JtsParser<Polygon> {

    /**
     * <code>PolygonParser</code>
     * <p>Instantiates a new polygon parser.</p>
     * @param geometryFactory {@link org.locationtech.jts.geom.GeometryFactory} <p>The geometry factory parameter is <code>GeometryFactory</code> type.</p>
     * @see  org.locationtech.jts.geom.GeometryFactory
     */
    public PolygonParser(GeometryFactory geometryFactory) {
        super(geometryFactory);
    }


    /**
     * <code>parsePolygon</code>
     * <p>The parse polygon method.</p>
     * @param arrayOfRings {@link com.fasterxml.jackson.databind.JsonNode} <p>The array of rings parameter is <code>JsonNode</code> type.</p>
     * @see  com.fasterxml.jackson.databind.JsonNode
     * @see  org.locationtech.jts.geom.Polygon
     * @return  {@link org.locationtech.jts.geom.Polygon} <p>The parse polygon return object is <code>Polygon</code> type.</p>
     */
    public Polygon parsePolygon(JsonNode arrayOfRings) {
        LinearRing shell = parseLinearRing(arrayOfRings.get(0));
        int size = arrayOfRings.size();
        LinearRing[] holes = new LinearRing[size - 1];
        for (int i = 1; i < size; i++) {
            holes[i - 1] = parseLinearRing(arrayOfRings.get(i));
        }
        return geometryFactory.createPolygon(shell, holes);
    }

    /**
     * <code>parseLinearRing</code>
     * <p>The parse linear ring method.</p>
     * @param coordinatesNode {@link com.fasterxml.jackson.databind.JsonNode} <p>The coordinates node parameter is <code>JsonNode</code> type.</p>
     * @see  com.fasterxml.jackson.databind.JsonNode
     * @see  org.locationtech.jts.geom.LinearRing
     * @return  {@link org.locationtech.jts.geom.LinearRing} <p>The parse linear ring return object is <code>LinearRing</code> type.</p>
     */
    private LinearRing parseLinearRing(JsonNode coordinatesNode) {
        assert coordinatesNode.isArray() : "expected coordinates array";
        Coordinate[] coordinates = PointParser.parseCoordinates(coordinatesNode);
        return geometryFactory.createLinearRing(coordinates);
    }


    @Override
    public Polygon parse(JsonNode jsonNode) {
        JsonNode arrayOfRings = jsonNode.get(COORDINATES);
        return parsePolygon(arrayOfRings);
    }
}
