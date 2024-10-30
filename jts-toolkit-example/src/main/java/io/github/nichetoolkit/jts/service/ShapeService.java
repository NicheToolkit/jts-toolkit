package io.github.nichetoolkit.jts.service;

import io.github.nichetoolkit.rest.RestException;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

/**
 * <p>ShapeService</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
public interface ShapeService {

    void upload(MultipartFile file) throws RestException;

    void download(String uuid, String filename) throws RestException;

    boolean download(String uuid) throws RestException;

    void download(String uuid, HttpServletResponse response) throws RestException;
}
