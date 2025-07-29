package com.app.fragments.data.entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;

import java.io.Serializable;

@Entity(
        tableName = "xgp_caracteristica",
        primaryKeys = {"id_melhoramento", "id_caracteristica"},
        indices = {@Index(value = "id_caracteristica", unique = true)}
)
public class Caracteristica {

    @NonNull
    @ColumnInfo(name = "id_melhoramento")
    private Long idMelhoramento;

    @ColumnInfo(name = "id_caracteristica")
    @NonNull
    private Long idCaracteristica;

    @ColumnInfo(name = "descricao")
    private String descricao;

    @ColumnInfo(name = "sigla")
    private String sigla;

    @ColumnInfo(name = "tipo_dado")
    private String tipoDado;

    @ColumnInfo(name = "nota_inicial")
    private Integer notaInicial;

    @ColumnInfo(name = "nota_final")
    private Integer notaFinal;

    @ColumnInfo(name = "excessao")
    private String excessao;

    @ColumnInfo(name = "eh_observacao")
    private String ehObservacao;


    public Caracteristica() {
    }

    public Caracteristica(@NonNull Long idMelhoramento, @NonNull Long idCaracteristica, String descricao, String sigla, String tipoDado, Integer notaInicial, Integer notaFinal, String excessao, String ehObservacao) {
        this.idMelhoramento = idMelhoramento;
        this.idCaracteristica = idCaracteristica;
        this.descricao = descricao;
        this.sigla = sigla;
        this.tipoDado = tipoDado;
        this.notaInicial = notaInicial;
        this.notaFinal = notaFinal;
        this.excessao = excessao;
        this.ehObservacao = ehObservacao;
    }

    @NonNull
    public Long getIdMelhoramento() {
        return idMelhoramento;
    }

    public void setIdMelhoramento(@NonNull Long idMelhoramento) {
        this.idMelhoramento = idMelhoramento;
    }

    @NonNull
    public Long getIdCaracteristica() {
        return idCaracteristica;
    }

    public void setIdCaracteristica(@NonNull Long idCaracteristica) {
        this.idCaracteristica = idCaracteristica;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public String getTipoDado() {
        return tipoDado;
    }

    public void setTipoDado(String tipoDado) {
        this.tipoDado = tipoDado;
    }

    public Integer getNotaInicial() {
        return notaInicial;
    }

    public void setNotaInicial(Integer notaInicial) {
        this.notaInicial = notaInicial;
    }

    public Integer getNotaFinal() {
        return notaFinal;
    }

    public void setNotaFinal(Integer notaFinal) {
        this.notaFinal = notaFinal;
    }

    public String getExcessao() {
        return excessao;
    }

    public void setExcessao(String excessao) {
        this.excessao = excessao;
    }

    public String getEhObservacao() {
        return ehObservacao;
    }

    public void setEhObservacao(String ehObservacao) {
        this.ehObservacao = ehObservacao;
    }
}
