package com.imbidgod.db.repository;

import com.imbidgod.db.entity.ActivityDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository(value = "activityDetailRepository")
public interface IActivityDetailRepository  extends JpaRepository<ActivityDetail, Long> {

    @Modifying
    @Query( nativeQuery = true,
            value = "update activity_detail set has_get_bid = 0, return_bid_coin = (?2*join_bid_coin)/100 where activity_id=?1 "
    )
    void setNoBidder(long activityId, int returnBidPercentage);


    @Modifying
    @Query( nativeQuery = true,
            value = "update activity_detail a,( " +
                    "select activity_id, max(coin) maxcoin, count from ( " +
                    "select activity_id, " +
                    "join_bid_coin as coin, " +
                    "count(join_bid_coin) as count " +
                    "from activity_detail " +
                    "where activity_id=?1 " +
                    "group by join_bid_coin, activity_id) as result " +
                    "where count = 1 " +
                    "group by activity_id, count " +
                    ") as b " +
                    "set a.has_get_bid = 100, " +
                    "a.return_bid_coin = 0, a.update_date = now(), a.update_By = 'Cron Job' " +
                    "where a.activity_id = b.activity_id " +
                    "and a.activity_id=?1 " +
                    "and a.join_bid_coin = b.maxcoin "+
                    "and a.has_get_bid = 0 ; "
    )
    void setMaxBidder(long activityId);

    @Modifying
    @Query( nativeQuery = true,
            value = "update activity_detail a,( " +
                    "select activity_id, min(coin) mincoin, count from ( " +
                    "select activity_id, " +
                    "join_bid_coin as coin, " +
                    "count(join_bid_coin) as count " +
                    "from activity_detail " +
                    "where activity_id=?1 " +
                    "group by join_bid_coin, activity_id) as result " +
                    "where count = 1 " +
                    "group by activity_id, count " +
                    ") as b " +
                    "set a.has_get_bid = 1, " +
                    "a.return_bid_coin = 0, a.update_date = now(), a.update_By = 'Cron Job' " +
                    "where a.activity_id = b.activity_id " +
                    "and a.activity_id=?1 " +
                    "and a.join_bid_coin = b.mincoin " +
                    "and a.has_get_bid = 0 ; "
    )
    void setMinBidder(long activityId);

    @Query("SELECT entity FROM ActivityDetail entity WHERE entity.activityId=?1 order by entity.id  ")
    Iterable<ActivityDetail> findEntitiesByActivityId(long activityId);

    @Query("SELECT entity FROM ActivityDetail entity WHERE entity.activityId=?1 and entity.hasGetBid !=0 order by entity.hasGetBid  ")
    Iterable<ActivityDetail> findEntitiesByHasGetBidder(long activityId);

}
