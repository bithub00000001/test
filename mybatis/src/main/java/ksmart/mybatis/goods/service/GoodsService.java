package ksmart.mybatis.goods.service;

import ksmart.mybatis.goods.dto.Goods;

import java.util.List;

public interface GoodsService {
    List<Goods> getGoodsList();
    List<Goods> getGoodsDetailList();
}
