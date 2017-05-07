package io.github.skomarica.practice.dto;

import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author Sinisa Komarica
 */
@EqualsAndHashCode
@ToString
public class RolePermissionCreateUpdateDto {

    private Long permissionId;

    private Boolean denied;

    public RolePermissionCreateUpdateDto() {
    }

    public RolePermissionCreateUpdateDto(Long permissionId, Boolean denied) {
        this.permissionId = permissionId;
        this.denied = denied;
    }

    public Long getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Long permissionId) {
        this.permissionId = permissionId;
    }

    public Boolean getDenied() {
        return denied;
    }

    public void setDenied(Boolean denied) {
        this.denied = denied;
    }
}
