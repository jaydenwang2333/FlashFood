package main.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import main.dto.OrdersDto;
import main.entity.Orders;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public interface OrdersService extends IService<Orders> {

    /**
     * User orders
     *
     * @param orders order information
     */
    void submit(Orders orders);


    /**
     * Get user order pagination
     *
     * @param page page number
     * @param pageSize Number of pages per page
     * @return paging data
     */
    Page<OrdersDto> getUserPage(int page, int pageSize);


    /**
     * Get administrator order details
     *
     * @param page Page size
     * @param pageSize Number per page
     * @param number Order number
     * @param beginTime Start time
     * @param endTime end time
     */
    Page<OrdersDto> getPage(int page,
                            int pageSize,
                            String number,
                            @DateTimeFormat(pattern = "yyyy-mm-dd HH:mm:ss") Date beginTime,
                            @DateTimeFormat(pattern = "yyyy-mm-dd HH:mm:ss") Date endTime);
}
