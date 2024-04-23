package ksmart.mybatis.order.service;

import ksmart.mybatis.order.dto.Order;
import ksmart.mybatis.order.mapper.OrderMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService{
    private final OrderMapper orderMapper;

    /**
     * 주문 상세 현황 목록
     * @return List<Order> orderList
     */
    @Override
    public List<Order> getOrderList() {
        List<Order> orderList = orderMapper.getOrderList();
        return orderList;
    }
}
