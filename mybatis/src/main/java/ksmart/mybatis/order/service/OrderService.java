package ksmart.mybatis.order.service;

import ksmart.mybatis.order.dto.Order;

import java.util.List;

public interface OrderService {
    List<Order> getOrderList();
}
