package io.github.nichetoolkit.jts.shape;

import io.github.nichetoolkit.rest.RestException;
import org.geotools.data.shapefile.ShapefileDataStore;
import org.geotools.feature.FeatureIterator;
import org.opengis.feature.Property;
import org.opengis.feature.simple.SimpleFeature;

import java.io.Closeable;
import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * <p>ShapeReader</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public abstract class ShapeReader<T extends Shapefile> implements Closeable {

    public abstract Map<String, Object> params(File shapeFile) throws RestException;

    public abstract ShapefileDataStore dataStore() throws RestException;

    public abstract ShapefileDataStore dataStore(File shapeFile) throws RestException;

    public abstract ShapefileDataStore dataStore(Map<String, Object> params) throws RestException;

    public abstract FeatureIterator<SimpleFeature> features() throws RestException;

    public abstract FeatureIterator<SimpleFeature> features(File shapeFile) throws RestException;

    public abstract FeatureIterator<SimpleFeature> features(Map<String, Object> params) throws RestException;

    public abstract FeatureIterator<SimpleFeature> features(ShapefileDataStore dataStore) throws RestException;

    public abstract List<T> read() throws RestException;

    public abstract List<T> read(File shapeFile) throws RestException;

    public abstract List<T> read(Map<String, Object> params) throws RestException;

    public abstract List<T> read(ShapefileDataStore dataStore) throws RestException;

    public abstract List<T> read(FeatureIterator<SimpleFeature> features) throws RestException;

    public abstract T feature(SimpleFeature feature) throws RestException;

    public abstract void property(T shapefile, Property property) throws RestException;

}
