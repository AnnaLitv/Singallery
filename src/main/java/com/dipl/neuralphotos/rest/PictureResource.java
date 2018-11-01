package com.dipl.neuralphotos.rest;

import com.dipl.neuralphotos.model.Picture;
import com.dipl.neuralphotos.model.form.PictureForm;
import com.dipl.neuralphotos.service.PictureService;
import com.dipl.neuralphotos.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(value = "/api/picture")
public class PictureResource {
    private final PictureService pictureService;
    private final UploadService uploadService;

    @Autowired
    public PictureResource(PictureService pictureService, UploadService uploadService) {
        this.pictureService = pictureService;
        this.uploadService = uploadService;
    }

    @RequestMapping(method = RequestMethod.POST)
    public String savePicture(Model model, @ModelAttribute("picture") @Validated PictureForm picture,
                              BindingResult result, RedirectAttributes redirectAttributes) {
        String filepath = "";
        if(result.hasErrors()){
            return "picture";
        }
        try {
            filepath = uploadService.uploadFile(picture.getFile());
        }catch (Exception e){
            model.addAttribute("errorMessage", e.getMessage());
            model.addAttribute("pictureForm",picture);
            return "picture";
        }
        pictureService.savePicture(picture,filepath);
        model.addAttribute("errorMessage", "successfully uploaded!");
        model.addAttribute("pictureForm",picture);
        return "picture";
    }

    @RequestMapping(method = RequestMethod.GET)
    public String showPicture(Model model){
        PictureForm pictureForm = new PictureForm();
        model.addAttribute("pictureForm",pictureForm);
        return "picture";
    }
}
