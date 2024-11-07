package com.relief.domain.services;

import com.relief.domain.models.IncrementCode;
import com.relief.domain.services.interfaces.IncrementCodeService;
import com.relief.infrastructure.repositories.IncrementCodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class IncrementCodeServiceImpl implements IncrementCodeService {
    @Autowired
    private IncrementCodeRepository incrementCodeRepository;

    @Override
    public IncrementCode getById(Long id) {
        return this.incrementCodeRepository.getReferenceById(id);
    }

    @Override
    public String save(String code) {
        IncrementCode incrementCode = this.getByCode(code);
        if (incrementCode == null) {
            incrementCode = new IncrementCode(code);
        } else {
            incrementCode.setIncrement(incrementCode.getIncrement() + 1);
        }

        this.incrementCodeRepository.save(incrementCode);

        return incrementCode.getIncrement() > 0 ?
                incrementCode.getCode() + incrementCode.getIncrement() : incrementCode.getCode();
    }

    @Override
    public IncrementCode getByCode(String code) {
        return this.incrementCodeRepository.getByCode(code);
    }
}
