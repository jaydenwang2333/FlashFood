package main.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import main.entity.Orders;

public interface OrdersService extends IService<Orders> {
    void submitOrder(Orders orders);
    Page page(int page, int pageSize, String number, String beginTime, String endTime);
    void changeOrdersStatus(Orders orders);
    Page userOrdersPage(int page, int pageSize);
    void again(Long id);

}
