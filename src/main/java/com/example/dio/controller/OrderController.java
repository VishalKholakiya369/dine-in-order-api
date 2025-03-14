package com.example.dio.controller;

import com.example.dio.dto.response.OrderResponse;
import com.example.dio.service.OrderService;
import com.example.dio.util.ResponseBuilder;
import com.example.dio.util.ResponseStructur;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("${app.base-url}")

public class OrderController {

    private OrderService orderService;

    @PostMapping("/orders/tables/{restaurantTableId}")
    public ResponseEntity<ResponseStructur<OrderResponse>> createOrder(@PathVariable long restaurantTableId){
        return ResponseBuilder.created("order crated",orderService.createOrder(restaurantTableId));

    }
}
