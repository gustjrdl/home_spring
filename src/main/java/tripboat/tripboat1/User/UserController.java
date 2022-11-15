package tripboat.tripboat1.User;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import tripboat.tripboat1.User.UserCreateForm;
import tripboat.tripboat1.User.UserServices;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@RequiredArgsConstructor
@Controller
@RequestMapping("/user")
public class UserController {
    private final UserServices userServices;

    @GetMapping("/signup")
    public String signup(UserCreateForm userCreateForm) {
        return "SignupForm";
    }


    @PostMapping("/signup")
    public String signup(@Valid UserCreateForm userCreateForm, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            return "SignupForm";
        }
        if (!userCreateForm.getPassword1().equals(userCreateForm.getPassword2())) {
            bindingResult.rejectValue("password2", "passwordInCorrect", "비밀번호가 일치하지 않습니다.");
            return "SignupForm";
        }
        try {
            userServices.create(userCreateForm.getUsername(),
                    userCreateForm.getEmail(),
                    userCreateForm.getPassword1(),
                    userCreateForm.getNickname());
        } catch (DataIntegrityViolationException e) {
            e.printStackTrace();
            bindingResult.reject("signupFailed", "이미 등록된 아이디 입니다.");
            return "SignupForm";
        } catch (Exception e) {
            e.printStackTrace();
            bindingResult.reject("signupFailed", e.getMessage());
            return "SignupForm";
        }
        return "Error";
    }

    @RequestMapping("login_errors")
    private String login_err () {
        return "LoginError";
    }

    @GetMapping("/login")
    public String login() {
        return "LoginForm";
    }

    @ResponseBody
    @RequestMapping("/logout")
    public String Logout(HttpSession session) {
        session.invalidate();
        String str = "<script>";
        str+="alert('로그아웃이 완료 되었습니다.');";
        str+="window.location.href = '/main';";
        str+="</script>";
        return str;
    }

}