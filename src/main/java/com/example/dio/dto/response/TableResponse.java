package com.example.dio.dto.response;

import com.example.dio.enums.TableStatus;
import lombok.Getter;
import lombok.Setter;


public class TableResponse {

    private long tableNo;
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
