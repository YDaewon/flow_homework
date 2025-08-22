package com.homework.homework.extension.service;

import java.util.List;

import org.springframework.stereotype.Service;

import static com.homework.homework.common.response.BaseResponseStatus.*;
import com.homework.homework.common.response.BusinessException;
import com.homework.homework.extension.entity.Extension;
import com.homework.homework.extension.entity.ExtensionType;
import com.homework.homework.extension.entity.dto.CustomExtensionDto;
import com.homework.homework.extension.entity.dto.ToggleActiveDto;
import com.homework.homework.extension.repository.ExtensionRepository;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Service
public class ExtensionService {
    private final ExtensionRepository extensionRepository;

    @Transactional(readOnly = true)
    public List<Extension> getAllExtensions() {
        return extensionRepository.findAll();
    }

    @Transactional
    public Extension toggleExtension(ToggleActiveDto dto) {
        Extension extension = getExtensionByName(dto.name());
        
        if(extension.getType() == ExtensionType.CUSTOM) throw new BusinessException(BAD_REQUEST);
        
        extension.toggleActive(dto.active());

        return extension;
    }

    @Transactional
    public Extension createExtension(CustomExtensionDto dto) {

        extensionRepository.findByName(dto.name())
        .ifPresent(e -> {
            throw new BusinessException(BAD_REQUEST);
        });

        Extension extension = Extension.from(dto);

        return extensionRepository.save(extension);
    }

    @Transactional
    public Extension deleteExtension(CustomExtensionDto dto) {
        Extension extension = getExtensionByName(dto.name());
        
        if(extension.getType() != ExtensionType.CUSTOM) throw new BusinessException(BAD_REQUEST);
        
        extensionRepository.delete(extension);
        
        return extension;
    }

    private Extension getExtensionByName(String name) {
        return extensionRepository.findByName(name)
                .orElseThrow(() -> new BusinessException(NOT_FOUND));
    }
}
