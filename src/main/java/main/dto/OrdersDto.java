package main.dto;
import main.entity.OrderDetail;
import main.entity.Orders;


import lombok.Data;
import java.util.List;

@Data
public class OrdersDto extends Orders {

    private String userName;

    private String email;

    private String address;

    private String consignee;

    private List<OrderDetail> orderDetails;

}
