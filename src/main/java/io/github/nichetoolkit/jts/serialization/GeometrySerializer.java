package io.github.nichetoolkit.jts.serialization;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import io.github.nichetoolkit.jts.error.JtsParseException;
import io.github.nichetoolkit.jts.error.JtsSerializeErrorException;
import io.github.nichetoolkit.rest.util.common.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.locationtech.jts.geom.*;

import java.io.IOException;
import java.util.Arrays;

import static io.github.nichetoolkit.jts.JtsGeojson.*;

/**
 * <p>GeometrySerializer</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
@Slf4j
public class GeometrySerializer extends JsonSerializer<Geometry> {

    public static String[] SUPPORTED_CLASS = new String[]{Point.class.getName(),
            LineString.class.getName(),
            Polygon.class.getName(),
            MultiPoint.class.getName(),
            MultiLineString.class.getName(),
            MultiPolygon.class.getName(),
            GeometryCollection.class.getName()};

    @Override
    public void serialize(Geometry value, JsonGenerator jsonGenerator, SerializerProvider provider) throws IOException {
        writeGeometry(jsonGenerator, value);
    }

    public void writeGeometry(JsonGenerator jsonGenerator, Geometry value) throws IOException {
        if (value instanceof Polygon) {
            writePolygon(jsonGenerator, (Polygon) value);
        } else if (value instanceof Point) {
            writePoint(jsonGenerator, (Point) value);
        } else if (value instanceof MultiPoint) {
            writeMultiPoint(jsonGenerator, (MultiPoint) value);
        } else if (value instanceof MultiPolygon) {
            writeMultiPolygon(jsonGenerator, (MultiPolygon) value);
        } else if (value instanceof LineString) {
            writeLineString(jsonGenerator, (LineString) value);
        } else if (value instanceof MultiLineString) {
            writeMultiLineString(jsonGenerator, (MultiLineString) value);
        } else if (value instanceof GeometryCollection) {
            writeGeometryCollection(jsonGenerator, (GeometryCollection) value);
        } else {
            throw new JtsSerializeErrorException("Geometry type "
                    + value.getClass().getName() + " cannot be serialized as Geojson." +
                    "Supported types are: " + Arrays.asList(
                    Point.class.getName(),
                    LineString.class.getName(),
                    Polygon.class.getName(),
                    MultiPoint.class.getName(),
                    MultiLineString.class.getName(),
                    MultiPolygon.class.getName(),
                    GeometryCollection.class.getName()));
        }
    }

    private void writeGeometryCollection(JsonGenerator jsonGenerator, GeometryCollection value) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField(TYPE, GEOMETRY_COLLECTION);
        jsonGenerator.writeArrayFieldStart(GEOMETRIES);
        for (int i = 0; i != value.getNumGeometries(); ++i) {
            writeGeometry(jsonGenerator, value.getGeometryN(i));
        }
        jsonGenerator.writeEndArray();
        jsonGenerator.writeEndObject();
    }

    private void writeMultiPoint(JsonGenerator jsonGenerator, MultiPoint value) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField(TYPE, MULTI_POINT);
        jsonGenerator.writeArrayFieldStart(COORDINATES);
        for (int i = 0; i != value.getNumGeometries(); ++i) {
            writePointCoords(jsonGenerator, (Point) value.getGeometryN(i));
        }
        jsonGenerator.writeEndArray();
        jsonGenerator.writeEndObject();
    }

    private void writeMultiLineString(JsonGenerator jsonGenerator, MultiLineString value) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField(TYPE, MULTI_LINE_STRING);
        jsonGenerator.writeArrayFieldStart(COORDINATES);
        for (int i = 0; i != value.getNumGeometries(); ++i) {
            writeLineStringCoords(jsonGenerator, (LineString) value.getGeometryN(i));
        }
        jsonGenerator.writeEndArray();
        jsonGenerator.writeEndObject();
    }

    @Override
    public Class<Geometry> handledType() {
        return Geometry.class;
    }

    private void writeMultiPolygon(JsonGenerator jsonGenerator, MultiPolygon value) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField(TYPE, MULTI_POLYGON);
        jsonGenerator.writeArrayFieldStart(COORDINATES);
        for (int i = 0; i != value.getNumGeometries(); ++i) {
            writePolygonCoordinates(jsonGenerator, (Polygon) value.getGeometryN(i));
        }
        jsonGenerator.writeEndArray();
        jsonGenerator.writeEndObject();
    }

    private void writePolygon(JsonGenerator jsonGenerator, Polygon value) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField(TYPE, POLYGON);
        jsonGenerator.writeFieldName(COORDINATES);
        writePolygonCoordinates(jsonGenerator, value);
        jsonGenerator.writeEndObject();
    }

    private void writePolygonCoordinates(JsonGenerator jsonGenerator, Polygon value) throws IOException {
        jsonGenerator.writeStartArray();
        writeLineStringCoords(jsonGenerator, value.getExteriorRing());
        for (int i = 0; i < value.getNumInteriorRing(); ++i) {
            writeLineStringCoords(jsonGenerator, value.getInteriorRingN(i));
        }
        jsonGenerator.writeEndArray();
    }

    private void writeLineStringCoords(JsonGenerator jsonGenerator, LineString ring) throws IOException {
        jsonGenerator.writeStartArray();
        for (int i = 0; i != ring.getNumPoints(); ++i) {
            Point p = ring.getPointN(i);
            writePointCoords(jsonGenerator, p);
        }
        jsonGenerator.writeEndArray();
    }

    private void writeLineString(JsonGenerator jsonGenerator, LineString lineString) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField(TYPE, LINE_STRING);
        jsonGenerator.writeFieldName(COORDINATES);
        writeLineStringCoords(jsonGenerator, lineString);
        jsonGenerator.writeEndObject();
    }

    private void writePoint(JsonGenerator jsonGenerator, Point p) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField(TYPE, POINT);
        jsonGenerator.writeFieldName(COORDINATES);
        writePointCoords(jsonGenerator, p);
        jsonGenerator.writeEndObject();
    }

    private void writePointCoords(JsonGenerator jsonGenerator, Point p) throws IOException {
        jsonGenerator.writeStartArray();
        jsonGenerator.writeNumber(p.getCoordinate().x);
        jsonGenerator.writeNumber(p.getCoordinate().y);
        if (!Double.isNaN(p.getCoordinate().z)) {
            jsonGenerator.writeNumber(p.getCoordinate().z);
        }
        jsonGenerator.writeEndArray();
    }
}
