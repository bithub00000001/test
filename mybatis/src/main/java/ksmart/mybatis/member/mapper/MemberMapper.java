package ksmart.mybatis.member.mapper;

import ksmart.mybatis.member.dto.Member;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MemberMapper {
    /**
     * 회원 목록 조회 추상 메서드
     */
    List<Member> getMemberList();
/**
     * 회원 목록 조회 추상 메서드
     */
    List<Member> getMemberList2();

    /**
     * 회원 등급 조회 추상 메서드
     */
    List<Member> getMemberLevelList();

    // 중복 아이디 체크
    boolean memberIdCheck(String memberId);

    // 회원 가입
    // mapper에는 void를 사용하지 않는 것이 좋다 그래서 int로 변경해야한다
    int insertMember(Member member);

    // 회원 등록
    int addMember(Member member);

    // 회원 수정
    int updateMember(Member member);

    // 아이디와 잉치하는 정보
    Member getMemberInfoById(String memberId);

    // 회원 수정(동적쿼리)
    int modifyMember(Member member);

    // 판매자: 주문 이력 삭제(상품코드 기준)
    int removeOrderByCode(String sellerId);

    // 판매자: 상품 삭제
    int removeGoodsById(String sellerId);

    // 구매자: 주문 이력 삭제
    int removeOrderById(String orderId);

    // 모든 회원: 로그인 이력 삭제
    int removeLoginHistoryById(String loginId);

    // 모든 회원 : 회원 탈퇴
    int removeMemberById(String memberId);

    // 판매자 목록 조회
    List<Member> getSellerList();
}
