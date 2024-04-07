package com.imbidgo.db.test;

import com.imbidgod.db.entity.Order;
import com.imbidgod.db.service.IOrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;

import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = com.imbidgo.db.TestApplication.class)
@TestPropertySource(locations = "/test-application.properties")
@AutoConfigureTestDatabase(replace = NONE)
@Rollback(value = false)
public class TestOrderService {
    @Autowired(required=true)
    @Qualifier("orderService")
    private IOrderService service;

    @Test
    public void testCreate() {
        Order entity = new Order();
        entity.setCreateBy("Admin");
        entity.setOrderDesc("orderDesc");
        entity.setOrdererId(1);
        entity.setReceiverAddress("ReceiverAddress");
        entity.setId("ordererid123");
        entity.setCreateMethod(1);
        entity.setReceiverName("receivername");
        entity.setReceiverPhone("receiverPhone");
        entity.setStatus(2);
        System.out.println(service.createEntity(entity));
    }

    @Test
    public void testUpdate() {
        Order entity = new Order();
        String id = "ordererid123";
        entity.setStatus(1);
        System.out.println(service.updateEntity(id, entity));
    }

    @Test
    public void getPriceInfoByOrderId(){
        String id = "ordererid123";
        StringBuilder sql = new StringBuilder();
        sql.append("select sum(d.product_price-d.product_discount_price)*d.product_count as total, ");
        sql.append("       (sum(d.product_price-d.product_discount_price)*d.product_count)-m.discount_price actual ");
        sql.append("  from order_d d, order_m m ");
        sql.append(" where d.order_id = m.id ");
        sql.append("   and d.order_id='").append(id).append("' ");
        sql.append(" group by d.product_count");
        List<Map<String, Object>> results = service.getBySQLNoWrap(sql.toString());
        Map<String, Object> result = results.get(0);
        System.out.println(result.get("total"));
        System.out.println(result.get("actual"));
    }


    @Test
    public void TestUpdateOrderTotalOrderPriceAndTotalOrderActualPrice(){
        String id = "ordererid123";
        System.out.println(service.updateOrderTotalOrderPriceAndTotalOrderActualPrice(id));
    }
}
