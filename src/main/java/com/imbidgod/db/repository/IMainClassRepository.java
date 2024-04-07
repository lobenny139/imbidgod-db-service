package com.imbidgod.db.repository;

import com.imbidgod.db.entity.MainClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository(value = "mainClassRepository")
public interface IMainClassRepository extends JpaRepository<MainClass, Long> {
}
