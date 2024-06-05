package com.kidzmyujikku.sekolah.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kidzmyujikku.sekolah.models.ERole;
import com.kidzmyujikku.sekolah.models.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
  Optional<Role> findByName(ERole name);
}
