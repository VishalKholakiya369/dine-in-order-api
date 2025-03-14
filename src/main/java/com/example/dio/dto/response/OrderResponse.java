package com.example.dio.dto.response;

import com.example.dio.enums.OrderStatus;
import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class OrderResponse {

    private OrderStatus orderStatus;

    private LocalDateTime orderAt;

    private List<CartItemResponse> cartItems;

    private double totalAmount;


}
