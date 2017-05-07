package io.github.skomarica.practice.dto;

import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Set;

/**
 * @author Sinisa Komarica
 */
@EqualsAndHashCode
@ToString
public class RoleCreateUpdateDto {

    private String name;

    private Set<Long> attributeIds;

    private Set<RolePermissionCreateUpdateDto> rolePermissions;

    public RoleCreateUpdateDto() {
    }

    public RoleCreateUpdateDto(String name, Set<Long> attributeIds, Set<RolePermissionCreateUpdateDto> rolePermissions) {
        this.name = name;
        this.attributeIds = attributeIds;
        this.rolePermissions = rolePermissions;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Long> getAttributeIds() {
        return attributeIds;
    }

    public void setAttributeIds(Set<Long> attributeIds) {
        this.attributeIds = attributeIds;
    }

    public Set<RolePermissionCreateUpdateDto> getRolePermissions() {
        return rolePermissions;
    }

    public void setRolePermissions(Set<RolePermissionCreateUpdateDto> rolePermissions) {
        this.rolePermissions = rolePermissions;
    }
}
