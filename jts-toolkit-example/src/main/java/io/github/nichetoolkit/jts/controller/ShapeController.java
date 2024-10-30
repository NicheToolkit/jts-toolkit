package io.github.nichetoolkit.jts.controller;

import io.github.nichetoolkit.jts.service.ShapeService;
import io.github.nichetoolkit.rest.RestResult;
import io.github.nichetoolkit.rest.userlog.stereotype.RestNotelog;
import io.github.nichetoolkit.rest.util.GeneralUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

/**
 * <code>ShapeController</code>
 * <p>The shape controller class.</p>
 * @see  lombok.extern.slf4j.Slf4j
 * @see  io.github.nichetoolkit.rest.userlog.stereotype.RestNotelog
 * @see  org.springframework.web.bind.annotation.CrossOrigin
 * @see  org.springframework.web.bind.annotation.RestController
 * @see  org.springframework.web.bind.annotation.RequestMapping
 * @author Cyan (snow22314@outlook.com)
 * @since Jdk1.8
 */
@Slf4j
@RestNotelog
@CrossOrigin
@RestController
@RequestMapping("/shape")
public class ShapeController {

    /**
     * <code>shapeService</code>
     * {@link io.github.nichetoolkit.jts.service.ShapeService} <p>The <code>shapeService</code> field.</p>
     * @see  io.github.nichetoolkit.jts.service.ShapeService
     */
    private final ShapeService shapeService;

    /**
     * <code>ShapeController</code>
     * <p>Instantiates a new shape controller.</p>
     * @param shapeService {@link io.github.nichetoolkit.jts.service.ShapeService} <p>The shape service parameter is <code>ShapeService</code> type.</p>
     * @see  io.github.nichetoolkit.jts.service.ShapeService
     */
    public ShapeController(ShapeService shapeService) {
        this.shapeService = shapeService;
    }


    /**
     * <code>upload</code>
     * <p>The upload method.</p>
     * @param file {@link org.springframework.web.multipart.MultipartFile} <p>The file parameter is <code>MultipartFile</code> type.</p>
     * @see  org.springframework.web.multipart.MultipartFile
     * @see  org.springframework.web.bind.annotation.RequestPart
     * @see  io.github.nichetoolkit.rest.RestResult
     * @see  org.springframework.web.bind.annotation.PostMapping
     * @see  java.lang.Exception
     * @return  {@link io.github.nichetoolkit.rest.RestResult} <p>The upload return object is <code>RestResult</code> type.</p>
     * @throws Exception {@link java.lang.Exception} <p>The exception is <code>Exception</code> type.</p>
     */
    @PostMapping("/upload")
    public RestResult<?> upload(@RequestPart(value = "file") MultipartFile file) throws Exception {
        shapeService.upload(file);
        return RestResult.success();
    }

    /**
     * <code>downloadPath</code>
     * <p>The download path method.</p>
     * @param filename {@link java.lang.String} <p>The filename parameter is <code>String</code> type.</p>
     * @see  java.lang.String
     * @see  org.springframework.web.bind.annotation.RequestParam
     * @see  io.github.nichetoolkit.rest.RestResult
     * @see  org.springframework.web.bind.annotation.GetMapping
     * @see  java.lang.Exception
     * @return  {@link io.github.nichetoolkit.rest.RestResult} <p>The download path return object is <code>RestResult</code> type.</p>
     * @throws Exception {@link java.lang.Exception} <p>The exception is <code>Exception</code> type.</p>
     */
    @GetMapping("/download/path")
    public RestResult<String> downloadPath(@RequestParam(value = "filename") String filename) throws Exception {
        String uuid = GeneralUtils.uuid();
        shapeService.download(uuid, filename);
        return RestResult.success( "success",uuid);
    }

    /**
     * <code>downloadLoad</code>
     * <p>The download load method.</p>
     * @param uuid {@link java.lang.String} <p>The uuid parameter is <code>String</code> type.</p>
     * @see  java.lang.String
     * @see  org.springframework.web.bind.annotation.RequestParam
     * @see  io.github.nichetoolkit.rest.RestResult
     * @see  org.springframework.web.bind.annotation.GetMapping
     * @see  java.lang.Exception
     * @return  {@link io.github.nichetoolkit.rest.RestResult} <p>The download load return object is <code>RestResult</code> type.</p>
     * @throws Exception {@link java.lang.Exception} <p>The exception is <code>Exception</code> type.</p>
     */
    @GetMapping("/download/load")
    public RestResult<?> downloadLoad(@RequestParam("uuid") String uuid) throws Exception {
        boolean isOver = shapeService.download(uuid);
        return RestResult.success("success", isOver);
    }

    /**
     * <code>downloadFile</code>
     * <p>The download file method.</p>
     * @param uuid {@link java.lang.String} <p>The uuid parameter is <code>String</code> type.</p>
     * @param response {@link javax.servlet.http.HttpServletResponse} <p>The response parameter is <code>HttpServletResponse</code> type.</p>
     * @see  java.lang.String
     * @see  org.springframework.web.bind.annotation.RequestParam
     * @see  javax.servlet.http.HttpServletResponse
     * @see  org.springframework.web.bind.annotation.GetMapping
     * @see  java.lang.Exception
     * @throws Exception {@link java.lang.Exception} <p>The exception is <code>Exception</code> type.</p>
     */
    @GetMapping("/download/file")
    public void downloadFile(@RequestParam("uuid") String uuid, HttpServletResponse response) throws Exception {
        shapeService.download(uuid, response);
    }

}
