package io.github.nichetoolkit.jts.shape.simple;

import io.github.nichetoolkit.jts.JtsUtils;
import io.github.nichetoolkit.jts.constant.JtsConstants;
import io.github.nichetoolkit.jts.error.JtsErrorStatus;
import io.github.nichetoolkit.jts.error.shape.*;
import io.github.nichetoolkit.jts.shape.ShapeReader;
import io.github.nichetoolkit.jts.shape.Shapefile;
import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rest.util.common.GeneralUtils;
import io.github.nichetoolkit.rest.util.common.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.geotools.data.DataStoreFinder;
import org.geotools.data.FeatureSource;
import org.geotools.data.shapefile.ShapefileDataStore;
import org.geotools.feature.FeatureCollection;
import org.geotools.feature.FeatureIterator;
import org.locationtech.jts.geom.Geometry;
import org.opengis.feature.Property;
import org.opengis.feature.simple.SimpleFeature;
import org.opengis.feature.simple.SimpleFeatureType;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>DefaultShapeReader</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
@Slf4j
public class SimpleShapeReader extends ShapeReader<SimpleShapefile> {
    protected Map<String, Object> params = new HashMap<>();
    protected String typeName;
    protected ShapefileDataStore dataStore;
    protected FeatureIterator<SimpleFeature> features;


    @Override
    public Map<String, Object> params(File shapeFile) throws RestException {
        try {
            HashMap<String, Object> params = new HashMap<>();
            URL value = shapeFile.toURI().toURL();
            params.put(JtsConstants.PARAM_URL, value);
            log.debug("shape reader set up url params for the data store success! url: {}.", value.toString());
            this.params = params;
            return params;
        } catch (IOException exception) {
            log.error("shape reader set up params for the data store happened error, error: {}.", exception.getMessage());
            throw new ParamsErrorException(JtsErrorStatus.SHAPE_READER_PARAMS_ERROR, exception.getMessage());
        }
    }

    @Override
    public ShapefileDataStore dataStore() throws RestException {
        if (GeneralUtils.isEmpty(this.params)) {
            log.error("shape reader need to initialize!");
            throw new ReaderUninitializedErrorException("shape reader need to initialize!");
        }
        return dataStore(this.params);
    }

    @Override
    public ShapefileDataStore dataStore(File shapeFile) throws RestException {
        Map<String, Object> params = params(shapeFile);
        return dataStore(params);
    }

    @Override
    public ShapefileDataStore dataStore(Map<String, Object> params) throws RestException {
        try {
            ShapefileDataStore dataStore = (ShapefileDataStore) DataStoreFinder.getDataStore(params);
            dataStore.setCharset(Charset.forName(JtsConstants.SHAPE_ENCODING));
            log.debug("shape reader set up data store success! params: {}.", JsonUtils.parseJson(params));
            this.params = params;
            this.dataStore = dataStore;
            return dataStore;
        } catch (IOException exception) {
            log.error("shape reader set up data store happened error, params: {}, error: {}.", JsonUtils.parseJson(params), exception.getMessage());
            throw new DataStoreErrorException(JtsErrorStatus.SHAPE_READER_DATA_STORE_ERROR, exception.getMessage());
        }
    }

    @Override
    public FeatureIterator<SimpleFeature> features() throws RestException {
        if (GeneralUtils.isEmpty(this.dataStore)) {
            if (GeneralUtils.isEmpty(this.params)) {
                log.error("shape reader need to initialize!");
                throw new ReaderUninitializedErrorException("shape reader need to initialize!");
            } else {
                return features(this.params);
            }
        }
        return features(this.dataStore);
    }

    @Override
    public FeatureIterator<SimpleFeature> features(File shapeFile) throws RestException {
        ShapefileDataStore dataStore = dataStore(shapeFile);
        return features(dataStore);
    }

    @Override
    public FeatureIterator<SimpleFeature> features(Map<String, Object> params) throws RestException {
        ShapefileDataStore dataStore = dataStore(params);
        return features(dataStore);
    }

