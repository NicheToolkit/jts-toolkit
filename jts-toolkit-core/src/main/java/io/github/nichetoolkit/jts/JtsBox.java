package io.github.nichetoolkit.jts;

import io.github.nichetoolkit.jts.error.JtsBoxInvalidException;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

/**
 * <code>JtsBox</code>
 * <p>The jts box class.</p>
 * @author Cyan (snow22314@outlook.com)
 * @see java.io.Serializable
 * @see lombok.Getter
 * @see lombok.Setter
 * @see lombok.experimental.SuperBuilder
 * @see lombok.NoArgsConstructor
 * @see lombok.AllArgsConstructor
 * @see java.lang.SuppressWarnings
 * @since Jdk17
 */
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@SuppressWarnings("unused")
public class JtsBox implements Serializable {
    /**
     * <code>MIN_LATITUDE</code>
     * {@link java.lang.Double} <p>The constant <code>MIN_LATITUDE</code> field.</p>
     * @see java.lang.Double
     */
    public static final Double MIN_LATITUDE = -90.0;
    /**
     * <code>MIN_INVALID_LATITUDE</code>
     * {@link java.lang.Double} <p>The constant <code>MIN_INVALID_LATITUDE</code> field.</p>
     * @see java.lang.Double
     */
    public static final Double MIN_INVALID_LATITUDE = -91.0;
    /**
     * <code>MAX_LATITUDE</code>
     * {@link java.lang.Double} <p>The constant <code>MAX_LATITUDE</code> field.</p>
     * @see java.lang.Double
     */
    public static final Double MAX_LATITUDE = 90.0;
    /**
     * <code>MAX_INVALID_LATITUDE</code>
     * {@link java.lang.Double} <p>The constant <code>MAX_INVALID_LATITUDE</code> field.</p>
     * @see java.lang.Double
     */
    public static final Double MAX_INVALID_LATITUDE = 91.0;
    /**
     * <code>MIN_LONGITUDE</code>
     * {@link java.lang.Double} <p>The constant <code>MIN_LONGITUDE</code> field.</p>
     * @see java.lang.Double
     */
    public static final Double MIN_LONGITUDE = -180.0;
    /**
     * <code>MIN_INVALID_LONGITUDE</code>
     * {@link java.lang.Double} <p>The constant <code>MIN_INVALID_LONGITUDE</code> field.</p>
     * @see java.lang.Double
     */
    public static final Double MIN_INVALID_LONGITUDE = -181.0;
    /**
     * <code>MAX_LONGITUDE</code>
     * {@link java.lang.Double} <p>The constant <code>MAX_LONGITUDE</code> field.</p>
     * @see java.lang.Double
     */
    public static final Double MAX_LONGITUDE = 180.0;
    /**
     * <code>MAX_INVALID_LONGITUDE</code>
     * {@link java.lang.Double} <p>The constant <code>MAX_INVALID_LONGITUDE</code> field.</p>
     * @see java.lang.Double
     */
    public static final Double MAX_INVALID_LONGITUDE = 181.0;

    /**
     * <code>minX</code>
     * {@link java.lang.Double} <p>The <code>minX</code> field.</p>
     * @see java.lang.Double
     * @see lombok.Builder.Default
     */
    @Builder.Default
    protected Double minX = 0.0;
    /**
     * <code>minY</code>
     * {@link java.lang.Double} <p>The <code>minY</code> field.</p>
     * @see java.lang.Double
     * @see lombok.Builder.Default
     */
    @Builder.Default
    protected Double minY = 0.0;
    /**
     * <code>minZ</code>
     * {@link java.lang.Double} <p>The <code>minZ</code> field.</p>
     * @see java.lang.Double
     * @see lombok.Builder.Default
     */
    @Builder.Default
    protected Double minZ = 0.0;
    /**
     * <code>maxX</code>
     * {@link java.lang.Double} <p>The <code>maxX</code> field.</p>
     * @see java.lang.Double
     * @see lombok.Builder.Default
     */
    @Builder.Default
    protected Double maxX = 0.0;
    /**
     * <code>maxY</code>
     * {@link java.lang.Double} <p>The <code>maxY</code> field.</p>
     * @see java.lang.Double
     * @see lombok.Builder.Default
     */
    @Builder.Default
    protected Double maxY = 0.0;
    /**
     * <code>maxZ</code>
     * {@link java.lang.Double} <p>The <code>maxZ</code> field.</p>
     * @see java.lang.Double
     * @see lombok.Builder.Default
     */
    @Builder.Default
    protected Double maxZ = 0.0;

