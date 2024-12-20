package io.github.nichetoolkit.jts.shape.simple;

import io.github.nichetoolkit.jts.constant.JtsConstants;
import io.github.nichetoolkit.jts.error.JtsErrorStatus;
import io.github.nichetoolkit.jts.error.shape.*;
import io.github.nichetoolkit.jts.shape.ShapeWriter;
import io.github.nichetoolkit.jts.shape.Shapefile;
import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rest.util.GeneralUtils;
import io.github.nichetoolkit.rest.util.JsonUtils;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.geotools.data.FeatureWriter;
import org.geotools.data.Transaction;
import org.geotools.data.shapefile.ShapefileDataStore;
import org.geotools.data.shapefile.ShapefileDataStoreFactory;
import org.geotools.feature.simple.SimpleFeatureTypeBuilder;
import org.geotools.geometry.jts.Geometries;
import org.geotools.referencing.crs.DefaultGeographicCRS;
import org.opengis.feature.simple.SimpleFeature;
import org.opengis.feature.simple.SimpleFeatureType;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.*;

/**
 * <code>SimpleShapeWriter</code>
 * <p>The simple shape writer class.</p>
 * @see  io.github.nichetoolkit.jts.shape.ShapeWriter
 * @see  lombok.extern.slf4j.Slf4j
 * @see  lombok.Getter
 * @see  lombok.Setter
 * @see  java.lang.SuppressWarnings
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
@Slf4j
@Getter
@Setter
@SuppressWarnings("unused")
public class SimpleShapeWriter extends ShapeWriter<SimpleShapefile> {
    /**
     * <code>shapefile</code>
     * {@link java.io.File} <p>The <code>shapefile</code> field.</p>
     * @see  java.io.File
     */
    protected File shapefile;
    /**
     * <code>dataStore</code>
     * {@link org.geotools.data.shapefile.ShapefileDataStore} <p>The <code>dataStore</code> field.</p>
     * @see  org.geotools.data.shapefile.ShapefileDataStore
     */
    protected ShapefileDataStore dataStore;
    /**
     * <code>params</code>
     * {@link java.util.Map} <p>The <code>params</code> field.</p>
     * @see  java.util.Map
     */
    protected Map<String, Serializable> params;
    /**
     * <code>featureWriter</code>
     * {@link org.geotools.data.FeatureWriter} <p>The <code>featureWriter</code> field.</p>
     * @see  org.geotools.data.FeatureWriter
     */
    protected FeatureWriter<SimpleFeatureType, SimpleFeature> featureWriter;
    /**
     * <code>attributeClassMap</code>
     * {@link java.util.Map} <p>The <code>attributeClassMap</code> field.</p>
     * @see  java.util.Map
     */
    protected Map<String, Class<?>> attributeClassMap;
    /**
     * <code>typeBuilder</code>
     * {@link org.geotools.feature.simple.SimpleFeatureTypeBuilder} <p>The <code>typeBuilder</code> field.</p>
     * @see  org.geotools.feature.simple.SimpleFeatureTypeBuilder
     */
    protected SimpleFeatureTypeBuilder typeBuilder;


    @Override
    public Map<String, Serializable> params(File shapefile) throws RestException {
        try {
            Map<String, Serializable> params = new HashMap<>();
            URL value = shapefile.toURI().toURL();
            params.put(ShapefileDataStoreFactory.URLP.key, value);
            log.debug("shape writer set up url params for the data store success! url: {}.", value);
            this.shapefile = shapefile;
            this.params = params;
            return params;
        } catch (IOException exception) {
            throw new ParamsErrorException(JtsErrorStatus.SHAPE_WRITER_PARAMS_ERROR, exception.getMessage());
        }
    }

    @Override
    public SimpleFeatureTypeBuilder typeBuilder(File shapefile) {
        return typeBuilder(shapefile, null, null);
    }

    @Override
    public SimpleFeatureTypeBuilder typeBuilder(String filename) {
        return typeBuilder(filename, null, null);
    }

    @Override
    public SimpleFeatureTypeBuilder typeBuilder(File shapefile, Geometries geometries) {
        return typeBuilder(shapefile, geometries, null);
    }

    @Override
    public SimpleFeatureTypeBuilder typeBuilder(String filename, Geometries geometries) {
        return typeBuilder(filename, geometries, null);
    }

    @Override
    public SimpleFeatureTypeBuilder typeBuilder(File shapefile, Map<String, Class<?>> attributeClassMap) {
        return typeBuilder(shapefile, null, attributeClassMap);
    }

    @Override
    public SimpleFeatureTypeBuilder typeBuilder(String filename, Map<String, Class<?>> attributeClassMap) {
        return typeBuilder(filename, null, attributeClassMap);
    }

    @Override
    public SimpleFeatureTypeBuilder typeBuilder(File shapefile, Geometries geometries, Map<String, Class<?>> attributeClassMap) {
        String fileName = shapefile.getName();
        String shapeFilename = FilenameUtils.getName(fileName);
        return typeBuilder(shapeFilename, geometries, attributeClassMap);
    }

    @Override
    public SimpleFeatureTypeBuilder typeBuilder(String filename, Geometries geometries, Map<String, Class<?>> attributeClassMap) {
        SimpleFeatureTypeBuilder typeBuilder = new SimpleFeatureTypeBuilder();
        typeBuilder.setCRS(DefaultGeographicCRS.WGS84);
        typeBuilder.setName(filename);
        if (GeneralUtils.isNotEmpty(geometries)) {
            typeBuilder.add(Shapefile.THE_GEOM, geometries == Geometries.GEOMETRY ? Geometries.MULTIPOLYGON.getBinding() : geometries.getBinding());
        }
        if (GeneralUtils.isNotEmpty(attributeClassMap)) {
            this.attributeClassMap = attributeClassMap;
            for (Map.Entry<String, Class<?>> entry : attributeClassMap.entrySet()) {
                typeBuilder.add(entry.getKey(), entry.getValue());
            }
        }
        this.typeBuilder = typeBuilder;
        return typeBuilder;
    }

    @Override
    public ShapefileDataStore dataStore(Map<String, Serializable> params, SimpleFeatureTypeBuilder typeBuilder) throws RestException {
        try {
            ShapefileDataStore dataStore = (ShapefileDataStore) new ShapefileDataStoreFactory().createNewDataStore(params);
            dataStore.createSchema(typeBuilder.buildFeatureType());
            dataStore.setCharset(Charset.forName(JtsConstants.SHAPE_ENCODING));
            this.params = params;
            this.dataStore = dataStore;
            log.debug("shape writer set up data store type builder success! params: {}.", JsonUtils.parseJson(params));
            return dataStore;
        } catch (IOException exception) {
            throw new DataStoreErrorException(JtsErrorStatus.SHAPE_WRITER_DATA_STORE_ERROR, exception.getMessage());
        }
    }

    @Override
    public ShapefileDataStore dataStore(File shapefile) throws RestException {
        Map<String, Serializable> params = params(shapefile);
        SimpleFeatureTypeBuilder typeBuilder = typeBuilder(shapefile);
        return dataStore(params, typeBuilder);
    }

    @Override
    public ShapefileDataStore dataStore(File shapefile, SimpleFeatureTypeBuilder typeBuilder) throws RestException {
        Map<String, Serializable> params = params(shapefile);
        return dataStore(params, typeBuilder);
    }

    @Override
    public ShapefileDataStore dataStore(File shapefile, Geometries geometries) throws RestException {
        SimpleFeatureTypeBuilder typeBuilder = typeBuilder(shapefile, geometries);
        return dataStore(shapefile, typeBuilder);
    }

    @Override
    public ShapefileDataStore dataStore(File shapefile, String filename, Geometries geometries) throws RestException {
        SimpleFeatureTypeBuilder typeBuilder = typeBuilder(shapefile, geometries);
        return dataStore(shapefile, typeBuilder);
    }

    @Override
    public ShapefileDataStore dataStore(File shapefile, Map<String, Class<?>> attributeClassMap) throws RestException {
        SimpleFeatureTypeBuilder typeBuilder = typeBuilder(shapefile, attributeClassMap);
        return dataStore(shapefile, typeBuilder);
    }

    @Override
    public ShapefileDataStore dataStore(File shapefile, String filename, Map<String, Class<?>> attributeClassMap) throws RestException {
        SimpleFeatureTypeBuilder typeBuilder = typeBuilder(filename, attributeClassMap);
        return dataStore(shapefile, typeBuilder);
    }

    @Override
    public ShapefileDataStore dataStore(File shapefile, Geometries geometries, Map<String, Class<?>> attributeClassMap) throws RestException {
        SimpleFeatureTypeBuilder typeBuilder = typeBuilder(shapefile, geometries, attributeClassMap);
        return dataStore(shapefile, typeBuilder);
    }

    @Override
    public ShapefileDataStore dataStore(File shapefile, String filename, Geometries geometries, Map<String, Class<?>> attributeClassMap) throws RestException {
        SimpleFeatureTypeBuilder typeBuilder = typeBuilder(filename, geometries, attributeClassMap);
        return dataStore(shapefile, typeBuilder);
    }

    @Override
    public FeatureWriter<SimpleFeatureType, SimpleFeature> featureWriter(Map<String, Serializable> params, SimpleFeatureTypeBuilder typeBuilder) throws RestException {
        ShapefileDataStore dataStore = dataStore(params, typeBuilder);
        return featureWriter(dataStore);
    }

    @Override
    public FeatureWriter<SimpleFeatureType, SimpleFeature> featureWriter(ShapefileDataStore dataStore) throws RestException {
        try {
            FeatureWriter<SimpleFeatureType, SimpleFeature> featureWriter = dataStore.getFeatureWriter(this.dataStore.getTypeNames()[0], Transaction.AUTO_COMMIT);
            this.featureWriter = featureWriter;
            log.debug("The shape writer set up feature writer success!");
            return featureWriter;
        } catch (IOException exception) {
            throw new FeaturesErrorException(JtsErrorStatus.SHAPE_WRITER_FEATURES_ERROR, exception.getMessage());
        }
    }

    @Override
    public File write(SimpleShapefile shapefile) throws RestException {
        return write(Collections.singletonList(shapefile));
    }

    @Override
    public File write(Collection<SimpleShapefile> shapefiles) throws RestException {
        if (GeneralUtils.isEmpty(this.featureWriter)) {
            if (GeneralUtils.isEmpty(this.dataStore)) {
                if (GeneralUtils.isEmpty(this.params) || GeneralUtils.isEmpty(this.typeBuilder)) {
                    throw new ReaderUninitializedErrorException("The shape reader need to initialize!");
                } else {
                    featureWriter(this.params, this.typeBuilder);
                }
            } else {
                featureWriter(this.dataStore);
            }
        }
        try {
            if (GeneralUtils.isNotEmpty(shapefiles)) {
                for (SimpleShapefile shapefile : shapefiles) {
                    SimpleFeature feature = this.featureWriter.next();
                    write(feature, shapefile);
                }
            } else {
                this.featureWriter.next();
            }
            log.debug("The shape writer write shapefiles success! size: {}.", shapefiles.size());
            this.featureWriter.write();
            this.featureWriter.close();
            this.dataStore.dispose();
        } catch (IOException exception) {
            throw new FeaturesErrorException(JtsErrorStatus.SHAPE_WRITER_FEATURES_ERROR, exception.getMessage());
        }
        return this.shapefile;
    }

    @Override
    public File write(File shapeFile, Collection<SimpleShapefile> shapefiles) throws RestException {
        ShapefileDataStore dataStore = dataStore(shapeFile);
        featureWriter(dataStore);
        return write(shapefiles);
    }

    @Override
    public File write(File shapeFile, Geometries geometries, List<SimpleShapefile> shapefiles) throws RestException {
        ShapefileDataStore dataStore = dataStore(shapeFile, geometries);
        featureWriter(dataStore);
        return write(shapefiles);
    }

    @Override
    public File write(File shapeFile, Map<String, Class<?>> attributeClassMap, Collection<SimpleShapefile> shapefiles) throws RestException {
        ShapefileDataStore dataStore = dataStore(shapeFile, attributeClassMap);
        featureWriter(dataStore);
        return write(shapefiles);
    }

    @Override
    public File write(File shapeFile, Geometries geometries, Map<String, Class<?>> attributeClassMap, Collection<SimpleShapefile> shapefiles) throws RestException {
        ShapefileDataStore dataStore = dataStore(shapeFile, geometries, attributeClassMap);
        featureWriter(dataStore);
        return write(shapefiles);
    }

    @Override
    public void write(SimpleFeature feature, SimpleShapefile shapefile) {
        feature.setAttribute(Shapefile.THE_GEOM, shapefile.getGeometry());
        if (GeneralUtils.isNotEmpty(shapefile.getProperties()) && GeneralUtils.isNotEmpty(this.attributeClassMap)) {
            shapefile.getProperties().entrySet().stream().filter(entry -> {
                String key = entry.getKey();
                return GeneralUtils.isNotEmpty(key) && this.attributeClassMap.containsKey(key);
            }).forEach(entry -> feature.setAttribute(entry.getKey(), entry.getValue()));
        }
    }
}
