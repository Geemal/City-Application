package com.kuehne.city.entity;


import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;

@Entity
public class Role implements GrantedAuthority {

    @Id
    @SequenceGenerator(
            name = "role_seq",
            sequenceName = "role_seq"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "role_seq"
    )
    private Integer roleSeq;
    private String roleName;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "user_seq",
            referencedColumnName = "userSeq"
    )
    private User user;

    public Integer getRoleSeq() {
        return roleSeq;
    }

    public void setRoleSeq(Integer roleSeq) {
        this.roleSeq = roleSeq;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Override
    public String getAuthority() {
        return roleName;
    }
}
