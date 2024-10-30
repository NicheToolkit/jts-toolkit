package io.github.nichetoolkit.jts.shape;

import io.github.nichetoolkit.rest.RestException;
import org.geotools.data.FeatureWriter;
import org.geotools.data.shapefile.ShapefileDataStore;
import org.geotools.feature.simple.SimpleFeatureTypeBuilder;
import org.geotools.geometry.jts.Geometries;
import org.opengis.feature.simple.SimpleFeature;
import org.opengis.feature.simple.SimpleFeatureType;

import java.io.File;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * <code>ShapeWriter</code>
 * <p>The shape writer class.</p>
 * @param <T>  {@link io.github.nichetoolkit.jts.shape.Shapefile} <p>The generic parameter is <code>Shapefile</code> type.</p>
 * @see  io.github.nichetoolkit.jts.shape.Shapefile
 * @see  java.lang.SuppressWarnings
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
@SuppressWarnings({"unused"})
public abstract class ShapeWriter<T extends Shapefile> {

    /**
     * <code>params</code>
     * <p>The params method.</p>
     * @param shapefile {@link java.io.File} <p>The shapefile parameter is <code>File</code> type.</p>
     * @see  java.io.File
     * @see  java.util.Map
     * @see  io.github.nichetoolkit.rest.RestException
     * @return  {@link java.util.Map} <p>The params return object is <code>Map</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     */
    public abstract Map<String, Serializable> params(File shapefile) throws RestException;

    /**
     * <code>typeBuilder</code>
     * <p>The type builder method.</p>
     * @param shapefile {@link java.io.File} <p>The shapefile parameter is <code>File</code> type.</p>
     * @see  java.io.File
     * @see  org.geotools.feature.simple.SimpleFeatureTypeBuilder
     * @see  io.github.nichetoolkit.rest.RestException
     * @return  {@link org.geotools.feature.simple.SimpleFeatureTypeBuilder} <p>The type builder return object is <code>SimpleFeatureTypeBuilder</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     */
    public abstract SimpleFeatureTypeBuilder typeBuilder(File shapefile) throws RestException;

    /**
     * <code>typeBuilder</code>
     * <p>The type builder method.</p>
     * @param filename {@link java.lang.String} <p>The filename parameter is <code>String</code> type.</p>
     * @see  java.lang.String
     * @see  org.geotools.feature.simple.SimpleFeatureTypeBuilder
     * @see  io.github.nichetoolkit.rest.RestException
     * @return  {@link org.geotools.feature.simple.SimpleFeatureTypeBuilder} <p>The type builder return object is <code>SimpleFeatureTypeBuilder</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     */
    public abstract SimpleFeatureTypeBuilder typeBuilder(String filename) throws RestException;

    /**
     * <code>typeBuilder</code>
     * <p>The type builder method.</p>
     * @param shapefile {@link java.io.File} <p>The shapefile parameter is <code>File</code> type.</p>
     * @param geometries {@link org.geotools.geometry.jts.Geometries} <p>The geometries parameter is <code>Geometries</code> type.</p>
     * @see  java.io.File
     * @see  org.geotools.geometry.jts.Geometries
     * @see  org.geotools.feature.simple.SimpleFeatureTypeBuilder
     * @see  io.github.nichetoolkit.rest.RestException
     * @return  {@link org.geotools.feature.simple.SimpleFeatureTypeBuilder} <p>The type builder return object is <code>SimpleFeatureTypeBuilder</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     */
    public abstract SimpleFeatureTypeBuilder typeBuilder(File shapefile, Geometries geometries) throws RestException;

    /**
     * <code>typeBuilder</code>
     * <p>The type builder method.</p>
     * @param filename {@link java.lang.String} <p>The filename parameter is <code>String</code> type.</p>
     * @param geometries {@link org.geotools.geometry.jts.Geometries} <p>The geometries parameter is <code>Geometries</code> type.</p>
     * @see  java.lang.String
     * @see  org.geotools.geometry.jts.Geometries
     * @see  org.geotools.feature.simple.SimpleFeatureTypeBuilder
     * @see  io.github.nichetoolkit.rest.RestException
     * @return  {@link org.geotools.feature.simple.SimpleFeatureTypeBuilder} <p>The type builder return object is <code>SimpleFeatureTypeBuilder</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     */
    public abstract SimpleFeatureTypeBuilder typeBuilder(String filename, Geometries geometries) throws RestException;

