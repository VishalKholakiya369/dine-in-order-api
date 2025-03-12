package com.example.dio.service.impl;

import com.example.dio.service.QRCodeServices;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Service
public class QRCodeGeneratorServiceImpl implements QRCodeServices {

    @Override
    public  byte[] generateQRCodeImage(String url) throws IOException, WriterException {
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        int width = 250;
        int height = 250;
        BitMatrix bitMatrix = qrCodeWriter.encode(url, BarcodeFormat.QR_CODE,width,height);

        try (ByteArrayOutputStream pngOutputStream = new ByteArrayOutputStream()) {
            MatrixToImageWriter.writeToStream(bitMatrix, "PNG", pngOutputStream);
            return pngOutputStream.toByteArray();
        }
    }
}
