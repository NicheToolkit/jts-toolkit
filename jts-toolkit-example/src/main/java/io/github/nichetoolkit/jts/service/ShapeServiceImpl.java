package io.github.nichetoolkit.jts.service;

import io.github.nichetoolkit.jts.JtsGeojson;
import io.github.nichetoolkit.jts.constant.FileConstants;
import io.github.nichetoolkit.jts.shape.ShapefileUtils;
import io.github.nichetoolkit.jts.shape.simple.SimpleShapefile;
import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rest.error.natives.FileErrorException;
import io.github.nichetoolkit.rest.error.supply.ResourceNotFoundException;
import io.github.nichetoolkit.rest.util.GeneralUtils;
import io.github.nichetoolkit.rest.util.JsonUtils;
import io.github.nichetoolkit.rest.util.IoStreamUtils;
import lombok.extern.slf4j.Slf4j;
import org.geotools.geometry.jts.Geometries;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Point;
import org.springframework.http.MediaType;
import org.springframework.http.MediaTypeFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * <code>ShapeServiceImpl</code>
 * <p>The shape service class.</p>
 * @see  lombok.extern.slf4j.Slf4j
 * @see  org.springframework.stereotype.Service
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
@Slf4j
@Service
public class ShapeServiceImpl implements ShapeService {

    /**
     * <code>FILE_CACHE</code>
     * {@link java.util.Map} <p>The constant <code>FILE_CACHE</code> field.</p>
     * @see  java.util.Map
     */
//TODO use redis
    private static final Map<String, File> FILE_CACHE = new HashMap<>();

    @Override
    public void upload(MultipartFile file) throws RestException {
        String uuid = GeneralUtils.uuid();
        List<SimpleShapefile> shapefiles = ShapefileUtils.readShapeFile(uuid, file);
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

        Map<String, Class<?>> attributeClassMap = new HashMap<>();
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
            String filename = zipFiles.getName();
            Optional<MediaType> mediaTypeOptional = MediaTypeFactory.getMediaType(filename);
            String contentType = mediaTypeOptional.orElse(MediaType.APPLICATION_OCTET_STREAM).toString();
            try (FileInputStream inputStream = new FileInputStream(zipFiles);
                 ServletOutputStream outputStream = response.getOutputStream()) {
                log.debug("file size: {}", zipFiles.length());
                response.addHeader(FileConstants.CONTENT_DISPOSITION_HEADER, FileConstants.ATTACHMENT_FILENAME_VALUE + URLEncoder.encode(filename, StandardCharsets.UTF_8.name()));
                response.addHeader(FileConstants.CONTENT_LENGTH_HEADER, "" + zipFiles.length());
                response.setCharacterEncoding(StandardCharsets.UTF_8.name());
                response.setContentType(contentType);
                IoStreamUtils.write(outputStream, inputStream);
            } catch (IOException exception) {
                throw new FileErrorException("the file service download has error",exception);
            }
            ShapefileUtils.clear(uuid);
        } else {
            throw new ResourceNotFoundException("the zip file of shape no found!");
        }
        FILE_CACHE.remove(uuid);
    }
}
