package io.github.nichetoolkit.jts.shape;

import io.github.nichetoolkit.rest.RestException;

import java.io.File;
import java.util.List;

/**
 * <p>ShapeFactory</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public abstract class ShapeFactory<T extends Shapefile> {

    protected ShapeConfig shapeConfig;

    protected ShapeReader<T> shapeReader;

    protected ShapeWriter<T> shapeWriter;

    public ShapeFactory(ShapeConfig shapeConfig) {
        this.shapeConfig = shapeConfig;
    }

    public ShapeFactory(ShapeReader<T> shapeReader) {
        this.shapeReader = shapeReader;
    }

    public ShapeFactory(ShapeWriter<T> shapeWriter) {
        this.shapeWriter = shapeWriter;
    }

    public ShapeFactory(ShapeConfig shapeConfig, ShapeReader<T> shapeReader) {
        this.shapeConfig = shapeConfig;
        this.shapeReader = shapeReader;
    }

    public ShapeFactory(ShapeConfig shapeConfig, ShapeWriter<T> shapeWriter) {
        this.shapeConfig = shapeConfig;
        this.shapeWriter = shapeWriter;
    }

    public ShapeFactory(ShapeConfig shapeConfig, ShapeReader<T> shapeReader, ShapeWriter<T> shapeWriter) {
        this.shapeConfig = shapeConfig;
        this.shapeReader = shapeReader;
        this.shapeWriter = shapeWriter;
    }

    public ShapeConfig getShapeConfig() {
        return shapeConfig;
    }

    public void setShapeConfig(ShapeConfig shapeConfig) {
        this.shapeConfig = shapeConfig;
    }

    public abstract List<T> read(File shapefile) throws RestException;

    public abstract File write(File shapefile, List<T> shapefiles) throws RestException;

    public ShapeReader<T> getShapeReader() {
        return shapeReader;
    }

    public void setShapeReader(ShapeReader<T> shapeReader) {
        this.shapeReader = shapeReader;
    }

    public ShapeWriter<T> getShapeWriter() {
        return shapeWriter;
    }

    public void setShapeWriter(ShapeWriter<T> shapeWriter) {
        this.shapeWriter = shapeWriter;
    }
}
