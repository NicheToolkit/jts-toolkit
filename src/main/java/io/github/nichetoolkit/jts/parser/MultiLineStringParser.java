package io.github.nichetoolkit.jts.parser;

import com.fasterxml.jackson.databind.JsonNode;
import io.github.nichetoolkit.jts.JtsParser;
import io.github.nichetoolkit.jts.error.JtsParseException;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.LineString;
import org.locationtech.jts.geom.MultiLineString;

import static io.github.nichetoolkit.jts.JtsGeojson.COORDINATES;

/**
 * <p>MultiLineStringParser</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public class MultiLineStringParser extends JtsParser<MultiLineString> {
    public MultiLineStringParser(GeometryFactory geometryFactory) {
        super(geometryFactory);
    }

    public MultiLineString parseMultiLineString(JsonNode root) {
        LineString[] lineStrings = parseLineStrings(root.get(COORDINATES));
        return geometryFactory.createMultiLineString(lineStrings);
    }

    private LineString[] parseLineStrings(JsonNode array) {
        LineString[] strings = new LineString[array.size()];
        for (int i = 0; i != array.size(); ++i) {
            Coordinate[] coordinates = PointParser.parseCoordinates(array.get(i));
            strings[i] = geometryFactory.createLineString(coordinates);
        }
        return strings;
    }

    @Override
    public MultiLineString parse(JsonNode node) throws JtsParseException {
        return parseMultiLineString(node);
    }
}
