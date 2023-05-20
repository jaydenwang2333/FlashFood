package main.service;

import com.baomidou.mybatisplus.extension.service.IService;
import main.entity.User;

import javax.servlet.http.HttpSession;

public interface UserService extends IService<User> {

    /**
     * Send verification code
     *
     * @param email email address
     * @param session session
     */
    void sendCode(String email, HttpSession session);


    /**
     * Captcha login account, or automatic registration if new user
     *
     * @param email email address
     * @param code Verification code
     * @param session session
     * @return user information
     */
    User loginByVerificationCode(String email, String code, HttpSession session);
}
