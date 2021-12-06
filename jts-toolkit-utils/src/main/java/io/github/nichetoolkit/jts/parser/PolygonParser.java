package io.github.nichetoolkit.jts.parser;

import com.fasterxml.jackson.databind.JsonNode;
import io.github.nichetoolkit.jts.JtsParser;
import io.github.nichetoolkit.jts.error.JtsParseException;
import io.github.nichetoolkit.jts.error.JtsParserErrorException;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.LinearRing;
import org.locationtech.jts.geom.Polygon;

import static io.github.nichetoolkit.jts.JtsGeojson.COORDINATES;

/**
 * <p>PolygonParser</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public class PolygonParser extends JtsParser<Polygon> {

    public PolygonParser(GeometryFactory geometryFactory) {
        super(geometryFactory);
    }


    public Polygon parsePolygon(JsonNode arrayOfRings) {
        LinearRing shell = parseLinearRing(arrayOfRings.get(0));
        int size = arrayOfRings.size();
        LinearRing[] holes = new LinearRing[size - 1];
        for (int i = 1; i < size; i++) {
            holes[i - 1] = parseLinearRing(arrayOfRings.get(i));
        }
        return geometryFactory.createPolygon(shell, holes);
    }

    private LinearRing parseLinearRing(JsonNode coordinatesNode) {
        assert coordinatesNode.isArray() : "expected coordinates array";
        Coordinate[] coordinates = PointParser.parseCoordinates(coordinatesNode);
        return geometryFactory.createLinearRing(coordinates);
    }


    @Override
    public Polygon parse(JsonNode jsonNode) throws JtsParserErrorException {
        JsonNode arrayOfRings = jsonNode.get(COORDINATES);
        return parsePolygon(arrayOfRings);
    }
}
