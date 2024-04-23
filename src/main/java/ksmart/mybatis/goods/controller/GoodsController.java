package ksmart.mybatis.goods.controller;

import ksmart.mybatis.goods.dto.Goods;
import ksmart.mybatis.goods.service.GoodsService;
import ksmart.mybatis.goods.service.GoodsServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping(value = "/goods")
public class GoodsController {

    private static final Logger log = LoggerFactory.getLogger(GoodsController.class);
    private final GoodsService goodsService;

    public GoodsController(GoodsServiceImpl goodsServiceImpl) {
        this.goodsService = goodsServiceImpl;
    }

    @GetMapping("/goodsList")
    public String goodsList(Model model) {
        List<Goods> goodsList = goodsService.getGoodsList();
        model.addAttribute("title", "Goods List");
        model.addAttribute("goodsList", goodsList);
        return "goods/goodsList";
    }

    @GetMapping("/goodsDetailList")
    public String goodsDetailList(Model model) {
        List<Goods> goodsDetailList = goodsService.getGoodsDetailList();
        log.info("goodsDetailList: {}", goodsDetailList);
        model.addAttribute("title", "Goods Detail List");
        model.addAttribute("goodsDetailList", goodsDetailList);
        return "goods/goodsDetailList";
    }

}
