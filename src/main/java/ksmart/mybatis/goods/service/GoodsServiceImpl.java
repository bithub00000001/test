package ksmart.mybatis.goods.service;

import ksmart.mybatis.goods.dto.Goods;
import ksmart.mybatis.goods.mapper.GoodsMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class GoodsServiceImpl implements GoodsService {

    private final GoodsMapper goodsMapper;
    public GoodsServiceImpl(GoodsMapper goodsMapper) {
        this.goodsMapper = goodsMapper;
    }

    /**
     * 상품 목록 조회 처리
     * @return
     */
    @Override
    public List<Goods> getGoodsList() {
        List<Goods> goodsList = goodsMapper.getGoodsList();

        return goodsList;
    }
    @Override
    public List<Goods> getGoodsDetailList() {
        List<Goods> goodsDetailList = goodsMapper.getGoodsDetailList();
        return goodsDetailList;
    }
}
