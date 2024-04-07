package com.imbidgod.db.repository;

import com.imbidgod.db.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository(value = "productRepository")
public interface IProductRepository extends JpaRepository<Product, String> {
    @Query(nativeQuery = true, value = "select product.* from product, order_d where order_d.order_id = ?1 and product.id = order_d.product_id")
    Iterable<Product> findEntitiesByOrderId(String orderId);
}
