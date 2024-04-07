package com.imbidgod.db.service.provider;

import com.imbidgod.db.entity.Activity;
import com.imbidgod.db.entity.ActivityDetail;
import com.imbidgod.db.repository.IActivityDetailRepository;
import com.imbidgod.db.service.IActivityDetailService;
import com.imbidgod.db.service.IActivityService;
import com.imbidgod.db.service.IMemberService;
import com.imbidgod.db.service.tool.EntityService;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Date;

@Getter
@Setter
@Component
@Service
public class ActivityDetailService  extends EntityService<ActivityDetail, Long> implements IActivityDetailService {
    @Autowired(required = true)
    @Qualifier(value = "activityDetailRepository")
    protected IActivityDetailRepository repository;

    @Autowired(required=true)
    @Qualifier("memberService")
    protected IMemberService memberService;

    @Autowired(required=true)
    @Qualifier("activityService")
    protected IActivityService activityService;

    @Override
    public ActivityDetail createEntity(ActivityDetail entity) {
        try {
            Activity activity = getActivityService().getEntityById(entity.getActivityId());

            entity.setUpdateDate(null);
            if (entity.getCreateDate() == null) {
                entity.setCreateDate(new Date());
            }
            if(entity.getJoinMemberId() != 0){
                entity.setMember( getMemberService().getEntityById(entity.getJoinMemberId() ));
            }
            entity.setReturnBidCoin( (activity.getReturnBidPercentage()* entity.getJoinBidCoin() )/100);
            return super.createEntity(entity);
        }catch(Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public ActivityDetail updateEntity(Long id, ActivityDetail entity) {
        ActivityDetail dbEntity = getEntityById(id); // from db
        dbEntity.setUpdateDate(new Date());

        if(!StringUtils.isBlank(entity.getUpdateBy())){
            dbEntity.setUpdateBy(entity.getUpdateBy());
        }

        if(entity.getJoinBidCoin()  !=  dbEntity.getJoinBidCoin() ){
            dbEntity.setJoinBidCoin(entity.getJoinBidCoin());
        }

        if(entity.getReturnBidCoin()  !=  dbEntity.getReturnBidCoin() ){
            dbEntity.setReturnBidCoin(entity.getReturnBidCoin());
        }

        return super.updateEntity(id, dbEntity);
    }

    @Override
    public void updateMaxBidder(long activityId){
        getRepository().setMaxBidder(activityId);
    }

    @Override
    public void updateMinBidder(long activityId){
        getRepository().setMinBidder(activityId);
    }

    @Override
    public void updateNoBidder(long activityId, int returnBidPercentage){
        getRepository().setNoBidder(activityId, returnBidPercentage);
    }

    @Override
    public Iterable<ActivityDetail> getEntitiesByActivityId(long activityId){
        return getRepository().findEntitiesByActivityId(activityId);
    }

    @Override
    public Iterable<ActivityDetail> getEntitiesByHasGetBidder(long activityId){
        return getRepository().findEntitiesByHasGetBidder(activityId);
    }

}
