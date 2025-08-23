package com.homework.homework.extension.service;

import static com.homework.homework.common.response.BaseResponseStatus.*;

import java.util.List;

import org.springframework.stereotype.Service;

import com.homework.homework.common.exception.BusinessException;
import com.homework.homework.common.util.ExtNormalizer;
import com.homework.homework.extension.entity.Extension;
import com.homework.homework.extension.entity.ExtensionType;
import com.homework.homework.extension.entity.dto.CustomExtensionDto;
import com.homework.homework.extension.entity.dto.ToggleActiveDto;
import com.homework.homework.extension.repository.ExtensionRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Service
@Slf4j
public class ExtensionServiceImpl implements ExtensionService {
    
    private final ExtensionRepository extensionRepository;
    private final ExtNormalizer extNormalizer;

    @Override
    public List<Extension> getAllExtensions() {
        return extensionRepository.findAll();
    }

    @Override
    public Extension toggleExtension(ToggleActiveDto dto) {
        Extension extension = getExtensionByName(dto.name());
        
        if(extension.getType() == ExtensionType.CUSTOM) throw new BusinessException(CUSTOM_NOT_CHANGED);
        
        extension.toggleActive(dto.active());

        return extension;
    }

    @Override
    public Extension createExtension(CustomExtensionDto dto) {
        String normalizedExt = extNormalizer.checkExtension(dto.name());
        
        if (normalizedExt == null) {
            throw new BusinessException(NOT_NORMAL_NAME);
        }

        extensionRepository.findByName(normalizedExt)
        .ifPresent(e -> {
            throw new BusinessException(EXTENSION_ALREADY_EXISTS);
        });

        long customCount = extensionRepository.countByType(ExtensionType.CUSTOM);
        if (customCount >= 200) {
            throw new BusinessException(CUSTOM_LIMIT_EXCEEDED);
        }

        Extension extension = Extension.from(normalizedExt);

        return extensionRepository.save(extension);
    }

    @Override
    public Extension deleteExtension(CustomExtensionDto dto) {
        Extension extension = getExtensionByName(dto.name());
        
        if(extension.getType() != ExtensionType.CUSTOM) throw new BusinessException(ONLY_CUSTOM_DELETE);
        
        extensionRepository.delete(extension);
        
        return extension;
    }

    private Extension getExtensionByName(String name) {
        return extensionRepository.findByName(name)
                .orElseThrow(() -> new BusinessException(EXTENSION_NOT_FOUND));
    }
}
