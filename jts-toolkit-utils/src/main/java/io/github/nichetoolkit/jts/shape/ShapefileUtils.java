package io.github.nichetoolkit.jts.shape;

import io.github.nichetoolkit.jts.configure.JtsShapeProperties;
import io.github.nichetoolkit.jts.constant.JtsConstants;
import io.github.nichetoolkit.jts.shape.simple.SimpleShapefile;
import io.github.nichetoolkit.rest.RestErrorStatus;
import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rest.error.natives.FileErrorException;
import io.github.nichetoolkit.rest.error.often.FieldNullException;
import io.github.nichetoolkit.rest.error.often.IoStreamReadException;
import io.github.nichetoolkit.rest.error.often.IoStreamWriteException;
import io.github.nichetoolkit.rest.helper.CloseableHelper;
import io.github.nichetoolkit.rest.helper.IoStreamHelper;
import io.github.nichetoolkit.rest.util.CollectUtils;
import io.github.nichetoolkit.rest.util.FileUtils;
import io.github.nichetoolkit.rest.util.GeneralUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FilenameUtils;
import org.geotools.geometry.jts.Geometries;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

/**
 * <code>ShapefileUtils</code>
 * <p>The shapefile utils class.</p>
 * @see  lombok.extern.slf4j.Slf4j
 * @see  java.lang.SuppressWarnings
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
@Slf4j
@SuppressWarnings("unused")
public class ShapefileUtils {
    /**
     * <code>SHAPE_FACTORY</code>
     * {@link io.github.nichetoolkit.jts.shape.ShapeFactory} <p>The <code>SHAPE_FACTORY</code> field.</p>
     * @see  io.github.nichetoolkit.jts.shape.ShapeFactory
     */
    private static ShapeFactory<SimpleShapefile> SHAPE_FACTORY;
    /**
     * <code>PROPERTIES</code>
     * {@link io.github.nichetoolkit.jts.configure.JtsShapeProperties} <p>The constant <code>PROPERTIES</code> field.</p>
     * @see  io.github.nichetoolkit.jts.configure.JtsShapeProperties
     */
    private static JtsShapeProperties PROPERTIES;

    /**
     * <code>initShapefile</code>
     * <p>The init shapefile method.</p>
     * @param shapeFactory {@link io.github.nichetoolkit.jts.shape.ShapeFactory} <p>The shape factory parameter is <code>ShapeFactory</code> type.</p>
     * @param properties {@link io.github.nichetoolkit.jts.configure.JtsShapeProperties} <p>The properties parameter is <code>JtsShapeProperties</code> type.</p>
     * @see  io.github.nichetoolkit.jts.shape.ShapeFactory
     * @see  io.github.nichetoolkit.jts.configure.JtsShapeProperties
     */
    public static void initShapefile(ShapeFactory<SimpleShapefile> shapeFactory, JtsShapeProperties properties) {
        SHAPE_FACTORY = shapeFactory;
        PROPERTIES = properties;
    }

    /**
     * <code>readShapeFile</code>
     * <p>The read shape file method.</p>
     * @param uuid {@link java.lang.String} <p>The uuid parameter is <code>String</code> type.</p>
     * @param file {@link org.springframework.web.multipart.MultipartFile} <p>The file parameter is <code>MultipartFile</code> type.</p>
     * @see  java.lang.String
     * @see  org.springframework.web.multipart.MultipartFile
     * @see  java.util.List
     * @see  io.github.nichetoolkit.rest.RestException
     * @return  {@link java.util.List} <p>The read shape file return object is <code>List</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     */
    public static List<SimpleShapefile> readShapeFile(String uuid, MultipartFile file) throws RestException {
        if (GeneralUtils.isEmpty(SHAPE_FACTORY)) {
            log.error("the shape factory  need to initialize!");
            throw new FieldNullException("shape factory", "please initialize the shape file factory !");
        }
        File shapeFile = ShapefileUtils.shapeFile(uuid, file);
        return SHAPE_FACTORY.read(shapeFile);
    }

    /**
     * <code>writeShapeFile</code>
     * <p>The write shape file method.</p>
     * @param uuid {@link java.lang.String} <p>The uuid parameter is <code>String</code> type.</p>
     * @param filename {@link java.lang.String} <p>The filename parameter is <code>String</code> type.</p>
     * @param geometriesListMap {@link java.util.Map} <p>The geometries list map parameter is <code>Map</code> type.</p>
     * @see  java.lang.String
     * @see  java.util.Map
     * @see  java.io.File
     * @see  io.github.nichetoolkit.rest.RestException
     * @return  {@link java.io.File} <p>The write shape file return object is <code>File</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     */
    public static File writeShapeFile(String uuid, String filename, Map<Geometries, List<SimpleShapefile>> geometriesListMap) throws RestException {
        return writeShapeFile(uuid, filename, null, geometriesListMap);
    }

    /**
     * <code>writeShapeFile</code>
     * <p>The write shape file method.</p>
     * @param uuid {@link java.lang.String} <p>The uuid parameter is <code>String</code> type.</p>
     * @param filename {@link java.lang.String} <p>The filename parameter is <code>String</code> type.</p>
     * @param attributeClassMap {@link java.util.Map} <p>The attribute class map parameter is <code>Map</code> type.</p>
     * @param geometriesListMap {@link java.util.Map} <p>The geometries list map parameter is <code>Map</code> type.</p>
     * @see  java.lang.String
     * @see  java.util.Map
     * @see  java.io.File
     * @see  io.github.nichetoolkit.rest.RestException
     * @return  {@link java.io.File} <p>The write shape file return object is <code>File</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     */
    public static File writeShapeFile(String uuid, String filename, Map<String, Class<?>> attributeClassMap, Map<Geometries, List<SimpleShapefile>> geometriesListMap) throws RestException {
        if (GeneralUtils.isEmpty(SHAPE_FACTORY) || GeneralUtils.isEmpty(PROPERTIES)) {
            throw new FieldNullException("The shape factory", "please initialize the shape file factory and shape properties !");
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

    /**
     * <code>clear</code>
     * <p>The clear method.</p>
     * @param uuid {@link java.lang.String} <p>The uuid parameter is <code>String</code> type.</p>
     * @see  java.lang.String
     * @see  io.github.nichetoolkit.rest.RestException
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     */
    public static void clear(String uuid) throws RestException {
        if (GeneralUtils.isEmpty(PROPERTIES)) {
            throw new FieldNullException("shape properties", "please initialize the shape properties !");
        }
        String cachePath = PROPERTIES.getSpace().getCachePath(uuid);
        FileUtils.clear(cachePath);
    }

    /**
     * <code>shapeFile</code>
     * <p>The shape file method.</p>
     * @param uuid {@link java.lang.String} <p>The uuid parameter is <code>String</code> type.</p>
     * @param shape {@link org.springframework.web.multipart.MultipartFile} <p>The shape parameter is <code>MultipartFile</code> type.</p>
     * @see  java.lang.String
     * @see  org.springframework.web.multipart.MultipartFile
     * @see  java.io.File
     * @see  io.github.nichetoolkit.rest.RestException
     * @return  {@link java.io.File} <p>The shape file return object is <code>File</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     */
    public static File shapeFile(String uuid, MultipartFile shape) throws RestException {
        if (GeneralUtils.isEmpty(PROPERTIES)) {
            throw new FieldNullException("shape properties", "please initialize the shape properties !");
        }
        File shapeZipFile = cacheShapeFile(shape, PROPERTIES.getSpace().getCachePath(uuid));
        return unzipShapeFile(shapeZipFile, PROPERTIES.getSpace().getZipPath(uuid));
    }

    /**
     * <code>cacheShapeFile</code>
     * <p>The cache shape file method.</p>
     * @param shape {@link org.springframework.web.multipart.MultipartFile} <p>The shape parameter is <code>MultipartFile</code> type.</p>
     * @param cachePath {@link java.lang.String} <p>The cache path parameter is <code>String</code> type.</p>
     * @see  org.springframework.web.multipart.MultipartFile
     * @see  java.lang.String
     * @see  java.io.File
     * @see  io.github.nichetoolkit.rest.RestException
     * @return  {@link java.io.File} <p>The cache shape file return object is <code>File</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     */
    public static File cacheShapeFile(MultipartFile shape, String cachePath) throws RestException {
        if (GeneralUtils.isEmpty(cachePath)) {
            throw new FieldNullException("shape cache path", "please initialize the shape file cache path!");
        }
        return FileUtils.cacheFile(cachePath, shape);
    }

    /**
     * <code>zipShapeFile</code>
     * <p>The zip shape file method.</p>
     * @param uuid {@link java.lang.String} <p>The uuid parameter is <code>String</code> type.</p>
     * @param shapeFile {@link java.io.File} <p>The shape file parameter is <code>File</code> type.</p>
     * @see  java.lang.String
     * @see  java.io.File
     * @see  io.github.nichetoolkit.rest.RestException
     * @return  {@link java.io.File} <p>The zip shape file return object is <code>File</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     */
    public static File zipShapeFile(String uuid, File shapeFile) throws RestException {
        if (GeneralUtils.isEmpty(PROPERTIES)) {
            throw new FieldNullException("shape space", "please initialize the shape properties !");
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

    /**
     * <code>zipFiles</code>
     * <p>The zip files method.</p>
     * @param zipPath {@link java.lang.String} <p>The zip path parameter is <code>String</code> type.</p>
     * @param zipFiles {@link java.util.Map} <p>The zip files parameter is <code>Map</code> type.</p>
     * @see  java.lang.String
     * @see  java.util.Map
     * @see  java.io.File
     * @see  io.github.nichetoolkit.rest.RestException
     * @return  {@link java.io.File} <p>The zip files return object is <code>File</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     */
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

    /**
     * <code>zipFiles</code>
     * <p>The zip files method.</p>
     * @param zipPath {@link java.lang.String} <p>The zip path parameter is <code>String</code> type.</p>
     * @param filename {@link java.lang.String} <p>The filename parameter is <code>String</code> type.</p>
     * @param zipFiles {@link java.util.List} <p>The zip files parameter is <code>List</code> type.</p>
     * @see  java.lang.String
     * @see  java.util.List
     * @see  java.io.File
     * @see  io.github.nichetoolkit.rest.RestException
     * @return  {@link java.io.File} <p>The zip files return object is <code>File</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     */
    public static File zipFiles(String zipPath, String filename, List<File> zipFiles) throws RestException {
        if (zipFiles.size() == 1) {
            return zipFiles.stream().findFirst().get();
        }
        String zipFilePath = zipPath + File.separator + filename + JtsConstants.ZIP_EXT;
        File zipFile = FileUtils.createFile(zipFilePath);
        try (ZipOutputStream zipOutputStream = new ZipOutputStream(Files.newOutputStream(zipFile.toPath()))) {
            zipOutputStream.setComment(filename);
            for (File shapeFile : zipFiles) {
                try (InputStream inputStream = Files.newInputStream(shapeFile.toPath())) {
                    zipOutputStream.putNextEntry(new ZipEntry(shapeFile.getName()));
                    int temp;
                    while ((temp = inputStream.read()) != -1) {
                        zipOutputStream.write(temp);
                    }
                }
            }
        } catch (IOException exception) {
            throw new IoStreamWriteException(zipPath, "zip error: ".concat(exception.getMessage()));
        }
        return zipFile;
    }

    /**
     * <code>unzipShapeFile</code>
     * <p>The unzip shape file method.</p>
     * @param shapeZipFile {@link java.io.File} <p>The shape zip file parameter is <code>File</code> type.</p>
     * @param zipPath {@link java.lang.String} <p>The zip path parameter is <code>String</code> type.</p>
     * @see  java.io.File
     * @see  java.lang.String
     * @see  io.github.nichetoolkit.rest.RestException
     * @return  {@link java.io.File} <p>The unzip shape file return object is <code>File</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     */
    public static File unzipShapeFile(File shapeZipFile, String zipPath) throws RestException {
        if (GeneralUtils.isEmpty(zipPath)) {
            throw new FieldNullException("shape zip path", "please initialize the shape file zip path!");
        }
        String suffix = FileUtils.suffix(shapeZipFile.getName());
        if (GeneralUtils.isEmpty(suffix)) {
            throw new FileErrorException(RestErrorStatus.FILE_UNAVAILABLE, "only zip files are supported, the file suffix is null!");
        }
        if (!JtsConstants.ZIP_SUFFIX.equals(suffix)) {
            throw new FileErrorException(RestErrorStatus.FILE_UNAVAILABLE, "only zip files are supported! suffix: ".concat(suffix));
        }
        File shapeFile = null;
        ZipFile zipFile = null;
        try {
            zipFile = new ZipFile(shapeZipFile, StandardCharsets.ISO_8859_1);
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
                    IoStreamHelper.write(fileOutputStream, inputStream);
                }
            }
        } catch (IOException exception) {
            throw new IoStreamReadException(shapeZipFile.getAbsolutePath(), "unzip error: ".concat(exception.getMessage()));
        } finally {
            CloseableHelper.close(zipFile);
        }
        if (GeneralUtils.isEmpty(shapeFile)) {
            throw new FileErrorException(RestErrorStatus.FILE_UNAVAILABLE, "no '.shp' files were found!");
        }
        return shapeFile;
    }

}
