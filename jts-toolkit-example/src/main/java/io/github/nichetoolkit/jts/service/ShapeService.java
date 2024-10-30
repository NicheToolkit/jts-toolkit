package io.github.nichetoolkit.jts.service;

import io.github.nichetoolkit.rest.RestException;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

/**
 * <code>ShapeService</code>
 * <p>The shape service interface.</p>
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
public interface ShapeService {

    /**
     * <code>upload</code>
     * <p>The upload method.</p>
     * @param file {@link org.springframework.web.multipart.MultipartFile} <p>The file parameter is <code>MultipartFile</code> type.</p>
     * @see  org.springframework.web.multipart.MultipartFile
     * @see  io.github.nichetoolkit.rest.RestException
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     */
    void upload(MultipartFile file) throws RestException;

    /**
     * <code>download</code>
     * <p>The download method.</p>
     * @param uuid {@link java.lang.String} <p>The uuid parameter is <code>String</code> type.</p>
     * @param filename {@link java.lang.String} <p>The filename parameter is <code>String</code> type.</p>
     * @see  java.lang.String
     * @see  io.github.nichetoolkit.rest.RestException
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     */
    void download(String uuid, String filename) throws RestException;

    /**
     * <code>download</code>
     * <p>The download method.</p>
     * @param uuid {@link java.lang.String} <p>The uuid parameter is <code>String</code> type.</p>
     * @see  java.lang.String
     * @see  io.github.nichetoolkit.rest.RestException
     * @return boolean <p>The download return object is <code>boolean</code> type.</p>
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     */
    boolean download(String uuid) throws RestException;

    /**
     * <code>download</code>
     * <p>The download method.</p>
     * @param uuid {@link java.lang.String} <p>The uuid parameter is <code>String</code> type.</p>
     * @param response {@link javax.servlet.http.HttpServletResponse} <p>The response parameter is <code>HttpServletResponse</code> type.</p>
     * @see  java.lang.String
     * @see  javax.servlet.http.HttpServletResponse
     * @see  io.github.nichetoolkit.rest.RestException
     * @throws RestException {@link io.github.nichetoolkit.rest.RestException} <p>The rest exception is <code>RestException</code> type.</p>
     */
    void download(String uuid, HttpServletResponse response) throws RestException;
}
