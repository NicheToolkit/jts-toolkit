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
 * <p>ShapeReader</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
@SuppressWarnings({"unused"})
public abstract class ShapeWriter<T extends Shapefile> {

    public abstract Map<String, Serializable> params(File shapefile) throws RestException;

    public abstract SimpleFeatureTypeBuilder typeBuilder(File shapefile) throws RestException;

    public abstract SimpleFeatureTypeBuilder typeBuilder(String filename) throws RestException;

    public abstract SimpleFeatureTypeBuilder typeBuilder(File shapefile, Geometries geometries) throws RestException;

    public abstract SimpleFeatureTypeBuilder typeBuilder(String filename, Geometries geometries) throws RestException;

    public abstract SimpleFeatureTypeBuilder typeBuilder(File shapefile, Map<String, Class<?>> attributeClassMap) throws RestException;

    public abstract SimpleFeatureTypeBuilder typeBuilder(String filename, Map<String, Class<?>> attributeClassMap) throws RestException;

    public abstract SimpleFeatureTypeBuilder typeBuilder(File shapefile, Geometries geometries, Map<String, Class<?>> attributeClassMap) throws RestException;

    public abstract SimpleFeatureTypeBuilder typeBuilder(String filename, Geometries geometries, Map<String, Class<?>> attributeClassMap) throws RestException;

    public abstract ShapefileDataStore dataStore(File shapefile) throws RestException;

    public abstract ShapefileDataStore dataStore(Map<String, Serializable> params, SimpleFeatureTypeBuilder typeBuilder) throws RestException;

    public abstract ShapefileDataStore dataStore(File shapefile, SimpleFeatureTypeBuilder typeBuilder) throws RestException;

    public abstract ShapefileDataStore dataStore(File shapefile, Geometries geometries) throws RestException;

    public abstract ShapefileDataStore dataStore(File shapefile, String filename, Geometries geometries) throws RestException;

    public abstract ShapefileDataStore dataStore(File shapefile, Map<String, Class<?>> attributeClassMap) throws RestException;

    public abstract ShapefileDataStore dataStore(File shapefile, String filename, Map<String, Class<?>> attributeClassMap) throws RestException;

    public abstract ShapefileDataStore dataStore(File shapefile, Geometries geometries, Map<String, Class<?>> attributeClassMap) throws RestException;

    public abstract ShapefileDataStore dataStore(File shapefile, String filename, Geometries geometries, Map<String, Class<?>> attributeClassMap) throws RestException;

    public abstract FeatureWriter<SimpleFeatureType, SimpleFeature> featureWriter(Map<String, Serializable> params, SimpleFeatureTypeBuilder typeBuilder) throws RestException;

    public abstract FeatureWriter<SimpleFeatureType, SimpleFeature> featureWriter(ShapefileDataStore dataStore) throws RestException;

    public abstract File write(T shapefile) throws RestException;

    public abstract File write(Collection<T> shapefile) throws RestException;

    public abstract File write(File shapeFile, Collection<T> shapefiles) throws RestException;

    public abstract File write(File shapeFile, Geometries geometries, List<T> shapefiles) throws RestException;

    public abstract File write(File shapeFile, Map<String, Class<?>> attributeClassMap, Collection<T> shapefiles) throws RestException;

    public abstract File write(File shapeFile, Geometries geometries, Map<String, Class<?>> attributeClassMap, Collection<T> shapefiles) throws RestException;

    public abstract void write(SimpleFeature feature, T shapefile) throws RestException;

}
