package com.example.spring_estoque.models;

import jakarta.persistence.*;
import org.springframework.hateoas.RepresentationModel;

import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "TB_ESTOQUE")
public class EstoqueModel extends RepresentationModel<EstoqueModel> implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id_estoque;

    private String nm_estoque;

    private String ds_estoque;

    public UUID getId_estoque() {
        return id_estoque;
    }

    public void setId_estoque(UUID id_estoque) {
        this.id_estoque = id_estoque;
    }

    public String getNm_estoque() {
        return nm_estoque;
    }

    public void setNm_estoque(String nm_estoque) {
        this.nm_estoque = nm_estoque;
    }

    public String getDs_estoque() {
        return ds_estoque;
    }

    public void setDs_estoque(String ds_estoque) {
        this.ds_estoque = ds_estoque;
    }
}
