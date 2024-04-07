package com.imbidgod.db.service.provider;

import com.imbidgod.db.entity.Activity;
import com.imbidgod.db.entity.StatisActivity;
import com.imbidgod.db.repository.IStatisActivityRepository;
import com.imbidgod.db.service.IStatisActivityService;
import com.imbidgod.db.service.tool.EntityService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;

@Getter
@Setter
@Component
@Service
public class StatisActivityService extends EntityService<StatisActivity, Long> implements IStatisActivityService {
    @Autowired(required = true)
    @Qualifier(value = "statisActivityRepository")
    protected IStatisActivityRepository repository;

    @Override
    public void createEntinities(Activity activity){
        try{
            int max = activity.getMaxBidCoin();
            int min = activity.getMinBidCoin();
            int level = activity.getStatisLevel();
            int levelCount =  (max - min) / level;
            Date createDate = new Date();
            int levelMax = 0;
            int levelMin = 0;
            StatisActivity[] entinities = new StatisActivity[level];
            for(int i = 0; i < level; i++){
                entinities[i] = new StatisActivity();
                entinities[i].setCreateDate(createDate);
                entinities[i].setCreateBy("Init");
                levelMin = i*levelCount + min;
                if(i == level-1){
                    levelMax = max;
                }else{
                    levelMax = (i+1) * levelCount + min - 1;
                }
                entinities[i].setStatisLevel(i+1);
                entinities[i].setStatisStart(levelMin);
                entinities[i].setStatisEnd(levelMax);
                entinities[i].setActivityId(activity.getId());
            }
            getRepository().saveAll( Arrays.asList(entinities));
        }catch(Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public Iterable<StatisActivity> getEntinitiesByActivityId(long activityId){
        return getRepository().findEntinitiesByActivityId(activityId);
    }


    @Override
    public void updateJoingCount(long id, long activityId, int start, int end){
        getRepository().updateJoingCount(id, activityId, start, end);
    }
}
