package com.example.dio.service.impl;

import com.example.dio.dto.response.BillResponse;
import com.example.dio.enums.OrderStatus;
import com.example.dio.enums.TableStatus;
import com.example.dio.exception.UserNotFoundByIdException;
import com.example.dio.mapper.BillMapper;
import com.example.dio.model.Bill;
import com.example.dio.model.Order;
import com.example.dio.model.Restaurant;
import com.example.dio.model.RestaurantTable;
import com.example.dio.repository.BillRepository;
import com.example.dio.repository.OrderRepository;
import com.example.dio.repository.RestaurantRepository;
import com.example.dio.repository.TableRepository;
import com.example.dio.service.BillService;
import com.example.dio.util.BillGenerator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@Service
@AllArgsConstructor
public class BillServiceImpl implements BillService {
    private final BillRepository billRepository;
    private final TableRepository tableRepository;
    private final OrderRepository orderRepository;
    private final BillMapper billMapper;
    private final RestaurantRepository restaurantRepository;
    private final BillGenerator billGenerator;

    @Override
    public BillResponse createBill(long tableId) {
        RestaurantTable restaurantTable = tableRepository.findById(tableId)
                .orElseThrow(() -> new NoSuchElementException("Table not found !!"));

        List<Order> orderList = orderRepository.findByRestaurantTable(restaurantTable, OrderStatus.Build);
        double totalAmount = orderList.stream()
                .mapToDouble(Order::getTotalAmount)
                .sum();
        Bill bill = null;
        if (!orderList.isEmpty()) {
            bill = new Bill();
            bill.setOrders(orderList);
            bill.setTotalPayableAmount(totalAmount);
            billRepository.save(bill);
        } else {
            throw new NoSuchElementException(" No CartItem Selected !! ");
        }

        orderList.forEach(order -> order.setOrderStatus(OrderStatus.Build));
        restaurantTable.setTableStatus(TableStatus.AVAILABLE);
        tableRepository.save(restaurantTable);
        orderRepository.saveAll(orderList);

        return billMapper.mapToBillResponse(bill);
    }

    @Override
    public BillResponse findById(long billId) {
        Bill bill = billRepository.findById(billId)
                .orElseThrow(() -> new UserNotFoundByIdException("No bill found with " + billId + " id"));
        return billMapper.mapToBillResponse(bill);
    }

    @Override
    public byte[] pdfGeneration(long billId) throws Exception{


        BillResponse response = this.findById(billId);

        long foodId = response.getOrders().getFirst().getCartItems().getFirst().getFoodItem().getItemId();

        Restaurant restaurant = restaurantRepository.findNameByFoodItems_ItemId(foodId);

        long orderId = response.getOrders().getFirst().getOrderId();

        Order order = orderRepository.findRestaurantTableByOrderId(orderId);

        Map<String, Object> bill = Map.of(
                "restaurantName",restaurant.getName(),
                "tableNo",order.getOrderId(),
                "bill",response);
        return billGenerator.generatePdf("RestaurantBill",bill);
    }
}
