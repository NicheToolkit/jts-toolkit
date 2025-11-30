package io.github.nichetoolkit.jts.parser;

import tools.jackson.databind.JsonNode;
import io.github.nichetoolkit.jts.JtsParser;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.MultiPolygon;
import org.locationtech.jts.geom.Polygon;

import static io.github.nichetoolkit.jts.JtsGeojson.COORDINATES;

/**
 * <code>MultiPolygonParser</code>
 * <p>The multi polygon parser class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.jts.JtsParser
 * @since Jdk17
 */
public class MultiPolygonParser extends JtsParser<MultiPolygon> {

    private final PolygonParser polygonParser;

    /**
     * <code>MultiPolygonParser</code>
     * <p>Instantiates a new multi polygon parser.</p>
     * @param geometryFactory {@link org.locationtech.jts.geom.GeometryFactory} <p>The geometry factory parameter is <code>GeometryFactory</code> type.</p>
     * @see org.locationtech.jts.geom.GeometryFactory
     */
    public MultiPolygonParser(GeometryFactory geometryFactory) {
        super(geometryFactory);
        polygonParser = new PolygonParser(geometryFactory);
    }

    /**
     * <code>parseMultiPolygon</code>
     * <p>The parse multi polygon method.</p>
     * @param root {@link tools.jackson.databind.JsonNode} <p>The root parameter is <code>JsonNode</code> type.</p>
     * @return {@link org.locationtech.jts.geom.MultiPolygon} <p>The parse multi polygon return object is <code>MultiPolygon</code> type.</p>
     * @see tools.jackson.databind.JsonNode
     * @see org.locationtech.jts.geom.MultiPolygon
     */
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
