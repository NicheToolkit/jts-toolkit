package io.github.nichetoolkit.jts.shape.simple;

import io.github.nichetoolkit.jts.shape.ShapeConfig;
import io.github.nichetoolkit.jts.shape.ShapeFactory;
import io.github.nichetoolkit.jts.shape.ShapeReader;
import io.github.nichetoolkit.jts.shape.ShapeWriter;
import io.github.nichetoolkit.rest.RestException;
import org.springframework.beans.factory.InitializingBean;

import java.io.File;
import java.util.List;

/**
 * <p>ShapeReaderFactory</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public class SimpleShapeFactory extends ShapeFactory<SimpleShapefile> implements InitializingBean {

    private static SimpleShapeFactory INSTANCE = null;

    public static SimpleShapeFactory getInstance() {
        return INSTANCE;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        INSTANCE = this;
    }

    public SimpleShapeFactory(ShapeConfig shapeConfig, ShapeReader<SimpleShapefile> shapeReader, ShapeWriter<SimpleShapefile> shapeWriter) {
        super(shapeConfig, shapeReader, shapeWriter);
    }

    @Override
    public List<SimpleShapefile> read(File shapefile) throws RestException {
        return shapeReader.read(shapefile);
    }

    public static List<SimpleShapefile> readShape(File shapefile) throws RestException {
        return INSTANCE.read(shapefile);
    }

    @Override
    public File write(File shapefile, List<SimpleShapefile> shapefiles) throws RestException {
        return shapeWriter.write(shapefile, shapefiles);
    }

    public static File writeShape(File shapefile, List<SimpleShapefile> shapefiles) throws RestException {
        return INSTANCE.write(shapefile, shapefiles);
    }

}
