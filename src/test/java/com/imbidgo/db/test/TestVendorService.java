package com.imbidgo.db.test;

import com.imbidgod.db.entity.Vendor;
import com.imbidgod.db.service.IVendorService;
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
public class TestVendorService {
    @Autowired(required=true)
    @Qualifier("vendorService")
    private IVendorService service;

    @Test
    public void testCreate() {
        Vendor entity = new Vendor();
        entity.setName("Sơ ri(宏碁)");
        entity.setPhone1("02-1234567");
        entity.setAddress1("台北市中山區民生東路二段");
        entity.setCreateBy("Admin");
        System.out.println(service.createEntity(entity));
    }

    @Test
    public void testCreate2() {
        Vendor entity = new Vendor();
        entity.setName("BMW");
        entity.setPhone1("+86-24-2345678");
        entity.setAddress1("台北市中山區民生東路二段");
        entity.setCreateBy("Admin");
        System.out.println(service.createEntity(entity));
    }


    @Test
    public void testGetById(){
        long id = 1L;
        Vendor entity = service.getEntityById(id);
        System.out.println(entity);
        System.out.println(entity.getProducts());
    }

    @Test
    public void testUpdate(){
        long id = 1L;
        Vendor entity = new Vendor();
        entity.setIsActive(1);
        entity.setUpdateBy("Admin");
        System.out.println(service.updateEntity(id, entity));
    }



}
