package com.example.cah.s3;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/s3")
@RequiredArgsConstructor
public class S3Controller {

    @Autowired
    private S3Uploader s3Uploader;

    @Autowired
    private S3UploadService s3UploadService;

    @ResponseBody
    @PostMapping("/find")
    public List<String> findImg(@RequestParam("imgCode") String imgCode) {

        ArrayList<String>arr = new ArrayList<>();
        String imgPath1 = s3UploadService.getThumbnailPath(imgCode+"/"+imgCode+"_1.PNG");
        String imgPath2 = s3UploadService.getThumbnailPath(imgCode+"/"+imgCode+"_2.PNG");
        arr.add(imgPath1);
        arr.add(imgPath2);
        return arr;
        //log.info(imgPath);
        //Assertions.assertThat(imgPath).isNotNull();
    }
}



