package com.wang.service;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.QRCodeReader;
import com.wang.util.ImageLuminanceSource;
import com.wang.util.ORCodeImageUtil;
import org.bytedeco.opencv.opencv_core.Mat;
import org.bytedeco.opencv.opencv_core.StringVector;
import org.bytedeco.opencv.opencv_wechat_qrcode.WeChatQRCode;
import org.opencv.core.Core;
import org.opencv.imgcodecs.Imgcodecs;

import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.nio.charset.StandardCharsets;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.*;

import static org.bytedeco.opencv.global.opencv_imgcodecs.imread;

public class QrCodeService {
    public static void main(String[] args) throws Exception {
        // 4f4ecad39002d7af49bf35cb0053d77d.jpg
        // 23df497354e1b41dc68549f8d7af1046.jpeg
        // 977f24283768d56c59fd6b3fe5713f9a.jpeg
        // da9b44c86a3d3c95cde3107c08e16b3f.jpeg
        // Screenshot_20231128_212017.png
        // IMG_0473.PNG
        String filePath = "/home/wang/04data/test/IMG_0473.PNG";
//        System.out.println(readQrCodeImage(filePath));
        System.out.println(readQrCodeImage2(filePath));
//        QRReader(filePath, "jpg");
//        openPic(filePath);
    }

    private static String openPic(String path) {

        Mat img = imread(path);
        return deCode(img);
    }
    public static String deCode(Mat img) {
        //微信二维码对象，要返回二维码坐标前2个参数必传；后2个在二维码小或不清晰时必传。
        WeChatQRCode we = new WeChatQRCode();
        List<Mat> points = new ArrayList<Mat>();
        //微信二维码引擎解码，返回的valList中存放的是解码后的数据，points中Mat存放的是二维码4个角的坐标
        StringVector stringVector = we.detectAndDecode(img);

        System.out.println(stringVector.get(0).getString(StandardCharsets.UTF_8));
        return stringVector.get(0).getString(StandardCharsets.UTF_8);
    }

//    public static String OpenCVExampl(File file) throws Exception {
//        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
//
//        String imagePath = "path/to/image.jpg"; // 替换为实际的图像路径
//
//        Mat image = Imgcodecs.imread(imagePath);
//        if (image.empty()) {
//            System.out.println("Failed to load image");
//            return;
//        }
//        // 在这里进行图像处理操作，例如边缘检测、图像增强等
//        // 保存处理后的图像
//        String outputPath = "path/to/output.jpg"; // 替换为实际的输出路径
//        Imgcodecs.imwrite(outputPath, image);
//        System.out.println("Image processing completed");
//    }

    public static String readQrCodeImage(String filePath) throws Exception {
        BufferedImage readImage = ImageIO.read(new File(filePath));
        LuminanceSource source = new BufferedImageLuminanceSource(readImage);
        BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
        Reader qrCodeReader = new MultiFormatReader();
        Result decode = qrCodeReader.decode(bitmap);
        return decode.getText();
    }

    public static String readQrCodeImage2(String filePath) throws Exception {
        BufferedImage readImage = ImageIO.read(new File(filePath));
        int width = readImage.getWidth();
        int height = readImage.getHeight();
        int cropWidth = width / 2;
        int cropHeight = height / 2;
        int cropX = width - cropWidth;
        int cropY = 0;
        //截取图片右上1/4进行识别
        readImage = readImage.getSubimage(cropX, cropY, cropWidth, cropHeight);
//        readImage = getScale(readImage, 1.5);
        LuminanceSource source = new BufferedImageLuminanceSource(readImage);
        BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
        EnumMap<DecodeHintType,Object> hints = new EnumMap<>(DecodeHintType.class);
        hints.put(DecodeHintType.CHARACTER_SET, "UTF-8");
//        hints.put(DecodeHintType.PURE_BARCODE, Boolean.TRUE);
        hints.put(DecodeHintType.TRY_HARDER, Boolean.TRUE);
//        hints.put(DecodeHintType.POSSIBLE_FORMATS, Boolean.TRUE);
//        Reader qrCodeReader = new MultiFormatReader();
        Result decode = new QRCodeReader().decode(bitmap, hints);
        return decode.getText();
    }
    public static BufferedImage getScale(BufferedImage image,double scale) {
        AffineTransformOp transformOp = new AffineTransformOp(AffineTransform.getScaleInstance(scale, scale), null);
        image = transformOp.filter(image, null);
        return image;
    }

    public static void QRReader(String pathName, String fileType) {
        try {
            File file = new File(pathName);
            BufferedImage image = ImageIO.read(file);
            // image =getSubImage(image);
            image= ORCodeImageUtil.blackAndWhite(image);
            image= ORCodeImageUtil.opening(image);


            ImageLuminanceSource source=new ImageLuminanceSource(image,image.getWidth(),image.getHeight());
            Binarizer binarizer = new HybridBinarizer(source);
            BinaryBitmap binaryBitmap = new BinaryBitmap(binarizer);
            QRCodeReader reader=new QRCodeReader();

            Map<DecodeHintType,Object> hints = new LinkedHashMap<DecodeHintType,Object>();
            // 解码设置编码方式为：utf-8，
            hints.put(DecodeHintType.CHARACTER_SET, "UTF-8");
            //优化精度
            hints.put(DecodeHintType.TRY_HARDER, Boolean.TRUE);
            //复杂模式，开启PURE_BARCODE模式
            //hints.put(DecodeHintType.PURE_BARCODE, Boolean.TRUE);

            Result result = reader.decode(binaryBitmap, null);
            String text=result.getText();
            System.out.println("解析的结果: " + result.getText());
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }catch(NotFoundException e) {
            System.out.println("二维码没找到");
        }catch (FormatException  e)
        { System.out.println("二维码无法解析");

        }catch(ChecksumException e) {
            System.out.println("二维码纠错失败");
            System.out.println(e.toString());
        }

    }

    public static void QRReaderWithResize(String pathName, String fileType) {
        try {
            File file = new File(pathName);
            BufferedImage image = ImageIO.read(file);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            // Write the image to the byte array output stream
            ImageIO.write(image, fileType, baos);
            baos.flush();
            byte[] imageBytes = baos.toByteArray();
            baos.close();

            // Encode the image bytes as a string using Base64
            String str= Base64.getEncoder().encodeToString(imageBytes);
//            String str= QRCodeUtil.readToString(image);
            System.out.println("输出"+str);

        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
    }

    public static BufferedImage getSubImage(BufferedImage image) {
        int x=0;
        int y=220;

        int w=400;
        int h=500;
        if(x>image.getWidth()) {
            x=image.getWidth();
        }
        if(y>image.getHeight()) {
            y=image.getHeight();
        }

        if(w>image.getWidth()-x) {
            w=image.getWidth()-x;
        }
        if(h>image.getHeight()-y) {
            h=image.getHeight()-y;
        }

        return image.getSubimage(x,y,w,h);
    }

}
