package com.example.dio.service.impl;

import com.example.dio.dto.response.OrderResponse;
import com.example.dio.enums.IsOrdered;
import com.example.dio.enums.OrderStatus;
import com.example.dio.enums.TableStatus;
import com.example.dio.exception.UserNotFoundByIdException;
import com.example.dio.mapper.OrderMapper;
import com.example.dio.model.CartItem;
import com.example.dio.model.Order;
import com.example.dio.model.RestaurantTable;
import com.example.dio.repository.CartItemRepository;
import com.example.dio.repository.OrderRepository;
import com.example.dio.repository.TableRepository;
import com.example.dio.service.OrderService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final CartItemRepository  cartItemRepository;
    private final OrderMapper orderMapper;
    private final TableRepository tableRepository;

    @Override
    @Transactional
    public OrderResponse createOrder(long restaurantTableId) {
        RestaurantTable restaurantTable = tableRepository.findById(restaurantTableId)
                .orElseThrow(() -> new NoSuchElementException("Table not found !!"));

        List<CartItem> cartItemList = cartItemRepository.findByRestaurantTable(restaurantTable);
        System.out.println(cartItemList);
        Order order = null ;

        if(!cartItemList.isEmpty()){
            order = new Order();
            order.setOrderStatus(OrderStatus.Build);
            order.setCartItems(cartItemList);
            order.setRestaurantTable(restaurantTable);
            order.setTotalAmount(cartItemList.stream()
                    .mapToDouble(CartItem::getTotalPrice)
                    .sum());
            orderRepository.save(order);
        }
        else{
            throw new NoSuchElementException(" No CartItem Selected !! ");
        }

        restaurantTable.setTableStatus(TableStatus.OCCUPIED);
        tableRepository.save(restaurantTable);

        cartItemList.forEach(item -> item.setOrdered(true));
        cartItemRepository.saveAll(cartItemList);

        return orderMapper.mapToOrderResponse(order);
    }
}
