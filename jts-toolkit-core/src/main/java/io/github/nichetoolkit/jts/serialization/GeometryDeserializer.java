package io.github.nichetoolkit.jts.serialization;

import tools.jackson.core.JacksonException;
import tools.jackson.core.JsonParser;
import tools.jackson.databind.DeserializationContext;
import tools.jackson.databind.JsonNode;
import io.github.nichetoolkit.jts.JtsParser;
import io.github.nichetoolkit.jts.parser.GeometryParser;
import org.locationtech.jts.geom.Geometry;
import tools.jackson.databind.ValueDeserializer;

import static io.github.nichetoolkit.jts.JtsGeojson.GEOMETRY_FACTORY;

/**
 * <code>GeometryDeserializer</code>
 * <p>The geometry deserializer class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see tools.jackson.databind.ValueDeserializer
 * @see java.lang.SuppressWarnings
 * @since Jdk17
 */
@SuppressWarnings("unused")
public class GeometryDeserializer extends ValueDeserializer<Geometry> {

    private final JtsParser<?> jtsParser;

    /**
     * <code>GeometryDeserializer</code>
     * <p>Instantiates a new geometry deserializer.</p>
     */
    public GeometryDeserializer() {
        this.jtsParser = new GeometryParser(GEOMETRY_FACTORY);
    }

    @Override
    public Geometry deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws JacksonException {
        JsonNode root = jsonParser.readValueAsTree();
        return jtsParser.parse(root);
    }
}
