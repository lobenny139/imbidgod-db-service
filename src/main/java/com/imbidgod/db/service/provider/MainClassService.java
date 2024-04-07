package com.imbidgod.db.service.provider;

import com.imbidgod.db.entity.MainClass;
import com.imbidgod.db.repository.IMainClassRepository;
import com.imbidgod.db.service.IMainClassService;
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
public class MainClassService extends EntityService<MainClass, Long> implements IMainClassService {
    @Autowired(required = true)
    @Qualifier(value = "mainClassRepository")
    protected IMainClassRepository repository;

    @Override
    public MainClass createEntity(MainClass entity) {
        try {
            entity.setUpdateDate(null);
            if (entity.getCreateDate() == null) {
                entity.setCreateDate(new Date());
            }
            return  super.createEntity(entity);
        }catch(Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public MainClass updateEntity(Long id, MainClass entity) {
        MainClass dbEntity = getEntityById(id); // from db
        dbEntity.setUpdateDate(new Date());
        if(!StringUtils.isBlank( entity.getUpdateBy())){
            dbEntity.setUpdateBy(entity.getUpdateBy().trim());
        }

        if(!StringUtils.isBlank( entity.getName())){
            dbEntity.setName(entity.getName().trim());
        }

        if(entity.getIsActive()  !=  dbEntity.getIsActive() ){
            dbEntity.setIsActive(entity.getIsActive());
        }
        return super.updateEntity(id, dbEntity);
    }
}
