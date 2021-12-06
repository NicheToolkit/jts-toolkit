package io.github.nichetoolkit.jts.controller;

import io.github.nichetoolkit.jts.service.ShapeService;
import io.github.nichetoolkit.rest.RestNote;
import io.github.nichetoolkit.rest.RestResult;
import io.github.nichetoolkit.rest.identity.IdentityUtils;
import io.github.nichetoolkit.rest.identity.worker.IdentityWorker;
import io.github.nichetoolkit.rest.util.bean.ContextUtils;
import io.github.nichetoolkit.rest.util.common.JsonUtils;
import io.github.nichetoolkit.rest.util.often.RandomUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

/**
 * <p>IdentityController</p>
 * @author Cyan (snow22314@outlook.com)
 * @version v1.0.0
 */
@Slf4j
@RestNote
@CrossOrigin
@RestController
@RequestMapping("/shape")
public class ShapeController {

    @Autowired
    private ShapeService shapeService;

    @GetMapping("/download/path")
    public ResponseEntity downloadGetShape(@RequestParam(value = "filename") String filename) throws Exception {
        String uuid = RandomUtils.uuid();
        shapeService.download(uuid,filename);
        return RestResult.ok("成功",uuid);
    }

    @GetMapping("/download/load")
    public ResponseEntity loadShape(@RequestParam("uuid") String uuid) throws Exception {
        boolean isOver = shapeService.download(uuid);
        return RestResult.ok("成功",isOver);
    }

    @GetMapping("/download/file")
    public void downloadShape(@RequestParam("uuid") String uuid, HttpServletResponse response) throws Exception {
        shapeService.download(uuid, response);
    }

}