    /**
     * <code>typeBuilder</code>
     * <p>The type builder method.</p>
     * @param shapefile {@link java.io.File} <p>The shapefile parameter is <code>File</code> type.</p>
     * @param attributeClassMap {@link java.util.Map} <p>The attribute class map parameter is <code>Map</code> type.</p>
     * @see  java.io.File
     * @see  java.util.Map
     * @see  org.geotools.feature.simple.SimpleFeatureTypeBuilder
     * @see  io.github.nichetoolkit.rest.RestException
     * @return  {@link org.geotools.feature.simple.SimpleFeatureTypeBuilder} <p>The type builder return object is <code>SimpleFeatureTypeBuilder</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     */
    public abstract SimpleFeatureTypeBuilder typeBuilder(File shapefile, Map<String, Class<?>> attributeClassMap) throws RestException;

    /**
     * <code>typeBuilder</code>
     * <p>The type builder method.</p>
     * @param filename {@link java.lang.String} <p>The filename parameter is <code>String</code> type.</p>
     * @param attributeClassMap {@link java.util.Map} <p>The attribute class map parameter is <code>Map</code> type.</p>
     * @see  java.lang.String
     * @see  java.util.Map
     * @see  org.geotools.feature.simple.SimpleFeatureTypeBuilder
     * @see  io.github.nichetoolkit.rest.RestException
     * @return  {@link org.geotools.feature.simple.SimpleFeatureTypeBuilder} <p>The type builder return object is <code>SimpleFeatureTypeBuilder</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     */
    public abstract SimpleFeatureTypeBuilder typeBuilder(String filename, Map<String, Class<?>> attributeClassMap) throws RestException;

    /**
     * <code>typeBuilder</code>
     * <p>The type builder method.</p>
     * @param shapefile {@link java.io.File} <p>The shapefile parameter is <code>File</code> type.</p>
     * @param geometries {@link org.geotools.geometry.jts.Geometries} <p>The geometries parameter is <code>Geometries</code> type.</p>
     * @param attributeClassMap {@link java.util.Map} <p>The attribute class map parameter is <code>Map</code> type.</p>
     * @see  java.io.File
     * @see  org.geotools.geometry.jts.Geometries
     * @see  java.util.Map
     * @see  org.geotools.feature.simple.SimpleFeatureTypeBuilder
     * @see  io.github.nichetoolkit.rest.RestException
     * @return  {@link org.geotools.feature.simple.SimpleFeatureTypeBuilder} <p>The type builder return object is <code>SimpleFeatureTypeBuilder</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     */
    public abstract SimpleFeatureTypeBuilder typeBuilder(File shapefile, Geometries geometries, Map<String, Class<?>> attributeClassMap) throws RestException;

    /**
     * <code>typeBuilder</code>
     * <p>The type builder method.</p>
     * @param filename {@link java.lang.String} <p>The filename parameter is <code>String</code> type.</p>
     * @param geometries {@link org.geotools.geometry.jts.Geometries} <p>The geometries parameter is <code>Geometries</code> type.</p>
     * @param attributeClassMap {@link java.util.Map} <p>The attribute class map parameter is <code>Map</code> type.</p>
     * @see  java.lang.String
     * @see  org.geotools.geometry.jts.Geometries
     * @see  java.util.Map
     * @see  org.geotools.feature.simple.SimpleFeatureTypeBuilder
     * @see  io.github.nichetoolkit.rest.RestException
     * @return  {@link org.geotools.feature.simple.SimpleFeatureTypeBuilder} <p>The type builder return object is <code>SimpleFeatureTypeBuilder</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     */
    public abstract SimpleFeatureTypeBuilder typeBuilder(String filename, Geometries geometries, Map<String, Class<?>> attributeClassMap) throws RestException;

    /**
     * <code>dataStore</code>
     * <p>The data store method.</p>
     * @param shapefile {@link java.io.File} <p>The shapefile parameter is <code>File</code> type.</p>
     * @see  java.io.File
     * @see  org.geotools.data.shapefile.ShapefileDataStore
     * @see  io.github.nichetoolkit.rest.RestException
     * @return  {@link org.geotools.data.shapefile.ShapefileDataStore} <p>The data store return object is <code>ShapefileDataStore</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     */
    public abstract ShapefileDataStore dataStore(File shapefile) throws RestException;

