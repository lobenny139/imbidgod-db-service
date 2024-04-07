package com.imbidgo.db.test;

import com.imbidgod.db.entity.ProductClass;
import com.imbidgod.db.service.IProductClassService;
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

import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = com.imbidgo.db.TestApplication.class)
@TestPropertySource(locations = "/test-application.properties")
@AutoConfigureTestDatabase(replace = NONE)
@Rollback(value = false)

public class TestProductClassService {
    @Autowired(required=true)
    @Qualifier("productClassService")
    private IProductClassService service;

    @Test
    public void testCreate() {
        ProductClass entity = new ProductClass();
        entity.setName("IPhone");
        entity.setIsActive(1);
        entity.setCreateBy("Admin");
        entity.setMainClassId(1L);
        System.out.println(service.createEntity(entity));
    }

    @Test
    public void testCreate2() {
        ProductClass entity = new ProductClass();
        entity.setName("汽車");
        entity.setIsActive(1);
        entity.setCreateBy("Admin");
        System.out.println(service.createEntity(entity));
    }

    @Test
    public void testUpdate(){
        ProductClass entity = new ProductClass();
        long id = 1L;
        entity.setIsActive(1);
        entity.setUpdateBy("Admin");
        System.out.println(service.updateEntity(id, entity));
    }

    @Test
    public void testGetById(){
        long productClassId = 1L;
        ProductClass entity = service.getEntityById(productClassId);
        System.out.println(entity);
    }

    @Test
    public void testGetEntityByIdAndIsActive(){
        long productClassId = 2L;
        int isActive = 1;
        ProductClass entity = service.getEntityByIdAndIsActive(productClassId, isActive);
        System.out.println(entity);
    }

    @Test
    public void testGetEntitiesByIsActive(){
        int isActive = 1;
        List<ProductClass> entities = (List<ProductClass>)service.getEntitiesByIsActive(isActive);
        System.out.println(entities.size());
        System.out.println(entities);
    }

    @Test
    public void testGetEntitiesBySQL(){
        String sql = "select * from product_class"; //pure sql
        List<ProductClass> entities = (List<ProductClass>)service.getEntitiesBySQL(sql);
        System.out.println(entities.size());
        System.out.println(entities);
    }

    @Test
    public void testUpdate2(){
        ProductClass entity = new ProductClass();
        long id = 1L;
        entity.setIsActive(1);
        entity.setUpdateBy("Admin");
        System.out.println(service.updateEntity(id, entity));
    }

}
