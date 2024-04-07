package com.imbidgod.db.service;

import com.imbidgod.db.entity.Activity;
import com.imbidgod.db.entity.StatisActivity;
import com.imbidgod.db.service.tool.IEntityService;

public interface IStatisActivityService extends IEntityService<StatisActivity, Long>{
    void createEntinities(Activity activity);

    Iterable<StatisActivity> getEntinitiesByActivityId(long activityId);

    void updateJoingCount(long id, long activityId, int start, int end);

}
