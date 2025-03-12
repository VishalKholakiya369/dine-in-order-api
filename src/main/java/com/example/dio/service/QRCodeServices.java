package com.example.dio.service;

import com.google.zxing.WriterException;

import java.io.IOException;

public interface QRCodeServices {

    public byte[] generateQRCodeImage (String url) throws IOException, WriterException;
}
