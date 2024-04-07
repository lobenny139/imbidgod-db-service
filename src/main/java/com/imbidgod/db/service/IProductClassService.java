package com.imbidgod.db.service;

import com.imbidgod.db.entity.ProductClass;
import com.imbidgod.db.service.tool.IEntityService;

public interface IProductClassService extends IEntityService<ProductClass, Long> {

    ProductClass getEntityByIdAndIsActive(long id, int isActive);

    Iterable<ProductClass> getEntitiesByIsActive(int isActive);

}
