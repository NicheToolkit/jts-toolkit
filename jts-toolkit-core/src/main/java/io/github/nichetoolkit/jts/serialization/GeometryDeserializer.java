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
 * <code>GeometryDeserializer</code>
 * <p>The geometry deserializer class.</p>
 * @see  com.fasterxml.jackson.databind.JsonDeserializer
 * @see  java.lang.SuppressWarnings
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
@SuppressWarnings("unused")
public class GeometryDeserializer extends JsonDeserializer<Geometry> {

    /**
     * <code>jtsParser</code>
     * {@link io.github.nichetoolkit.jts.JtsParser} <p>The <code>jtsParser</code> field.</p>
     * @see  io.github.nichetoolkit.jts.JtsParser
     */
    private final JtsParser<?> jtsParser;

    /**
     * <code>GeometryDeserializer</code>
     * <p>Instantiates a new geometry deserializer.</p>
     */
    public GeometryDeserializer() {
        this.jtsParser = new GeometryParser(GEOMETRY_FACTORY);
    }

    @Override
    public Geometry deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        JsonNode root = jsonParser.getCodec().readTree(jsonParser);
        return jtsParser.parse(root);
    }
}
