package io.github.nichetoolkit.jts.shape;

import io.github.nichetoolkit.rest.RestException;
import lombok.Getter;
import lombok.Setter;
import org.geotools.geometry.jts.Geometries;

import java.io.File;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * <code>ShapeFactory</code>
 * <p>The shape factory class.</p>
 * @param <T>  {@link io.github.nichetoolkit.jts.shape.Shapefile} <p>The generic parameter is <code>Shapefile</code> type.</p>
 * @see  io.github.nichetoolkit.jts.shape.Shapefile
 * @see  lombok.Getter
 * @see  lombok.Setter
 * @see  java.lang.SuppressWarnings
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
@Getter
@Setter
@SuppressWarnings("unused")
public abstract class ShapeFactory<T extends Shapefile> {

    /**
     * <code>shapeReader</code>
     * {@link io.github.nichetoolkit.jts.shape.ShapeReader} <p>The <code>shapeReader</code> field.</p>
     * @see  io.github.nichetoolkit.jts.shape.ShapeReader
     */
    protected ShapeReader<T> shapeReader;

    /**
     * <code>shapeWriter</code>
     * {@link io.github.nichetoolkit.jts.shape.ShapeWriter} <p>The <code>shapeWriter</code> field.</p>
     * @see  io.github.nichetoolkit.jts.shape.ShapeWriter
     */
    protected ShapeWriter<T> shapeWriter;

    /**
     * <code>ShapeFactory</code>
     * <p>Instantiates a new shape factory.</p>
     * @param shapeReader {@link io.github.nichetoolkit.jts.shape.ShapeReader} <p>The shape reader parameter is <code>ShapeReader</code> type.</p>
     * @see  io.github.nichetoolkit.jts.shape.ShapeReader
     */
    public ShapeFactory(ShapeReader<T> shapeReader) {
        this.shapeReader = shapeReader;
    }

    /**
     * <code>ShapeFactory</code>
     * <p>Instantiates a new shape factory.</p>
     * @param shapeWriter {@link io.github.nichetoolkit.jts.shape.ShapeWriter} <p>The shape writer parameter is <code>ShapeWriter</code> type.</p>
     * @see  io.github.nichetoolkit.jts.shape.ShapeWriter
     */
    public ShapeFactory(ShapeWriter<T> shapeWriter) {
        this.shapeWriter = shapeWriter;
    }

    /**
     * <code>ShapeFactory</code>
     * <p>Instantiates a new shape factory.</p>
     * @param shapeReader {@link io.github.nichetoolkit.jts.shape.ShapeReader} <p>The shape reader parameter is <code>ShapeReader</code> type.</p>
     * @param shapeWriter {@link io.github.nichetoolkit.jts.shape.ShapeWriter} <p>The shape writer parameter is <code>ShapeWriter</code> type.</p>
     * @see  io.github.nichetoolkit.jts.shape.ShapeReader
     * @see  io.github.nichetoolkit.jts.shape.ShapeWriter
     */
    public ShapeFactory(ShapeReader<T> shapeReader, ShapeWriter<T> shapeWriter) {
        this.shapeReader = shapeReader;
        this.shapeWriter = shapeWriter;
    }

    /**
     * <code>read</code>
     * <p>The read method.</p>
     * @param shapefile {@link java.io.File} <p>The shapefile parameter is <code>File</code> type.</p>
     * @see  java.io.File
     * @see  java.util.List
     * @see  io.github.nichetoolkit.rest.RestException
     * @return  {@link java.util.List} <p>The read return object is <code>List</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     */
    public abstract List<T> read(File shapefile) throws RestException;

    /**
     * <code>write</code>
     * <p>The write method.</p>
     * @param shapefile {@link java.io.File} <p>The shapefile parameter is <code>File</code> type.</p>
     * @param shapefiles {@link java.util.List} <p>The shapefiles parameter is <code>List</code> type.</p>
     * @see  java.io.File
     * @see  java.util.List
     * @see  io.github.nichetoolkit.rest.RestException
     * @return  {@link java.io.File} <p>The write return object is <code>File</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     */
    public abstract File write(File shapefile, List<T> shapefiles) throws RestException;

