package main.service;

import com.baomidou.mybatisplus.extension.service.IService;
import main.entity.User;

public interface UserService extends IService<User> {
    User getUserByPhone(String phone);
    void createUser(User user);
    User getUserById(Long userId);
}
