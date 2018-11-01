package com.dipl.neuralphotos.rest;

import com.dipl.neuralphotos.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Controller
@RequestMapping(value = "api/upload")
public class UploadResource {

    private final UploadService uploadService;

    @Autowired
    public UploadResource(UploadService uploadService) {
        this.uploadService = uploadService;
    }

    @RequestMapping(method=RequestMethod.GET)
    public @ResponseBody
    String provideUploadInfo() {
        return "Вы можете загружать файл с использованием того же URL.";
    }

    @RequestMapping(method=RequestMethod.POST)
    public @ResponseBody String handleFileUpload(@RequestParam("file") MultipartFile file) throws IOException {
        return uploadService.uploadFile(file);
    }

}
