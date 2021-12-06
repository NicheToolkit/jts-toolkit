package io.github.nichetoolkit.jts.service;

import io.github.nichetoolkit.jts.JtsGeojson;
import io.github.nichetoolkit.jts.JtsUtils;
import io.github.nichetoolkit.jts.configure.JtsShapeProperties;
import io.github.nichetoolkit.jts.constant.JtsConstants;
import io.github.nichetoolkit.jts.shape.ShapeFactory;
import io.github.nichetoolkit.jts.shape.Shapefile;
import io.github.nichetoolkit.jts.shape.ShapefileUtils;
import io.github.nichetoolkit.jts.shape.simple.SimpleShapefile;
import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rest.error.supply.ResourceNotFoundException;
import io.github.nichetoolkit.rest.identity.IdentityUtils;
import io.github.nichetoolkit.rest.util.common.CollectUtils;
import io.github.nichetoolkit.rest.util.common.FileUtils;
import io.github.nichetoolkit.rest.util.common.GeneralUtils;
import org.geotools.geometry.jts.Geometries;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Point;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.*;

/**
 * <p>ShapeServiceImpl</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
@Service
public class ShapeServiceImpl implements ShapeService{

    private static final Map<String, File> FILE_CACHE = new HashMap<>();

    @Autowired
    private JtsShapeProperties shapeProperties;
    @Autowired
    private ShapeFactory<SimpleShapefile> shapeFactory;

    @Override
    public void upload(MultipartFile file) throws RestException {

    }

    @Override
    public void upload(MultipartFile[] files) throws RestException {

    }

    @Override
    public void download(String uuid, String filename) throws RestException {
        String shapePath = shapeProperties.getSpace().getShapePath(uuid);
        String zipPath = shapeProperties.getSpace().getZipPath(uuid);
        Map<String, List<File>> zipFileMap = new HashMap<>();
        Map<Geometries, List<SimpleShapefile>> geometriesListMap = new HashMap<>();
        List<SimpleShapefile> shapefileList = new ArrayList<>();
        SimpleShapefile simpleShapefile = new SimpleShapefile();
        Point point = JtsGeojson.GEOMETRY_FACTORY.createPoint(new Coordinate(108.28125,32.84267363195431));
        simpleShapefile.setGeometry(point);
        simpleShapefile.setGeometries(Geometries.MULTIPOINT);
        shapefileList.add(simpleShapefile);

        SimpleShapefile simpleShapefile1 = new SimpleShapefile();
        Point point1 = JtsGeojson.GEOMETRY_FACTORY.createPoint(new Coordinate( 109.0283203125, 33.33970700424026));
        simpleShapefile1.setGeometry(point1);
        simpleShapefile1.setGeometries(Geometries.MULTIPOINT);
        shapefileList.add(simpleShapefile1);

        geometriesListMap.putIfAbsent(Geometries.MULTIPOINT, shapefileList);
        Map<String, Class> attributeClassMap = new HashMap<>();
        attributeClassMap.put("sdomain", String.class);
        attributeClassMap.put("realtime", String.class);

        for (Map.Entry<Geometries, List<SimpleShapefile>> innerEntry : geometriesListMap.entrySet()) {
            Geometries geometries = innerEntry.getKey();
            List<SimpleShapefile> shapefiles = innerEntry.getValue();
            String shapeFilename = filename.concat("_").concat(geometries.getSimpleName().toLowerCase());
            String templatePath = shapePath.concat(File.separator).concat(shapeFilename).concat(JtsConstants.SHP_EXT);
            File templateFile = FileUtils.createFile(templatePath);
            File shapeFile = shapeFactory.write(templateFile, geometries, attributeClassMap, shapefiles);
            File zipShapeFile = ShapefileUtils.zipShapeFile(uuid,shapeFile);
            CollectUtils.collect(filename,zipShapeFile,zipFileMap);
        }

        File zipFiles = ShapefileUtils.zipFiles(zipPath,zipFileMap);
        FILE_CACHE.putIfAbsent(uuid,zipFiles);
    }

    @Override
    public boolean download(String uuid) throws RestException {
        File zipFiles = FILE_CACHE.get(uuid);
        return GeneralUtils.isNotEmpty(zipFiles) && zipFiles.exists() && zipFiles.isFile();
    }

    @Override
    public void download(String uuid, HttpServletResponse response) throws RestException {
        File zipFiles = FILE_CACHE.get(uuid);
        if (GeneralUtils.isNotEmpty(zipFiles) && zipFiles.exists() && zipFiles.isFile()) {
            FileUtils.writeZip(zipFiles, response);
            String cachePath = shapeProperties.getSpace().getCachePath(uuid);
            FileUtils.clear(cachePath);
        } else {
            throw new ResourceNotFoundException("the zip file of shape no found!");
        }
        FILE_CACHE.remove(uuid);
    }
}
