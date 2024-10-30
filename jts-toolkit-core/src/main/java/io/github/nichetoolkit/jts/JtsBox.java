package io.github.nichetoolkit.jts;

import io.github.nichetoolkit.jts.error.JtsBoxInvalidException;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * <code>JtsBox</code>
 * <p>The jts box class.</p>
 * @see  java.io.Serializable
 * @see  java.lang.SuppressWarnings
 * @see  lombok.Getter
 * @see  lombok.Setter
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
@SuppressWarnings("unused")
@Getter
@Setter
public class JtsBox implements Serializable {
    /**
     * <code>MIN_LATITUDE</code>
     * {@link java.lang.Double} <p>The constant <code>MIN_LATITUDE</code> field.</p>
     * @see  java.lang.Double
     */
    public static final Double MIN_LATITUDE = -90.0;
    /**
     * <code>MIN_INVALID_LATITUDE</code>
     * {@link java.lang.Double} <p>The constant <code>MIN_INVALID_LATITUDE</code> field.</p>
     * @see  java.lang.Double
     */
    public static final Double MIN_INVALID_LATITUDE = -91.0;
    /**
     * <code>MAX_LATITUDE</code>
     * {@link java.lang.Double} <p>The constant <code>MAX_LATITUDE</code> field.</p>
     * @see  java.lang.Double
     */
    public static final Double MAX_LATITUDE = 90.0;
    /**
     * <code>MAX_INVALID_LATITUDE</code>
     * {@link java.lang.Double} <p>The constant <code>MAX_INVALID_LATITUDE</code> field.</p>
     * @see  java.lang.Double
     */
    public static final Double MAX_INVALID_LATITUDE = 91.0;
    /**
     * <code>MIN_LONGITUDE</code>
     * {@link java.lang.Double} <p>The constant <code>MIN_LONGITUDE</code> field.</p>
     * @see  java.lang.Double
     */
    public static final Double MIN_LONGITUDE = -180.0;
    /**
     * <code>MIN_INVALID_LONGITUDE</code>
     * {@link java.lang.Double} <p>The constant <code>MIN_INVALID_LONGITUDE</code> field.</p>
     * @see  java.lang.Double
     */
    public static final Double MIN_INVALID_LONGITUDE = -181.0;
    /**
     * <code>MAX_LONGITUDE</code>
     * {@link java.lang.Double} <p>The constant <code>MAX_LONGITUDE</code> field.</p>
     * @see  java.lang.Double
     */
    public static final Double MAX_LONGITUDE = 180.0;
    /**
     * <code>MAX_INVALID_LONGITUDE</code>
     * {@link java.lang.Double} <p>The constant <code>MAX_INVALID_LONGITUDE</code> field.</p>
     * @see  java.lang.Double
     */
    public static final Double MAX_INVALID_LONGITUDE = 181.0;

    /**
     * <code>minX</code>
     * {@link java.lang.Double} <p>The <code>minX</code> field.</p>
     * @see  java.lang.Double
     */
    protected Double minX = 0.0;
    /**
     * <code>minY</code>
     * {@link java.lang.Double} <p>The <code>minY</code> field.</p>
     * @see  java.lang.Double
     */
    protected Double minY = 0.0;
    /**
     * <code>minZ</code>
     * {@link java.lang.Double} <p>The <code>minZ</code> field.</p>
     * @see  java.lang.Double
     */
    protected Double minZ = 0.0;
    /**
     * <code>maxX</code>
     * {@link java.lang.Double} <p>The <code>maxX</code> field.</p>
     * @see  java.lang.Double
     */
    protected Double maxX = 0.0;
    /**
     * <code>maxY</code>
     * {@link java.lang.Double} <p>The <code>maxY</code> field.</p>
     * @see  java.lang.Double
     */
    protected Double maxY = 0.0;
    /**
     * <code>maxZ</code>
     * {@link java.lang.Double} <p>The <code>maxZ</code> field.</p>
     * @see  java.lang.Double
     */
    protected Double maxZ = 0.0;

