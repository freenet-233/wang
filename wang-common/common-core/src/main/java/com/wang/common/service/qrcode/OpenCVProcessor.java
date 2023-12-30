package com.wang.common.service.qrcode;

import com.wang.common.service.QRCodeProcessor;
import lombok.extern.slf4j.Slf4j;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.QRCodeDetector;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
@Service
@Slf4j
public class OpenCVProcessor implements QRCodeProcessor {

    static {
        QRCodeTool.loadLibraries();
    }

    @Override
    public int sort() {
        return 0;
    }

    @Override
    public QRCodeResult process(String fileName) {

        List<String> qrCodeContexts = new ArrayList<>();
        Mat image = Imgcodecs.imread(fileName);
        // 将图像转换成灰度图像
        Mat gray = new Mat();
        Imgproc.cvtColor(image, gray, Imgproc.COLOR_BGR2GRAY);

        // 检测图像中的QR码
        QRCodeDetector detector = new QRCodeDetector();
        MatOfPoint points = new MatOfPoint();
        String qrCode = detector.detectAndDecode(gray, points);
        log.info("qrCode:{}", qrCode);

        return QRCodeResult.success(Collections.singletonList(qrCode));
    }

    public static void main(String[] args) {
//        QRCodeTool.loadLibraries();

        OpenCVProcessor processor = new OpenCVProcessor();
        processor.process("/home/wang/02media/4f4ecad39002d7af49bf35cb0053d77d.jpg");
    }


}
