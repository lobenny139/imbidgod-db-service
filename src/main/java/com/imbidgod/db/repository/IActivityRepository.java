package com.imbidgod.db.repository;

import com.imbidgod.db.entity.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository(value = "activityRepository")
public interface IActivityRepository extends JpaRepository<Activity, Long> {
    @Query("SELECT entity FROM Activity entity WHERE entity.isActive=?1 and entity.canBid=?2 ")
    Iterable<Activity> findEntitiesByActivateAndCanBid(int isActive, int canBid);

    @Modifying
    @Query( nativeQuery = true,
            value = "update activity a, " +
                    "(select activity_id, " +
                    "count(*) joinTotalMemberCount, " +
                    "sum(join_bid_coin) joinTotalCoinCount, " +
                    "sum(return_bid_coin) totalReturnBidCount " +
                    "from activity_detail " +
                    "where activity_id=?1 " +
                    "group by activity_id) as b " +
                    "set " +
                    "a.join_total_member_count=b.joinTotalMemberCount, " +
                    "a.join_total_coin_count=b.joinTotalCoinCount," +
                    "a.total_return_bid_count=b.totalReturnBidCount, " +
                    "a.update_date=now()," +
                    "a.update_by='Cron Job' " +
                    "where a.id=b.activity_id " +
                    "and a.id=?1 ;"
    )
    void updateInfo(long activityId);

}
