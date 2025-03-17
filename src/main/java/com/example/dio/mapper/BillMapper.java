package com.example.dio.mapper;

import com.example.dio.dto.response.BillResponse;
import com.example.dio.model.Bill;
import com.example.dio.model.Category;
import com.example.dio.model.CuisineType;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BillMapper {
    public BillResponse mapToBillResponse(Bill bill);
    List<String> map(List<Category> value);
    default String mapToString(Category value) {
        if(value == null) {
            return null;
        }
        else return value.getCategory().toLowerCase();
    }

    default String mapToString (CuisineType cuisineType){
        if(cuisineType ==null)
        {
            return null;
        }
        return cuisineType.getCuisine();
    }
}
