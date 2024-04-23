package ksmart.mybatis.member.service;

import ksmart.mybatis.member.dto.Member;

import java.util.List;
import java.util.Map;

public interface MemberService {
    List<Member> getMemberList();
    List<Member> getMemberList2();
    List<Member> getMemberLevelList();
    boolean isMemberIdTaken(String memberId);
    void insertNewMember(Member member);
    void addMember(Member member);
    void updateMember(Member member);
    Member getMemberInfoByMemberId(String memberId);
    void modifyMember(Member member);
    Map<String, Object> checkMemberExist(String memberId, String memberPassword);
    void removeMember(int memberLevel, String memberId);
    List<Member> getSellerList();
}
