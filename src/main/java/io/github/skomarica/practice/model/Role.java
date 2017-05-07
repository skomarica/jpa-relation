package io.github.skomarica.practice.model;

import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Sinisa Komarica
 */
@Entity
@EqualsAndHashCode(of = "name")
@ToString
@Table(name = "ac_role")
public class Role {

    @Id
    @GeneratedValue
    @Column
    protected Long id;

    @Column
    protected String name;

    @ElementCollection
    @CollectionTable(name = "ac_role_attribute", joinColumns = @JoinColumn(name = "ac_role_id", referencedColumnName = "id"))
    @Column(name = "ac_attribute_id")
    private Set<Long> attributeIds = new HashSet<>();

    @ManyToMany
    @JoinTable(name = "ac_role_attribute",
            joinColumns = @JoinColumn(name = "ac_role_id", referencedColumnName = "id",
                    insertable = false, updatable = false),
            inverseJoinColumns = @JoinColumn(name = "ac_attribute_id", referencedColumnName = "id",
                    insertable = false, updatable = false))
    private Set<Attribute> attributes = new HashSet<>();

    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL, orphanRemoval = true)
    protected Set<RolePermission> rolePermissions = new HashSet<>();

    public Role() {

    }

    public Role(Long id, String name, Set<Long> attributeIds, Set<Attribute> attributes, Set<RolePermission> rolePermissions) {
        this.id = id;
        this.name = name;
        this.attributeIds = attributeIds;
        this.rolePermissions = rolePermissions;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Set<Attribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(Set<Attribute> attributes) {
        this.attributes = attributes;
    }

    public Set<RolePermission> getRolePermissions() {
        return rolePermissions;
    }

    public void setRolePermissions(Set<RolePermission> rolePermissions) {
        this.rolePermissions.clear();
        if (rolePermissions != null) {
            rolePermissions.forEach(this::addPermission);
        }
    }

    public void addPermission(RolePermission rolePermission) {
        rolePermission.setRole(this);
        this.rolePermissions.add(rolePermission);
    }

}

