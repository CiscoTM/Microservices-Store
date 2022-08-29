package org.tk3dv.store.customer.repository.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@Entity
@Table(name = "tbl_customers")
public class Customer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @NotEmpty(message = "El número del documento no puede estar vacio")
    @Size(min = 8, max = 8,message = "Debe tener un mínimo de 8 carácteres")
    @Column(name = "number_id",unique = true,length = 8,nullable = false)
    private String numberId;

    @NotEmpty(message = "Debe introducirse un nombre")
    @Column(name = "first_name",nullable = false)
    private String firstName;

    @NotEmpty(message = "Debe introducirse un apellido")
    @Column(name = "last_name",nullable = false)
    private String lastName;

    @NotEmpty(message = "Debe introducirse un correo")
    @Email(message = "Dirección de correo no valida")
    @Column(unique = true,nullable = false)
    private String email;

    @Column(name = "photo_url")
    private String photoUrl;

    @NotNull(message = "Debe introducir una region")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "region_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
    private Region region;

    private String state;


}