    /**
     * <code>dataStore</code>
     * <p>The data store method.</p>
     * @param params {@link java.util.Map} <p>The params parameter is <code>Map</code> type.</p>
     * @param typeBuilder {@link org.geotools.feature.simple.SimpleFeatureTypeBuilder} <p>The type builder parameter is <code>SimpleFeatureTypeBuilder</code> type.</p>
     * @see  java.util.Map
     * @see  org.geotools.feature.simple.SimpleFeatureTypeBuilder
     * @see  org.geotools.data.shapefile.ShapefileDataStore
     * @see  io.github.nichetoolkit.rest.RestException
     * @return  {@link org.geotools.data.shapefile.ShapefileDataStore} <p>The data store return object is <code>ShapefileDataStore</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     */
    public abstract ShapefileDataStore dataStore(Map<String, Serializable> params, SimpleFeatureTypeBuilder typeBuilder) throws RestException;

    /**
     * <code>dataStore</code>
     * <p>The data store method.</p>
     * @param shapefile {@link java.io.File} <p>The shapefile parameter is <code>File</code> type.</p>
     * @param typeBuilder {@link org.geotools.feature.simple.SimpleFeatureTypeBuilder} <p>The type builder parameter is <code>SimpleFeatureTypeBuilder</code> type.</p>
     * @see  java.io.File
     * @see  org.geotools.feature.simple.SimpleFeatureTypeBuilder
     * @see  org.geotools.data.shapefile.ShapefileDataStore
     * @see  io.github.nichetoolkit.rest.RestException
     * @return  {@link org.geotools.data.shapefile.ShapefileDataStore} <p>The data store return object is <code>ShapefileDataStore</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     */
    public abstract ShapefileDataStore dataStore(File shapefile, SimpleFeatureTypeBuilder typeBuilder) throws RestException;

    /**
     * <code>dataStore</code>
     * <p>The data store method.</p>
     * @param shapefile {@link java.io.File} <p>The shapefile parameter is <code>File</code> type.</p>
     * @param geometries {@link org.geotools.geometry.jts.Geometries} <p>The geometries parameter is <code>Geometries</code> type.</p>
     * @see  java.io.File
     * @see  org.geotools.geometry.jts.Geometries
     * @see  org.geotools.data.shapefile.ShapefileDataStore
     * @see  io.github.nichetoolkit.rest.RestException
     * @return  {@link org.geotools.data.shapefile.ShapefileDataStore} <p>The data store return object is <code>ShapefileDataStore</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     */
    public abstract ShapefileDataStore dataStore(File shapefile, Geometries geometries) throws RestException;

    /**
     * <code>dataStore</code>
     * <p>The data store method.</p>
     * @param shapefile {@link java.io.File} <p>The shapefile parameter is <code>File</code> type.</p>
     * @param filename {@link java.lang.String} <p>The filename parameter is <code>String</code> type.</p>
     * @param geometries {@link org.geotools.geometry.jts.Geometries} <p>The geometries parameter is <code>Geometries</code> type.</p>
     * @see  java.io.File
     * @see  java.lang.String
     * @see  org.geotools.geometry.jts.Geometries
     * @see  org.geotools.data.shapefile.ShapefileDataStore
     * @see  io.github.nichetoolkit.rest.RestException
     * @return  {@link org.geotools.data.shapefile.ShapefileDataStore} <p>The data store return object is <code>ShapefileDataStore</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     */
    public abstract ShapefileDataStore dataStore(File shapefile, String filename, Geometries geometries) throws RestException;

    /**
     * <code>dataStore</code>
     * <p>The data store method.</p>
     * @param shapefile {@link java.io.File} <p>The shapefile parameter is <code>File</code> type.</p>
     * @param attributeClassMap {@link java.util.Map} <p>The attribute class map parameter is <code>Map</code> type.</p>
     * @see  java.io.File
     * @see  java.util.Map
     * @see  org.geotools.data.shapefile.ShapefileDataStore
     * @see  io.github.nichetoolkit.rest.RestException
     * @return  {@link org.geotools.data.shapefile.ShapefileDataStore} <p>The data store return object is <code>ShapefileDataStore</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     */
    public abstract ShapefileDataStore dataStore(File shapefile, Map<String, Class<?>> attributeClassMap) throws RestException;

