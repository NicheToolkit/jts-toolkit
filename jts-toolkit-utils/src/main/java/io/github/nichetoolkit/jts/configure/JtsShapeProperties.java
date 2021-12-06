package io.github.nichetoolkit.jts.configure;

import io.github.nichetoolkit.jts.constant.JtsConstants;
import io.github.nichetoolkit.jts.shape.ShapeConfig;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.stereotype.Component;

import java.io.File;

/**
 * <p>ShapeProperties</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
@Component
@ConfigurationProperties(prefix = "nichetoolkit.jts.shape")
public class JtsShapeProperties {
    private static final String ROOT_PREFIX = "nichetoolkit-jts-shape";
    private Boolean enabled = false;
    @NestedConfigurationProperty
    private Space space = new Space();

    public JtsShapeProperties() {
    }

    public static class Space {
        private String rootPath = toRootPath();
        private String cachePath = toCachePath();
        private String shapePath = toShapePath();
        private String zipPath = toZipPath();

        public Space() {
        }

        public String getRootPath() {
            String trim = this.rootPath.toLowerCase().trim();
            if (!trim.endsWith(ROOT_PREFIX) || !trim.contains(ROOT_PREFIX)) {
                if (!trim.endsWith(File.separator)) {
                    return trim.concat(File.separator).concat(ROOT_PREFIX);
                } else {
                    return trim.concat(ROOT_PREFIX);
                }
            }
            return trim;
        }

        public void setRootPath(String rootPath) {
            this.rootPath = rootPath;
        }

        public String getCachePath() {
            String trim = this.cachePath.toLowerCase().trim();
            return concatRoot(trim);
        }

        public String getCachePath(String uuid) {
            String trim = this.cachePath.toLowerCase().trim();
            return concatRoot(trim).concat(File.separator).concat(uuid);
        }

        public void setCachePath(String cachePath) {
            this.cachePath = cachePath;
        }

        public String getShapePath() {
            String trim = this.shapePath.toLowerCase().trim();
            return concatCache(trim);
        }

        public String getShapePath(String uuid) {
            String trim = this.shapePath.toLowerCase().trim();
            return concatCache(trim, uuid);
        }

        public void setShapePath(String shapePath) {
            this.shapePath = shapePath;
        }

        public String getZipPath() {
            String trim = this.zipPath.toLowerCase().trim();
            return concatCache(trim);
        }

        public String getZipPath(String uuid) {
            String trim = this.zipPath.toLowerCase().trim();
            return concatCache(trim, uuid);
        }

        public void setZipPath(String zipPath) {
            this.zipPath = zipPath;
        }

        private String concatRoot(String path) {
            String rootPath = getRootPath();
            if (!path.startsWith(rootPath) || !path.contains(rootPath)) {
                return rootPath.concat(path);
            }
            return path;
        }

        private String concatCache(String path) {
            String cachePath = getCachePath();
            if (!path.startsWith(cachePath) || !path.contains(cachePath)) {
                return cachePath.concat(path);
            }
            return path;
        }

        private String concatCache(String path, String uuid) {
            String cachePath = getCachePath(uuid);
            if (!path.startsWith(cachePath) || !path.contains(cachePath)) {
                return cachePath.concat(path);
            }
            return path;
        }

    }

    public ShapeConfig toConfig() {
        return new ShapeConfig(this.getSpace().getCachePath(),this.getSpace().getZipPath());
    }

    public static String toRootPath() {
        return File.separator.concat(JtsConstants.DATA_PREFIX).concat(File.separator).concat(ROOT_PREFIX);
    }

    public static String toCachePath() {
        return File.separator.concat(JtsConstants.CACHE_PREFIX).concat(File.separator);
    }

    public static String toShapePath() {
        return File.separator.concat(JtsConstants.SHAPE_PREFIX).concat(File.separator);
    }

    public static String toZipPath() {
        return File.separator.concat(JtsConstants.ZIP_SUFFIX).concat(File.separator);
    }

    public static class Jackson {
        private Boolean enabled = false;

        public Jackson() {
        }

        public Boolean getEnabled() {
            return enabled;
        }

        public void setEnabled(Boolean enabled) {
            this.enabled = enabled;
        }
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Space getSpace() {
        return space;
    }

    public void setSpace(Space space) {
        this.space = space;
    }

}
