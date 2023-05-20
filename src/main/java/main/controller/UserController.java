package main.controller;

import com.alibaba.druid.util.StringUtils;
import main.common.R;
import main.entity.User;
import main.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    /**
     * User service
     */
    @Autowired
    private UserService userService;

    /**
     * <h2>Send code<h2/>
     */
    @PostMapping("/sendMsg")
    public R<String> sendMsg(@RequestBody User user, HttpSession session) {
        log.info("Send verify code，user：{}，session:{}", user, session);
        String email = user.getEmail();
        if (!StringUtils.isEmpty(email)) {
            userService.sendCode(email, session);
            return R.success("Code send, please check email");
        }
        return R.error("Send code failure!");
    }


    /**
     * <h2>Use code to login<h2/>
     */
    @PostMapping("/login")
    public R<User> login(@RequestBody Map map, HttpSession session) {
        log.info("User login，user：{}，session:{}", map.toString(), session);
        String email = (String) map.get("email");
        String code = (String) map.get("code");

        if (!StringUtils.isEmpty(email) && !StringUtils.isEmpty(code)) {

            User user = userService.loginByVerificationCode(email, code, session);
            return R.success(user);
        }
        return R.error("Login error, please check code and email");
    }


    /**
     * <h2>Logout<h2/>
     */
    @PostMapping("/loginout")
    public R<String> logout(HttpSession session) {
        log.info("User logout，session:{}", session);
        session.invalidate();
        return R.success("Logout success!");
    }

}
