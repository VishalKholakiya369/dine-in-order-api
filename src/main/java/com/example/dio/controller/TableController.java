package com.example.dio.controller;

import com.example.dio.dto.request.TableRequest;
import com.example.dio.dto.response.TableResponse;
import com.example.dio.service.TableService;
import com.example.dio.util.ResponseBuilder;
import com.example.dio.util.ResponseStructur;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("${app.base-url}")
public class TableController {

    private TableService tableService;

    @PostMapping("/table/{restaurantId}")
    public ResponseEntity<ResponseStructur<TableResponse>> createTable(@Valid @RequestBody TableRequest tableRequest, @PathVariable long restaurantId){
        TableResponse tableResponse = tableService.createTable(tableRequest,restaurantId);
        return ResponseBuilder.created("Table Created",tableResponse);
    }
}
