package io.github.skomarica.practice.repo;

import io.github.skomarica.practice.model.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Sinisa Komarica
 */
public interface PermissionRepository extends JpaRepository<Permission, Long> {
}
