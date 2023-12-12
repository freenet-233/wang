package com.wang.common.service.qrcode;

import lombok.extern.slf4j.Slf4j;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.QRCodeDetector;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;



//@Service
@Slf4j
public class OpenCVProcessor implements QRCodeProcessor {

    private static final String NATIVE_LIBRARY_NAME = "libopencv_java481";

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
        System.out.println(qrCode);
        return QRCodeResult.success(qrCodeContexts);
    }

    public static void main(String[] args) {
        loadLibraries();

        OpenCVProcessor processor = new OpenCVProcessor();
        processor.process("/home/wang/02media/4f4ecad39002d7af49bf35cb0053d77d.jpg");
    }

    private static void loadLibraries() {

        try {

            String osName = System.getProperty("os.name");
            String opencvpath = System.getProperty("user.dir") + "\\wang-common\\lib";
            if(osName.startsWith("Windows")) {
                int bitness = Integer.parseInt(System.getProperty("sun.arch.data.model"));
                if(bitness == 32) {
                    opencvpath=opencvpath+"\\windows\\x86_32\\";
                } else if (bitness == 64) {
                    opencvpath=opencvpath+"\\windows\\x64_64\\";
                } else {
                    opencvpath=opencvpath+"\\windows\\x86_64\\";
                }
                opencvpath = opencvpath + NATIVE_LIBRARY_NAME + ".dll";
            } else if(osName.equals("Mac OS X")){
                opencvpath = opencvpath+"\\osx\\x86_64\\" + NATIVE_LIBRARY_NAME + ".dylib";
            } else if(osName.startsWith("Linux")) {
                int bitness = Integer.parseInt(System.getProperty("sun.arch.data.model"));
                if(bitness == 32) {
                    opencvpath=opencvpath+"\\linux\\x86_32\\";
                } else if (bitness == 64) {
                    opencvpath=opencvpath+"\\linux\\x64_64\\";
                } else {
                    opencvpath=opencvpath+"\\linux\\x64_64\\";
                }
                opencvpath = opencvpath+ NATIVE_LIBRARY_NAME + ".so";
            }
            System.out.println(opencvpath);
            System.load(opencvpath);
        } catch (Exception e) {
            throw new RuntimeException("Failed to load opencv native library", e);
        }
    }
}
