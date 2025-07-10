package com.app.fragments.data.entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;

@Entity(tableName = "xgp_observacao", primaryKeys = {"id_melhoramento", "id_observacao"},
        indices = {@Index(value = "id_observacao", unique = true)})
public class Observacao {

    @NonNull
    @ColumnInfo(name = "id_melhoramento")
    private Long idMelhoramento;

    @NonNull
    @ColumnInfo(name = "id_observacao")
    private Long idObservacao;

    @ColumnInfo(name = "sigla")
    private String sigla;

    @ColumnInfo(name = "descricao")
    private String descricao;

    public Observacao() {
    }

    public Observacao(Long idMelhoramento, Long idObservacao, String sigla, String descricao) {
        this.idMelhoramento = idMelhoramento;
        this.idObservacao = idObservacao;
        this.sigla = sigla;
        this.descricao = descricao;
    }

    public Long getIdMelhoramento() {
        return idMelhoramento;
    }

    public void setIdMelhoramento(Long idMelhoramento) {
        this.idMelhoramento = idMelhoramento;
    }

    public Long getIdObservacao() {
        return idObservacao;
    }

    public void setIdObservacao(Long idObservacao) {
        this.idObservacao = idObservacao;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
