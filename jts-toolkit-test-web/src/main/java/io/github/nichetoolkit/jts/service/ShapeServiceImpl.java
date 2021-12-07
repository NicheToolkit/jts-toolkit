package io.github.nichetoolkit.jts.service;

import io.github.nichetoolkit.jts.JtsGeojson;
import io.github.nichetoolkit.jts.shape.ShapefileUtils;
import io.github.nichetoolkit.jts.shape.simple.SimpleShapefile;
import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rest.error.supply.ResourceNotFoundException;
import io.github.nichetoolkit.rest.util.common.FileUtils;
import io.github.nichetoolkit.rest.util.common.GeneralUtils;
import io.github.nichetoolkit.rest.util.common.JsonUtils;
import io.github.nichetoolkit.rest.util.often.RandomUtils;
import lombok.extern.slf4j.Slf4j;
import org.geotools.geometry.jts.Geometries;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Point;
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
@Slf4j
@Service
public class ShapeServiceImpl implements ShapeService {

    //TODO use redis
    private static final Map<String, File> FILE_CACHE = new HashMap<>();

    @Override
    public void upload(MultipartFile file) throws RestException {
        String uuid = RandomUtils.uuid();
        List<SimpleShapefile> shapefiles = ShapefileUtils.readShapeFile(uuid,file);
        log.info("shapefiles: {}", JsonUtils.parseJson(shapefiles));
        ShapefileUtils.clear(uuid);
    }

    @Override
    public void download(String uuid, String filename) throws RestException {
        Map<Geometries, List<SimpleShapefile>> geometriesListMap = new HashMap<>();
        List<SimpleShapefile> shapefileList = new ArrayList<>();
        SimpleShapefile simpleShapefile = new SimpleShapefile();
        Point point = JtsGeojson.GEOMETRY_FACTORY.createPoint(new Coordinate(108.28125, 32.84267363195431));
        simpleShapefile.setGeometry(point);
        simpleShapefile.setGeometries(Geometries.MULTIPOINT);
        simpleShapefile.setProperties(Collections.singletonMap("name", "test"));
        shapefileList.add(simpleShapefile);
        geometriesListMap.putIfAbsent(Geometries.MULTIPOINT, shapefileList);

        Map<String, Class> attributeClassMap = new HashMap<>();
        attributeClassMap.put("name", String.class);

        File zipFiles = ShapefileUtils.writeShapeFile(uuid, filename, attributeClassMap, geometriesListMap);
        FILE_CACHE.putIfAbsent(uuid, zipFiles);
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
            ShapefileUtils.clear(uuid);
        } else {
            throw new ResourceNotFoundException("the zip file of shape no found!");
        }
        FILE_CACHE.remove(uuid);
    }
}
