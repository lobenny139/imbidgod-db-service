package com.imbidgo.db.test;

import com.imbidgod.db.entity.MainClass;
import com.imbidgod.db.service.IMainClassService;
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
public class TestMainClassService {
    @Autowired(required = true)
    @Qualifier("mainClassService")
    private IMainClassService service;

    @Test
    public void testCreate() {
        MainClass entity = new MainClass();
        entity.setName("3C");
        entity.setIsActive(1);
        entity.setCreateBy("Admin");
        System.out.println(service.createEntity(entity));
    }

    @Test
    public void testGetById(){
        long mainClassId = 1L;
        MainClass entity = service.getEntityById(mainClassId);
        System.out.println(entity);
    }
}
