package com.example.empresasjava.models;

import com.example.empresasjava.enums.SexEnum;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Entity
@Data
@ToString
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id_user;

    @NotNull(message = "Campo name não pode ser nulo")
    @NotEmpty(message = "Campo name não pode ser vazio")
    private String name;

    @NotNull(message = "Campo email não pode ser nulo")
    @NotEmpty(message = "Campo email não pode ser vazio")
    private String email;

    @NotNull(message = "Campo password não pode ser nulo")
    @NotEmpty(message = "Campo password não pode ser vazio")
    private String password;

    @NotNull(message = "Campo password não pode ser nulo")
    @NotEmpty(message = "Campo password não pode ser vazio")
    private String sex;

    @NotNull(message = "Campo legal_document não pode ser nulo")
    @NotEmpty(message = "Campo legal_document não pode ser vazio")
    private String legal_document;

    @OneToOne
    @JoinColumn(name = "address_id")
    private Address address;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private List<Role> roles;



    private Date createdAt;
    private Date deletedAt;

    public User(){}
    public User(String name,
                @NotNull(message = "Campo data não pode ser nulo")
                @NotEmpty(message = "Campo data não pode ser vazio")
                String email,
                @NotNull(message = "Campo data não pode ser nulo")
                @NotEmpty(message = "Campo data não pode ser vazio")
                String password,
                String legal_document,
                Address address,
                SexEnum sex,
                List<Role> roles
                ) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.sex = sex.getCode();
        this.legal_document = legal_document;
        this.address = address;
        this.roles = roles;
    }


}
