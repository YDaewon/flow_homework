package com.homework.homework.extension.service;

import java.util.List;

import com.homework.homework.extension.entity.Extension;
import com.homework.homework.extension.entity.dto.CustomExtensionDto;
import com.homework.homework.extension.entity.dto.ToggleActiveDto;
import org.springframework.transaction.annotation.Transactional;



/**
 * 파일 확장자 관리 서비스 인터페이스.
 * <p>
 * - 고정 확장자 활성/비활성 토글<br>
 * - 커스텀 확장자 추가/삭제<br>
 * - 전체 확장자 조회
 * </p>
 */
public interface ExtensionService {

    /**
     * 등록된 전체 확장자를 조회합니다.
     *
     * @return 전체 확장자 리스트
     */
    @Transactional(readOnly = true)
    public List<Extension> getAllExtensions();

    /**
     * 고정 확장자의 활성/비활성 상태를 변경합니다.
     *
     * @param dto 토글할 확장자 DTO
     * @return 변경된 확장자 엔티티
     */
    @Transactional
    public Extension toggleExtension(ToggleActiveDto dto);

    /**
     * 새로운 커스텀 확장자를 생성합니다.
     *
     * @param dto 추가할 확장자 DTO
     * @return 생성된 확장자 엔티티
     */
    @Transactional
    public Extension createExtension(CustomExtensionDto dto);

    /**
     * 커스텀 확장자를 삭제합니다.
     *
     * @param dto 삭제할 확장자 DTO
     * @return 삭제된 확장자 엔티티
     */
    @Transactional
    public Extension deleteExtension(CustomExtensionDto dto);
}
