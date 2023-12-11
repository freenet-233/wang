package com.wang.common.service.qrcode;

import lombok.extern.slf4j.Slf4j;
import org.opencv.core.Mat;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class OpenCVProcessor implements QRCodeProcessor {
    @Override
    public int sort() {
        return 0;
    }

    @Override
    public QRCodeResult process(String fileName) {


        List<String> qrCodeContexts = new ArrayList<>();

        return QRCodeResult.success(qrCodeContexts);
    }
}
