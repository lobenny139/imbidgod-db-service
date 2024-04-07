package com.imbidgod.db.service;

import com.imbidgod.db.entity.Member;
import com.imbidgod.db.service.tool.IEntityService;

public interface IMemberService extends IEntityService<Member, Long> {
    public Member getEntityByAccount(String account);
}
