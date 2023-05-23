package main.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import main.common.BaseContext;
import main.common.R;
import main.entity.AddressBook;
import main.service.AddressBookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * address book manage
 */
@Slf4j
@RestController
@RequestMapping("/addressBook")
public class AddressBookController {

    @Autowired
    private AddressBookService addressBookService;

    /**
     * <h2>new address<h2/>
     *
     * @param addressBook address book，@RequestBody json data type
     * @return {@link R}
     */
    @PostMapping
    public R<AddressBook> save(@RequestBody AddressBook addressBook) {
        addressBook.setUserId(BaseContext.getCurrentId());
        log.info("addressBook:{}", addressBook);
        addressBookService.save(addressBook);
        return R.success(addressBook);
    }

    /**
     * <h2>set default address<h2/>
     *
     * @param addressBook address book，@RequestBody json data type
     * @return {@link R}
     */
    @PutMapping("default")
    public R<AddressBook> setDefault(@RequestBody AddressBook addressBook) {
        log.info("addressBook:{}", addressBook);
        LambdaUpdateWrapper<AddressBook> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(AddressBook::getUserId, BaseContext.getCurrentId());
        wrapper.set(AddressBook::getIsDefault, 0);
        //SQL:update address_book set is_default = 0 where user_id = ?
        addressBookService.update(wrapper);

        addressBook.setIsDefault(1);
        //SQL:update address_book set is_default = 1 where id = ?
        addressBookService.updateById(addressBook);
        return R.success(addressBook);
    }

    /**
     * <h2>Query address by id<h2/>
     *
     * @param id address id
     * @return {@link R}
     */
    @GetMapping("/{id}")
    public R get(@PathVariable Long id) {
        AddressBook addressBook = addressBookService.getById(id);
        if (addressBook != null) {
            return R.success(addressBook);
        } else {
            return R.error("object not found");
        }
    }

    /**
     * <h2>Query the default address<h2/>
     *
     * @return {@link R}
     */
    @GetMapping("default")
    public R<AddressBook> getDefault() {
        LambdaQueryWrapper<AddressBook> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(AddressBook::getUserId, BaseContext.getCurrentId());
        queryWrapper.eq(AddressBook::getIsDefault, 1);

        //SQL:select * from address_book where user_id = ? and is_default = 1
        AddressBook addressBook = addressBookService.getOne(queryWrapper);

        if (null == addressBook) {
            return R.error("object not found");
        } else {
            return R.success(addressBook);
        }
    }

    /**
     * <h2>Query all addresses of a specified user<h2/>
     *
     * @param addressBook address book，@RequestBody
     * @return {@link R}
     */
    @GetMapping("/list")
    public R<List<AddressBook>> list(AddressBook addressBook) {
        addressBook.setUserId(BaseContext.getCurrentId());
        log.info("addressBook:{}", addressBook);


        LambdaQueryWrapper<AddressBook> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(null != addressBook.getUserId(), AddressBook::getUserId, addressBook.getUserId());
        queryWrapper.orderByDesc(AddressBook::getUpdateTime);

        //SQL:select * from address_book where user_id = ? order by update_time desc
        return R.success(addressBookService.list(queryWrapper));
    }


    /**
     * <h2>modify address book<h2/>
     *
     * @param addressBook address book，@RequestBody
     * @return {@link R}
     */
    @PutMapping
    public R<AddressBook> update(@RequestBody AddressBook addressBook) {
        log.info("Modified addressBook:{}", addressBook);
        // SQL: update address_book set name = ?, phone = ?, address = ?, is_default = ?, update_time = ? where id = ?
        addressBookService.updateById(addressBook);

        return R.success(addressBook);
    }


    /**
     * <h2>delete address book by id<h2/>
     *
     * @param ids
     * @return {@link R}
     */
    @DeleteMapping
    public R<String> delete(Long ids) {
        log.info("deleted id:{}", ids);
        addressBookService.removeById(ids);

        return R.success("successfully deleted");
    }

}
