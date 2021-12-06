package io.github.nichetoolkit.jts.serialization;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import io.github.nichetoolkit.jts.JtsParser;
import io.github.nichetoolkit.jts.parser.GeometryParser;
import org.locationtech.jts.geom.Geometry;

import java.io.IOException;

import static io.github.nichetoolkit.jts.JtsGeojson.GEOMETRY_FACTORY;

/**
 * <p>GeometryDeserializer</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public class GeometryDeserializer extends JsonDeserializer<Geometry> {

    private JtsParser jtsParser;

    public GeometryDeserializer() {
        this.jtsParser = new GeometryParser(GEOMETRY_FACTORY);
    }

    @Override
    public Geometry deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        JsonNode root = jsonParser.getCodec().readTree(jsonParser);
        return jtsParser.parse(root);
    }
}
