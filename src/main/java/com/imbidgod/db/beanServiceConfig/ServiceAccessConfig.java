package com.imbidgod.db.beanServiceConfig;

import com.imbidgod.asyncService.IAsyncService;
import com.imbidgod.asyncService.provider.AsyncService;
import com.imbidgod.cronService.ICronService;
import com.imbidgod.cronService.provider.CronService;
import com.imbidgod.db.service.*;
import com.imbidgod.db.service.provider.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceAccessConfig {

    // ---------------------- 在這註冊你的資料表存取服務 -------------------

    @Bean(name="asyncService")
    public IAsyncService asyncService(){
        return new AsyncService();
    }

    @Bean(name="productService")
    public IProductService productService(){
        return new ProductService();
    }

    @Bean(name="productClassService")
    public IProductClassService productClassService(){
        return new ProductClassService();
    }

    @Bean(name="vendorService")
    public IVendorService vendorService(){
        return new VendorService();
    }

    @Bean(name="orderService")
    public IOrderService orderService(){
        return new OrderService();
    }

    @Bean(name="memberService")
    public IMemberService memberService(){
        return new MemberService();
    }

    @Bean(name="orderDetailService")
    public IOrderDetailService orderDetailService(){
        return new OrderDetailService();
    }

    @Bean(name="mainClassService")
    public IMainClassService mainService(){
        return new MainClassService();
    }

    @Bean(name="activityService")
    public IActivityService activityService(){
        return new ActivityService();
    }

    @Bean(name="activityDetailService")
    public IActivityDetailService activityDetailService(){
        return new ActivityDetailService();
    }

    @Bean(name="cronService")
    public ICronService cronService(){
        return new CronService();
    }

    @Bean(name="statisActivityService")
    public IStatisActivityService staticActivityJoingBidService() {
        return new StatisActivityService();
    }
}
