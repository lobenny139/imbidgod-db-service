package com.imbidgod.db.service;

import com.imbidgod.db.entity.OrderDetail;
import com.imbidgod.db.service.tool.IEntityService;

public interface IOrderDetailService extends IEntityService<OrderDetail, Long> {

    Iterable<OrderDetail> getEntitiesByOrderId(String orderId);

}


