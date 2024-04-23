package io.github.nichetoolkit.jts.parser;

import com.fasterxml.jackson.databind.JsonNode;
import io.github.nichetoolkit.jts.JtsParser;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.MultiPolygon;
import org.locationtech.jts.geom.Polygon;

import static io.github.nichetoolkit.jts.JtsGeojson.COORDINATES;

/**
 * <p>MultiPolygonParser</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public class MultiPolygonParser extends JtsParser<MultiPolygon> {

    private final PolygonParser polygonParser;

    public MultiPolygonParser(GeometryFactory geometryFactory) {
        super(geometryFactory);
        polygonParser = new PolygonParser(geometryFactory);
    }

    public MultiPolygon parseMultiPolygon(JsonNode root) {
        JsonNode arrayOfPolygons = root.get(COORDINATES);
        return geometryFactory.createMultiPolygon(parsePolygons(arrayOfPolygons));
    }

    private Polygon[] parsePolygons(JsonNode arrayOfPolygons) {
        Polygon[] polygons = new Polygon[arrayOfPolygons.size()];
        for (int i = 0; i != arrayOfPolygons.size(); ++i) {
            polygons[i] = polygonParser.parsePolygon(arrayOfPolygons.get(i));
        }
        return polygons;
    }

    @Override
    public MultiPolygon parse(JsonNode node) {
        return parseMultiPolygon(node);
    }
}
