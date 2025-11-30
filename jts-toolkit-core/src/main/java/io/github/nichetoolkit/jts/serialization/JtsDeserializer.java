package io.github.nichetoolkit.jts.serialization;

import tools.jackson.core.JacksonException;
import tools.jackson.core.JsonParser;
import tools.jackson.databind.DeserializationContext;
import tools.jackson.databind.JsonNode;
import io.github.nichetoolkit.jts.JtsParser;
import org.locationtech.jts.geom.Geometry;
import tools.jackson.databind.ValueDeserializer;

/**
 * <code>JtsDeserializer</code>
 * <p>The jts deserializer class.</p>
 * @param <S> {@link org.locationtech.jts.geom.Geometry} <p>The generic parameter is <code>Geometry</code> type.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see org.locationtech.jts.geom.Geometry
 * @see tools.jackson.databind.ValueDeserializer
 * @since Jdk17
 */
public class JtsDeserializer<S extends Geometry> extends ValueDeserializer<S> {

    private final JtsParser<S> jtsParser;

    /**
     * <code>JtsDeserializer</code>
     * <p>Instantiates a new jts deserializer.</p>
     * @param jtsParser {@link io.github.nichetoolkit.jts.JtsParser} <p>The jts parser parameter is <code>JtsParser</code> type.</p>
     * @see io.github.nichetoolkit.jts.JtsParser
     */
    public JtsDeserializer(JtsParser<S> jtsParser) {
        this.jtsParser = jtsParser;
    }

    @Override
    public S deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws JacksonException {
        JsonNode root = jsonParser.readValueAsTree();
        return jtsParser.parse(root);
    }
}
