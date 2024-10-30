package io.github.nichetoolkit.jts;

import com.fasterxml.jackson.databind.module.SimpleModule;
import io.github.nichetoolkit.jts.parser.*;
import io.github.nichetoolkit.jts.serialization.GeometrySerializer;
import io.github.nichetoolkit.jts.serialization.JtsDeserializer;
import org.locationtech.jts.geom.*;

/**
 * <code>JtsModule</code>
 * <p>The jts module class.</p>
 * @see  com.fasterxml.jackson.databind.module.SimpleModule
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
public class JtsModule extends SimpleModule {

    /**
     * <code>JtsModule</code>
     * <p>Instantiates a new jts module.</p>
     */
    public JtsModule() {
        this(new GeometryFactory());
    }

    /**
     * <code>JtsModule</code>
     * <p>Instantiates a new jts module.</p>
     * @param geometryFactory {@link org.locationtech.jts.geom.GeometryFactory} <p>The geometry factory parameter is <code>GeometryFactory</code> type.</p>
     * @see  org.locationtech.jts.geom.GeometryFactory
     */
    public JtsModule(GeometryFactory geometryFactory) {
        super("JtsModule", JtsVersion.VERSION);
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
