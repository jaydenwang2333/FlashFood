package main.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import main.common.R;
import main.dto.OrdersDto;
import main.entity.Orders;
import main.service.OrdersService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Slf4j
@RestController
@RequestMapping("/order")
public class OrdersController {

    /**
     * Order service
     */
    @Autowired
    private OrdersService ordersService;


    /**
     * <h2>Submit order<h2/>
     *
     * @param orders information
     * @return {@link R}
     */
    @PostMapping("/submit")
    public R<String> submit(@RequestBody Orders orders) {
        log.info("submit order: {}", orders);
        ordersService.submit(orders);
        return R.success("Order submitted");
    }


    /**
     * <h2>Check order detail<h2/>
     *
     * @param page
     * @param pageSize
     * @return {@link R}
     */
    @GetMapping("/userPage")
    public R<Page<OrdersDto>> userPage(int page, int pageSize) {
        Page<OrdersDto> userPage = ordersService.getUserPage(page, pageSize);

        return R.success(userPage);
    }


    /**
     * <h2>Search order details<h2/>
     *
     * @param page      page
     * @param pageSize  size in page
     * @param number    order number
     * @param beginTime begin time
     * @param endTime   end time
     * @return {@link R}
     */
    @GetMapping("/page")
    public R<Page<OrdersDto>> page(
            int page,
            int pageSize,
            String number,
            @DateTimeFormat(pattern = "yyyy-mm-dd HH:mm:ss") Date beginTime,
            @DateTimeFormat(pattern = "yyyy-mm-dd HH:mm:ss") Date endTime) {
        log.info(
                "Order search：page={}，pageSize={}，number={},beginTime={},endTime={}",
                page,
                pageSize,
                number,
                beginTime,
                endTime);
        Page<OrdersDto> pageInfo = ordersService.getPage(page, pageSize, number, beginTime, endTime);

        return R.success(pageInfo);
    }

    /**
     * <h2>Update order<h2/>
     *
     * @param id     order id
     * @param orders order information
     * @return {@link R}
     */
    @PutMapping("/{id}")
    public R<String> update(@PathVariable Long id, @RequestBody Orders orders) {
        log.info("Update order: {}", orders);
        orders.setId(id);
        ordersService.update(orders);
        return R.success("Order updated");
    }


}