package com.wang.common.service.qrcode;

/**
 *  二维码处理器
 *  解析二维码
 */
public interface QRCodeProcessor {

    int sort();

    QRCodeResult process(String fileName);
}