    /**
     * <code>JtsBox</code>
     * <p>Instantiates a new jts box.</p>
     */
    public JtsBox() {
    }

    /**
     * <code>JtsBox</code>
     * <p>Instantiates a new jts box.</p>
     * @param minX {@link java.lang.Double} <p>The min x parameter is <code>Double</code> type.</p>
     * @param minY {@link java.lang.Double} <p>The min y parameter is <code>Double</code> type.</p>
     * @param maxX {@link java.lang.Double} <p>The max x parameter is <code>Double</code> type.</p>
     * @param maxY {@link java.lang.Double} <p>The max y parameter is <code>Double</code> type.</p>
     * @see  java.lang.Double
     */
    public JtsBox(Double minX, Double minY, Double maxX, Double maxY) {
        this.minX = minX;
        this.minY = minY;
        this.maxX = maxX;
        this.maxY = maxY;
    }

    /**
     * <code>JtsBox</code>
     * <p>Instantiates a new jts box.</p>
     * @param builder {@link io.github.nichetoolkit.jts.JtsBox.Builder} <p>The builder parameter is <code>Builder</code> type.</p>
     * @see  io.github.nichetoolkit.jts.JtsBox.Builder
     */
    public JtsBox(JtsBox.Builder builder) {
        this.minX = builder.minX;
        this.minY = builder.minY;
        this.minZ = builder.minZ;
        this.maxX = builder.maxX;
        this.maxY = builder.maxY;
        this.maxZ = builder.maxZ;
    }

    /**
     * <code>invalid</code>
     * <p>The invalid method.</p>
     * @return  {@link java.lang.Boolean} <p>The invalid return object is <code>Boolean</code> type.</p>
     * @see  java.lang.Boolean
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
     * @see  io.github.nichetoolkit.jts.error.JtsBoxInvalidException
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
     * @see  io.github.nichetoolkit.jts.error.JtsBoxInvalidException
     */
    public static void verify(JtsBox box) throws JtsBoxInvalidException {
        box.verify();
    }

    /**
     * <code>invalidX</code>
     * <p>The invalid x method.</p>
     * @param valueX {@link java.lang.Double} <p>The value x parameter is <code>Double</code> type.</p>
     * @see  java.lang.Double
     * @return boolean <p>The invalid x return object is <code>boolean</code> type.</p>
     */
    public static boolean invalidX(Double valueX) {
        return valueX == null || valueX > MAX_INVALID_LONGITUDE || valueX < MIN_INVALID_LONGITUDE;
    }

    /**
     * <code>invalidY</code>
     * <p>The invalid y method.</p>
     * @param valueY {@link java.lang.Double} <p>The value y parameter is <code>Double</code> type.</p>
     * @see  java.lang.Double
     * @return boolean <p>The invalid y return object is <code>boolean</code> type.</p>
     */
    public static boolean invalidY(Double valueY) {
        return valueY == null || valueY > MAX_INVALID_LATITUDE || valueY < MIN_INVALID_LATITUDE;
    }

    /**
     * <code>Builder</code>
     * <p>The builder class.</p>
     * @see  java.lang.SuppressWarnings
     * @author Cyan (snow22314@outlook.com)
     * @since Jdk1.8
     */
    @SuppressWarnings("unused")
    public static class Builder {
        /**
         * <code>minX</code>
         * {@link java.lang.Double} <p>The <code>minX</code> field.</p>
         * @see  java.lang.Double
         */
        protected Double minX = 0.0;
        /**
         * <code>minY</code>
         * {@link java.lang.Double} <p>The <code>minY</code> field.</p>
         * @see  java.lang.Double
         */
        protected Double minY = 0.0;
        /**
         * <code>minZ</code>
         * {@link java.lang.Double} <p>The <code>minZ</code> field.</p>
         * @see  java.lang.Double
         */
        protected Double minZ = 0.0;
        /**
         * <code>maxX</code>
         * {@link java.lang.Double} <p>The <code>maxX</code> field.</p>
         * @see  java.lang.Double
         */
        protected Double maxX = 0.0;
        /**
         * <code>maxY</code>
         * {@link java.lang.Double} <p>The <code>maxY</code> field.</p>
         * @see  java.lang.Double
         */
        protected Double maxY = 0.0;
        /**
         * <code>maxZ</code>
         * {@link java.lang.Double} <p>The <code>maxZ</code> field.</p>
         * @see  java.lang.Double
         */
        protected Double maxZ = 0.0;

