package com.imbidgod.db.service;

import com.imbidgod.db.entity.Product;
import com.imbidgod.db.service.tool.IEntityService;

public interface IProductService extends IEntityService<Product, String> {
    Iterable<Product> getEntitiesByOrderId(String orderId);
}


