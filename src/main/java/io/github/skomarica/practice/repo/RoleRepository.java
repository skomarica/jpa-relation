package io.github.skomarica.practice.repo;

import io.github.skomarica.practice.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Sinisa Komarica
 */
public interface RoleRepository extends JpaRepository<Role, Long> {
}
