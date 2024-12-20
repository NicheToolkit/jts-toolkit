package io.github.nichetoolkit.jts.configure;

import io.github.nichetoolkit.jts.constant.JtsConstants;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.stereotype.Component;

import java.io.File;

/**
 * <code>JtsShapeProperties</code>
 * <p>The jts shape properties class.</p>
 * @see  lombok.Setter
 * @see  lombok.Getter
 * @see  org.springframework.stereotype.Component
 * @see  org.springframework.boot.context.properties.ConfigurationProperties
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
@Setter
@Getter
@Component
@ConfigurationProperties(prefix = "nichetoolkit.jts.shape")
public class JtsShapeProperties {
    /**
     * <code>enabled</code>
     * {@link java.lang.Boolean} <p>The <code>enabled</code> field.</p>
     * @see  java.lang.Boolean
     */
    private Boolean enabled = false;
    /**
     * <code>space</code>
     * {@link io.github.nichetoolkit.jts.configure.JtsShapeProperties.Space} <p>The <code>space</code> field.</p>
     * @see  io.github.nichetoolkit.jts.configure.JtsShapeProperties.Space
     * @see  org.springframework.boot.context.properties.NestedConfigurationProperty
     */
    @NestedConfigurationProperty
    private Space space = new Space();

    /**
     * <code>Space</code>
     * <p>The space class.</p>
     * @see  lombok.Setter
     * @see  lombok.Getter
     * @author Cyan (snow22314@outlook.com)
     * @since Jdk1.8
     */
    @Setter
    @Getter
    public static class Space {
        /**
         * <code>rootPath</code>
         * {@link java.lang.String} <p>The <code>rootPath</code> field.</p>
         * @see  java.lang.String
         */
        private String rootPath = toRootPath();
        /**
         * <code>cachePath</code>
         * {@link java.lang.String} <p>The <code>cachePath</code> field.</p>
         * @see  java.lang.String
         */
        private String cachePath = toCachePath();
        /**
         * <code>shapePath</code>
         * {@link java.lang.String} <p>The <code>shapePath</code> field.</p>
         * @see  java.lang.String
         */
        private String shapePath = toShapePath();
        /**
         * <code>zipPath</code>
         * {@link java.lang.String} <p>The <code>zipPath</code> field.</p>
         * @see  java.lang.String
         */
        private String zipPath = toZipPath();

        /**
         * <code>getRootPath</code>
         * <p>The get root path getter method.</p>
         * @return  {@link java.lang.String} <p>The get root path return object is <code>String</code> type.</p>
         * @see  java.lang.String
         */
        public String getRootPath() {
            String trim = this.rootPath.toLowerCase().trim();
            if (!trim.endsWith(JtsConstants.SHAPE_ROOT_PREFIX) || !trim.contains(JtsConstants.SHAPE_ROOT_PREFIX)) {
                if (!trim.endsWith(File.separator)) {
                    return trim.concat(File.separator).concat(JtsConstants.SHAPE_ROOT_PREFIX);
                } else {
                    return trim.concat(JtsConstants.SHAPE_ROOT_PREFIX);
                }
            }
            return trim;
        }

        /**
         * <code>getCachePath</code>
         * <p>The get cache path getter method.</p>
         * @return  {@link java.lang.String} <p>The get cache path return object is <code>String</code> type.</p>
         * @see  java.lang.String
         */
        public String getCachePath() {
            String trim = this.cachePath.toLowerCase().trim();
            return concatRoot(trim);
        }

        /**
         * <code>getCachePath</code>
         * <p>The get cache path getter method.</p>
         * @param uuid {@link java.lang.String} <p>The uuid parameter is <code>String</code> type.</p>
         * @see  java.lang.String
         * @return  {@link java.lang.String} <p>The get cache path return object is <code>String</code> type.</p>
         */
        public String getCachePath(String uuid) {
            String trim = this.cachePath.toLowerCase().trim();
            return concatRoot(trim).concat(File.separator).concat(uuid);
        }


        /**
         * <code>getShapePath</code>
         * <p>The get shape path getter method.</p>
         * @return  {@link java.lang.String} <p>The get shape path return object is <code>String</code> type.</p>
         * @see  java.lang.String
         */
        public String getShapePath() {
            String trim = this.shapePath.toLowerCase().trim();
            return concatCache(trim);
        }

        /**
         * <code>getShapePath</code>
         * <p>The get shape path getter method.</p>
         * @param uuid {@link java.lang.String} <p>The uuid parameter is <code>String</code> type.</p>
         * @see  java.lang.String
         * @return  {@link java.lang.String} <p>The get shape path return object is <code>String</code> type.</p>
         */
        public String getShapePath(String uuid) {
            String trim = this.shapePath.toLowerCase().trim();
            return concatCache(trim, uuid);
        }

        /**
         * <code>getZipPath</code>
         * <p>The get zip path getter method.</p>
         * @return  {@link java.lang.String} <p>The get zip path return object is <code>String</code> type.</p>
         * @see  java.lang.String
         */
        public String getZipPath() {
            String trim = this.zipPath.toLowerCase().trim();
            return concatCache(trim);
        }

        /**
         * <code>getZipPath</code>
         * <p>The get zip path getter method.</p>
         * @param uuid {@link java.lang.String} <p>The uuid parameter is <code>String</code> type.</p>
         * @see  java.lang.String
         * @return  {@link java.lang.String} <p>The get zip path return object is <code>String</code> type.</p>
         */
        public String getZipPath(String uuid) {
            String trim = this.zipPath.toLowerCase().trim();
            return concatCache(trim, uuid);
        }

        /**
         * <code>concatRoot</code>
         * <p>The concat root method.</p>
         * @param path {@link java.lang.String} <p>The path parameter is <code>String</code> type.</p>
         * @see  java.lang.String
         * @return  {@link java.lang.String} <p>The concat root return object is <code>String</code> type.</p>
         */
        private String concatRoot(String path) {
            String rootPath = getRootPath();
            if (!path.startsWith(rootPath) || !path.contains(rootPath)) {
                return rootPath.concat(path);
            }
            return path;
        }

        /**
         * <code>concatCache</code>
         * <p>The concat cache method.</p>
         * @param path {@link java.lang.String} <p>The path parameter is <code>String</code> type.</p>
         * @see  java.lang.String
         * @return  {@link java.lang.String} <p>The concat cache return object is <code>String</code> type.</p>
         */
        private String concatCache(String path) {
            String cachePath = getCachePath();
            if (!path.startsWith(cachePath) || !path.contains(cachePath)) {
                return cachePath.concat(path);
            }
            return path;
        }

        /**
         * <code>concatCache</code>
         * <p>The concat cache method.</p>
         * @param path {@link java.lang.String} <p>The path parameter is <code>String</code> type.</p>
         * @param uuid {@link java.lang.String} <p>The uuid parameter is <code>String</code> type.</p>
         * @see  java.lang.String
         * @return  {@link java.lang.String} <p>The concat cache return object is <code>String</code> type.</p>
         */
        private String concatCache(String path, String uuid) {
            String cachePath = getCachePath(uuid);
            if (!path.startsWith(cachePath) || !path.contains(cachePath)) {
                return cachePath.concat(path);
            }
            return path;
        }

    }


    /**
     * <code>toRootPath</code>
     * <p>The to root path method.</p>
     * @return  {@link java.lang.String} <p>The to root path return object is <code>String</code> type.</p>
     * @see  java.lang.String
     */
    public static String toRootPath() {
        return File.separator.concat(JtsConstants.DATA_PREFIX).concat(File.separator).concat(JtsConstants.SHAPE_ROOT_PREFIX);
    }

    /**
     * <code>toCachePath</code>
     * <p>The to cache path method.</p>
     * @return  {@link java.lang.String} <p>The to cache path return object is <code>String</code> type.</p>
     * @see  java.lang.String
     */
    public static String toCachePath() {
        return File.separator.concat(JtsConstants.CACHE_PREFIX).concat(File.separator);
    }

    /**
     * <code>toShapePath</code>
     * <p>The to shape path method.</p>
     * @return  {@link java.lang.String} <p>The to shape path return object is <code>String</code> type.</p>
     * @see  java.lang.String
     */
    public static String toShapePath() {
        return File.separator.concat(JtsConstants.SHAPE_PREFIX).concat(File.separator);
    }

    /**
     * <code>toZipPath</code>
     * <p>The to zip path method.</p>
     * @return  {@link java.lang.String} <p>The to zip path return object is <code>String</code> type.</p>
     * @see  java.lang.String
     */
    public static String toZipPath() {
        return File.separator.concat(JtsConstants.ZIP_SUFFIX).concat(File.separator);
    }

}
