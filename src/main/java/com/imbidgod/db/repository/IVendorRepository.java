package com.imbidgod.db.repository;

import com.imbidgod.db.entity.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository(value = "vendorRepository")
public interface IVendorRepository extends JpaRepository<Vendor, Long> {
}
