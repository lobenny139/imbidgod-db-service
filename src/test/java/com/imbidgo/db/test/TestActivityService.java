package com.imbidgo.db.test;

import com.imbidgod.db.entity.Activity;
import com.imbidgod.db.service.IActivityService;
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
public class TestActivityService {
    @Autowired(required = true)
    @Qualifier("activityService")
    private IActivityService service;

    @Test
    public void TestGetEntityById(){
        Activity activity = service.getEntityById(1L);
        System.out.println(activity.getMaxBidCoin());//999

        System.out.println(activity.getMinBidCoin());//11
        System.out.println(activity.getStatisLevel());//10

        int max = activity.getMaxBidCoin();
        int min = activity.getMinBidCoin();
        int level = activity.getStatisLevel();
        int levelCount =  (max - min) / level;

        System.out.println(levelCount);

        int levelMax = 0;
        int levelMin = 0;
        for(int i = 0; i < level; i++){
            levelMin = i*levelCount + min;
            if(i == level-1){
                levelMax = max;
            }else{
                levelMax = (i+1) * levelCount + min - 1;
            }
            System.out.println(i + ": " + levelMin + "~" + levelMax);
        }

        //(999-11)/10 =98
        // 0*98+11~1*98+11-1 -> 11~108
        // 1*98+11~2*98+11-1 -> 109~206
        // 2*98+11~3*98+11-1 -> 207~304
        // 3*98+11~4*98+11-1 -> 305~402
        // 4*98+11~5*98+11-1 -> 403~500
        // 5*98+11~6*98+11-1 -> 501~598
        // 6*98+11~7*98+11-1 -> 599~696
        // 7*98+11~8*98+11-1 -> 697~794
        // 8*98+11~9*98+11-1 -> 795~892
        // 9*98+11~10*98+11-1 -> 893~991




    }


}
