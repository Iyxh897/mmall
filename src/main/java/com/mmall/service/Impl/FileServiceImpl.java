package com.mmall.service.Impl;

import com.google.common.collect.Lists;
import com.mmall.service.IFileService;
import com.mmall.util.FTPUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service("iFileService")
public class FileServiceImpl implements IFileService {

    private Logger logger = LoggerFactory.getLogger(FileServiceImpl.class);

    public String upload(MultipartFile file, String path) {
        String fileName = file.getOriginalFilename();
        //获取文件名的扩展名
        String fileExtensionName = fileName.substring(fileName.lastIndexOf(".") + 1);
        //为了避免因不同客户之间上传相同名字的文件，顾此为文件重组新的名字
        String uploadFileName = UUID.randomUUID().toString() + "." + fileExtensionName;
        logger.info("开始上传文件，上传文件的文件名:{},上传的路径:{]，新文件名:{}", fileName, path, uploadFileName);

        //创建目录
        File file1Dir = new File(path);
        if (!file1Dir.exists()) {
            //设置可写的权限
            file1Dir.setWritable(true);
            file1Dir.mkdirs();
        }
        File targetFile = new File(path, uploadFileName);

        try {
            file.transferTo(targetFile);
            //文件已经上传成功
            FTPUtil.uploadFile(Lists.newArrayList(targetFile));
            //已经上传到FTP服务器上
            targetFile.delete();
            //上传完之后，删除upload下面的文件
        } catch (IOException e) {
            logger.error("上传文件异常", e);
            return null;
        }
        return targetFile.getName();
    }

}
