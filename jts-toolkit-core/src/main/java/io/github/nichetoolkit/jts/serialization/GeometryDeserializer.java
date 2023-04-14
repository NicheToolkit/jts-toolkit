package io.github.nichetoolkit.jts.serialization;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import io.github.nichetoolkit.jts.JtsParser;
import io.github.nichetoolkit.jts.JtsUtils;
import io.github.nichetoolkit.jts.parser.GeometryParser;
import io.github.nichetoolkit.rest.util.GeneralUtils;
import org.locationtech.jts.geom.Geometry;

import java.io.IOException;

import static io.github.nichetoolkit.jts.JtsGeojson.GEOMETRY_FACTORY;

/**
 * <p>GeometryDeserializer</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
@SuppressWarnings("unused")
public class GeometryDeserializer extends JsonDeserializer<Geometry> {

    private final JtsParser jtsParser;

    public GeometryDeserializer() {
        this.jtsParser = new GeometryParser(GEOMETRY_FACTORY);
    }

    @Override
    public Geometry deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        JsonNode root = jsonParser.getCodec().readTree(jsonParser);
        JsonToken currentToken = jsonParser.getCurrentToken();
        if (JsonToken.VALUE_STRING == currentToken) {
            String geoJson = root.asText();
            if (GeneralUtils.isEmpty(geoJson)) {
                geoJson = root.toString();
            }
            return JtsUtils.parseGeojson(geoJson);
        } else {

            return jtsParser.parse(root);
        }
    }
}
