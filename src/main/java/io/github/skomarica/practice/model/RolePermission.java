package io.github.skomarica.practice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Sinisa Komarica
 */
@Entity
@EqualsAndHashCode(of = {"role", "permissionId"})
@ToString(of = {"permissionId", "denied"})
@Table(name = "ac_role_permission")
public class RolePermission implements Serializable {

    @Id
    @GeneratedValue
    @Column
    protected Long id;

    @ManyToOne
    @JoinColumn(name = "ac_role_id")
    @JsonIgnore
    private Role role;

    @Column(name = "ac_permission_id")
    private Long permissionId;

    @ManyToOne
    @JoinColumn(name = "ac_permission_id", insertable = false, updatable = false)
    private Permission permission;

    @Column(name = "is_denied")
    private boolean denied;

    public RolePermission() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Long getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Long permissionId) {
        this.permissionId = permissionId;
    }

    public Permission getPermission() {
        return permission;
    }

    public void setPermission(Permission permission) {
        this.permission = permission;
    }

    public boolean isDenied() {
        return denied;
    }

    public void setDenied(boolean denied) {
        this.denied = denied;
    }
}
