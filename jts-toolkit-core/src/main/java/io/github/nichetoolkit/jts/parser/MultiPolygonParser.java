package io.github.nichetoolkit.jts.parser;

import com.fasterxml.jackson.databind.JsonNode;
import io.github.nichetoolkit.jts.JtsParser;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.MultiPolygon;
import org.locationtech.jts.geom.Polygon;

import static io.github.nichetoolkit.jts.JtsGeojson.COORDINATES;

/**
 * <code>MultiPolygonParser</code>
 * <p>The multi polygon parser class.</p>
 * @see  io.github.nichetoolkit.jts.JtsParser
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
public class MultiPolygonParser extends JtsParser<MultiPolygon> {

    /**
     * <code>polygonParser</code>
     * {@link io.github.nichetoolkit.jts.parser.PolygonParser} <p>The <code>polygonParser</code> field.</p>
     * @see  io.github.nichetoolkit.jts.parser.PolygonParser
     */
    private final PolygonParser polygonParser;

    /**
     * <code>MultiPolygonParser</code>
     * <p>Instantiates a new multi polygon parser.</p>
     * @param geometryFactory {@link org.locationtech.jts.geom.GeometryFactory} <p>The geometry factory parameter is <code>GeometryFactory</code> type.</p>
     * @see  org.locationtech.jts.geom.GeometryFactory
     */
    public MultiPolygonParser(GeometryFactory geometryFactory) {
        super(geometryFactory);
        polygonParser = new PolygonParser(geometryFactory);
    }

    /**
     * <code>parseMultiPolygon</code>
     * <p>The parse multi polygon method.</p>
     * @param root {@link com.fasterxml.jackson.databind.JsonNode} <p>The root parameter is <code>JsonNode</code> type.</p>
     * @see  com.fasterxml.jackson.databind.JsonNode
     * @see  org.locationtech.jts.geom.MultiPolygon
     * @return  {@link org.locationtech.jts.geom.MultiPolygon} <p>The parse multi polygon return object is <code>MultiPolygon</code> type.</p>
     */
    public MultiPolygon parseMultiPolygon(JsonNode root) {
        JsonNode arrayOfPolygons = root.get(COORDINATES);
        return geometryFactory.createMultiPolygon(parsePolygons(arrayOfPolygons));
    }

    /**
     * <code>parsePolygons</code>
     * <p>The parse polygons method.</p>
     * @param arrayOfPolygons {@link com.fasterxml.jackson.databind.JsonNode} <p>The array of polygons parameter is <code>JsonNode</code> type.</p>
     * @see  com.fasterxml.jackson.databind.JsonNode
     * @see  org.locationtech.jts.geom.Polygon
     * @return  {@link org.locationtech.jts.geom.Polygon} <p>The parse polygons return object is <code>Polygon</code> type.</p>
     */
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
