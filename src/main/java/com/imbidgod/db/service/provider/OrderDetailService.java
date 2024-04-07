package com.imbidgod.db.service.provider;

import com.imbidgod.asyncService.IAsyncService;
import com.imbidgod.db.entity.OrderDetail;
import com.imbidgod.db.entity.Product;
import com.imbidgod.db.exception.DataException;
import com.imbidgod.db.repository.IOrderDetailRepository;
import com.imbidgod.db.service.IOrderDetailService;
import com.imbidgod.db.service.IOrderService;
import com.imbidgod.db.service.IProductService;
import com.imbidgod.db.service.tool.EntityService;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Getter
@Setter
@Component
@Service
public class OrderDetailService extends EntityService<OrderDetail, Long> implements IOrderDetailService {
    @Autowired(required = true)
    @Qualifier(value = "orderDetailRepository")
    protected IOrderDetailRepository repository;

//    @Autowired(required = true)
//    @Qualifier(value = "orderRepository")
//    protected IOrderRepository orderRepository;

    @Autowired(required=true)
    @Qualifier("productService")
    protected IProductService productService;

    @Autowired(required=true)
    @Qualifier("orderService")
    protected IOrderService orderService;

    @Autowired(required=true)
    @Qualifier("asyncService")
    protected IAsyncService asyncService;

    protected Product getProductById(String id){
        return getProductService().getEntityById(id);
    }

    @Override
    @Transactional
    public OrderDetail createEntity(OrderDetail entity) {
        try {
            entity.setUpdateDate(null);
            if (entity.getCreateDate() == null) {
                entity.setCreateDate(new Date());
            }
            // setup Product
            if(entity.getProduct() == null && entity.getProductId() == null){
                throw new DataException("Product Can't be empty.");
            }
            if(entity.getProduct() == null && entity.getProductId() != null){
                entity.setProduct(this.getProductById(entity.getProductId()));
            }
            entity.setProductPrice(entity.getProduct().getPrice());

            //create OrderDetail
            OrderDetail orderDetail = super.createEntity(entity);

            //update Order
            getAsyncService().reCalcOrderPrice(
                    getOrderService(), entity.getOrderId()
            );

            return orderDetail;
        }catch(Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    @Transactional
    public OrderDetail updateEntity(Long id, OrderDetail entity) {
        OrderDetail dbEntity = getEntityById(id); // from db
        dbEntity.setUpdateDate(new Date());

        boolean updateOrder = false;

        // update Product and productPrice and recalcuate Order price and actual price
        if(!StringUtils.isBlank( entity.getProductId())){
            Product product = this.getProductById(entity.getProductId());
            dbEntity.setProduct(product );
            dbEntity.setProductPrice(product.getPrice());
            updateOrder = true;
        }
        if(entity.getProduct() != null){
            dbEntity.setProductPrice(entity.getProduct().getPrice());
            dbEntity.setProduct(entity.getProduct() );
            updateOrder = true;
        }

        if(!StringUtils.isBlank( entity.getOrderId())){
            dbEntity.setOrderId(entity.getOrderId().trim());
        }

        if(entity.getProductCount()  !=  dbEntity.getProductCount() ){
            dbEntity.setProductCount(entity.getProductCount());
            updateOrder = true;
        }

        if(entity.getProductDiscountPrice()  !=  dbEntity.getProductDiscountPrice() ){
            dbEntity.setProductDiscountPrice(entity.getProductDiscountPrice());
            updateOrder = true;
        }

        if(!StringUtils.isBlank( entity.getProductDiscountDesc())){
            dbEntity.setProductDiscountDesc(entity.getProductDiscountDesc().trim());
        }

        if(!StringUtils.isBlank( entity.getUpdateBy())){
            dbEntity.setUpdateBy(entity.getUpdateBy().trim());
        }

        OrderDetail orderDetail = super.updateEntity(id, dbEntity);

        if(updateOrder){
            //update Order
            getAsyncService().reCalcOrderPrice(
                    getOrderService(), entity.getOrderId()
            );
        }

        return orderDetail;
    }

    @Override
    @Transactional
    public boolean deleteEntity(Long id){
        try {
            OrderDetail entity = getEntityById(id);
            String orderId = entity.getOrderId();
            super.deleteEntity(id);
            //update Order
            getAsyncService().reCalcOrderPrice(
                    getOrderService(), orderId
            );
            return true;
        }catch(Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public Iterable<OrderDetail> getEntitiesByOrderId(String orderId){
        return getRepository().findEntitiesByOrderId(orderId);
    }

}