    /**
     * <code>dataStore</code>
     * <p>The data store method.</p>
     * @param shapefile {@link java.io.File} <p>The shapefile parameter is <code>File</code> type.</p>
     * @param filename {@link java.lang.String} <p>The filename parameter is <code>String</code> type.</p>
     * @param attributeClassMap {@link java.util.Map} <p>The attribute class map parameter is <code>Map</code> type.</p>
     * @see  java.io.File
     * @see  java.lang.String
     * @see  java.util.Map
     * @see  org.geotools.data.shapefile.ShapefileDataStore
     * @see  io.github.nichetoolkit.rest.RestException
     * @return  {@link org.geotools.data.shapefile.ShapefileDataStore} <p>The data store return object is <code>ShapefileDataStore</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     */
    public abstract ShapefileDataStore dataStore(File shapefile, String filename, Map<String, Class<?>> attributeClassMap) throws RestException;

    /**
     * <code>dataStore</code>
     * <p>The data store method.</p>
     * @param shapefile {@link java.io.File} <p>The shapefile parameter is <code>File</code> type.</p>
     * @param geometries {@link org.geotools.geometry.jts.Geometries} <p>The geometries parameter is <code>Geometries</code> type.</p>
     * @param attributeClassMap {@link java.util.Map} <p>The attribute class map parameter is <code>Map</code> type.</p>
     * @see  java.io.File
     * @see  org.geotools.geometry.jts.Geometries
     * @see  java.util.Map
     * @see  org.geotools.data.shapefile.ShapefileDataStore
     * @see  io.github.nichetoolkit.rest.RestException
     * @return  {@link org.geotools.data.shapefile.ShapefileDataStore} <p>The data store return object is <code>ShapefileDataStore</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     */
    public abstract ShapefileDataStore dataStore(File shapefile, Geometries geometries, Map<String, Class<?>> attributeClassMap) throws RestException;

    /**
     * <code>dataStore</code>
     * <p>The data store method.</p>
     * @param shapefile {@link java.io.File} <p>The shapefile parameter is <code>File</code> type.</p>
     * @param filename {@link java.lang.String} <p>The filename parameter is <code>String</code> type.</p>
     * @param geometries {@link org.geotools.geometry.jts.Geometries} <p>The geometries parameter is <code>Geometries</code> type.</p>
     * @param attributeClassMap {@link java.util.Map} <p>The attribute class map parameter is <code>Map</code> type.</p>
     * @see  java.io.File
     * @see  java.lang.String
     * @see  org.geotools.geometry.jts.Geometries
     * @see  java.util.Map
     * @see  org.geotools.data.shapefile.ShapefileDataStore
     * @see  io.github.nichetoolkit.rest.RestException
     * @return  {@link org.geotools.data.shapefile.ShapefileDataStore} <p>The data store return object is <code>ShapefileDataStore</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     */
    public abstract ShapefileDataStore dataStore(File shapefile, String filename, Geometries geometries, Map<String, Class<?>> attributeClassMap) throws RestException;

    /**
     * <code>featureWriter</code>
     * <p>The feature writer method.</p>
     * @param params {@link java.util.Map} <p>The params parameter is <code>Map</code> type.</p>
     * @param typeBuilder {@link org.geotools.feature.simple.SimpleFeatureTypeBuilder} <p>The type builder parameter is <code>SimpleFeatureTypeBuilder</code> type.</p>
     * @see  java.util.Map
     * @see  org.geotools.feature.simple.SimpleFeatureTypeBuilder
     * @see  org.geotools.data.FeatureWriter
     * @see  io.github.nichetoolkit.rest.RestException
     * @return  {@link org.geotools.data.FeatureWriter} <p>The feature writer return object is <code>FeatureWriter</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     */
    public abstract FeatureWriter<SimpleFeatureType, SimpleFeature> featureWriter(Map<String, Serializable> params, SimpleFeatureTypeBuilder typeBuilder) throws RestException;

    /**
     * <code>featureWriter</code>
     * <p>The feature writer method.</p>
     * @param dataStore {@link org.geotools.data.shapefile.ShapefileDataStore} <p>The data store parameter is <code>ShapefileDataStore</code> type.</p>
     * @see  org.geotools.data.shapefile.ShapefileDataStore
     * @see  org.geotools.data.FeatureWriter
     * @see  io.github.nichetoolkit.rest.RestException
     * @return  {@link org.geotools.data.FeatureWriter} <p>The feature writer return object is <code>FeatureWriter</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     */
    public abstract FeatureWriter<SimpleFeatureType, SimpleFeature> featureWriter(ShapefileDataStore dataStore) throws RestException;

