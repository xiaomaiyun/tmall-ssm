package com.cjw.common.utils;

import com.google.gson.Gson;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;

import java.io.Serializable;

/**
 * 接收前台传过来的json数据，其中包含一个status状态码属性
 * @author codeAC
 * @Date: 2018/7/8
 * @Time: 10:04
 */
public class PicUpload implements Serializable {
    //根据七牛云的个人中心来填写
    private static final String accessKey = "QnWYTBujIJ4Gvm8mJm3DmGYCLFcRrZb0xeXn-V0a";
    private static final String secretKey = "uu2jqhC4FKWfyIY3WiGHsZtiU9oxFWwYNtB7Qn3T";
    private static final String bucket = "images";


    /**
     * @param  data
     * @return  外键地址
     */
    public static String upLoadImage(byte[] data){
        //图片的外链地址
        StringBuffer imgUrl  = new StringBuffer("http://pbiznvrns.bkt.clouddn.com/");

        //构造一个带指定Zone对象的配置类
        Configuration cfg = new Configuration(Zone.zone1());

        UploadManager uploadManager = new UploadManager(cfg);
        //...生成上传凭证，然后准备上传

        //默认不指定key的情况下，以文件内容的hash值作为文件名
        String key = null;

        Auth auth = Auth.create(accessKey, secretKey);
        String upToken = auth.uploadToken(bucket);

        try {
            //上传文件
            Response response = uploadManager.put(data,key,upToken);

            //解析上传成功的结果
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            imgUrl.append(putRet.key);
        } catch (Exception ex) {
           ex.printStackTrace();
        }
        return imgUrl.toString();
    }
}
