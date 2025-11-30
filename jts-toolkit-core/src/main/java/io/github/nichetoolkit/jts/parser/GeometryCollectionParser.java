package io.github.nichetoolkit.jts.parser;

import tools.jackson.databind.JsonNode;
import io.github.nichetoolkit.jts.JtsParser;
import io.github.nichetoolkit.jts.error.JtsParserErrorException;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.GeometryCollection;
import org.locationtech.jts.geom.GeometryFactory;

import static io.github.nichetoolkit.jts.JtsGeojson.GEOMETRIES;

/**
 * <code>GeometryCollectionParser</code>
 * <p>The geometry collection parser class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see io.github.nichetoolkit.jts.JtsParser
 * @since Jdk17
 */
public class GeometryCollectionParser extends JtsParser<GeometryCollection> {

    private final GeometryParser geometryParser;

    /**
     * <code>GeometryCollectionParser</code>
     * <p>Instantiates a new geometry collection parser.</p>
     * @param geometryFactory {@link org.locationtech.jts.geom.GeometryFactory} <p>The geometry factory parameter is <code>GeometryFactory</code> type.</p>
     * @param geometryParser  {@link io.github.nichetoolkit.jts.parser.GeometryParser} <p>The geometry parser parameter is <code>GeometryParser</code> type.</p>
     * @see org.locationtech.jts.geom.GeometryFactory
     * @see io.github.nichetoolkit.jts.parser.GeometryParser
     */
    public GeometryCollectionParser(GeometryFactory geometryFactory, GeometryParser geometryParser) {
        super(geometryFactory);
        this.geometryParser = geometryParser;
    }

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
