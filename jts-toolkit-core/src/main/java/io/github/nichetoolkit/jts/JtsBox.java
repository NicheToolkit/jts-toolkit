package io.github.nichetoolkit.jts;

import io.github.nichetoolkit.jts.error.JtsBoxInvalidException;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * <p>JtsBox</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
@SuppressWarnings("unused")
@Getter
@Setter
public class JtsBox implements Serializable {
    public static final Double MIN_LATITUDE = -90.0;
    public static final Double MIN_INVALID_LATITUDE = -91.0;
    public static final Double MAX_LATITUDE = 90.0;
    public static final Double MAX_INVALID_LATITUDE = 91.0;
    public static final Double MIN_LONGITUDE = -180.0;
    public static final Double MIN_INVALID_LONGITUDE = -181.0;
    public static final Double MAX_LONGITUDE = 180.0;
    public static final Double MAX_INVALID_LONGITUDE = 181.0;

    protected Double minX = 0.0;
    protected Double minY = 0.0;
    protected Double minZ = 0.0;
    protected Double maxX = 0.0;
    protected Double maxY = 0.0;
    protected Double maxZ = 0.0;

    public JtsBox() {
    }

    public JtsBox(Double minX, Double minY, Double maxX, Double maxY) {
        this.minX = minX;
        this.minY = minY;
        this.maxX = maxX;
        this.maxY = maxY;
    }

    public JtsBox(JtsBox.Builder builder) {
        this.minX = builder.minX;
        this.minY = builder.minY;
        this.minZ = builder.minZ;
        this.maxX = builder.maxX;
        this.maxY = builder.maxY;
        this.maxZ = builder.maxZ;
    }

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

    public static void verify(JtsBox box) throws JtsBoxInvalidException {
        box.verify();
    }

    public static boolean invalidX(Double valueX) {
        return valueX == null || valueX > MAX_INVALID_LONGITUDE || valueX < MIN_INVALID_LONGITUDE;
    }

    public static boolean invalidY(Double valueY) {
        return valueY == null || valueY > MAX_INVALID_LATITUDE || valueY < MIN_INVALID_LATITUDE;
    }

    @SuppressWarnings("unused")
    public static class Builder {
        protected Double minX = 0.0;
        protected Double minY = 0.0;
        protected Double minZ = 0.0;
        protected Double maxX = 0.0;
        protected Double maxY = 0.0;
        protected Double maxZ = 0.0;

        public Builder() {
        }

        public JtsBox.Builder minX(Double minX) {
            this.minX = minX;
            return this;
        }

        public JtsBox.Builder minY(Double minY) {
            this.minY = minY;
            return this;
        }

        public JtsBox.Builder minZ(Double minZ) {
            this.minZ = minZ;
            return this;
        }

        public JtsBox.Builder maxX(Double maxX) {
            this.maxX = maxX;
            return this;
        }

        public JtsBox.Builder maxY(Double maxY) {
            this.maxY = maxY;
            return this;
        }

        public JtsBox.Builder maxZ(Double maxZ) {
            this.maxZ = maxZ;
            return this;
        }

        public JtsBox build() {
            return new JtsBox(this);
        }
    }
}
