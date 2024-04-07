package com.imbidgod.db.service.provider;

import com.imbidgod.db.entity.ProductClass;
import com.imbidgod.db.repository.IProductClassRepository;
import com.imbidgod.db.service.IProductClassService;
import com.imbidgod.db.service.tool.EntityService;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Component
@Service
public class ProductClassService extends EntityService<ProductClass, Long> implements IProductClassService {

    @Autowired(required = true)
    @Qualifier(value = "productClassRepository")
    protected IProductClassRepository repository;

    @Override
    public ProductClass createEntity(ProductClass entity) {
        try {
            entity.setUpdateDate(null);
            if (entity.getCreateDate() == null) {
                entity.setCreateDate(new Date());
            }
            ProductClass record = super.createEntity(entity);
            return record;
        }catch(Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }


    @Override
    public ProductClass updateEntity(Long id, ProductClass entity) {
        ProductClass dbEntity = getEntityById(id); // from db
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

        if(entity.getMainClassId() != null ){
            dbEntity.setMainClassId(entity.getMainClassId());
        }

        return super.updateEntity(id, dbEntity);
    }

    @Override
    public ProductClass getEntityByIdAndIsActive(long id, int isActive) {
        ProductClass entity = getRepository().findEntityByIdAndIsActive(id, isActive);
        if( entity != null ){
            return entity;
        }else{
            throw new com.imbidgod.db.exception.EntityNotFoundException(getChildsGenericClass().getSimpleName(), "id/isActive", Long.toString(id)+"/"+isActive );
        }
    }

    @Override
    public List<ProductClass> getEntitiesByIsActive(int isActive) {
        Iterable<ProductClass> entities = getRepository().findEntitiesByIsActive(isActive);
        if( entities != null ){
            return (List<ProductClass>)entities;
        }else{
            throw new com.imbidgod.db.exception.EntityNotFoundException(getChildsGenericClass().getSimpleName(), "isActive", Integer.toString(isActive) );
        }
    }

}
