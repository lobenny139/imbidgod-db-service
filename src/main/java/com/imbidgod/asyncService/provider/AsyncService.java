package com.imbidgod.asyncService.provider;

import com.imbidgod.asyncService.IAsyncService;
import com.imbidgod.db.entity.Activity;
import com.imbidgod.db.service.IOrderService;
import com.imbidgod.db.service.IStatisActivityService;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Random;

@Getter
@Setter
@Component
@Service
/**
 * attention:這種 Async 不能經過 AOP, proxy
 */
public class AsyncService implements IAsyncService {

    private static Logger logger = LoggerFactory.getLogger(AsyncService.class);


    @Autowired(required=true)
    @Qualifier("statisActivityService")
    public IStatisActivityService staticActivityJoingBidService;


    @Async
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void reCalcOrderPrice(IOrderService service, String orderId){
        try {
            int upperbound = 1000;
            int int_random = new Random().nextInt(upperbound) + 1000;
            Thread.sleep(int_random); //1000-2000 ms
            logger.info("準備重新計算 Order[id=" + orderId + "]");
            long startTime = System.currentTimeMillis();
            service.updateOrderTotalOrderPriceAndTotalOrderActualPrice(orderId);
            long endTime = System.currentTimeMillis();
            logger.info("成功重新計算 Order[id=" + orderId + "], 耗時:" + (endTime - startTime) + "豪秒");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Async
    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = false)
    public void InitStaticActivityJoingBid(Activity activity){
        try {
            int upperbound = 1000;
            int int_random = new Random().nextInt(upperbound) + 1000;
            Thread.sleep(int_random); //1000-2000 ms
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        logger.info("準備建立活動[id=" + activity.getId() + "]統計表");
        getStaticActivityJoingBidService().createEntinities(activity);
        logger.info("成功建立活動[id=" + activity.getId() + "]統計表");
    }
}
