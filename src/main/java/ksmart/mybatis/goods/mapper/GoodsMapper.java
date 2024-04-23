package ksmart.mybatis.goods.mapper;

import ksmart.mybatis.goods.dto.Goods;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface GoodsMapper {

    // 상품 목록 조회
    List<Goods> getGoodsList();

    // 상품 상세 목록 조회
    List<Goods> getGoodsDetailList();
}
