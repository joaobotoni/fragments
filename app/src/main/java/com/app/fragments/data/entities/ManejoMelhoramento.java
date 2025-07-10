package com.app.fragments.data.entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(
        tableName = "xgp_manejo_melhoramento",
        foreignKeys = {
                @ForeignKey(
                        entity = Melhoramento.class,
                        parentColumns = "id_melhoramento",
                        childColumns = "id_melhoramento",
                        onDelete = ForeignKey.CASCADE
                ),
                @ForeignKey(
                        entity = Caracteristica.class,
                        parentColumns = {"id_melhoramento", "id_caracteristica"},
                        childColumns = {"id_melhoramento", "id_caracteristica"},
                        onDelete = ForeignKey.CASCADE
                ),
                @ForeignKey(
                        entity = Observacao.class,
                        parentColumns = {"id_melhoramento", "id_observacao"},
                        childColumns = {"id_melhoramento", "id_observacao"},
                        onDelete = ForeignKey.SET_NULL
                )
        }
)
public class ManejoMelhoramento {

    @NonNull
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id_manejo_melhoramento")
    private Long idManejoMelhoramento;

    @NonNull
    @ColumnInfo(name = "id_melhoramento")
    private Long idMelhoramento;

    @ColumnInfo(name = "id_caracteristica")
    private Long idCaracteristica;

    @ColumnInfo(name = "id_observacao")
    private Long idObservacao;

    @ColumnInfo(name = "nota")
    private Integer nota;

    @ColumnInfo(name = "excessao")
    private String excessao;

    @ColumnInfo(name = "observacao")
    private String observacao;

    public ManejoMelhoramento() {
    }

    public ManejoMelhoramento(Long idManejoMelhoramento, Long idMelhoramento, Long idCaracteristica,
                              Long idObservacao, Integer nota, String excessao, String observacao) {
        this.idManejoMelhoramento = idManejoMelhoramento;
        this.idMelhoramento = idMelhoramento;
        this.idCaracteristica = idCaracteristica;
        this.idObservacao = idObservacao;
        this.nota = nota;
        this.excessao = excessao;
        this.observacao = observacao;
    }

    public Long getIdManejoMelhoramento() {
        return idManejoMelhoramento;
    }

    public void setIdManejoMelhoramento(Long idManejoMelhoramento) {
        this.idManejoMelhoramento = idManejoMelhoramento;
    }

    public Long getIdMelhoramento() {
        return idMelhoramento;
    }

    public void setIdMelhoramento(Long idMelhoramento) {
        this.idMelhoramento = idMelhoramento;
    }

    public Long getIdCaracteristica() {
        return idCaracteristica;
    }

    public void setIdCaracteristica(Long idCaracteristica) {
        this.idCaracteristica = idCaracteristica;
    }

    public Long getIdObservacao() {
        return idObservacao;
    }

    public void setIdObservacao(Long idObservacao) {
        this.idObservacao = idObservacao;
    }

    public Integer getNota() {
        return nota;
    }

    public void setNota(Integer nota) {
        this.nota = nota;
    }

    public String getExcessao() {
        return excessao;
    }

    public void setExcessao(String excessao) {
        this.excessao = excessao;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }
}
