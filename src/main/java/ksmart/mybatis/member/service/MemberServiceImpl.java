package ksmart.mybatis.member.service;

import ksmart.mybatis.member.dto.Member;
import ksmart.mybatis.member.mapper.MemberMapper;
import ksmart.mybatis.util.PasswordUtils;
import lombok.RequiredArgsConstructor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

//@RequiredArgsConstructor : Lombok 사용 시 생성자 메서드를 자동으로 만들어준다
@Service
@Transactional
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private static final Logger log = LoggerFactory.getLogger(MemberServiceImpl.class);
    private final MemberMapper memberMapper;

    // @RequiredArgsConstructor 를 사용해서 대체해준다
    /*public MemberServiceImpl(MemberMapper memberMapper) {
        this.memberMapper = memberMapper;
    }*/

    /**
     * 회원 목록 조회 처리
     * @return List<Member> memberList
     */
    @Override
    public List<Member> getMemberList() {
        List<Member> memberList = memberMapper.getMemberList();
        return getMembers(memberList);
    }

    /**
         * 회원 목록 조회 처리
         * @return List<Member> memberList
         */
        @Override
        public List<Member> getMemberList2() {
            List<Member> memberList = memberMapper.getMemberList2();
            return getMembers(memberList);
        }

    private List<Member> getMembers(List<Member> memberList) {
        if (memberList != null){
            memberList.forEach(member -> {
                getMemberLevelList(member);
            });
        }
        return memberList;
    }

    /**
     * 회원 등급 목록 조회
     * @return
     */
    @Override
    public List<Member> getMemberLevelList() {
        List<Member> memberLevelList = memberMapper.getMemberLevelList();
        return memberLevelList;
    }

    /**
     * 회원 중복 아이디 체크 처리 로직
     * @param memberId 입력받은 아이디를 컨트롤러를 통해 서비스로 전달
     */
    @Override
    public boolean isMemberIdTaken(String memberId) {
        return memberMapper.memberIdCheck(memberId);
    }

    /**
     * 회원 가입 처리 로직
     * @param member 입력받은 Member 데이터를 서비스로 전달받아 mapper로 전달
     */
    @Override
    public void insertNewMember(Member member) {
        System.out.printf("memberServiceImpl : %s%n", member);
        String salt = PasswordUtils.generateSalt();
        String hashedPassword = PasswordUtils.hashPassword(member.getMemberPassword(), salt);
        member.setMemberPassword(hashedPassword);
        member.setSalt(salt);
        memberMapper.insertMember(member);
    }


    /**
     * 회원등록
     */
    @Override
    public void addMember(Member member) {
        memberMapper.addMember(member);
    }

    @Override
    public void updateMember(Member member) {
        memberMapper.updateMember(member);
    }

    @Override
    public Member getMemberInfoByMemberId(String memberId) {
        Member member = memberMapper.getMemberInfoById(memberId);
        getMemberLevelList(member);
        return member;
    }

    private void getMemberLevelList(Member member) {
        int memberLevel = member.getMemberLevel();
        String memberLevelName;
        switch (memberLevel) {
            case 1 -> memberLevelName = "관리자";
            case 2 -> memberLevelName = "판매자";
            case 3 -> memberLevelName = "구매자";
            default -> memberLevelName = "일반 회원";
        }
        member.setMemberLevelName(memberLevelName);
    }

    /**
     * 회원 정보 수정
     * @param member
     */
    @Override
    public void modifyMember(Member member) {
        memberMapper.modifyMember(member);
    }

    @Override
    public Map<String, Object> checkMemberExist(String memberId, String memberPassword) {
        Map<String, Object> returnMap = new HashMap<>();
        boolean isCheck = false;
        Member checkMemberInfo = memberMapper.getMemberInfoById(memberId);
        if(checkMemberInfo != null){
            returnMap.put("memberInfo",checkMemberInfo);
            String checkMemberPassword = checkMemberInfo.getMemberPassword();
            if(checkMemberPassword.equals(memberPassword)){
                isCheck = true;
                returnMap.put("isCheck",true);
            }
        }
        returnMap.put("isCheck",isCheck);
        return returnMap;
    }
    @Override
    public void removeMember(int memberLevel, String memberId) {
        // 권한별 회원탈퇴
        // 1. 관리자, 2:판매자, 3:구매자, 4:일반회원
        switch (memberLevel) {
            case 2 ->{
                memberMapper.removeOrderByCode(memberId);
                memberMapper.removeGoodsById(memberId);
            }
            case 3 -> memberMapper.removeOrderById(memberId);
        }
        memberMapper.removeLoginHistoryById(memberId);
        memberMapper.removeMemberById(memberId);
    }

    @Override
    public List<Member> getSellerList() {
        List<Member> sellerList = memberMapper.getSellerList();
        log.info("sellerList: {}", sellerList);
        if (sellerList != null) log.info("sellerList size: {}", sellerList.size());
        return sellerList;
    }


}
