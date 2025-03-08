package com.example.dio.mapper;

import com.example.dio.dto.request.TableRequest;
import com.example.dio.dto.response.TableResponse;
import com.example.dio.model.RestaurantTable;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TableMapper {
    TableResponse mapToTableResponse(RestaurantTable table);

    RestaurantTable mapToTable (TableRequest tableRequest);

}
