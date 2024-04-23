package ksmart.mybatis.order.mapper;

import ksmart.mybatis.order.dto.Order;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderMapper {
    List<Order> getOrderList();
}
