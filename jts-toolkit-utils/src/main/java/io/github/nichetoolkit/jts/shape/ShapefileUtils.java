package io.github.nichetoolkit.jts.shape;

import io.github.nichetoolkit.jts.configure.JtsShapeProperties;
import io.github.nichetoolkit.jts.constant.JtsConstants;
import io.github.nichetoolkit.jts.shape.simple.SimpleShapefile;
import io.github.nichetoolkit.rest.RestErrorStatus;
import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rest.error.natives.FileErrorException;
import io.github.nichetoolkit.rest.error.often.FieldNullException;
import io.github.nichetoolkit.rest.error.often.StreamReadException;
import io.github.nichetoolkit.rest.error.often.StreamWriteException;
import io.github.nichetoolkit.rest.helper.CloseableHelper;
import io.github.nichetoolkit.rest.helper.StreamHelper;
import io.github.nichetoolkit.rest.util.common.CollectUtils;
import io.github.nichetoolkit.rest.util.common.FileUtils;
import io.github.nichetoolkit.rest.util.common.GeneralUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.geotools.geometry.jts.Geometries;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.charset.Charset;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

/**
 * <p>ShapefileUtils</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
@Slf4j
public class ShapefileUtils {
    private static ShapeFactory<SimpleShapefile> SHAPE_FACTORY;
    private static JtsShapeProperties PROPERTIES;

    public static void initShapefile(ShapeFactory<SimpleShapefile> shapeFactory,JtsShapeProperties properties) {
        SHAPE_FACTORY = shapeFactory;
        PROPERTIES = properties;
    }

    public static List<SimpleShapefile> readShapeFile(String uuid, MultipartFile file) throws RestException {
        if (GeneralUtils.isEmpty(SHAPE_FACTORY)) {
            log.error("the shape factory  need to initialize!");
            throw new FieldNullException("shape space","please initialize the shape file factory !");
        }
        File shapeFile = ShapefileUtils.shapeFile(uuid,file);
        return SHAPE_FACTORY.read(shapeFile);
    }

    public static File writeShapeFile(String uuid, String filename, Map<Geometries, List<SimpleShapefile>> geometriesListMap) throws RestException {
       return writeShapeFile(uuid,filename,null, geometriesListMap);
    }

    public static File writeShapeFile(String uuid, String filename, Map<String, Class> attributeClassMap, Map<Geometries, List<SimpleShapefile>> geometriesListMap) throws RestException {
        if (GeneralUtils.isEmpty(SHAPE_FACTORY) || GeneralUtils.isEmpty(PROPERTIES)) {
            log.error("the shape factory or shape properties need to initialize!");
            throw new FieldNullException("shape space","please initialize the shape file factory and shape properties !");
        }
        String shapePath = PROPERTIES.getSpace().getShapePath(uuid);
        String zipPath = PROPERTIES.getSpace().getZipPath(uuid);
        Map<String, List<File>> zipFileMap = new HashMap<>();
        for (Map.Entry<Geometries, List<SimpleShapefile>> innerEntry : geometriesListMap.entrySet()) {
            Geometries geometries = innerEntry.getKey();
            List<SimpleShapefile> shapefiles = innerEntry.getValue();
            String shapeFilename = filename.concat(JtsConstants.NAME_PREFIX).concat(geometries.getSimpleName().toLowerCase());
            String templatePath = shapePath.concat(File.separator).concat(shapeFilename).concat(JtsConstants.SHP_EXT);
            File templateFile = FileUtils.createFile(templatePath);
            File shapeFile;
            if (GeneralUtils.isNotEmpty(attributeClassMap)) {
                shapeFile = SHAPE_FACTORY.write(templateFile, geometries, attributeClassMap, shapefiles);
            } else {
                shapeFile = SHAPE_FACTORY.write(templateFile, geometries, shapefiles);
            }
            File zipShapeFile = ShapefileUtils.zipShapeFile(uuid, shapeFile);
            CollectUtils.collect(filename, zipShapeFile, zipFileMap);
        }
        return ShapefileUtils.zipFiles(zipPath, zipFileMap);
    }

    public static void clear(String uuid) throws RestException {
        if (GeneralUtils.isEmpty(PROPERTIES)) {
            log.error("the shape properties need to initialize!");
            throw new FieldNullException("shape space","please initialize the shape properties !");
        }
        String cachePath = PROPERTIES.getSpace().getCachePath(uuid);
        FileUtils.clear(cachePath);
    }

    public static File shapeFile(String uuid, MultipartFile shape) throws RestException {
        if (GeneralUtils.isEmpty(PROPERTIES)) {
            log.error("the shape properties need to initialize!");
            throw new FieldNullException("shape space","please initialize the shape properties !");
        }
        File shapeZipFile = cacheShapeFile(shape, PROPERTIES.getSpace().getCachePath(uuid));
        return unzipShapeFile(shapeZipFile,PROPERTIES.getSpace().getZipPath(uuid));
    }

    public static File cacheShapeFile(MultipartFile shape, String cachePath) throws RestException {
        if (GeneralUtils.isEmpty(cachePath)) {
            log.error("the shape cache path need to initialize!");
            throw new FieldNullException("shape cache path","please initialize the shape file cache path!");
        }
        return FileUtils.cacheFile(cachePath, shape);
    }

    public static File zipShapeFile(String uuid, File shapeFile) throws RestException {
        if (GeneralUtils.isEmpty(PROPERTIES)) {
            log.error("the shape properties need to initialize!");
            throw new FieldNullException("shape space","please initialize the shape properties !");
        }
        String zipPath = PROPERTIES.getSpace().getZipPath(uuid);
        String shapeFilePath = shapeFile.getPath();
        String shapeName = FilenameUtils.getBaseName(shapeFilePath);
        String parentPath = FilenameUtils.getFullPath(shapeFilePath);
        List<File> shapeFiles = Arrays.asList(
                new File(parentPath + File.separator + shapeName + JtsConstants.DBF_EXT),
                new File(parentPath + File.separator + shapeName + JtsConstants.PRJ_EXT),
                new File(parentPath + File.separator + shapeName + JtsConstants.SHP_EXT),
                new File(parentPath + File.separator + shapeName + JtsConstants.SHX_EXT));
        return zipFiles(zipPath, shapeName, shapeFiles);
    }

    public static File zipFiles(String zipPath, Map<String, List<File>> zipFiles) throws RestException {
        List<File> fileList = new ArrayList<>();
        if (zipFiles.keySet().size() == 1) {
            String filename = zipFiles.keySet().stream().findFirst().get();
            String shapeOTypePath = zipPath.concat(filename).concat(File.separator);
            return zipFiles(shapeOTypePath, filename, zipFiles.get(filename));
        }
        for (Map.Entry<String, List<File>> zipEntry : zipFiles.entrySet()) {
            String zipFilePath = zipPath.concat(zipEntry.getKey()).concat(File.separator);
            File zipFile = zipFiles(zipFilePath, zipEntry.getKey(), zipEntry.getValue());
            fileList.add(zipFile);
        }
        return zipFiles(zipPath, "shapes", fileList);
    }

    public static File zipFiles(String zipPath, String filename, List<File> zipFiles) throws RestException {
        if (zipFiles.size() == 1) {
            return zipFiles.stream().findFirst().get();
        }
        String zipFilePath = zipPath + File.separator + filename + JtsConstants.ZIP_EXT;
        File zipFile = FileUtils.createFile(zipFilePath);
        InputStream inputStream;
        ZipOutputStream zipOutputStream;
        try {
            zipOutputStream = new ZipOutputStream(new FileOutputStream(zipFile));
            zipOutputStream.setComment(filename);
            for (File shapeFile : zipFiles) {
                inputStream = new FileInputStream(shapeFile);
                zipOutputStream.putNextEntry(new ZipEntry(shapeFile.getName()));
                int temp;
                while ((temp = inputStream.read()) != -1) {
                    zipOutputStream.write(temp);
                }
                inputStream.close();
            }
            zipOutputStream.close();
        } catch (IOException exception) {
            log.error("the shape file zip error: {}",exception.getMessage());
            throw new StreamWriteException(zipPath,"zip error: ".concat(exception.getMessage()));
        }

        return zipFile;
    }

    public static File unzipShapeFile(File shapeZipFile, String zipPath) throws RestException {
        if (GeneralUtils.isEmpty(zipPath)) {
            log.error("the shape zip path need to initialize!");
            throw new FieldNullException("shape zip path","please initialize the shape file zip path!");
        }
        String suffix = FileUtils.suffix(shapeZipFile.getName());
        if (GeneralUtils.isEmpty(suffix)) {
            log.error("the file suffix is null!");
            throw new FileErrorException(RestErrorStatus.FILE_UNAVAILABLE,"only zip files are supported, the file suffix is null!");
        }
        if (!JtsConstants.ZIP_SUFFIX.equals(suffix)) {
            log.error("the file suffix is not supported!, suffix: {}",suffix);
            throw new FileErrorException(RestErrorStatus.FILE_UNAVAILABLE,"only zip files are supported! suffix: ".concat(suffix));
        }
        File shapeFile = null;
        ZipFile zipFile = null;
        try {
            zipFile = new ZipFile(shapeZipFile, Charset.forName(JtsConstants.ZIP_ENCODING));
            Enumeration<? extends ZipEntry> entries = zipFile.entries();
            while (entries.hasMoreElements()) {
                ZipEntry entry = entries.nextElement();
                String entryPath = zipPath + entry.getName();
                if (entry.isDirectory()) {
                    FileUtils.createPath(entryPath);
                } else {
                    File entryFile = FileUtils.createFile(entryPath);
                    String entryName = entryFile.getName();
                    String entrySuffix = FileUtils.suffix(entryName);
                    if (JtsConstants.SHAPE_SUFFIX.equals(entrySuffix)) {
                        shapeFile = entryFile;
                    }
                    InputStream inputStream = zipFile.getInputStream(entry);
                    FileOutputStream fileOutputStream = new FileOutputStream(entryFile);
                    StreamHelper.write(fileOutputStream,inputStream);
                }
            }
        } catch (IOException exception) {
            log.error("the shape file unzip error: {}",exception.getMessage());
            throw new StreamReadException(shapeZipFile.getAbsolutePath(),"unzip error: ".concat(exception.getMessage()));
        } finally {
            CloseableHelper.close(zipFile);
        }
        if (GeneralUtils.isEmpty(shapeFile)) {
            log.error("no '.shp' files were found after unzip!");
            throw new FileErrorException(RestErrorStatus.FILE_UNAVAILABLE,"no '.shp' files were found!");
        }
        return shapeFile;
    }

}
