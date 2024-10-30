package io.github.nichetoolkit.jts.parser;

import com.fasterxml.jackson.databind.JsonNode;
import io.github.nichetoolkit.jts.JtsParser;
import io.github.nichetoolkit.jts.error.JtsParserErrorException;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.GeometryCollection;
import org.locationtech.jts.geom.GeometryFactory;

import static io.github.nichetoolkit.jts.JtsGeojson.GEOMETRIES;

/**
 * <code>GeometryCollectionParser</code>
 * <p>The geometry collection parser class.</p>
 * @see  io.github.nichetoolkit.jts.JtsParser
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
public class GeometryCollectionParser extends JtsParser<GeometryCollection> {

    /**
     * <code>geometryParser</code>
     * {@link io.github.nichetoolkit.jts.parser.GeometryParser} <p>The <code>geometryParser</code> field.</p>
     * @see  io.github.nichetoolkit.jts.parser.GeometryParser
     */
    private final GeometryParser geometryParser;

    /**
     * <code>GeometryCollectionParser</code>
     * <p>Instantiates a new geometry collection parser.</p>
     * @param geometryFactory {@link org.locationtech.jts.geom.GeometryFactory} <p>The geometry factory parameter is <code>GeometryFactory</code> type.</p>
     * @param geometryParser {@link io.github.nichetoolkit.jts.parser.GeometryParser} <p>The geometry parser parameter is <code>GeometryParser</code> type.</p>
     * @see  org.locationtech.jts.geom.GeometryFactory
     * @see  io.github.nichetoolkit.jts.parser.GeometryParser
     */
    public GeometryCollectionParser(GeometryFactory geometryFactory, GeometryParser geometryParser) {
        super(geometryFactory);
        this.geometryParser = geometryParser;
    }

    /**
     * <code>parseGeometries</code>
     * <p>The parse geometries method.</p>
     * @param geometryArray {@link com.fasterxml.jackson.databind.JsonNode} <p>The geometry array parameter is <code>JsonNode</code> type.</p>
     * @see  com.fasterxml.jackson.databind.JsonNode
     * @see  org.locationtech.jts.geom.Geometry
     * @see  io.github.nichetoolkit.jts.error.JtsParserErrorException
     * @return  {@link org.locationtech.jts.geom.Geometry} <p>The parse geometries return object is <code>Geometry</code> type.</p>
     * @throws JtsParserErrorException {@link io.github.nichetoolkit.jts.error.JtsParserErrorException} <p>The jts parser error exception is <code>JtsParserErrorException</code> type.</p>
     */
    private Geometry[] parseGeometries(JsonNode geometryArray) throws JtsParserErrorException {
        Geometry[] items = new Geometry[geometryArray.size()];
        for (int i = 0; i != geometryArray.size(); ++i) {
            items[i] = geometryParser.parse(geometryArray.get(i));
        }
        return items;
    }

    @Override
    public GeometryCollection parse(JsonNode node) throws JtsParserErrorException {
        Geometry[] geometries = parseGeometries(node.get(GEOMETRIES));
        return geometryFactory.createGeometryCollection(geometries);
    }
}
