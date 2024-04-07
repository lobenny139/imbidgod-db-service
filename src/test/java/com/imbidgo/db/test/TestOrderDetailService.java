package com.imbidgo.db.test;

import com.imbidgod.db.entity.OrderDetail;
import com.imbidgod.db.entity.Product;
import com.imbidgod.db.service.IOrderDetailService;
import com.imbidgod.db.service.IProductService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = com.imbidgo.db.TestApplication.class)
@TestPropertySource(locations = "/test-application.properties")
@AutoConfigureTestDatabase(replace = NONE)
@Rollback(value = false)
public class TestOrderDetailService {

    @Autowired(required=true)
    @Qualifier("orderDetailService")
    private IOrderDetailService service;

    @Autowired(required=true)
    @Qualifier("productService")
    private IProductService productService;


    @Test
    public void testCreate() {
        Product product = productService.getEntityById("prod-20220716_2");
        OrderDetail entity = new OrderDetail();
        entity.setCreateBy("Admin");
        entity.setOrderId("ordererid123");
        entity.setProduct(product);
        entity.setProductCount(2);
        entity.setProductDiscountDesc("discount desc");
        entity.setProductDiscountPrice(10);
        entity.setCreateBy("Admin");
        System.out.println(service.createEntity(entity));
    }

    @Test
    public void testCreate2() {
        OrderDetail entity = new OrderDetail();
        entity.setCreateBy("Admin");
        entity.setOrderId("ordererid123");
        entity.setProductId("prod-20220724");
        entity.setProductCount(2);
        entity.setProductDiscountDesc("discount desc");
        entity.setProductDiscountPrice(990);
        entity.setCreateBy("Admin");
        System.out.println(service.createEntity(entity));
    }

    @Test
    public void testUpdate(){
        Long id = 3L;
        OrderDetail entity = new OrderDetail();
        entity.setProductId("prod-20220716_2");
        entity.setOrderId("ordererid123");
        entity.setProductCount(5);
        System.out.println(service.updateEntity(id, entity));
    }

    @Test
    public void testUpdate2(){
        Long id = 19L;
        OrderDetail entity = new OrderDetail();
        entity.setProductId("prod-20220716_2");
        entity.setOrderId("ordererid123");
        entity.setProductCount(5);
        System.out.println(service.updateEntity(id, entity));
    }

    @Test
    public void testDelete(){
        Long id = 19L;
        System.out.println(service.deleteEntity(id));
    }

}
