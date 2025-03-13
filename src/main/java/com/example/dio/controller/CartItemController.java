package com.example.dio.controller;

import com.example.dio.dto.response.CartItemResponse;
import com.example.dio.service.CartItemService;
import com.example.dio.util.ResponseBuilder;
import com.example.dio.util.ResponseStructur;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("${app.base-url}")

public class CartItemController {

    private final CartItemService cartItemService;

    @PostMapping("/table/{tableId}/cart-items/food-items/{itemId}")
    public ResponseEntity<ResponseStructur<CartItemResponse>> createCart(@PathVariable long tableId,
                                                                         @PathVariable long itemId,@RequestParam int quantity){

        return ResponseBuilder.created("Cart created",cartItemService.createCartItem(tableId,itemId,quantity));
    }

//    @PatchMapping("/cart-items/{cartId}")
//    public ResponseEntity<ResponseStructur<CartItemResponse>> updateQuantity(@PathVariable long cartId,@RequestParam int quantity){
//        return ResponseBuilder.ok("cart quantity updated",cartItemService.updateQuantity(cartId,quantity));
//    }
}
