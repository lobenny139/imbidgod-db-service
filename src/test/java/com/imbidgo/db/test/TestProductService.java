package com.imbidgo.db.test;

import com.imbidgod.db.entity.Product;
import com.imbidgod.db.exception.EntityNotFoundException;
import com.imbidgod.db.service.IProductService;
import org.apache.commons.lang3.RandomStringUtils;
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
public class TestProductService {

    @Autowired(required=true)
    @Qualifier("productService")
    private IProductService service;

    @Test
    public void testCreate() {
        String prodId = "prod-20220716_1";
        Product entity = new Product();
        entity.setId(prodId);
        entity.setName("áo sơ mi(襯衫)");
        entity.setSerialProductId("prod-20220716");
        entity.setPackageingSizeUnit("CM");
        entity.setPackageingSizeLength(100);
        entity.setPackageingSizeWidth(100);
        entity.setPackageingSizeHight(100);
        entity.setOnSaleUnit("sau");
        entity.setInStoreCount(99);
        entity.setSafetyCount(10);
        entity.setPrice(1000000);
        entity.setCost(900000);
        entity.setProductDesc("áo sơ mi nam giản dị(男性休閒襯衫)");
        entity.setIsActive(1);
        entity.setPropertyName1("màu sắc(顏色)");
        entity.setPropertyValue1("màu xanh da trời(藍色)");
        entity.setPropertyName2("kích thước(呎吋)");
        entity.setPropertyValue2("cỡ M(M号)");
        entity.setCreateBy("Admin");

        entity.setProductClassId(1L);
        entity.setVendorId(1L);

        System.out.println(service.createEntity(entity));
    }


    @Test
    public void testCreate2() {
        String prodId = "prod-20220716_2";
        Product entity = new Product();
        entity.setId(prodId);
        entity.setName("áo sơ mi(襯衫)");
        entity.setSerialProductId("prod-20220716");
        entity.setPackageingSizeUnit("CM");
        entity.setPackageingSizeLength(100);
        entity.setPackageingSizeWidth(100);
        entity.setPackageingSizeHight(100);
        entity.setOnSaleUnit("sau");
        entity.setInStoreCount(80);
        entity.setSafetyCount(10);
        entity.setPrice(1000000);
        entity.setCost(900000);
        entity.setProductDesc("áo sơ mi nam giản dị(男性休閒襯衫)");
        entity.setIsActive(1);
        entity.setPropertyName1("màu sắc(顏色)");
        entity.setPropertyValue1("mTrắng(白色)");
        entity.setPropertyName2("kích thước(呎吋)");
        entity.setPropertyValue2("Kích thước L(ML号)");
        entity.setCreateBy("Benny");

        entity.setProductClassId(1L);
        entity.setVendorId(1L);

        System.out.println(service.createEntity(entity));
    }

    @Test
    public void testCreate3() {
        String prodId = "prod-20220724";
        Product entity = new Product();
        entity.setId(prodId);
        entity.setName("Camry 2.0");
        entity.setSerialProductId("prod-20220724");
        entity.setPackageingSizeUnit("MM");
        entity.setPackageingSizeLength(4885);
        entity.setPackageingSizeWidth(1840);
        entity.setPackageingSizeHight(1445);
        entity.setOnSaleUnit("sau");
        entity.setInStoreCount(2);
//        entity.setSafetyCount(10);
        entity.setPrice(990000);
        entity.setCost(1050000);
        entity.setProductDesc("áCamry 汽油尊爵");
        entity.setIsActive(1);
        entity.setPropertyName1("màu sắc(顏色)");
        entity.setPropertyValue1("mTrắng(白色)");
        entity.setCreateBy("Admin");

        entity.setProductClassId(2L);
        entity.setVendorId(2L);

        System.out.println(service.createEntity(entity));
    }

    @Test
    public void testUpdate(){
        String id = "prod-20220716_2";
        Product entity = new Product();
        entity.setIsActive(1);
        entity.setUpdateBy("Admin");
        System.out.println(service.updateEntity(id, entity));
    }

    @Test
    public void TestGetEntitiesByOrderId(){
        String id = "ordererid123";
        System.out.println(service.getEntitiesByOrderId(id));
    }

    @Test
    public void TestGetEntityById(){
        try{
            System.out.println(service.getEntityById("a"));
        }catch(EntityNotFoundException e){
            System.out.println("---------------->");
        }
    }

    @Test
    public void TestRandomString(){
        String generatedString = RandomStringUtils.randomAlphanumeric(20);
        System.out.println(generatedString);
    }
}
