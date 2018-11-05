package com.dipl.neuralphotos.rest;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

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
            picture.setUrl(filepath);
            model.addAttribute("pictureForm",picture);
            return "picture";
        }
        pictureService.savePicture(picture,filepath);
        model.addAttribute("errorMessage", "successfully uploaded!");
        picture.setUrl(filepath);
        model.addAttribute("pictureForm",picture);
        return "picture";
    }

    @RequestMapping(method = RequestMethod.GET)
    public String showPicture(Model model){
        PictureForm pictureForm = new PictureForm();
        model.addAttribute("pictureForm",pictureForm);
        return "picture";
    }

    @RequestMapping(value = { "/image" }, method = RequestMethod.GET)
    public void productImage(HttpServletRequest request, HttpServletResponse response, Model model,
                             @RequestParam("url") String url) throws IOException {
        if (url != null) {
            File file = new File(url);
            if (file.exists()) {
                Path path = file.toPath();
                byte[] image = Files.readAllBytes(path);
                response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
                response.getOutputStream().write(image);
            }
        }
        response.getOutputStream().close();
    }
}
