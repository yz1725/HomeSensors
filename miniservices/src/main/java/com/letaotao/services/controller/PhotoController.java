package com.letaotao.services.controller;

import com.letaotao.common.utils.LogUtil;
import com.letaotao.services.handler.PhotoHandler;
import com.letaotao.services.request.PhotoUploadRequest;
import com.letaotao.services.request.ReveiewCreateRequest;
import com.letaotao.services.response.HttpBaseResponse;
import com.letaotao.services.response.PhotoUploadResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Slf4j
@RestController
public class PhotoController {

    @Autowired
    PhotoHandler photoHandler;

    private static String UPLOADED_FOLDER = "/Users/hongmeiyuan/workspace/mini/tmp/";

    @RequestMapping(method = RequestMethod.POST, value = "/photo/upload")
    public ResponseEntity<HttpBaseResponse> uploadPhoto(@RequestParam("file") MultipartFile file,
                                                        RedirectAttributes redirectAttributes) {
        PhotoUploadResponse photoUploadResponse = new PhotoUploadResponse();
        photoUploadResponse.setMsg("success");

        try {

            // Get the file and save it somewhere
            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOADED_FOLDER + file.getOriginalFilename());
            Files.write(path, bytes);

            redirectAttributes.addFlashAttribute("message",
                    "You successfully uploaded '" + file.getOriginalFilename() + "'");
            photoUploadResponse.setResult("1");
        } catch (IOException e) {
            e.printStackTrace();
            photoUploadResponse.setResult("0");
        }

        return new ResponseEntity(photoUploadResponse, HttpStatus.OK);

    }
}