    /**
     * <code>write</code>
     * <p>The write method.</p>
     * @param shapefile T <p>The shapefile parameter is <code>T</code> type.</p>
     * @return  {@link java.io.File} <p>The write return object is <code>File</code> type.</p>
     * @see  java.io.File
     * @see  io.github.nichetoolkit.rest.RestException
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     */
    public abstract File write(T shapefile) throws RestException;

    /**
     * <code>write</code>
     * <p>The write method.</p>
     * @param shapefile {@link java.util.Collection} <p>The shapefile parameter is <code>Collection</code> type.</p>
     * @see  java.util.Collection
     * @see  java.io.File
     * @see  io.github.nichetoolkit.rest.RestException
     * @return  {@link java.io.File} <p>The write return object is <code>File</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     */
    public abstract File write(Collection<T> shapefile) throws RestException;

    /**
     * <code>write</code>
     * <p>The write method.</p>
     * @param shapeFile {@link java.io.File} <p>The shape file parameter is <code>File</code> type.</p>
     * @param shapefiles {@link java.util.Collection} <p>The shapefiles parameter is <code>Collection</code> type.</p>
     * @see  java.io.File
     * @see  java.util.Collection
     * @see  io.github.nichetoolkit.rest.RestException
     * @return  {@link java.io.File} <p>The write return object is <code>File</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     */
    public abstract File write(File shapeFile, Collection<T> shapefiles) throws RestException;

    /**
     * <code>write</code>
     * <p>The write method.</p>
     * @param shapeFile {@link java.io.File} <p>The shape file parameter is <code>File</code> type.</p>
     * @param geometries {@link org.geotools.geometry.jts.Geometries} <p>The geometries parameter is <code>Geometries</code> type.</p>
     * @param shapefiles {@link java.util.List} <p>The shapefiles parameter is <code>List</code> type.</p>
     * @see  java.io.File
     * @see  org.geotools.geometry.jts.Geometries
     * @see  java.util.List
     * @see  io.github.nichetoolkit.rest.RestException
     * @return  {@link java.io.File} <p>The write return object is <code>File</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     */
    public abstract File write(File shapeFile, Geometries geometries, List<T> shapefiles) throws RestException;

    /**
     * <code>write</code>
     * <p>The write method.</p>
     * @param shapeFile {@link java.io.File} <p>The shape file parameter is <code>File</code> type.</p>
     * @param attributeClassMap {@link java.util.Map} <p>The attribute class map parameter is <code>Map</code> type.</p>
     * @param shapefiles {@link java.util.Collection} <p>The shapefiles parameter is <code>Collection</code> type.</p>
     * @see  java.io.File
     * @see  java.util.Map
     * @see  java.util.Collection
     * @see  io.github.nichetoolkit.rest.RestException
     * @return  {@link java.io.File} <p>The write return object is <code>File</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     */
    public abstract File write(File shapeFile, Map<String, Class<?>> attributeClassMap, Collection<T> shapefiles) throws RestException;

    /**
     * <code>write</code>
     * <p>The write method.</p>
     * @param shapeFile {@link java.io.File} <p>The shape file parameter is <code>File</code> type.</p>
     * @param geometries {@link org.geotools.geometry.jts.Geometries} <p>The geometries parameter is <code>Geometries</code> type.</p>
     * @param attributeClassMap {@link java.util.Map} <p>The attribute class map parameter is <code>Map</code> type.</p>
     * @param shapefiles {@link java.util.Collection} <p>The shapefiles parameter is <code>Collection</code> type.</p>
     * @see  java.io.File
     * @see  org.geotools.geometry.jts.Geometries
     * @see  java.util.Map
     * @see  java.util.Collection
     * @see  io.github.nichetoolkit.rest.RestException
     * @return  {@link java.io.File} <p>The write return object is <code>File</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     */
    public abstract File write(File shapeFile, Geometries geometries, Map<String, Class<?>> attributeClassMap, Collection<T> shapefiles) throws RestException;

}
