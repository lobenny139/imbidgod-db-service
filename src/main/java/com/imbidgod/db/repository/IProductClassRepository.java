package com.imbidgod.db.repository;

import com.imbidgod.db.entity.ProductClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository(value = "productClassRepository")
public interface IProductClassRepository extends JpaRepository<ProductClass, Long> {

    @Query("SELECT entity FROM ProductClass entity WHERE entity.id = ?1 and entity.isActive= ?2")
    ProductClass findEntityByIdAndIsActive(long id, int isActive);

    @Query("SELECT entity FROM ProductClass entity WHERE entity.isActive= ?1 ORDER BY entity.createDate desc")
    Iterable<ProductClass> findEntitiesByIsActive(int isActive);


}
