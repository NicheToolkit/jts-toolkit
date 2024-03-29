package io.github.nichetoolkit.jts.controller;

import io.github.nichetoolkit.jts.service.ShapeService;
import io.github.nichetoolkit.rest.RestException;
import io.github.nichetoolkit.rest.RestResult;
import io.github.nichetoolkit.rest.userlog.stereotype.RestNotelog;
import io.github.nichetoolkit.rest.util.GeneralUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    @Autowired
    private ShapeService shapeService;

    @GetMapping("/error")
    public ResponseEntity<RestResult> error() throws RestException {
        Object test = null;
        test.toString();
        return RestResult.ok();
    }

    @PostMapping("/upload")
    public ResponseEntity upload(@RequestPart(value = "file") MultipartFile file) throws Exception {
        shapeService.upload(file);
        return RestResult.ok("成功");
    }

    @GetMapping("/download/path")
    public ResponseEntity downloadPath(@RequestParam(value = "filename") String filename) throws Exception {
        String uuid = GeneralUtils.uuid();
        shapeService.download(uuid, filename);
        return RestResult.ok("成功", uuid);
    }

    @GetMapping("/download/load")
    public ResponseEntity downloadLoad(@RequestParam("uuid") String uuid) throws Exception {
        boolean isOver = shapeService.download(uuid);
        return RestResult.ok("成功", isOver);
    }

    @GetMapping("/download/file")
    public void downloadFile(@RequestParam("uuid") String uuid, HttpServletResponse response) throws Exception {
        shapeService.download(uuid, response);
    }

}
