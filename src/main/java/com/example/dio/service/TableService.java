package com.example.dio.service;

import com.example.dio.dto.request.TableRequest;
import com.example.dio.dto.response.TableResponse;

public interface TableService {

   public TableResponse createTable(TableRequest tableRequest, long restaurantId);
}
