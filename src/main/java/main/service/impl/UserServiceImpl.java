package main.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import main.common.CustomException;
import main.entity.User;
import main.mapper.UserMapper;
import main.service.UserService;
import main.util.ValidateCodeUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String FROM;

    @Value("${spring.mail.timeout}")
    private int TIME_OUT_MINUTES;

    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;


    /**
     * Send verification code
     *
     * @param email email address
     * @param session session
     */
    public void sendCode(String email, HttpSession session) {

        String code = ValidateCodeUtils.generateValidateCode(4).toString();
        log.info("Session:{}, Code:{}, to {}", session, code, email);


        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();

        simpleMailMessage.setFrom(FROM);

        simpleMailMessage.setTo(email);

        simpleMailMessage.setSubject("Login Verification Code");

        simpleMailMessage.setText("Welcome to our takeaway platform.\nYour verification code is：" + code + "Please use in " + TIME_OUT_MINUTES + ".");
        redisTemplate.opsForValue().set(email, code, TIME_OUT_MINUTES, java.util.concurrent.TimeUnit.MINUTES);

        try {
            javaMailSender.send(simpleMailMessage);
        } catch (MailException e) {
            e.printStackTrace();
            throw new CustomException("Severe Error!");
        }


    }


    /**
     * Captcha login account, or automatic registration if new user
     *
     * @param email email address
     * @param code Verification code
     * @param session session
     */
    @Override
    public User loginByVerificationCode(String email, String code, HttpSession session) {

        Object verificationCodeInRedis = redisTemplate.opsForValue().get(email);

        if (verificationCodeInRedis != null && verificationCodeInRedis.equals(code)) {

            LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(User::getEmail, email);
            User user = this.getOne(queryWrapper);

            if (user == null) {
                user = new User();
                user.setEmail(email);
                user.setStatus(1);
                this.save(user);
            }

            session.setAttribute("user", user.getId());

            redisTemplate.delete(email);

            return user;
        } else {

            throw new CustomException("Wrong Code!");
        }
    }
}
