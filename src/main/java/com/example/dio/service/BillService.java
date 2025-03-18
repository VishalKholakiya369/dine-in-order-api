package com.example.dio.service;

import com.example.dio.dto.response.BillResponse;

public interface BillService {
    BillResponse createBill(long tableId);

    BillResponse findById(long billId);

    byte[] pdfGeneration(long billId) throws Exception;
}
