package io.github.nichetoolkit.jts.shape.simple;

import io.github.nichetoolkit.jts.shape.ShapeFactory;
import io.github.nichetoolkit.jts.shape.ShapeReader;
import io.github.nichetoolkit.jts.shape.ShapeWriter;
import io.github.nichetoolkit.rest.RestException;
import org.geotools.geometry.jts.Geometries;
import org.springframework.beans.factory.InitializingBean;

import java.io.File;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * <code>SimpleShapeFactory</code>
 * <p>The simple shape factory class.</p>
 * @see  io.github.nichetoolkit.jts.shape.ShapeFactory
 * @see  org.springframework.beans.factory.InitializingBean
 * @see  java.lang.SuppressWarnings
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
@SuppressWarnings("unused")
public class SimpleShapeFactory extends ShapeFactory<SimpleShapefile> implements InitializingBean {

    /**
     * <code>INSTANCE</code>
     * {@link io.github.nichetoolkit.jts.shape.simple.SimpleShapeFactory} <p>The constant <code>INSTANCE</code> field.</p>
     */
    private static SimpleShapeFactory INSTANCE = null;

    /**
     * <code>getInstance</code>
     * <p>The get instance getter method.</p>
     * @return  {@link io.github.nichetoolkit.jts.shape.simple.SimpleShapeFactory} <p>The get instance return object is <code>SimpleShapeFactory</code> type.</p>
     */
    public static SimpleShapeFactory getInstance() {
        return INSTANCE;
    }

    @Override
    public void afterPropertiesSet() {
        INSTANCE = this;
    }

    /**
     * <code>SimpleShapeFactory</code>
     * <p>Instantiates a new simple shape factory.</p>
     * @param shapeReader {@link io.github.nichetoolkit.jts.shape.ShapeReader} <p>The shape reader parameter is <code>ShapeReader</code> type.</p>
     * @param shapeWriter {@link io.github.nichetoolkit.jts.shape.ShapeWriter} <p>The shape writer parameter is <code>ShapeWriter</code> type.</p>
     * @see  io.github.nichetoolkit.jts.shape.ShapeReader
     * @see  io.github.nichetoolkit.jts.shape.ShapeWriter
     */
    public SimpleShapeFactory(ShapeReader<SimpleShapefile> shapeReader, ShapeWriter<SimpleShapefile> shapeWriter) {
        super(shapeReader, shapeWriter);
    }

    @Override
    public List<SimpleShapefile> read(File shapefile) throws RestException {
        return shapeReader.read(shapefile);
    }

    @Override
    public File write(File shapefile, List<SimpleShapefile> shapefiles) throws RestException {
        return shapeWriter.write(shapefile, shapefiles);
    }

    @Override
    public File write(SimpleShapefile shapefile) throws RestException {
        return shapeWriter.write(shapefile);
    }

    @Override
    public File write(Collection<SimpleShapefile> shapefiles) throws RestException {
        return shapeWriter.write(shapefiles);
    }

    @Override
    public File write(File shapeFile, Collection<SimpleShapefile> shapefiles) throws RestException {
        return shapeWriter.write(shapefiles);
    }

    @Override
    public File write(File shapeFile, Geometries geometries, List<SimpleShapefile> shapefiles) throws RestException {
        return shapeWriter.write(shapeFile, geometries, shapefiles);
    }


    @Override
    public File write(File shapeFile, Map<String, Class<?>> attributeClassMap, Collection<SimpleShapefile> shapefiles) throws RestException {
        return shapeWriter.write(shapeFile, attributeClassMap, shapefiles);
    }

    @Override
    public File write(File shapeFile, Geometries geometries, Map<String, Class<?>> attributeClassMap, Collection<SimpleShapefile> shapefiles) throws RestException {
        return shapeWriter.write(shapeFile, geometries, attributeClassMap, shapefiles);
    }

}
