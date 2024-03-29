package io.github.nichetoolkit.jts;

import com.fasterxml.jackson.databind.module.SimpleModule;
import io.github.nichetoolkit.jts.parser.*;
import io.github.nichetoolkit.jts.serialization.GeometrySerializer;
import io.github.nichetoolkit.jts.serialization.JtsDeserializer;
import org.locationtech.jts.geom.*;

@SuppressWarnings("unused")
public class JtsModule3D extends SimpleModule {

    public JtsModule3D() {
        this(new GeometryFactory());
    }

    public JtsModule3D(GeometryFactory geometryFactory) {
        super("JtsModule3D", JtsVersion.VERSION);
        addSerializer(Geometry.class, new GeometrySerializer());
        GeometryParser genericGeometryParser = new GeometryParser(geometryFactory);
        addDeserializer(Geometry.class, new JtsDeserializer<>(genericGeometryParser));
        addDeserializer(Point.class, new JtsDeserializer<>(new PointParser(geometryFactory)));
        addDeserializer(MultiPoint.class, new JtsDeserializer<>(new MultiPointParser(geometryFactory)));
        addDeserializer(LineString.class, new JtsDeserializer<>(new LineStringParser(geometryFactory)));
        addDeserializer(MultiLineString.class, new JtsDeserializer<>(new MultiLineStringParser(geometryFactory)));
        addDeserializer(Polygon.class, new JtsDeserializer<>(new PolygonParser(geometryFactory)));
        addDeserializer(MultiPolygon.class, new JtsDeserializer<>(new MultiPolygonParser(geometryFactory)));
        addDeserializer(GeometryCollection.class, new JtsDeserializer<>(new GeometryCollectionParser(geometryFactory, genericGeometryParser)));
    }

    @Override
    public void setupModule(SetupContext context) {
        super.setupModule(context);
    }
}