        /**
         * <code>Builder</code>
         * <p>Instantiates a new builder.</p>
         */
        public Builder() {
        }

        /**
         * <code>minX</code>
         * <p>The min x method.</p>
         * @param minX {@link java.lang.Double} <p>The min x parameter is <code>Double</code> type.</p>
         * @see  java.lang.Double
         * @return  {@link io.github.nichetoolkit.jts.JtsBox.Builder} <p>The min x return object is <code>Builder</code> type.</p>
         */
        public JtsBox.Builder minX(Double minX) {
            this.minX = minX;
            return this;
        }

        /**
         * <code>minY</code>
         * <p>The min y method.</p>
         * @param minY {@link java.lang.Double} <p>The min y parameter is <code>Double</code> type.</p>
         * @see  java.lang.Double
         * @return  {@link io.github.nichetoolkit.jts.JtsBox.Builder} <p>The min y return object is <code>Builder</code> type.</p>
         */
        public JtsBox.Builder minY(Double minY) {
            this.minY = minY;
            return this;
        }

        /**
         * <code>minZ</code>
         * <p>The min z method.</p>
         * @param minZ {@link java.lang.Double} <p>The min z parameter is <code>Double</code> type.</p>
         * @see  java.lang.Double
         * @return  {@link io.github.nichetoolkit.jts.JtsBox.Builder} <p>The min z return object is <code>Builder</code> type.</p>
         */
        public JtsBox.Builder minZ(Double minZ) {
            this.minZ = minZ;
            return this;
        }

        /**
         * <code>maxX</code>
         * <p>The max x method.</p>
         * @param maxX {@link java.lang.Double} <p>The max x parameter is <code>Double</code> type.</p>
         * @see  java.lang.Double
         * @return  {@link io.github.nichetoolkit.jts.JtsBox.Builder} <p>The max x return object is <code>Builder</code> type.</p>
         */
        public JtsBox.Builder maxX(Double maxX) {
            this.maxX = maxX;
            return this;
        }

        /**
         * <code>maxY</code>
         * <p>The max y method.</p>
         * @param maxY {@link java.lang.Double} <p>The max y parameter is <code>Double</code> type.</p>
         * @see  java.lang.Double
         * @return  {@link io.github.nichetoolkit.jts.JtsBox.Builder} <p>The max y return object is <code>Builder</code> type.</p>
         */
        public JtsBox.Builder maxY(Double maxY) {
            this.maxY = maxY;
            return this;
        }

        /**
         * <code>maxZ</code>
         * <p>The max z method.</p>
         * @param maxZ {@link java.lang.Double} <p>The max z parameter is <code>Double</code> type.</p>
         * @see  java.lang.Double
         * @return  {@link io.github.nichetoolkit.jts.JtsBox.Builder} <p>The max z return object is <code>Builder</code> type.</p>
         */
        public JtsBox.Builder maxZ(Double maxZ) {
            this.maxZ = maxZ;
            return this;
        }

        /**
         * <code>build</code>
         * <p>The build method.</p>
         * @return  {@link io.github.nichetoolkit.jts.JtsBox} <p>The build return object is <code>JtsBox</code> type.</p>
         */
        public JtsBox build() {
            return new JtsBox(this);
        }
    }
}
