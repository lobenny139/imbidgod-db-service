package com.imbidgod.db.service.provider;

import com.imbidgod.db.entity.Order;
import com.imbidgod.db.repository.IOrderRepository;
import com.imbidgod.db.service.IMemberService;
import com.imbidgod.db.service.IOrderService;
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
public class OrderService extends EntityService<Order, String> implements IOrderService {
    @Autowired(required = true)
    @Qualifier(value = "orderRepository")
    protected IOrderRepository repository;

    @Autowired(required=true)
    @Qualifier("memberService")
    protected IMemberService memberService;

    @Override
    public boolean updateOrderTotalOrderPriceAndTotalOrderActualPrice(String id){
        try {
            getRepository().updateOrderTotalOrderPriceAndTotalOrderActualPrice(id);
            return true;
        }catch(Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }


    @Override
    public Order createEntity(Order entity) {
        try {
            entity.setUpdateDate(null);
            entity.setUpdateBy(null);
            if (entity.getCreateDate() == null) {
                entity.setCreateDate(new Date());
            }
            if(entity.getOrdererId() != 0){
                entity.setMember(getMemberService().getEntityById(entity.getOrdererId()));
            }
            return super.createEntity(entity);
        }catch(Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public Order updateEntity(String id, Order entity) {
        Order dbEntity = getEntityById(id); // from db
        dbEntity.setUpdateDate(new Date());

        if( entity.getOrdererId() != 0 && entity.getOrdererId() != dbEntity.getOrdererId() ){
            entity.setMember(getMemberService().getEntityById(entity.getOrdererId()));
        }

        if(!StringUtils.isBlank( entity.getReceiverName())){
            dbEntity.setReceiverName(entity.getReceiverName().trim());
        }
        if(!StringUtils.isBlank( entity.getReceiverAddress())){
            dbEntity.setReceiverAddress(entity.getReceiverAddress().trim());
        }
        if(!StringUtils.isBlank( entity.getReceiverPhone())){
            dbEntity.setReceiverPhone(entity.getReceiverPhone().trim());
        }
        if(!StringUtils.isBlank( entity.getOrderDesc())){
            dbEntity.setOrderDesc(entity.getOrderDesc().trim());
        }
        if(entity.getCreateMethod()  !=  dbEntity.getCreateMethod() ){
            dbEntity.setCreateMethod(entity.getCreateMethod());
        }
        if(entity.getStatus()  !=  dbEntity.getStatus() ){
            dbEntity.setStatus(entity.getStatus());
        }
        if(!StringUtils.isBlank( entity.getCreateBy())){
            dbEntity.setCreateBy(entity.getCreateBy().trim());
        }
        if(entity.getCreateDate()!= null){
            dbEntity.setCreateDate(entity.getCreateDate());
        }
        if(entity.getDiscountPrice()  !=  dbEntity.getDiscountPrice() ){
            dbEntity.setDiscountPrice(entity.getDiscountPrice());
        }

        return super.updateEntity(id, dbEntity);
    }

}
