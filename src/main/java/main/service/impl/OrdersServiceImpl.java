package main.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import main.common.BaseContext;
import main.common.CustomException;
import main.dto.OrdersDto;
import main.entity.*;
import main.mapper.OrdersMapper;
import main.service.*;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
@EnableTransactionManagement
public class OrdersServiceImpl extends ServiceImpl<OrdersMapper, Orders> implements OrdersService {


    @Autowired
    private ShoppingCartService shoppingCartService;

    @Autowired
    private UserService userService;

    @Autowired
    private AddressBookService addressBookService;

    @Autowired
    private OrderDetailService orderDetailService;


    /**
     * The user places an order
     * <p> The @Transactional annotation opens the transaction before the method is executed and commits the transaction when execution is complete
     *
     * @param orders order information
     */
    @Transactional
    public void submit(Orders orders) {

        long currentId = BaseContext.getCurrentId();

        LambdaQueryWrapper<ShoppingCart> cartQueryWrapper = new LambdaQueryWrapper<>();
        cartQueryWrapper.eq(ShoppingCart::getUserId, currentId);
        List<ShoppingCart> shoppingCarts = shoppingCartService.list(cartQueryWrapper);
        if (shoppingCarts == null || shoppingCarts.size() == 0) {
            throw new CustomException("The Cart is empty");
        }

        User user = userService.getById(currentId);

        Long addressBookId = orders.getAddressBookId();
        AddressBook addressBook = addressBookService.getById(addressBookId);
        if (addressBook == null) {
            throw new CustomException("Address does not exist");
        }

        long orderId = IdWorker.getId();

        AtomicInteger amount = new AtomicInteger(0);


        List<OrderDetail> orderDetails = shoppingCarts.stream().map(item -> {

            OrderDetail orderDetail = new OrderDetail();

            orderDetail.setOrderId(orderId);
            orderDetail.setNumber(item.getNumber());
            orderDetail.setDishFlavor(item.getDishFlavor());
            orderDetail.setDishId(item.getDishId());
            orderDetail.setSetmealId(item.getSetmealId());
            orderDetail.setName(item.getName());
            orderDetail.setImage(item.getImage());
            orderDetail.setAmount(item.getAmount());
            amount.addAndGet(item.getAmount().multiply(new BigDecimal(item.getNumber())).intValue());
            return orderDetail;
        }).collect(Collectors.toList());

        orders.setId(orderId);
        orders.setOrderTime(LocalDateTime.now());
        orders.setCheckoutTime(LocalDateTime.now());
        orders.setStatus(2);
        orders.setAmount(new BigDecimal(amount.get()));
        orders.setUserId(currentId);
        orders.setNumber(String.valueOf(orderId));
        orders.setUserName(user.getName());
        orders.setConsignee(addressBook.getConsignee());
        orders.setPhone(addressBook.getPhone());
        orders.setAddress((addressBook.getProvinceName() == null ? "" : addressBook.getProvinceName())
                + (addressBook.getCityName() == null ? "" : addressBook.getCityName())
                + (addressBook.getDistrictName() == null ? "" : addressBook.getDistrictName())
                + (addressBook.getDetail() == null ? "" : addressBook.getDetail()));
        this.save(orders);

        orderDetailService.saveBatch(orderDetails);

        shoppingCartService.remove(cartQueryWrapper);
    }


    /**
     * Get user order pagination
     *
     * @param page page number
     * @param pageSize Number of pages per page
     * @return paging data
     */
    public Page<OrdersDto> getUserPage(int page, int pageSize) {

        Page<Orders> ordersPage = new Page<>(page, pageSize);
        Page<OrdersDto> dtoPage = new Page<>();

        LambdaQueryWrapper<Orders> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Orders::getUserId, BaseContext.getCurrentId());
        queryWrapper.orderByDesc(Orders::getOrderTime);