    @Override
    public FeatureIterator<SimpleFeature> features(ShapefileDataStore dataStore) throws RestException {
        try {
            String typeName = dataStore.getTypeNames()[0];
            FeatureSource<SimpleFeatureType, SimpleFeature> source = dataStore.getFeatureSource(typeName);
            FeatureCollection<SimpleFeatureType, SimpleFeature> featureCollection = source.getFeatures();
            FeatureIterator<SimpleFeature> features = featureCollection.features();
            this.dataStore = dataStore;
            this.typeName = typeName;
            this.features = features;
            log.debug("shape reader obtains features success! size: {}.", featureCollection.size());
            return features;
        } catch (IOException exception) {
            log.error("shape reader obtains features happened error, error: {}.", exception.getMessage());
            throw new FeaturesErrorException(JtsErrorStatus.SHAPE_READER_FEATURES_ERROR, exception.getMessage());
        }
    }

    @Override
    public List<SimpleShapefile> read() throws RestException {
        if (GeneralUtils.isEmpty(this.features)) {
            if (GeneralUtils.isEmpty(this.dataStore)) {
                if (GeneralUtils.isEmpty(this.params)) {
                    log.error("shape reader need to initialize!");
                    throw new ReaderUninitializedErrorException("shape reader need to initialize!");
                } else {
                    return read(this.params);
                }
            } else {
                return read(this.dataStore);
            }
        }
        return read(this.features);
    }

    @Override
    public List<SimpleShapefile> read(File shapeFile) throws RestException {
        ShapefileDataStore dataStore = dataStore(shapeFile);
        return read(dataStore);
    }

    @Override
    public List<SimpleShapefile> read(Map<String, Object> params) throws RestException {
        ShapefileDataStore dataStore = dataStore(params);
        return read(dataStore);
    }

    @Override
    public List<SimpleShapefile> read(ShapefileDataStore dataStore) throws RestException {
        FeatureIterator<SimpleFeature> features = features(dataStore);
        return read(features);
    }

    @Override
    public List<SimpleShapefile> read(FeatureIterator<SimpleFeature> features) throws RestException {
        List<SimpleShapefile> shapefiles = new ArrayList<>();
        while (features.hasNext()) {
            SimpleFeature feature = features.next();
            SimpleShapefile shapefile = feature(feature);
            shapefiles.add(shapefile);
        }
        this.features.close();
        this.dataStore.dispose();
        DataStoreFinder.reset();
        return shapefiles;
    }

    @Override
    public SimpleShapefile feature(SimpleFeature feature) throws RestException {
        Object defaultGeometry = feature.getDefaultGeometry();
        String featureID = feature.getID();
        SimpleShapefile simpleShapefile;
        if (GeneralUtils.isNotEmpty(defaultGeometry)) {
            Geometry geometry;
            if (defaultGeometry instanceof Geometry) {
                geometry = (Geometry) defaultGeometry;
            } else {
                String geometryWkt = defaultGeometry.toString();
                geometry = JtsUtils.parseGeometry(geometryWkt);
            }
            simpleShapefile = new SimpleShapefile(featureID, geometry);
        } else {
            simpleShapefile = new SimpleShapefile(featureID);
        }
        String typeName = feature.getType().getTypeName();
        simpleShapefile.setTypeName(typeName);
        for (Property property : feature.getValue()) {
            property(simpleShapefile, property);
        }
        return simpleShapefile;
    }

    @Override
    public void property(SimpleShapefile shapefile, Property property) throws RestException {
        String name = property.getName().toString();
        Object value = property.getValue();
        if (Shapefile.THE_GEOM.equals(name)) {
            if (GeneralUtils.isNotEmpty(value)) {
                String geometryWkt = value.toString();
                Geometry geometry = JtsUtils.parseGeometry(geometryWkt);
                shapefile.setGeometry(geometry);
            }
        } else {
            shapefile.addProperties(name, value);
        }
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public Map<String, Object> getParams() {
        return params;
    }

    public void setParams(Map<String, Object> params) {
        this.params = params;
    }

    public ShapefileDataStore getDataStore() {
        return dataStore;
    }

    public void setDataStore(ShapefileDataStore dataStore) {
        this.dataStore = dataStore;
    }

    public FeatureIterator<SimpleFeature> getFeatures() {
        return features;
    }

    public void setFeatures(FeatureIterator<SimpleFeature> features) {
        this.features = features;
    }


}
