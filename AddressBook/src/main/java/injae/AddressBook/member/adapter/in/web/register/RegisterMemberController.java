package injae.AddressBook.member.adapter.in.web.register;

import injae.AddressBook.common.exception.DuplicateMemberException;
import injae.AddressBook.member.application.port.in.RegisterMemberUseCase;
import injae.AddressBook.member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@RequestMapping("/member")
public class RegisterMemberController {

    private final RegisterMemberUseCase useCase;

    @GetMapping("/register")
    public String registerForm(@ModelAttribute RegisterMemberForm form) {
        return "member/registerMemberForm";
    }

    @PostMapping("/register")
    public String registerMember(@Valid @ModelAttribute RegisterMemberForm form,
                                 BindingResult bindingResult,
                                 HttpServletRequest request) {

        //회원가입 성공 여부 boolean
        Boolean isRegisteringMemberSucceed = null;

        //비밀번호와 확인 비밀번호가 서로 일치 하지 않으면 예외 발생
        if(!form.getPassword().equals(form.getConfirmPassword())) {
            bindingResult.reject("notSamePassword",
                    "비밀번호와 확인 비밀번호가 서로 일치하지 않습니다.");
        }

        //예외가 생기면 다시 돌아감
        if (bindingResult.hasErrors()) {
            return "member/registerMemberForm";
        }

        //성공 로직
        try {
            useCase.registerMember(form.getEmail(), form.getPassword(), form.getName());
        } catch (DuplicateMemberException e) {
            //이미 존재하는 아이디를 입력하여 회원 가입할 경우
            bindingResult.reject("duplicateMember",
                    e.getMessage());
            //예외가 생겨서 다시 돌아감
            return "member/registerMemberForm";
        }

        //회원가입 성공 처리
        isRegisteringMemberSucceed = true;
        //세션이 있으면 있는 세션을 반환, 없으면 신규 세션을 생성함
        HttpSession session = request.getSession();
        //세션에 회웝 가입 성공 저장
        session.setAttribute("isRegisteringMemberSucceed",
                isRegisteringMemberSucceed);

        //예외가 없으면(회원가입이 성공적으로 처리되면) 홈화면으로 돌아감
        return "redirect:/";
    }
}
