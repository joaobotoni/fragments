package com.app.fragments.data.entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(
        tableName = "xgp_manejo_melhoramento",
        foreignKeys = {
                @ForeignKey(
                        entity = Melhoramento.class,
                        parentColumns = "id_melhoramento",
                        childColumns = "id_melhoramento",
                        onDelete = ForeignKey.SET_NULL
                ),
                @ForeignKey(
                        entity = Caracteristica.class,
                        parentColumns = {"id_melhoramento", "id_caracteristica"},
                        childColumns = {"id_melhoramento", "id_caracteristica"},
                        onDelete = ForeignKey.SET_NULL
                )
        },
        indices = {
                @Index(value = {"id_melhoramento"}),
                @Index(value = {"id_caracteristica", "id_melhoramento"}),
        }
)
public class ManejoMelhoramento implements Serializable {

    @NonNull
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_manejo_melhoramento")
    private Long idManejoMelhoramento;

    @NonNull
    @ColumnInfo(name = "id_melhoramento")
    private Long idMelhoramento;

    @ColumnInfo(name = "id_caracteristica")
    private Long idCaracteristica;

    @ColumnInfo(name = "valor")
    private String value;

    @ColumnInfo(name = "excessao")
    private String excessao;

    @ColumnInfo(name = "observacao")
    private String observacao;

    public ManejoMelhoramento() {
    }

    public ManejoMelhoramento(@NonNull Long idMelhoramento, Long idCaracteristica, String value, String excessao, String observacao) {
        this.idMelhoramento = idMelhoramento;
        this.idCaracteristica = idCaracteristica;
        this.value = value;
        this.excessao = excessao;
        this.observacao = observacao;
    }
    @NonNull
    public Long getIdManejoMelhoramento() { return idManejoMelhoramento; }

    @NonNull
    public Long getIdMelhoramento() { return idMelhoramento; }

    public Long getIdCaracteristica() { return idCaracteristica; }


    public String getValue() {
        return value;
    }

    public String getExcessao() { return excessao; }

    public String getObservacao() { return observacao; }

    public void setIdManejoMelhoramento(@NonNull Long idManejoMelhoramento) {
        this.idManejoMelhoramento = idManejoMelhoramento;
    }

    public void setIdMelhoramento(@NonNull Long idMelhoramento) {
        this.idMelhoramento = idMelhoramento;
    }

    public void setIdCaracteristica(Long idCaracteristica) {
        this.idCaracteristica = idCaracteristica;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setExcessao(String excessao) {
        this.excessao = excessao;
    }

    public void setObservacao(String observacao) { this.observacao = observacao; }
}