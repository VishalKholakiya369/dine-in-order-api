package com.example.dio.model;

import com.example.dio.enums.TableStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDate;
import java.util.List;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "restaurant_table")
public class RestaurantTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "table_id")
    private long tableId;

    @Column(name = "table_no")
    private long tableNo;

    @Column(name = "table_capacity")
    private long tableCapacity;

    @Column(name = "table_status")
    @Enumerated(EnumType.STRING)
    private TableStatus tableStatus;

    @CreatedDate
    private LocalDate createAt;

    @LastModifiedDate
    private LocalDate lastModifiedAt;

    @CreatedBy
    private String createdBy;

    @ManyToOne
    private Restaurant restaurant;

    @ManyToMany(mappedBy = "tables")
    private List<Staff> staffs;

    @OneToMany(mappedBy = "restaurantTable")
    private List<CartItem> cartItems;

    @OneToMany(mappedBy = "restaurantTable")
    private List<Order> orders;

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public long getTableId() {
        return tableId;
    }

    public void setTableId(long tableId) {
        this.tableId = tableId;
    }

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

    public LocalDate getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDate createAt) {
        this.createAt = createAt;
    }

    public LocalDate getLastModifiedAt() {
        return lastModifiedAt;
    }

    public void setLastModifiedAt(LocalDate lastModifiedAt) {
        this.lastModifiedAt = lastModifiedAt;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public void setRestaurant(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public List<Staff> getStaffs() {
        return staffs;
    }

    public void setStaffs(List<Staff> staffs) {
        this.staffs = staffs;
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<CartItem> cartItems) {
        this.cartItems = cartItems;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
