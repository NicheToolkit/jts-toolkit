package io.github.nichetoolkit.jts.shape;

/**
 * <p>ShapeCache</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public class ShapeConfig {
    private String cacheSpace;
    private String zipSpace;

    public ShapeConfig() {
    }

    public ShapeConfig(String cacheSpace, String zipSpace) {
        this.cacheSpace = cacheSpace;
        this.zipSpace = zipSpace;
    }

    public String getCacheSpace() {
        return cacheSpace;
    }

    public void setCacheSpace(String cacheSpace) {
        this.cacheSpace = cacheSpace;
    }

    public String getZipSpace() {
        return zipSpace;
    }

    public void setZipSpace(String zipSpace) {
        this.zipSpace = zipSpace;
    }
}
