package main.Mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import main.entity.AddressBook;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AddressBookMapper extends BaseMapper<AddressBook> {
}
