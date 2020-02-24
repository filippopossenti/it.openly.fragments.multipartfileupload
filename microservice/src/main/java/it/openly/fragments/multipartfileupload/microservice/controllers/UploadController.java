package it.openly.fragments.multipartfileupload.microservice.controllers;

import org.apache.commons.io.IOUtils;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
public class UploadController {

    @PostMapping(path = "{path}/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> upload(@RequestParam("file") MultipartFile file, @PathVariable("path") String path, @RequestParam("tag") String tag) throws IOException {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        IOUtils.copy(file.getInputStream(), bos);

        System.out.println("Uploading a file");
        System.out.println(String.format("path=%s, tag=%s, originalFileName=%s, name=%s, size=%d", path, tag, file.getOriginalFilename(), file.getName(), bos.size()));
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("original-file-name", file.getOriginalFilename());
        result.put("name", file.getName());
        result.put("size", bos.size());
        result.put("path", path);
        result.put("tag", tag);
        return result;
    }

}
