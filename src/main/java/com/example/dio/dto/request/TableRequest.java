package com.example.dio.dto.request;


import com.example.dio.enums.TableStatus;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;


public class TableRequest {


    @Min(value = 1, message = "Table number must be a positive integer")
    private long tableNo;

    @Min(value = 1, message = "Capacity must be at least 1")
    @Max(value = 20, message = "Capacity cannot be more than 20")
    private long tableCapacity;


    private TableStatus tableStatus;


    public long getTableNo() {
        return tableNo;
    }

    public void setTableNo(long tableNo) {
        this.tableNo = tableNo;
    }

    public long getTableCapacity() {
        return tableCapacity;
    }

    public void setTableCapacity(long tableCapacity) {
        this.tableCapacity = tableCapacity;
    }

    public TableStatus getTableStatus() {
        return tableStatus;
    }

    public void setTableStatus(TableStatus tableStatus) {
        this.tableStatus = tableStatus;
    }
}