    /**
     * <code>JtsBox</code>
     * <p>Instantiates a new jts box.</p>
     * @param minX {@link java.lang.Double} <p>The min x parameter is <code>Double</code> type.</p>
     * @param minY {@link java.lang.Double} <p>The min y parameter is <code>Double</code> type.</p>
     * @param maxX {@link java.lang.Double} <p>The max x parameter is <code>Double</code> type.</p>
     * @param maxY {@link java.lang.Double} <p>The max y parameter is <code>Double</code> type.</p>
     * @see java.lang.Double
     */
    public JtsBox(Double minX, Double minY, Double maxX, Double maxY) {
        this();
        this.minX = minX;
        this.minY = minY;
        this.maxX = maxX;
        this.maxY = maxY;
    }

    /**
     * <code>invalid</code>
     * <p>The invalid method.</p>
     * @return {@link java.lang.Boolean} <p>The invalid return object is <code>Boolean</code> type.</p>
     * @see java.lang.Boolean
     */
    public Boolean invalid() {
        if (invalidX(this.maxX)) {
            return true;
        }
        if (invalidX(this.minX)) {
            return true;
        }
        if (invalidY(this.maxY)) {
            return true;
        }
        return invalidY(this.minY);
    }

    /**
     * <code>verify</code>
     * <p>The verify method.</p>
     * @throws JtsBoxInvalidException {@link io.github.nichetoolkit.jts.error.JtsBoxInvalidException} <p>The jts box invalid exception is <code>JtsBoxInvalidException</code> type.</p>
     * @see io.github.nichetoolkit.jts.error.JtsBoxInvalidException
     */
    public void verify() throws JtsBoxInvalidException {
        if (invalidX(this.maxX)) {
            throw new JtsBoxInvalidException("值[maxX]:" + this.maxX + " 无效");
        }
        if (invalidX(this.minX)) {
            throw new JtsBoxInvalidException("值[minX]:" + this.minX + " 无效");
        }
        if (invalidY(this.maxY)) {
            throw new JtsBoxInvalidException("值[maxY]:" + this.maxY + " 无效");
        }
        if (invalidY(this.minY)) {
            throw new JtsBoxInvalidException("值[minY]:" + this.minY + " 无效");
        }
    }

    /**
     * <code>verify</code>
     * <p>The verify method.</p>
     * @param box {@link io.github.nichetoolkit.jts.JtsBox} <p>The box parameter is <code>JtsBox</code> type.</p>
     * @throws JtsBoxInvalidException {@link io.github.nichetoolkit.jts.error.JtsBoxInvalidException} <p>The jts box invalid exception is <code>JtsBoxInvalidException</code> type.</p>
     * @see io.github.nichetoolkit.jts.error.JtsBoxInvalidException
     */
    public static void verify(JtsBox box) throws JtsBoxInvalidException {
        box.verify();
    }

    /**
     * <code>invalidX</code>
     * <p>The invalid x method.</p>
     * @param valueX {@link java.lang.Double} <p>The value x parameter is <code>Double</code> type.</p>
     * @return boolean <p>The invalid x return object is <code>boolean</code> type.</p>
     * @see java.lang.Double
     */
    public static boolean invalidX(Double valueX) {
        return valueX == null || valueX > MAX_INVALID_LONGITUDE || valueX < MIN_INVALID_LONGITUDE;
    }

    /**
     * <code>invalidY</code>
     * <p>The invalid y method.</p>
     * @param valueY {@link java.lang.Double} <p>The value y parameter is <code>Double</code> type.</p>
     * @return boolean <p>The invalid y return object is <code>boolean</code> type.</p>
     * @see java.lang.Double
     */
    public static boolean invalidY(Double valueY) {
        return valueY == null || valueY > MAX_INVALID_LATITUDE || valueY < MIN_INVALID_LATITUDE;
    }

}
