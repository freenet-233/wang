package com.wang.common.service;

import com.wang.common.service.qrcode.QRCodeResult;

/**
 *  二维码处理器
 *  解析二维码
 */
public interface QRCodeProcessor {

    int sort();

    QRCodeResult process(String fileName);
}
