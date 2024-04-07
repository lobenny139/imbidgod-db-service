package com.imbidgo.db.test;

import com.imbidgod.db.entity.StatisActivity;
import com.imbidgod.db.service.IStatisActivityService;
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
public class TestStatisActivityService {

    @Autowired(required=true)
    @Qualifier("statisActivityService")
    public IStatisActivityService service;

    @Test
    public void testGetEntinitiesByActivityId(){
        Iterable<StatisActivity> entinities = service.getEntinitiesByActivityId( 1L );
    }
}
