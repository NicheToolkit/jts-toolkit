package io.github.nichetoolkit.jts.shape;

import io.github.nichetoolkit.rest.RestException;
import org.geotools.data.shapefile.ShapefileDataStore;
import org.geotools.feature.FeatureIterator;
import org.opengis.feature.Property;
import org.opengis.feature.simple.SimpleFeature;

import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * <code>ShapeReader</code>
 * <p>The shape reader class.</p>
 * @param <T>  {@link io.github.nichetoolkit.jts.shape.Shapefile} <p>The generic parameter is <code>Shapefile</code> type.</p>
 * @see  io.github.nichetoolkit.jts.shape.Shapefile
 * @see  java.lang.SuppressWarnings
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
@SuppressWarnings({"unused", "RedundantThrows"})
public abstract class ShapeReader<T extends Shapefile> {

    /**
     * <code>params</code>
     * <p>The params method.</p>
     * @param shapeFile {@link java.io.File} <p>The shape file parameter is <code>File</code> type.</p>
     * @see  java.io.File
     * @see  java.util.Map
     * @see  io.github.nichetoolkit.rest.RestException
     * @return  {@link java.util.Map} <p>The params return object is <code>Map</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     */
    public abstract Map<String, Object> params(File shapeFile) throws RestException;

    /**
     * <code>dataStore</code>
     * <p>The data store method.</p>
     * @return  {@link org.geotools.data.shapefile.ShapefileDataStore} <p>The data store return object is <code>ShapefileDataStore</code> type.</p>
     * @see  org.geotools.data.shapefile.ShapefileDataStore
     * @see  io.github.nichetoolkit.rest.RestException
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     */
    public abstract ShapefileDataStore dataStore() throws RestException;

    /**
     * <code>dataStore</code>
     * <p>The data store method.</p>
     * @param shapeFile {@link java.io.File} <p>The shape file parameter is <code>File</code> type.</p>
     * @see  java.io.File
     * @see  org.geotools.data.shapefile.ShapefileDataStore
     * @see  io.github.nichetoolkit.rest.RestException
     * @return  {@link org.geotools.data.shapefile.ShapefileDataStore} <p>The data store return object is <code>ShapefileDataStore</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     */
    public abstract ShapefileDataStore dataStore(File shapeFile) throws RestException;

    /**
     * <code>dataStore</code>
     * <p>The data store method.</p>
     * @param params {@link java.util.Map} <p>The params parameter is <code>Map</code> type.</p>
     * @see  java.util.Map
     * @see  org.geotools.data.shapefile.ShapefileDataStore
     * @see  io.github.nichetoolkit.rest.RestException
     * @return  {@link org.geotools.data.shapefile.ShapefileDataStore} <p>The data store return object is <code>ShapefileDataStore</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     */
    public abstract ShapefileDataStore dataStore(Map<String, Object> params) throws RestException;

    /**
     * <code>features</code>
     * <p>The features method.</p>
     * @return  {@link org.geotools.feature.FeatureIterator} <p>The features return object is <code>FeatureIterator</code> type.</p>
     * @see  org.geotools.feature.FeatureIterator
     * @see  io.github.nichetoolkit.rest.RestException
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     */
    public abstract FeatureIterator<SimpleFeature> features() throws RestException;

    /**
     * <code>features</code>
     * <p>The features method.</p>
     * @param shapeFile {@link java.io.File} <p>The shape file parameter is <code>File</code> type.</p>
     * @see  java.io.File
     * @see  org.geotools.feature.FeatureIterator
     * @see  io.github.nichetoolkit.rest.RestException
     * @return  {@link org.geotools.feature.FeatureIterator} <p>The features return object is <code>FeatureIterator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     */
    public abstract FeatureIterator<SimpleFeature> features(File shapeFile) throws RestException;

