package com.imbidgod.db.service.provider;

import com.imbidgod.db.entity.Member;
import com.imbidgod.db.repository.IMemberRepository;
import com.imbidgod.db.service.IMemberService;
import com.imbidgod.db.service.tool.EntityService;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Date;

@Getter
@Setter
@Component
@Service
public class MemberService extends EntityService<Member, Long> implements IMemberService {
    @Autowired(required = true)
    @Qualifier(value = "memberRepository")
    protected IMemberRepository repository;

    @Autowired
    private PasswordEncoder bcryptEncoder;

    @Override
    public Member createEntity(Member entity) {
        try {
            entity.setUpdateDate(null);
            if (entity.getCreateDate() == null) {
                entity.setCreateDate(new Date());
            }
            entity.setPassword(bcryptEncoder.encode(entity.getPassword()));
            return super.createEntity(entity);
        }catch(Exception e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Override
    public Member updateEntity(Long id, Member entity) {
        Member dbEntity = getEntityById(id); // from db
        dbEntity.setUpdateDate(new Date());

        if(!StringUtils.isBlank(entity.getUpdateBy())){
            dbEntity.setUpdateBy(entity.getUpdateBy());
        }

        if(!StringUtils.isBlank(entity.getAccount())){
            dbEntity.setAccount(entity.getAccount());
        }

        if(!StringUtils.isBlank(entity.getPassword())){
            dbEntity.setPassword( bcryptEncoder.encode(entity.getPassword()) );
        }

        if(entity.getIsActive()  !=  dbEntity.getIsActive() ){
            dbEntity.setIsActive(entity.getIsActive());
        }

        if(!StringUtils.isBlank( entity.getName())){
            dbEntity.setName(entity.getName().trim());
        }

        if(!StringUtils.isBlank(entity.getMobile())){
            dbEntity.setMobile(entity.getMobile());
        }

        if(!StringUtils.isBlank(entity.getPhone())){
            dbEntity.setPhone(entity.getPhone());
        }

        if(!StringUtils.isBlank(entity.getAddress())){
            dbEntity.setAddress(entity.getAddress());
        }

        if(entity.getLevel()  !=  dbEntity.getLevel() ){
            dbEntity.setLevel(entity.getLevel());
        }

        return super.updateEntity(id, dbEntity);
    }

    @Override
    public Member getEntityByAccount(String account){
        return getRepository().findEntityByAccount(account);
    }

}
