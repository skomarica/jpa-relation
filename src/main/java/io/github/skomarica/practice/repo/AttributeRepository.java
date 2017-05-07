package io.github.skomarica.practice.repo;

import io.github.skomarica.practice.model.Attribute;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Sinisa Komarica
 */
public interface AttributeRepository extends JpaRepository<Attribute, Long> {
}
