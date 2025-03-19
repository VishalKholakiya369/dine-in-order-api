package com.example.dio.controller;

import com.example.dio.dto.response.BillResponse;
import com.example.dio.service.BillService;
import com.example.dio.util.ResponseBuilder;
import com.example.dio.util.ResponseStructur;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${app.base-url}")
@AllArgsConstructor
public class BillController {

    private final BillService billService;

    @PostMapping("/bills/tables/{tableId}")
    public ResponseEntity<ResponseStructur<BillResponse>> createBill(@PathVariable long tableId){
        BillResponse billResponse = billService.createBill(tableId);
        return ResponseBuilder.created("Bill Generated !!",billResponse);
    }

    @GetMapping("/bills/{billId}")
    public ResponseEntity<ResponseStructur<BillResponse>> findByBillId(@PathVariable long billId){
        BillResponse billResponse = billService.findById(billId);
        return ResponseBuilder.ok("Bill Found !!",billResponse);
    }

    @GetMapping("/bills/pdf/{billId}")
    public ResponseEntity<byte[]> findBillById(@PathVariable long billId) throws  Exception{

       byte[] billPDF = billService.pdfGeneration(billId);
        return  ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .header(HttpHeaders.CONTENT_DISPOSITION,"attachment;filename=Dine-in-bill.pdf")
                .body(billPDF);
    }

}
