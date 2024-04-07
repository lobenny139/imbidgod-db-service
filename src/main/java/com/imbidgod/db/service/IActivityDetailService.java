package com.imbidgod.db.service;

import com.imbidgod.db.entity.ActivityDetail;
import com.imbidgod.db.service.tool.IEntityService;

public interface IActivityDetailService  extends IEntityService<ActivityDetail, Long> {
    void updateMaxBidder(long activityId);

    void updateMinBidder(long activityId);

    void updateNoBidder(long activityId, int returnBidPercentage);

    Iterable<ActivityDetail> getEntitiesByActivityId(long activityId);

    Iterable<ActivityDetail> getEntitiesByHasGetBidder(long activityId);

}
