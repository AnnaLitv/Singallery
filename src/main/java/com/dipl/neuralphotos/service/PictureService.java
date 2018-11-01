package com.dipl.neuralphotos.service;

import com.dipl.neuralphotos.model.Picture;
import com.dipl.neuralphotos.model.form.PictureForm;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PictureService {
    List<Picture> findAll();

    Picture findPictureById(long pictureId);

    List<Picture> findPicturesOfUser(long userId);

    void likePicture(long pictureId);

    Picture savePicture(Picture picture);

    Picture savePicture(PictureForm pictureForm, String filepath);
}
