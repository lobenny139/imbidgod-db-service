package com.imbidgod.asyncService;

import com.imbidgod.db.entity.Activity;
import com.imbidgod.db.service.IOrderService;

public interface IAsyncService {
    public void reCalcOrderPrice(IOrderService service, String orderId);

    public void InitStaticActivityJoingBid(Activity activity);
}
