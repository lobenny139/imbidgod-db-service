package com.imbidgod.db.service.provider;

import com.imbidgod.asyncService.IAsyncService;
import com.imbidgod.db.entity.Activity;
import com.imbidgod.db.repository.IActivityRepository;
import com.imbidgod.db.service.IActivityService;
import com.imbidgod.db.service.IProductService;
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
public class ActivityService extends EntityService<Activity, Long> implements IActivityService {
    @Autowired(required = true)
    @Qualifier(value = "activityRepository")
    protected IActivityRepository repository;

    @Autowired(required=true)
    @Qualifier("productService")
    protected IProductService productService;

    @Autowired(required=true)
    @Qualifier("asyncService")
    private IAsyncService asyncService;

    @Override
    public Activity createEntity(Activity entity) {
        try {
            entity.setUpdateDate(null);
            if (entity.getCreateDate() == null) {
                entity.setCreateDate(new Date());
            }
            entity.setProduct( getProductService().getEntityById(entity.getBidProductId()) );

            entity = super.createEntity(entity);

            getAsyncService().InitStaticActivityJoingBid(entity);

            return  entity;
        }catch(Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public Activity updateEntity(Long id, Activity entity) {
        Activity dbEntity = getEntityById(id); // from db
        dbEntity.setUpdateDate(new Date());
        if(entity.getCanBid() != dbEntity.getCanBid()){
            dbEntity.setCanBid(entity.getCanBid());
        }

        if(!StringUtils.isBlank( entity.getUpdateBy())){
            dbEntity.setUpdateBy(entity.getUpdateBy().trim());
        }

        if(!StringUtils.isBlank( entity.getName())){
            dbEntity.setName(entity.getName().trim());
        }

        if(entity.getIsActive()  !=  dbEntity.getIsActive() ){
            dbEntity.setIsActive(entity.getIsActive());
        }

        if(entity.getMinBidCoin()  !=  dbEntity.getMinBidCoin() ){
            dbEntity.setMinBidCoin(entity.getMinBidCoin());
        }

        if(entity.getMaxBidCoin()  !=  dbEntity.getMaxBidCoin() ){
            dbEntity.setMaxBidCoin(entity.getMaxBidCoin());
        }

        if(entity.getStopBidCoinCount() != dbEntity.getStopBidCoinCount()){
            dbEntity.setStopBidCoinCount(entity.getStopBidCoinCount());
        }

        if(entity.getReturnBidPercentage()  !=  dbEntity.getReturnBidPercentage() ){
            dbEntity.setReturnBidPercentage(entity.getReturnBidPercentage());
        }

        if(entity.getBidStartDate() != null && entity.getBidStartDate().getTime() != dbEntity.getBidStartDate().getTime() ){
            dbEntity.setBidStartDate(entity.getBidStartDate());
        }

        if(entity.getBidEndDate() != null && entity.getBidEndDate().getTime() != dbEntity.getBidEndDate().getTime() ){
            dbEntity.setBidEndDate(entity.getBidEndDate());
        }

        if(!StringUtils.isBlank( entity.getBidProductId())){
            dbEntity.setProduct(getProductService().getEntityById( entity.getBidProductId()));
        }

        return super.updateEntity(id, dbEntity);
    }

    @Override
    public Iterable<Activity> getActivateEntities(int isActive, int canBid){
        return getRepository().findEntitiesByActivateAndCanBid(isActive, canBid);
    }

    public void updateInfo(Long id){
        getRepository().updateInfo(id);;
    }

}
