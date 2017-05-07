package io.github.skomarica.practice.service;

import io.github.skomarica.practice.dto.RoleCreateUpdateDto;
import io.github.skomarica.practice.model.Role;
import io.github.skomarica.practice.repo.RoleRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Sinisa Komarica
 */
@Service
public class RoleService {

    private static final Logger logger = LoggerFactory.getLogger(RoleService.class);

    private final RoleRepository roleRepository;

    protected final ModelMapper modelMapper;

    public RoleService(RoleRepository roleRepository, ModelMapper modelMapper) {
        this.roleRepository = roleRepository;
        this.modelMapper = modelMapper;
    }

    @Transactional(readOnly = true)
    public List<Role> findAll() {
        return roleRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Role find(Long id) {
        return roleRepository.findOne(id);
    }

    @Transactional
    public void create(RoleCreateUpdateDto dto) {
        final Role role = toEntityFromCreateDto(dto);
        roleRepository.save(role);
    }

    @Transactional
    public void update(Long id, RoleCreateUpdateDto dto) {
        final Role role = roleRepository.findOne(id);
        toEntityFromUpdateDto(dto, role);
    }

    @Transactional
    public void delete(Long id) {
        roleRepository.delete(id);
    }

    public Role toEntityFromCreateDto(RoleCreateUpdateDto dto) {
        logger.debug("Mapping Create Dto {} to a new entity...", dto);
        Role result = modelMapper.map(dto, Role.class);
        logger.debug("Create Dto {} mapped to entity: {}", dto, result);
        return result;
    }

    public void toEntityFromUpdateDto(RoleCreateUpdateDto dto, Role role) {
        logger.debug("Mapping Update Dto {} to an existing entity: {}...", dto, role);
        modelMapper.map(dto, role);
        logger.debug("Update Dto {} mapped to entity: {}", dto, role);
    }


}
