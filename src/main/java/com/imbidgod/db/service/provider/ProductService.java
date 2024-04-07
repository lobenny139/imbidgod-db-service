package com.imbidgod.db.service.provider;

import com.imbidgod.db.entity.Product;
import com.imbidgod.db.repository.IProductRepository;
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
public class ProductService extends EntityService<Product, String> implements IProductService {

    @Autowired(required = true)
    @Qualifier(value = "productRepository")
    protected IProductRepository repository;

    @Override
    public Iterable<Product> getEntitiesByOrderId(String orderId){
        return getRepository().findEntitiesByOrderId(orderId);
    }

    @Override
    public Product createEntity(Product entity) {
        try {
            entity.setUpdateDate(null);
            entity.setUpdateBy(null);
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
    public Product updateEntity(String id, Product entity) {
        Product dbEntity = getEntityById(id);
        dbEntity.setUpdateDate(new Date());
        if(!StringUtils.isBlank( entity.getUpdateBy())){
            dbEntity.setUpdateBy(entity.getUpdateBy().trim());
        }
        if(!StringUtils.isBlank(entity.getName())){
            dbEntity.setName(entity.getName().trim());
        }
        if(!StringUtils.isBlank(entity.getSerialProductId())){
            dbEntity.setSerialProductId(entity.getSerialProductId().trim());
        }
        if( entity.getProductClassId() != null  ){
            dbEntity.setProductClassId(entity.getProductClassId());
        }
        if( entity.getVendorId() != null){
            dbEntity.setVendorId(entity.getVendorId());
        }
        if(!StringUtils.isBlank( entity.getPackageingSizeUnit())){
            dbEntity.setPackageingSizeUnit(entity.getPackageingSizeUnit().trim());
        }
        if(entity.getPackageingSizeLength()  !=  dbEntity.getPackageingSizeLength() ){
            dbEntity.setPackageingSizeLength(entity.getPackageingSizeLength());
        }
        if(entity.getPackageingSizeWidth()  !=  dbEntity.getPackageingSizeWidth() ){
            dbEntity.setPackageingSizeWidth(entity.getPackageingSizeWidth());
        }
        if(entity.getPackageingSizeHight()  !=  dbEntity.getPackageingSizeHight() ){
            dbEntity.setPackageingSizeHight(entity.getPackageingSizeHight());
        }
        if(!StringUtils.isBlank( entity.getOnSaleUnit())){
            dbEntity.setOnSaleUnit(entity.getOnSaleUnit().trim());
        }
        if(entity.getInStoreCount()  !=  dbEntity.getInStoreCount() ){
            dbEntity.setInStoreCount(entity.getInStoreCount());
        }
        if(entity.getSafetyCount()  !=  dbEntity.getSafetyCount() ){
            dbEntity.setSafetyCount(entity.getSafetyCount());
        }
        if(entity.getPrice()  !=  dbEntity.getPrice() ){
            dbEntity.setPrice(entity.getPrice());
        }
        if(entity.getCost()  !=  dbEntity.getCost() ){
            dbEntity.setCost(entity.getCost());
        }
        if(!StringUtils.isBlank( entity.getProductDesc())){
            dbEntity.setProductDesc(entity.getProductDesc().trim());
        }
        if(entity.getIsActive()  !=  dbEntity.getIsActive() ){
            dbEntity.setIsActive(entity.getIsActive());
        }
        if(!StringUtils.isBlank( entity.getPropertyName1())){
            dbEntity.setPropertyName1(entity.getPropertyName1().trim());
        }
        if(!StringUtils.isBlank(entity.getPropertyName2())){
            dbEntity.setPropertyName2(entity.getPropertyName2().trim());
        }
        if(!StringUtils.isBlank(entity.getPropertyName3())){
            dbEntity.setPropertyName3(entity.getPropertyName3().trim());
        }
        if(!StringUtils.isBlank( entity.getPropertyName4())){
            dbEntity.setPropertyName4(entity.getPropertyName4().trim());
        }
        if(!StringUtils.isBlank( entity.getPropertyName5() )){
            dbEntity.setPropertyName5(entity.getPropertyName5().trim());
        }
        if(!StringUtils.isBlank( entity.getPropertyValue1())){
            dbEntity.setPropertyValue1(entity.getPropertyValue1().trim());
        }
        if(!StringUtils.isBlank( entity.getPropertyValue2())){
            dbEntity.setPropertyValue2(entity.getPropertyValue2().trim());
        }
        if(!StringUtils.isBlank( entity.getPropertyValue3())){
            dbEntity.setPropertyValue3(entity.getPropertyValue3().trim());
        }
        if(!StringUtils.isBlank( entity.getPropertyValue4())){
            dbEntity.setPropertyValue4(entity.getPropertyValue4().trim());
        }
        if(!StringUtils.isBlank( entity.getPropertyValue5())){
            dbEntity.setPropertyValue5(entity.getPropertyValue5().trim());
        }

        if(!StringUtils.isBlank( entity.getCurrency())){
            dbEntity.setCurrency(entity.getCurrency().trim());
        }

        return super.updateEntity(id, dbEntity);
    }

}