        this.page(ordersPage, queryWrapper);
        // copy object, copy data from ordersPage to dtoPage, remove records property, records property needs to be handled separately
        BeanUtils.copyProperties(ordersPage, dtoPage, "records");

        List<Orders> ordersPageRecords = ordersPage.getRecords();
        List<OrdersDto> dtoPageRecords = ordersPageRecords.stream().map(item -> {

            OrdersDto ordersDto = new OrdersDto();

            BeanUtils.copyProperties(item, ordersDto);
            LambdaQueryWrapper<OrderDetail> orderDetailQueryWrapper = new LambdaQueryWrapper<>();
            orderDetailQueryWrapper.eq(OrderDetail::getOrderId, item.getId());
            List<OrderDetail> orderDetails = orderDetailService.list(orderDetailQueryWrapper);

            ordersDto.setOrderDetails(orderDetails);

            LambdaQueryWrapper<User> userQueryWrapper = new LambdaQueryWrapper<>();
            userQueryWrapper.eq(User::getId, item.getUserId());
            User user = userService.getOne(userQueryWrapper);
            if (user != null) {
                ordersDto.setUserName(user.getName());
                ordersDto.setEmail(user.getEmail());
            }
            ordersDto.setAmount(item.getAmount());
            ordersDto.setConsignee(item.getConsignee());
            ordersDto.setAddress(item.getAddress());
            ordersDto.setUserName(item.getConsignee());

            return ordersDto;

        }).collect(Collectors.toList());


        dtoPage.setRecords(dtoPageRecords);

        return dtoPage;
    }


    /**
     * Get administrator order details
     *
     * @param page Page size
     * @param pageSize Number per page
     * @param number Order number
     * @param beginTime Start time
     * @param endTime end time
     */
    public Page<OrdersDto> getPage(int page,
                                   int pageSize,
                                   String number,
                                   @DateTimeFormat(pattern = "yyyy-mm-dd HH:mm:ss") Date beginTime,
                                   @DateTimeFormat(pattern = "yyyy-mm-dd HH:mm:ss") Date endTime) {

        Page<Orders> ordersPage = new Page<>(page, pageSize);
        Page<OrdersDto> dtoPage = new Page<>();

        LambdaQueryWrapper<Orders> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderByDesc(Orders::getOrderTime);
        queryWrapper.like(StringUtils.isNotEmpty(number), Orders::getNumber, number);
        queryWrapper.between((beginTime != null && endTime != null), Orders::getOrderTime, beginTime, endTime);

        this.page(ordersPage, queryWrapper);

        BeanUtils.copyProperties(ordersPage, dtoPage, "records");

        List<Orders> ordersPageRecords = ordersPage.getRecords();

        List<OrdersDto> dtoPageRecords = ordersPageRecords.stream().map(item -> {

            OrdersDto ordersDto = new OrdersDto();

            BeanUtils.copyProperties(item, ordersDto);

            LambdaQueryWrapper<OrderDetail> orderDetailQueryWrapper = new LambdaQueryWrapper<>();
            orderDetailQueryWrapper.eq(OrderDetail::getOrderId, item.getId());
            List<OrderDetail> orderDetails = orderDetailService.list(orderDetailQueryWrapper);

            ordersDto.setOrderDetails(orderDetails);

            LambdaQueryWrapper<User> userQueryWrapper = new LambdaQueryWrapper<>();
            userQueryWrapper.eq(User::getId, item.getUserId());
            User user = userService.getOne(userQueryWrapper);
            if (user != null) {
                ordersDto.setUserName(user.getName());
                ordersDto.setEmail(user.getEmail());
            }
            ordersDto.setAmount(item.getAmount());
            ordersDto.setConsignee(item.getConsignee());
            ordersDto.setAddress(item.getAddress());
            ordersDto.setUserName(item.getConsignee());

            return ordersDto;
        }).collect(Collectors.toList());


        dtoPage.setRecords(dtoPageRecords);

        return dtoPage;
    }
}
