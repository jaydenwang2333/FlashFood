package main.service;

import com.baomidou.mybatisplus.extension.service.IService;
import main.entity.AddressBook;

import java.util.List;

public interface AddressBookService extends IService<AddressBook> {
    void saveAddress(AddressBook addressBook);
    List<AddressBook> queryAddressBookByUserId(Long userId);
    void setDefaultAddress(Long id);
    AddressBook getAddressBookById(Long id);
    void updateAddressBook(AddressBook addressBook);
    AddressBook getDefaultAddressBook();
    void deleteAddressById(Long ids);
    AddressBook lastUpdateAddress();



}
