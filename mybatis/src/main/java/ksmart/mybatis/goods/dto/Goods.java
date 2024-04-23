package ksmart.mybatis.goods.dto;

import ksmart.mybatis.member.dto.Member;
import lombok.Getter;

public class Goods {
    private String goodsCode;
    private String goodsName;
    private String goodsSellerId;
    private int goodsPrice;
    private String goodsRegDate;

    // 상품을 등록하는 판매자와의 관계 -> 1:1
    private Member memberInfo;

    public String getGoodsCode() {
        return goodsCode;
    }

    public void setGoodsCode(String goodsCode) {
        this.goodsCode = goodsCode;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsSellerId() {
        return goodsSellerId;
    }

    public void setGoodsSellerId(String goodsSellerId) {
        this.goodsSellerId = goodsSellerId;
    }

    public int getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(int goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public String getGoodsRegDate() {
        return goodsRegDate;
    }

    public void setGoodsRegDate(String goodsRegDate) {
        this.goodsRegDate = goodsRegDate;
    }

    public Member getMemberInfo() {
        return memberInfo;
    }

    public void setMemberInfo(Member memberInfo) {
        this.memberInfo = memberInfo;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Goods{");
        sb.append("goodsCode='").append(goodsCode).append('\'');
        sb.append(", goodsName='").append(goodsName).append('\'');
        sb.append(", goodsSellerId='").append(goodsSellerId).append('\'');
        sb.append(", goodsPrice=").append(goodsPrice);
        sb.append(", goodsRegDate='").append(goodsRegDate).append('\'');
        sb.append(", memberInfo=").append(memberInfo);
        sb.append('}');
        return sb.toString();
    }
}
