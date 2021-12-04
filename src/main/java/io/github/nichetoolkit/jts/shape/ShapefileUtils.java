package io.github.nichetoolkit.jts.shape;

import io.github.nichetoolkit.jts.constant.JtsConstants;
import io.github.nichetoolkit.rest.RestErrorStatus;
import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rest.error.natives.FileErrorException;
import io.github.nichetoolkit.rest.error.often.FieldNullException;
import io.github.nichetoolkit.rest.error.often.StreamReadException;
import io.github.nichetoolkit.rest.helper.CloseableHelper;
import io.github.nichetoolkit.rest.helper.StreamHelper;
import io.github.nichetoolkit.rest.util.common.FileUtils;
import io.github.nichetoolkit.rest.util.common.GeneralUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * <p>ShapefileUtils</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
@Slf4j
public class ShapefileUtils {
    private static ShapeFactory SHAPE_FACTORY = null;

    public static void initShapeFactory(ShapeFactory shapeFactory) {
        SHAPE_FACTORY = shapeFactory;
    }

    public static File shapeFile(MultipartFile shape) throws RestException {
        if (GeneralUtils.isEmpty(SHAPE_FACTORY)) {
            log.error("the shape factory need to initialize!");
            throw new FieldNullException("shape space","please initialize the shape file factory!");
        }
        File shapeZipFile = cacheShapeFile(shape, SHAPE_FACTORY.getShapeConfig().getCacheSpace());
        return unzipShapeFile(shapeZipFile,SHAPE_FACTORY.getShapeConfig().getZipSpace());
    }

    public static File cacheShapeFile(MultipartFile shape) throws RestException {
        if (GeneralUtils.isEmpty(SHAPE_FACTORY)) {
            log.error("the shape factory need to initialize!");
            throw new FieldNullException("shape space","please initialize the shape file factory!");
        }
        return cacheShapeFile(shape,SHAPE_FACTORY.getShapeConfig().getCacheSpace());
    }

    public static File unzipShapeFile(File shapeZipFile) throws RestException {
        if (GeneralUtils.isEmpty(SHAPE_FACTORY)) {
            log.error("the shape factory need to initialize!");
            throw new FieldNullException("shape space","please initialize the shape file factory!");
        }
        return unzipShapeFile(shapeZipFile,SHAPE_FACTORY.getShapeConfig().getZipSpace());
    }

    public static File cacheShapeFile(MultipartFile shape, String cachePath) throws RestException {
        if (GeneralUtils.isEmpty(cachePath)) {
            log.error("the shape cache path need to initialize!");
            throw new FieldNullException("shape cache path","please initialize the shape file cache path!");
        }
        return FileUtils.cacheFile(cachePath, shape);
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
