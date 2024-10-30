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
 * <p>ShapeController</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
@Slf4j
@RestNotelog
@CrossOrigin
@RestController
@RequestMapping("/shape")
public class ShapeController {

    private final ShapeService shapeService;

    public ShapeController(ShapeService shapeService) {
        this.shapeService = shapeService;
    }


    @PostMapping("/upload")
    public RestResult<?> upload(@RequestPart(value = "file") MultipartFile file) throws Exception {
        shapeService.upload(file);
        return RestResult.success();
    }

    @GetMapping("/download/path")
    public RestResult<String> downloadPath(@RequestParam(value = "filename") String filename) throws Exception {
        String uuid = GeneralUtils.uuid();
        shapeService.download(uuid, filename);
        return RestResult.success( "success",uuid);
    }

    @GetMapping("/download/load")
    public RestResult<?> downloadLoad(@RequestParam("uuid") String uuid) throws Exception {
        boolean isOver = shapeService.download(uuid);
        return RestResult.success("success", isOver);
    }

    @GetMapping("/download/file")
    public void downloadFile(@RequestParam("uuid") String uuid, HttpServletResponse response) throws Exception {
        shapeService.download(uuid, response);
    }

}