    /**
     * <code>features</code>
     * <p>The features method.</p>
     * @param params {@link java.util.Map} <p>The params parameter is <code>Map</code> type.</p>
     * @see  java.util.Map
     * @see  org.geotools.feature.FeatureIterator
     * @see  io.github.nichetoolkit.rest.RestException
     * @return  {@link org.geotools.feature.FeatureIterator} <p>The features return object is <code>FeatureIterator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     */
    public abstract FeatureIterator<SimpleFeature> features(Map<String, Object> params) throws RestException;

    /**
     * <code>features</code>
     * <p>The features method.</p>
     * @param dataStore {@link org.geotools.data.shapefile.ShapefileDataStore} <p>The data store parameter is <code>ShapefileDataStore</code> type.</p>
     * @see  org.geotools.data.shapefile.ShapefileDataStore
     * @see  org.geotools.feature.FeatureIterator
     * @see  io.github.nichetoolkit.rest.RestException
     * @return  {@link org.geotools.feature.FeatureIterator} <p>The features return object is <code>FeatureIterator</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     */
    public abstract FeatureIterator<SimpleFeature> features(ShapefileDataStore dataStore) throws RestException;

    /**
     * <code>read</code>
     * <p>The read method.</p>
     * @return  {@link java.util.List} <p>The read return object is <code>List</code> type.</p>
     * @see  java.util.List
     * @see  io.github.nichetoolkit.rest.RestException
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     */
    public abstract List<T> read() throws RestException;

    /**
     * <code>read</code>
     * <p>The read method.</p>
     * @param shapeFile {@link java.io.File} <p>The shape file parameter is <code>File</code> type.</p>
     * @see  java.io.File
     * @see  java.util.List
     * @see  io.github.nichetoolkit.rest.RestException
     * @return  {@link java.util.List} <p>The read return object is <code>List</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     */
    public abstract List<T> read(File shapeFile) throws RestException;

    /**
     * <code>read</code>
     * <p>The read method.</p>
     * @param params {@link java.util.Map} <p>The params parameter is <code>Map</code> type.</p>
     * @see  java.util.Map
     * @see  java.util.List
     * @see  io.github.nichetoolkit.rest.RestException
     * @return  {@link java.util.List} <p>The read return object is <code>List</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     */
    public abstract List<T> read(Map<String, Object> params) throws RestException;

    /**
     * <code>read</code>
     * <p>The read method.</p>
     * @param dataStore {@link org.geotools.data.shapefile.ShapefileDataStore} <p>The data store parameter is <code>ShapefileDataStore</code> type.</p>
     * @see  org.geotools.data.shapefile.ShapefileDataStore
     * @see  java.util.List
     * @see  io.github.nichetoolkit.rest.RestException
     * @return  {@link java.util.List} <p>The read return object is <code>List</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     */
    public abstract List<T> read(ShapefileDataStore dataStore) throws RestException;

    /**
     * <code>read</code>
     * <p>The read method.</p>
     * @param features {@link org.geotools.feature.FeatureIterator} <p>The features parameter is <code>FeatureIterator</code> type.</p>
     * @see  org.geotools.feature.FeatureIterator
     * @see  java.util.List
     * @see  io.github.nichetoolkit.rest.RestException
     * @return  {@link java.util.List} <p>The read return object is <code>List</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     */
    public abstract List<T> read(FeatureIterator<SimpleFeature> features) throws RestException;

    /**
     * <code>feature</code>
     * <p>The feature method.</p>
     * @param feature {@link org.opengis.feature.simple.SimpleFeature} <p>The feature parameter is <code>SimpleFeature</code> type.</p>
     * @see  org.opengis.feature.simple.SimpleFeature
     * @see  io.github.nichetoolkit.rest.RestException
     * @return T <p>The feature return object is <code>T</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     */
    public abstract T feature(SimpleFeature feature) throws RestException;

    /**
     * <code>property</code>
     * <p>The property method.</p>
     * @param shapefile T <p>The shapefile parameter is <code>T</code> type.</p>
     * @param property {@link org.opengis.feature.Property} <p>The property parameter is <code>Property</code> type.</p>
     * @see  org.opengis.feature.Property
     * @see  io.github.nichetoolkit.rest.RestException
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     */
    public abstract void property(T shapefile, Property property) throws RestException;

}
