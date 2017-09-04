package top.catalinali.service;

import org.springframework.web.multipart.MultipartFile;
import top.catalinali.common.pojo.PictureResult;

/**
 * <pre>
 * Description:
 * Copyright:	Copyright (c)2017
 * Author:		lllx
 * Version:		1.0
 * Created at:	2017/8/23
 * </pre>
 */
public interface PictureService {
    PictureResult uploadPicture(MultipartFile uploadFile);
}
