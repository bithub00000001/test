package ksmart.mybatis.member.controller;

import ksmart.mybatis.member.dto.Member;
import ksmart.mybatis.member.service.MemberService;
import ksmart.mybatis.member.service.MemberServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "/member")
@Slf4j
public class MemberController {



    private final MemberService memberService;

    public MemberController(MemberServiceImpl memberServiceImpl) {
        this.memberService = memberServiceImpl;
    }

    /**
     * 전체 회원 목록 조회
     *
     * @param model 제목과 회원 목록 전달
     */
    @GetMapping("/memberList")
    public String getMemberList(Model model) {
        List<Member> memberList = memberService.getMemberList();
        log.info("memberList: {}", memberList);
        model.addAttribute("title", "Member List");
        model.addAttribute("memberList", memberList);

        return "member/memberList";
    }

    /**
     * 전체 회원 목록 조회(salt)
     *
     * @param model 제목과 회원 목록 전달
     */
    @GetMapping("/memberList2")
    public String getMemberList2(Model model) {
        List<Member> memberList = memberService.getMemberList2();
        System.out.printf("member : %s%n", memberList);
        model.addAttribute("title", "Member List");
        model.addAttribute("memberList", memberList);

        return "member/memberList2";
    }

    /**
     * 회원 등록
     *
     * @param model
     */
    @GetMapping("/regMember")
    public String regMember(Model model) {
        List<Member> memberNameList = memberService.getMemberLevelList();
        model.addAttribute("title", "Reg Member");
        model.addAttribute("memberNameList", memberNameList);
        return "member/regMember";
    }

    /**
     * 회원 아이디 중복체크
     *
     * @param inputId 입력받은 회원 아이디
     * @return boolean true : 중복, false : 중복 아님
     */
    @PostMapping("/memberIdCheck")
    @ResponseBody
    public boolean memberIdCheck(@RequestParam(value = "memberId") String inputId) {

        System.out.printf("memberId : %s%n", inputId);
        boolean isMember = memberService.isMemberIdTaken(inputId);
        System.out.printf("회원 중복 여부 : %s%n", isMember);
        return isMember;
    }

    /**
     * 과제 : 2024년 04월 19일까지 회원 가입 완성
     * 회원 가입 처리 후 회원 목록 페이지로 전환
     * 커맨드 객체 : 화면 폼 양식 전송 시 name 속성과 DTO의 프로퍼티명과 동일하다면
     * 전송 Data 를 바인딩하는 객체
     */
    @PostMapping("/addMember")
    public String addMember(Member member) {
        System.out.printf("회원 가입 화면에서 입력받은 data : %s%n", member);
        // 회원 가입 처리 과제 :

        memberService.addMember(member);
        return "redirect:/member/memberList";
    }

    /**
     * 회원등록 화면
     *
     * @param model
     */
    @GetMapping("/addMember")
    public String addMember(Model model) {
        List<Member> memberLevelList = memberService.getMemberLevelList();
        System.out.println("memberLevelList: " + memberLevelList);

        model.addAttribute("title", "회원등록");
        model.addAttribute("memberLevelList", memberLevelList);

        return "member/addMember";
    }

    /**
     * 회원 등록(salt 추가)
     *
     * @param member form에서 입력받은 회원 정보
     * @return 회원 가입 후 memberList로 redirect
     */
    @PostMapping("/regMember")
    public String regMember(Member member) {
        System.out.printf("회원 가입 화면에서 입력받은 data : %s%n", member);

        memberService.insertNewMember(member);
        return "redirect:/member/memberList";
    }

    @GetMapping("/modifyMember")
    public String modifyMember(@RequestParam(name = "memberId") String memberId, Model model) {
        log.info("수정 화면 memberId: {}", memberId);
        Member memberInfoByMemberId = memberService.getMemberInfoByMemberId(memberId);
        log.info("memberInfoByMemberId: {}", memberInfoByMemberId);
        List<Member> memberLevelList = memberService.getMemberLevelList();
        model.addAttribute("title", "회원 수정");
        model.addAttribute("memberInfoByMemberId", memberInfoByMemberId);
        model.addAttribute("memberLevelList", memberLevelList);

        return "/member/modifyMember";
    }

    @PostMapping("/modifyMember")
    public String modifyMember(Member member) {
        log.info("회원 수정: {}",member);
        // 동적쿼리 테스트를 위해 주석처리
        //memberService.updateMember(member);
        memberService.modifyMember(member);
        return "redirect:/member/memberList";
    }

    @GetMapping("/removeMember")
    public String removeMember(@RequestParam(name = "memberId") String memberId,
                               @RequestParam(name = "msg", required = false)String msg,
                               Model model) {
        model.addAttribute("title", "회원탈퇴");
        model.addAttribute("memberId", memberId);
        if (msg != null) model.addAttribute("msg", msg);
        return "member/removeMember";
    }

    @PostMapping("/removeMember")
    public String removeMember(@RequestParam(name = "memberId")String memberId,
                               @RequestParam(name = "memberPassword")String memberPassword,
                               RedirectAttributes redirectAttributes) {
        String viewName;

        log.info("입력된 패스워드: {}", memberPassword);
        // 실습 : 회원 비밀번호가 일치하지 않으면 회원탈퇴폼, 페이지 일치하면 회원목록 페이지
        Map<String, Object> returnMap = memberService.checkMemberExist(memberId, memberPassword);
        boolean isMemberExist = (boolean) returnMap.get("isCheck");
        if (isMemberExist){
            Member memberInfo = (Member) returnMap.get("memberInfo");
            if (memberInfo != null){
                int memberLevel = memberInfo.getMemberLevel();
                // 회원 탈퇴
                memberService.removeMember(memberLevel, memberId);
            }
            viewName = "redirect:/member/memberList";
        }else {
            viewName = "redirect:/member/removeMember";
            String msg = "회원의 정보가 일치하지 않습니다.";
            // /member/removeMember 뒤에 쿼리문을 붙여주는 역할?memberId=변수memberId&msg="메시지+형식은+이렇게"
            redirectAttributes.addAttribute("memberId", memberId);
            redirectAttributes.addAttribute("msg", msg);
        }

        return viewName;
    }

    @GetMapping("/sellerList")
    public String sellerList(Model model) {
        List<Member> sellerList = memberService.getSellerList();
        model.addAttribute("title", "판매자 목록");
        model.addAttribute("sellerList", sellerList);
        return "member/sellerList";
    }


}
