package com.dipl.neuralphotos.service.transactional;

import com.dipl.neuralphotos.model.Picture;
import com.dipl.neuralphotos.model.User;
import com.dipl.neuralphotos.repository.PictureRepository;
import com.dipl.neuralphotos.service.PictureService;
import com.dipl.neuralphotos.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionalPictureService implements PictureService {

    private final PictureRepository pictureRepository;
    private final UserService userService;

    @Autowired
    public TransactionalPictureService(PictureRepository pictureRepository, UserService userService) {
        this.pictureRepository = pictureRepository;
        this.userService = userService;
    }

    @Override
    public List<Picture> findAll(){
        return pictureRepository.findAll();
    }

    @Override
    public Picture findPictureById(long pictureId){
        Optional<Picture> optionalUser = pictureRepository.findById(pictureId);
        if(optionalUser.isPresent()){
            return optionalUser.get();
        }else {
            throw new EntityNotFoundException("Picture with id "+pictureId+" not found");
        }
    }

    @Override
    public List<Picture> findPicturesOfUser(long userId){
        User user = userService.findUserById(userId);
        return pictureRepository.findAllByUser(user);
    }

    @Override
    public void likePicture(long pictureId){
        Picture picture = findPictureById(pictureId);
        picture.setLikes(picture.getLikes()+1);
        pictureRepository.save(picture);
    }

    @Override
    public Picture savePicture(Picture picture){
       return pictureRepository.save(picture);
    }
}
