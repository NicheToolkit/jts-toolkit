package io.github.nichetoolkit.jts.serialization;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import io.github.nichetoolkit.jts.JtsParser;
import org.locationtech.jts.geom.Geometry;

import java.io.IOException;

/**
 * <code>JtsDeserializer</code>
 * <p>The jts deserializer class.</p>
 * @param <S>  {@link org.locationtech.jts.geom.Geometry} <p>The generic parameter is <code>Geometry</code> type.</p>
 * @see  org.locationtech.jts.geom.Geometry
 * @see  com.fasterxml.jackson.databind.JsonDeserializer
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
public class JtsDeserializer<S extends Geometry> extends JsonDeserializer<S> {

    /**
     * <code>jtsParser</code>
     * {@link io.github.nichetoolkit.jts.JtsParser} <p>The <code>jtsParser</code> field.</p>
     * @see  io.github.nichetoolkit.jts.JtsParser
     */
    private final JtsParser<S> jtsParser;

    /**
     * <code>JtsDeserializer</code>
     * <p>Instantiates a new jts deserializer.</p>
     * @param jtsParser {@link io.github.nichetoolkit.jts.JtsParser} <p>The jts parser parameter is <code>JtsParser</code> type.</p>
     * @see  io.github.nichetoolkit.jts.JtsParser
     */
    public JtsDeserializer(JtsParser<S> jtsParser) {
        this.jtsParser = jtsParser;
    }

    @Override
    public S deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        JsonNode root = jsonParser.getCodec().readTree(jsonParser);
        return jtsParser.parse(root);
    }
}
