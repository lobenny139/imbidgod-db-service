package com.imbidgod.cronService.provider;

import com.imbidgod.cronService.ICronService;
import com.imbidgod.db.entity.Activity;
import com.imbidgod.db.entity.StatisActivity;
import com.imbidgod.db.service.IActivityDetailService;
import com.imbidgod.db.service.IActivityService;
import com.imbidgod.db.service.IStatisActivityService;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.collections4.IterableUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Getter
@Setter
@Component
@Service
public class CronService implements ICronService {

    private static Logger logger = LoggerFactory.getLogger(CronService.class);

    @Autowired(required=true)
    @Qualifier("activityService")
    private IActivityService activityService;

    @Autowired(required=true)
    @Qualifier("activityDetailService")
    private IActivityDetailService activityDetailService;

    @Autowired(required=true)
    @Qualifier("statisActivityService")
    private IStatisActivityService statisActivityService;

    @Scheduled(fixedDelay = 60000) // evert 60 seconds
    @Transactional
    @Override
    public void reCalcuateInfo() {
        logger.info(">>>>> 準備取得進行中且可競標的活動");
        List<Activity> activities = IterableUtils.toList(
                                        getActivityService().getActivateEntities(1, 1)
                                    );
        long startTime = System.currentTimeMillis();
        for(Activity activity : activities){

            logger.info(">>> 準備計算返還多少代幣給參與會員@[活動名稱=" + activity.getName() + "/活動ID=" + activity.getId());
            getActivityDetailService().updateNoBidder(activity.getId(), activity.getReturnBidPercentage());
            logger.info(">>> 成功計算返還多少代幣給參與會員@[活動名稱=" + activity.getName() + "/活動ID" + activity.getId() );

            logger.info(">>> 準備計算誰是最高標@[活動名稱=" + activity.getName() + "/活動ID=" + activity.getId() );
            getActivityDetailService().updateMaxBidder(activity.getId());
            logger.info(">>> 成功計算誰是" +
                    "最高標@[活動名稱=" + activity.getName() + "/活動ID=" + activity.getId() );

            logger.info(">>> 準備計算誰是最低標[活動名稱=" + activity.getName() + "/活動ID=" + activity.getId());
            getActivityDetailService().updateMinBidder(activity.getId());
            logger.info(">>> 成功計算誰是最低標[活動名稱=" + activity.getName() + "/活動ID=" + activity.getId());

            logger.info(">>> 準備重新計算競標資訊[活動名稱=" + activity.getName() + "/活動ID=" + activity.getId() );
            getActivityService().updateInfo(activity.getId());
            logger.info(">>> 成功重新計算競標資訊[活動名稱=" + activity.getName() + "/活動ID=" + activity.getId() );

            logger.info(">>> 準備重新統計競標資訊[活動名稱=" + activity.getName() + "/活動ID=" + activity.getId() );
            Iterable<StatisActivity> staticActivities = getStatisActivityService().getEntinitiesByActivityId(activity.getId());
            for(StatisActivity statisActivity:staticActivities){
                getStatisActivityService().updateJoingCount(statisActivity.getId(), activity.getId(), statisActivity.getStatisStart(), statisActivity.getStatisEnd());
            }
            logger.info(">>> 成功重新統計競標資訊[活動名稱=" + activity.getName() + "/活動ID=" + activity.getId() );

        }
        long endTime = System.currentTimeMillis();
        logger.info(">>>>> 成功已重新整理進行中且可競標的活動, 耗時:" + (endTime - startTime) + "豪秒\"");
    }
}
