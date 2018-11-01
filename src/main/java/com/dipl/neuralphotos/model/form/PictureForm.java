package com.dipl.neuralphotos.model.form;

import com.dipl.neuralphotos.model.Picture;
import org.springframework.web.multipart.MultipartFile;

public class PictureForm {
    private String name;
    private String description;
    private MultipartFile file;
    private String url;

    public PictureForm() {
    }

    public PictureForm(Picture picture){
        this.name = picture.getName();
        this.description = picture.getDescription();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
