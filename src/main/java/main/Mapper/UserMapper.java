package main.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import main.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}