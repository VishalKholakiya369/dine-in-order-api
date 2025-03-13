package com.example.dio.service;

import com.example.dio.dto.response.CartItemResponse;

public interface CartItemService {
  public  CartItemResponse createCartItem(long tableId, long itemId, int quantity);

  // public Object updateQuantity(long cartId, int quantity);
}
