package com.example.dio.service.impl;

import com.example.dio.dto.request.TableRequest;
import com.example.dio.dto.response.TableResponse;
import com.example.dio.exception.UserNotFoundByIdException;
import com.example.dio.mapper.TableMapper;
import com.example.dio.model.Admin;
import com.example.dio.model.Restaurant;
import com.example.dio.model.RestaurantTable;

import com.example.dio.model.User;
import com.example.dio.repository.RestaurantRepository;
import com.example.dio.repository.TableRepository;
import com.example.dio.repository.UserRepository;
import com.example.dio.service.TableService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class TableServiceImpl implements TableService {

    private TableRepository tableRepository;
    private UserRepository userRepository;
    private RestaurantRepository restaurantRepository;
    private TableMapper tableMapper;


    @Override
    public TableResponse createTable(TableRequest tableRequest, long restaurantId) {
        RestaurantTable table = tableMapper.mapToTable(tableRequest);

       Restaurant restaurant = restaurantRepository.findById(restaurantId).
               orElseThrow(()->new UserNotFoundByIdException("User not Found"));

       table.setRestaurant(restaurant);
        tableRepository.save(table);

        return tableMapper.mapToTableResponse(table);
    }
}
