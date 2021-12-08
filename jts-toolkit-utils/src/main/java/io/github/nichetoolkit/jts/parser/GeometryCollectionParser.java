package io.github.nichetoolkit.jts.parser;

import com.fasterxml.jackson.databind.JsonNode;
import io.github.nichetoolkit.jts.JtsParser;
import io.github.nichetoolkit.jts.error.JtsParserErrorException;
import org.locationtech.jts.geom.Geometry;
import org.locationtech.jts.geom.GeometryCollection;
import org.locationtech.jts.geom.GeometryFactory;

import static io.github.nichetoolkit.jts.JtsGeojson.GEOMETRIES;

/**
 * <p>GeometryCollectionParser</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public class GeometryCollectionParser extends JtsParser<GeometryCollection> {

    private final GeometryParser geometryParser;

    public GeometryCollectionParser(GeometryFactory geometryFactory, GeometryParser geometryParser) {
        super(geometryFactory);
        this.geometryParser = geometryParser;
    }

    private Geometry[] parseGeometries(JsonNode geometryArray) throws JtsParserErrorException {
        Geometry[] items = new Geometry[geometryArray.size()];
        for(int i=0;i!=geometryArray.size();++i) {
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
