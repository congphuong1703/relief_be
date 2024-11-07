package com.relief.infrastructure.repositories;

import com.relief.domain.models.IncrementCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IncrementCodeRepository extends JpaRepository<IncrementCode, Long> {

    IncrementCode getByCode(String code);
}
