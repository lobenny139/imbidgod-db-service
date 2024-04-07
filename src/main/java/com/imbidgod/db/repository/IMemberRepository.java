package com.imbidgod.db.repository;

import com.imbidgod.db.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository(value = "memberRepository")
public interface IMemberRepository extends JpaRepository<Member, Long> {
    @Query("SELECT entity FROM Member entity WHERE entity.account= ?1 and isActive >= 0")
    Member findEntityByAccount(String account);
}
