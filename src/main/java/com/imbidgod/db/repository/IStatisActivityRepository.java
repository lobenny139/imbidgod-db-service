package com.imbidgod.db.repository;

import com.imbidgod.db.entity.StatisActivity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository(value = "statisActivityRepository")
public interface IStatisActivityRepository extends JpaRepository<StatisActivity, Long> {

    @Modifying
    @Query( nativeQuery = true,
            value = "update statis_activity " +
                    "set joining_count = " +
                    "( " +
                    "select count(a.id) " +
                    "from activity_detail a " +
                    "where a.activity_id = ?2 " +
                    "and a.join_bid_coin >= ?3 " +
                    "and a.join_bid_coin <= ?4 " +
                    "), update_date = now(), update_by='Cron' " +
                    "where id = ?1 ; "
    )
    void updateJoingCount(long id, long activityId, int start, int end);

    @Query("SELECT entity FROM StatisActivity entity WHERE entity.activityId= ?1")
    Iterable<StatisActivity> findEntinitiesByActivityId(long activityId);


}
