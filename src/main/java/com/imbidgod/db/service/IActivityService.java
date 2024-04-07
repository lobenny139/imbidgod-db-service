package com.imbidgod.db.service;

import com.imbidgod.db.entity.Activity;
import com.imbidgod.db.service.tool.IEntityService;

public interface IActivityService extends IEntityService<Activity, Long> {

    public Iterable<Activity> getActivateEntities(int isActive, int canBid);

    public void updateInfo(Long id);

}
