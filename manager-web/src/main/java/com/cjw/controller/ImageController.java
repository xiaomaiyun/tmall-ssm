package com.cjw.controller;

import com.cjw.common.utils.PicUpload;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author codeAC
 * @Date: 2018/7/8
 * @Time: 11:43
 */
@RestController
public class ImageController {
    @Value("${server_Url}")
    private String server_Url;
    @RequestMapping(value="/pic/upload", produces=MediaType.TEXT_PLAIN_VALUE+";charset=utf-8")
    public String uploadFile(MultipartFile uploadFile) {
        try {
            byte[] bytes = uploadFile.getBytes();
            String url = PicUpload.upLoadImage(bytes);
            Map result_ok = new HashMap();
            result_ok.put("error",0);
            result_ok.put("url", url);
            Gson gson = new Gson();
            return gson.toJson(result_ok);
        } catch (IOException e) {
            e.printStackTrace();
            Map result = new HashMap();
            result.put("error", 1);
            result.put("message", "图片上传失败");
            Gson gson = new Gson();
            return gson.toJson(result);
        }
    }
}
