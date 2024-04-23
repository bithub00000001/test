package ksmart.mybatis.order.controller;

import ksmart.mybatis.order.dto.Order;
import ksmart.mybatis.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(value = "/order")
@RequiredArgsConstructor
public class OrderController {
    private static final Logger log = LoggerFactory.getLogger(OrderController.class);
    private final OrderService orderService;

    /**
     * 주문 목록 현황
     * @param model
     * @return
     */
    @GetMapping("/orderList")
    public String orderList(Model model) {
        List<Order> orderList = orderService.getOrderList();
        log.info("orderList: {}", orderList);
        model.addAttribute("title", "주문 목록 현황");
        model.addAttribute("orderList", orderList);
        return "order/orderList";
    }
}
