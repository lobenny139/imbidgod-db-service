package com.imbidgod.db.repository;

import com.imbidgod.db.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository(value = "orderDetailRepository")
public interface IOrderDetailRepository extends JpaRepository<OrderDetail, Long> {

    @Query("SELECT entity FROM OrderDetail entity WHERE entity.orderId= ?1 ORDER BY entity.createDate desc")
    Iterable<OrderDetail> findEntitiesByOrderId(String orderId);

}
