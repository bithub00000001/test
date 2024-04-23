package ksmart.mybatis.order.dto;

import ksmart.mybatis.goods.dto.Goods;
import ksmart.mybatis.member.dto.Member;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Order {
    private int orderNumber;
    private String orderId;
    private String orderGoodsCode;
    private int orderAmount;
    private String orderRegDate;
    private int orderTotal;

    // 상품 구입하는 구매자
    private Member memberInfo;

    // 주문과 상품
    private Goods goodsInfo;
}
