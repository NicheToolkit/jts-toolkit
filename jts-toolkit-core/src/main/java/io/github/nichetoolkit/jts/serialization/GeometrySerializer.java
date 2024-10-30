package io.github.nichetoolkit.jts.serialization;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import io.github.nichetoolkit.jts.error.JtsParserErrorException;
import lombok.extern.slf4j.Slf4j;
import org.locationtech.jts.geom.*;

import java.io.IOException;
import java.util.Arrays;

import static io.github.nichetoolkit.jts.JtsGeojson.*;

/**
 * <code>GeometrySerializer</code>
 * <p>The geometry serializer class.</p>
 * @see  com.fasterxml.jackson.databind.JsonSerializer
 * @see  lombok.extern.slf4j.Slf4j
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
@Slf4j
public class GeometrySerializer extends JsonSerializer<Geometry> {

    @Override
    public void serialize(Geometry value, JsonGenerator jsonGenerator, SerializerProvider provider) throws IOException {
        writeGeometry(jsonGenerator, value);
    }

    /**
     * <code>writeGeometry</code>
     * <p>The write geometry method.</p>
     * @param jsonGenerator {@link com.fasterxml.jackson.core.JsonGenerator} <p>The json generator parameter is <code>JsonGenerator</code> type.</p>
     * @param value {@link org.locationtech.jts.geom.Geometry} <p>The value parameter is <code>Geometry</code> type.</p>
     * @see  com.fasterxml.jackson.core.JsonGenerator
     * @see  org.locationtech.jts.geom.Geometry
     * @see  java.io.IOException
     * @throws IOException {@link java.io.IOException} <p>The io exception is <code>IOException</code> type.</p>
     */
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

    /**
     * <code>writeGeometryCollection</code>
     * <p>The write geometry collection method.</p>
     * @param jsonGenerator {@link com.fasterxml.jackson.core.JsonGenerator} <p>The json generator parameter is <code>JsonGenerator</code> type.</p>
     * @param value {@link org.locationtech.jts.geom.GeometryCollection} <p>The value parameter is <code>GeometryCollection</code> type.</p>
     * @see  com.fasterxml.jackson.core.JsonGenerator
     * @see  org.locationtech.jts.geom.GeometryCollection
     * @see  java.io.IOException
     * @throws IOException {@link java.io.IOException} <p>The io exception is <code>IOException</code> type.</p>
     */
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

    /**
     * <code>writeMultiPoint</code>
     * <p>The write multi point method.</p>
     * @param jsonGenerator {@link com.fasterxml.jackson.core.JsonGenerator} <p>The json generator parameter is <code>JsonGenerator</code> type.</p>
     * @param value {@link org.locationtech.jts.geom.MultiPoint} <p>The value parameter is <code>MultiPoint</code> type.</p>
     * @see  com.fasterxml.jackson.core.JsonGenerator
     * @see  org.locationtech.jts.geom.MultiPoint
     * @see  java.io.IOException
     * @throws IOException {@link java.io.IOException} <p>The io exception is <code>IOException</code> type.</p>
     */
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

    /**
     * <code>writeMultiLineString</code>
     * <p>The write multi line string method.</p>
     * @param jsonGenerator {@link com.fasterxml.jackson.core.JsonGenerator} <p>The json generator parameter is <code>JsonGenerator</code> type.</p>
     * @param value {@link org.locationtech.jts.geom.MultiLineString} <p>The value parameter is <code>MultiLineString</code> type.</p>
     * @see  com.fasterxml.jackson.core.JsonGenerator
     * @see  org.locationtech.jts.geom.MultiLineString
     * @see  java.io.IOException
     * @throws IOException {@link java.io.IOException} <p>The io exception is <code>IOException</code> type.</p>
     */
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

    /**
     * <code>writeMultiPolygon</code>
     * <p>The write multi polygon method.</p>
     * @param jsonGenerator {@link com.fasterxml.jackson.core.JsonGenerator} <p>The json generator parameter is <code>JsonGenerator</code> type.</p>
     * @param value {@link org.locationtech.jts.geom.MultiPolygon} <p>The value parameter is <code>MultiPolygon</code> type.</p>
     * @see  com.fasterxml.jackson.core.JsonGenerator
     * @see  org.locationtech.jts.geom.MultiPolygon
     * @see  java.io.IOException
     * @throws IOException {@link java.io.IOException} <p>The io exception is <code>IOException</code> type.</p>
     */
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

    /**
     * <code>writePolygon</code>
     * <p>The write polygon method.</p>
     * @param jsonGenerator {@link com.fasterxml.jackson.core.JsonGenerator} <p>The json generator parameter is <code>JsonGenerator</code> type.</p>
     * @param value {@link org.locationtech.jts.geom.Polygon} <p>The value parameter is <code>Polygon</code> type.</p>
     * @see  com.fasterxml.jackson.core.JsonGenerator
     * @see  org.locationtech.jts.geom.Polygon
     * @see  java.io.IOException
     * @throws IOException {@link java.io.IOException} <p>The io exception is <code>IOException</code> type.</p>
     */
    private void writePolygon(JsonGenerator jsonGenerator, Polygon value) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField(TYPE, POLYGON);
        jsonGenerator.writeFieldName(COORDINATES);
        writePolygonCoordinates(jsonGenerator, value);
        jsonGenerator.writeEndObject();
    }

    /**
     * <code>writePolygonCoordinates</code>
     * <p>The write polygon coordinates method.</p>
     * @param jsonGenerator {@link com.fasterxml.jackson.core.JsonGenerator} <p>The json generator parameter is <code>JsonGenerator</code> type.</p>
     * @param value {@link org.locationtech.jts.geom.Polygon} <p>The value parameter is <code>Polygon</code> type.</p>
     * @see  com.fasterxml.jackson.core.JsonGenerator
     * @see  org.locationtech.jts.geom.Polygon
     * @see  java.io.IOException
     * @throws IOException {@link java.io.IOException} <p>The io exception is <code>IOException</code> type.</p>
     */
    private void writePolygonCoordinates(JsonGenerator jsonGenerator, Polygon value) throws IOException {
        jsonGenerator.writeStartArray();
        writeLineStringCoords(jsonGenerator, value.getExteriorRing());
        for (int i = 0; i < value.getNumInteriorRing(); ++i) {
            writeLineStringCoords(jsonGenerator, value.getInteriorRingN(i));
        }
        jsonGenerator.writeEndArray();
    }

    /**
     * <code>writeLineStringCoords</code>
     * <p>The write line string coords method.</p>
     * @param jsonGenerator {@link com.fasterxml.jackson.core.JsonGenerator} <p>The json generator parameter is <code>JsonGenerator</code> type.</p>
     * @param ring {@link org.locationtech.jts.geom.LineString} <p>The ring parameter is <code>LineString</code> type.</p>
     * @see  com.fasterxml.jackson.core.JsonGenerator
     * @see  org.locationtech.jts.geom.LineString
     * @see  java.io.IOException
     * @throws IOException {@link java.io.IOException} <p>The io exception is <code>IOException</code> type.</p>
     */
    private void writeLineStringCoords(JsonGenerator jsonGenerator, LineString ring) throws IOException {
        jsonGenerator.writeStartArray();
        for (int i = 0; i != ring.getNumPoints(); ++i) {
            Point p = ring.getPointN(i);
            writePointCoords(jsonGenerator, p);
        }
        jsonGenerator.writeEndArray();
    }

    /**
     * <code>writeLineString</code>
     * <p>The write line string method.</p>
     * @param jsonGenerator {@link com.fasterxml.jackson.core.JsonGenerator} <p>The json generator parameter is <code>JsonGenerator</code> type.</p>
     * @param lineString {@link org.locationtech.jts.geom.LineString} <p>The line string parameter is <code>LineString</code> type.</p>
     * @see  com.fasterxml.jackson.core.JsonGenerator
     * @see  org.locationtech.jts.geom.LineString
     * @see  java.io.IOException
     * @throws IOException {@link java.io.IOException} <p>The io exception is <code>IOException</code> type.</p>
     */
    private void writeLineString(JsonGenerator jsonGenerator, LineString lineString) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField(TYPE, LINE_STRING);
        jsonGenerator.writeFieldName(COORDINATES);
        writeLineStringCoords(jsonGenerator, lineString);
        jsonGenerator.writeEndObject();
    }

    /**
     * <code>writePoint</code>
     * <p>The write point method.</p>
     * @param jsonGenerator {@link com.fasterxml.jackson.core.JsonGenerator} <p>The json generator parameter is <code>JsonGenerator</code> type.</p>
     * @param p {@link org.locationtech.jts.geom.Point} <p>The p parameter is <code>Point</code> type.</p>
     * @see  com.fasterxml.jackson.core.JsonGenerator
     * @see  org.locationtech.jts.geom.Point
     * @see  java.io.IOException
     * @throws IOException {@link java.io.IOException} <p>The io exception is <code>IOException</code> type.</p>
     */
    private void writePoint(JsonGenerator jsonGenerator, Point p) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField(TYPE, POINT);
        jsonGenerator.writeFieldName(COORDINATES);
        writePointCoords(jsonGenerator, p);
        jsonGenerator.writeEndObject();
    }

    /**
     * <code>writePointCoords</code>
     * <p>The write point coords method.</p>
     * @param jsonGenerator {@link com.fasterxml.jackson.core.JsonGenerator} <p>The json generator parameter is <code>JsonGenerator</code> type.</p>
     * @param p {@link org.locationtech.jts.geom.Point} <p>The p parameter is <code>Point</code> type.</p>
     * @see  com.fasterxml.jackson.core.JsonGenerator
     * @see  org.locationtech.jts.geom.Point
     * @see  java.io.IOException
     * @throws IOException {@link java.io.IOException} <p>The io exception is <code>IOException</code> type.</p>
     */
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
