package com.relief.domain.services.interfaces;

import com.relief.domain.models.IncrementCode;

public interface IncrementCodeService {
    IncrementCode getById(Long id);

    String save(String code);

    IncrementCode getByCode(String code);
}
