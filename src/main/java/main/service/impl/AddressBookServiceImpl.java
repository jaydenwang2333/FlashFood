package main.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import main.entity.AddressBook;
import main.mapper.AddressBookMapper;
import main.service.AddressBookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AddressBookServiceImpl extends ServiceImpl<AddressBookMapper, AddressBook> implements AddressBookService {

}
