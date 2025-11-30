package io.github.nichetoolkit.jts.serialization;

import tools.jackson.core.JacksonException;
import tools.jackson.core.JsonGenerator;
import io.github.nichetoolkit.jts.error.JtsParserErrorException;
import lombok.extern.slf4j.Slf4j;
import org.locationtech.jts.geom.*;
import tools.jackson.databind.SerializationContext;
import tools.jackson.databind.ValueSerializer;

import java.util.Arrays;

import static io.github.nichetoolkit.jts.JtsGeojson.*;

/**
 * <code>GeometrySerializer</code>
 * <p>The geometry serializer class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see tools.jackson.databind.ValueSerializer
 * @see lombok.extern.slf4j.Slf4j
 * @since Jdk17
 */
@Slf4j
public class GeometrySerializer extends ValueSerializer<Geometry> {

    @Override
    public void serialize(Geometry value, JsonGenerator jsonGenerator, SerializationContext provider) throws JacksonException {
        writeGeometry(jsonGenerator, value);
    }

    /**
     * <code>writeGeometry</code>
     * <p>The write geometry method.</p>
     * @param jsonGenerator {@link tools.jackson.core.JsonGenerator} <p>The json generator parameter is <code>JsonGenerator</code> type.</p>
     * @param value         {@link org.locationtech.jts.geom.Geometry} <p>The value parameter is <code>Geometry</code> type.</p>
     * @throws JacksonException {@link tools.jackson.core.JacksonException} <p>The jackson exception is <code>JacksonException</code> type.</p>
     * @see tools.jackson.core.JsonGenerator
     * @see org.locationtech.jts.geom.Geometry
     * @see tools.jackson.core.JacksonException
     */
    public void writeGeometry(JsonGenerator jsonGenerator, Geometry value) throws JacksonException {
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
            throw new JtsParserErrorException("Geometry type "
                    + value.getClass().getName() + " cannot be serialized as geojson." +
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

    private void writeGeometryCollection(JsonGenerator jsonGenerator, GeometryCollection value) throws JacksonException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringProperty(TYPE, GEOMETRY_COLLECTION);
        jsonGenerator.writeArrayPropertyStart(GEOMETRIES);
        for (int i = 0; i != value.getNumGeometries(); ++i) {
            writeGeometry(jsonGenerator, value.getGeometryN(i));
        }
        jsonGenerator.writeEndArray();
        jsonGenerator.writeEndObject();
    }

    private void writeMultiPoint(JsonGenerator jsonGenerator, MultiPoint value) throws JacksonException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringProperty(TYPE, MULTI_POINT);
        jsonGenerator.writeArrayPropertyStart(COORDINATES);
        for (int i = 0; i != value.getNumGeometries(); ++i) {
            writePointCoords(jsonGenerator, (Point) value.getGeometryN(i));
        }
        jsonGenerator.writeEndArray();
        jsonGenerator.writeEndObject();
    }

    private void writeMultiLineString(JsonGenerator jsonGenerator, MultiLineString value) throws JacksonException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringProperty(TYPE, MULTI_LINE_STRING);
        jsonGenerator.writeArrayPropertyStart(COORDINATES);
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

    private void writeMultiPolygon(JsonGenerator jsonGenerator, MultiPolygon value) throws JacksonException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringProperty(TYPE, MULTI_POLYGON);
        jsonGenerator.writeArrayPropertyStart(COORDINATES);
        for (int i = 0; i != value.getNumGeometries(); ++i) {
            writePolygonCoordinates(jsonGenerator, (Polygon) value.getGeometryN(i));
        }
        jsonGenerator.writeEndArray();
        jsonGenerator.writeEndObject();
    }

    private void writePolygon(JsonGenerator jsonGenerator, Polygon value) throws JacksonException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringProperty(TYPE, POLYGON);
        jsonGenerator.writeName(COORDINATES);
        writePolygonCoordinates(jsonGenerator, value);
        jsonGenerator.writeEndObject();
    }

    private void writePolygonCoordinates(JsonGenerator jsonGenerator, Polygon value) throws JacksonException {
        jsonGenerator.writeStartArray();
        writeLineStringCoords(jsonGenerator, value.getExteriorRing());
        for (int i = 0; i < value.getNumInteriorRing(); ++i) {
            writeLineStringCoords(jsonGenerator, value.getInteriorRingN(i));
        }
        jsonGenerator.writeEndArray();
    }

    private void writeLineStringCoords(JsonGenerator jsonGenerator, LineString ring) throws JacksonException {
        jsonGenerator.writeStartArray();
        for (int i = 0; i != ring.getNumPoints(); ++i) {
            Point p = ring.getPointN(i);
            writePointCoords(jsonGenerator, p);
        }
        jsonGenerator.writeEndArray();
    }

    private void writeLineString(JsonGenerator jsonGenerator, LineString lineString) throws JacksonException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringProperty(TYPE, LINE_STRING);
        jsonGenerator.writeName(COORDINATES);
        writeLineStringCoords(jsonGenerator, lineString);
        jsonGenerator.writeEndObject();
    }

    private void writePoint(JsonGenerator jsonGenerator, Point p) throws JacksonException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringProperty(TYPE, POINT);
        jsonGenerator.writeName(COORDINATES);
        writePointCoords(jsonGenerator, p);
        jsonGenerator.writeEndObject();
    }

    private void writePointCoords(JsonGenerator jsonGenerator, Point p) throws JacksonException {
        jsonGenerator.writeStartArray();
        jsonGenerator.writeNumber(p.getCoordinate().x);
        jsonGenerator.writeNumber(p.getCoordinate().y);
        if (!Double.isNaN(p.getCoordinate().z)) {
            jsonGenerator.writeNumber(p.getCoordinate().z);
        }
        jsonGenerator.writeEndArray();
    }
}