    /**
     * <code>write</code>
     * <p>The write method.</p>
     * @param shapefile T <p>The shapefile parameter is <code>T</code> type.</p>
     * @return  {@link java.io.File} <p>The write return object is <code>File</code> type.</p>
     * @see  java.io.File
     * @see  io.github.nichetoolkit.rest.RestException
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     */
    public abstract File write(T shapefile) throws RestException;

    /**
     * <code>write</code>
     * <p>The write method.</p>
     * @param shapefile {@link java.util.Collection} <p>The shapefile parameter is <code>Collection</code> type.</p>
     * @see  java.util.Collection
     * @see  java.io.File
     * @see  io.github.nichetoolkit.rest.RestException
     * @return  {@link java.io.File} <p>The write return object is <code>File</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     */
    public abstract File write(Collection<T> shapefile) throws RestException;

    /**
     * <code>write</code>
     * <p>The write method.</p>
     * @param shapeFile {@link java.io.File} <p>The shape file parameter is <code>File</code> type.</p>
     * @param shapefiles {@link java.util.Collection} <p>The shapefiles parameter is <code>Collection</code> type.</p>
     * @see  java.io.File
     * @see  java.util.Collection
     * @see  io.github.nichetoolkit.rest.RestException
     * @return  {@link java.io.File} <p>The write return object is <code>File</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     */
    public abstract File write(File shapeFile, Collection<T> shapefiles) throws RestException;

    /**
     * <code>write</code>
     * <p>The write method.</p>
     * @param shapeFile {@link java.io.File} <p>The shape file parameter is <code>File</code> type.</p>
     * @param geometries {@link org.geotools.geometry.jts.Geometries} <p>The geometries parameter is <code>Geometries</code> type.</p>
     * @param shapefiles {@link java.util.List} <p>The shapefiles parameter is <code>List</code> type.</p>
     * @see  java.io.File
     * @see  org.geotools.geometry.jts.Geometries
     * @see  java.util.List
     * @see  io.github.nichetoolkit.rest.RestException
     * @return  {@link java.io.File} <p>The write return object is <code>File</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     */
    public abstract File write(File shapeFile, Geometries geometries, List<T> shapefiles) throws RestException;

    /**
     * <code>write</code>
     * <p>The write method.</p>
     * @param shapeFile {@link java.io.File} <p>The shape file parameter is <code>File</code> type.</p>
     * @param attributeClassMap {@link java.util.Map} <p>The attribute class map parameter is <code>Map</code> type.</p>
     * @param shapefiles {@link java.util.Collection} <p>The shapefiles parameter is <code>Collection</code> type.</p>
     * @see  java.io.File
     * @see  java.util.Map
     * @see  java.util.Collection
     * @see  io.github.nichetoolkit.rest.RestException
     * @return  {@link java.io.File} <p>The write return object is <code>File</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     */
    public abstract File write(File shapeFile, Map<String, Class<?>> attributeClassMap, Collection<T> shapefiles) throws RestException;

    /**
     * <code>write</code>
     * <p>The write method.</p>
     * @param shapeFile {@link java.io.File} <p>The shape file parameter is <code>File</code> type.</p>
     * @param geometries {@link org.geotools.geometry.jts.Geometries} <p>The geometries parameter is <code>Geometries</code> type.</p>
     * @param attributeClassMap {@link java.util.Map} <p>The attribute class map parameter is <code>Map</code> type.</p>
     * @param shapefiles {@link java.util.Collection} <p>The shapefiles parameter is <code>Collection</code> type.</p>
     * @see  java.io.File
     * @see  org.geotools.geometry.jts.Geometries
     * @see  java.util.Map
     * @see  java.util.Collection
     * @see  io.github.nichetoolkit.rest.RestException
     * @return  {@link java.io.File} <p>The write return object is <code>File</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     */
    public abstract File write(File shapeFile, Geometries geometries, Map<String, Class<?>> attributeClassMap, Collection<T> shapefiles) throws RestException;

    /**
     * <code>write</code>
     * <p>The write method.</p>
     * @param feature {@link org.opengis.feature.simple.SimpleFeature} <p>The feature parameter is <code>SimpleFeature</code> type.</p>
     * @param shapefile T <p>The shapefile parameter is <code>T</code> type.</p>
     * @see  org.opengis.feature.simple.SimpleFeature
     * @see  io.github.nichetoolkit.rest.RestException
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     */
    public abstract void write(SimpleFeature feature, T shapefile) throws RestException;

}
