package com.dipl.neuralphotos.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;
import java.io.IOException;

@Service
public interface UploadService {
    String uploadFile(MultipartFile file) throws IOException;
}
