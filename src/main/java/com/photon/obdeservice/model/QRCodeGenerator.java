package com.photon.obdeservice.model;

import java.io.ByteArrayOutputStream;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

public class QRCodeGenerator {

    // public static byte[] generateQRCodeImage(String text, int width, int height) throws Exception {
    //     QRCodeWriter qrCodeWriter = new QRCodeWriter();
    //     BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height);

    //     ByteArrayOutputStream pngOutputStream = new ByteArrayOutputStream();
    //     MatrixToImageWriter.writeToStream(bitMatrix, "PNG", pngOutputStream);
    //     return pngOutputStream.toByteArray();
    // }

    public static byte[] generateQRCodeImage(String text, int width, int height) throws Exception {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height);

        try (ByteArrayOutputStream pngOutputStream = new ByteArrayOutputStream()) {
            MatrixToImageWriter.writeToStream(bitMatrix, "PNG", pngOutputStream);
            return pngOutputStream.toByteArray(); 
        }
    }
}