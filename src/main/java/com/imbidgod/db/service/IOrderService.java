package com.imbidgod.db.service;

import com.imbidgod.db.entity.Order;
import com.imbidgod.db.service.tool.IEntityService;

public interface IOrderService extends IEntityService<Order, String> {

//    boolean updateOrderPriceInfo(double totalOrderPrice, double totalOrderActualPrice, String orderId  );

    boolean updateOrderTotalOrderPriceAndTotalOrderActualPrice(String id);
}
