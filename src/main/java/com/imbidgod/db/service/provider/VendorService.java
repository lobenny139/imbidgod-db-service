package com.imbidgod.db.service.provider;

import com.imbidgod.db.entity.Vendor;
import com.imbidgod.db.repository.IVendorRepository;
import com.imbidgod.db.service.IVendorService;
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
public class VendorService extends EntityService<Vendor, Long> implements IVendorService {

    @Autowired(required = true)
    @Qualifier(value = "vendorRepository")
    protected IVendorRepository repository;

    @Override
    public Vendor createEntity(Vendor entity) {
        try {
            entity.setUpdateDate(null);
            if (entity.getCreateDate() == null) {
                entity.setCreateDate(new Date());
            }
            return super.createEntity(entity);
        }catch(Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }


    @Override
    public Vendor updateEntity(Long id, Vendor entity) {
        Vendor dbEntity = getEntityById(id); // from db
        dbEntity.setUpdateDate(new Date());

        if(!StringUtils.isBlank(entity.getUpdateBy())){
            dbEntity.setUpdateBy(entity.getUpdateBy());
        }

        if(!StringUtils.isBlank(entity.getName())){
            dbEntity.setName(entity.getName());
        }

        if(entity.getIsActive()  !=  dbEntity.getIsActive() ){
            dbEntity.setIsActive(entity.getIsActive());
        }

        if(!StringUtils.isBlank(entity.getPhone1())){
            dbEntity.setPhone1(entity.getPhone1());
        }

        if(!StringUtils.isBlank(entity.getPhone2())){
            dbEntity.setPhone2(entity.getPhone2());
        }

        if(!StringUtils.isBlank(entity.getAddress1())){
            dbEntity.setAddress1(entity.getAddress1());
        }

        if(!StringUtils.isBlank(entity.getAddress2())){
            dbEntity.setAddress2(entity.getAddress2());
        }

        if(!StringUtils.isBlank(entity.getRemark())){
            dbEntity.setRemark(entity.getRemark());
        }

        return super.updateEntity(id, dbEntity);
    }

}
