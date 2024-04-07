package com.imbidgod.db.repository;

import com.imbidgod.db.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository(value = "orderRepository")
public interface IOrderRepository  extends JpaRepository<Order, String> {


    @Modifying
    @Query( nativeQuery = true,
            value = "update order_m m, " +
                    "( " +
                    "select sum(d.product_price-d.product_discount_price)*d.product_count as totalOrderPrice," +
                    "(sum(d.product_price-d.product_discount_price)*d.product_count) - m.discount_price as totalOrderActualPrice " +
                    "from order_d d, order_m m  " +
                    "where d.order_id = m.id " +
                    "and d.order_id = ?1 " +
                    "group by d.product_count ) n " +
                    "set m.total_order_price=n.totalOrderPrice," +
                    "m.total_order_actual_price=n.totalOrderActualPrice " +
                    "where m.id = ?1")
    void updateOrderTotalOrderPriceAndTotalOrderActualPrice(String orderId);

}
