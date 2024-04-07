package com.imbidgo.db.test;

import com.imbidgod.db.entity.Member;
import com.imbidgod.db.service.IMemberService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Calendar;

import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = com.imbidgo.db.TestApplication.class)
@TestPropertySource(locations = "/test-application.properties")
@AutoConfigureTestDatabase(replace = NONE)
@Rollback(value = false)
public class TestMemberService {
    @Autowired(required = true)
    @Qualifier("memberService")
    private IMemberService service;

    @Test
    public void testCreate() {
        Member entity = new Member();
        entity.setName("Jack");
        entity.setCreateBy("Admin");
        entity.setCreateDate(Calendar.getInstance().getTime());
        System.out.println(service.createEntity(entity));
    }

    @Test
    public void testGetById() {
        Long memberId = 1L;
        Member entity = service.getEntityById(memberId);
        System.out.println(entity);
    }
}
