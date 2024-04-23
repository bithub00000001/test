package ksmart.mybatis.member.dto;

import ksmart.mybatis.goods.dto.Goods;
import lombok.Data;

import java.util.List;

@Data
public class Member {
    private String memberId;
    private String memberPassword;
    private String memberName;
    private int memberLevel;
    private String memberLevelName;
    private String memberAddr;
    private String memberEmail;
    private String salt;

    private String memberRegDate;
    // 1:N has many 관계
    private List<Goods> goodsList;


}
