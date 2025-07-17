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
public class ManejoMelhoramento  {

    @NonNull
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id_manejo_melhoramento")
    private Long idManejoMelhoramento;

    @NonNull
    @ColumnInfo(name = "id_melhoramento")
    private Long idMelhoramento;

    @ColumnInfo(name = "id_caracteristica")
    private Long idCaracteristica;

    @ColumnInfo(name = "nota")
    private Integer nota;

    @ColumnInfo(name = "excessao")
    private String excessao;

    public ManejoMelhoramento() {
    }

    public ManejoMelhoramento(@NonNull Long idManejoMelhoramento, @NonNull Long idMelhoramento, Long idCaracteristica, Integer nota, String excessao) {
        this.idManejoMelhoramento = idManejoMelhoramento;
        this.idMelhoramento = idMelhoramento;
        this.idCaracteristica = idCaracteristica;

        this.nota = nota;
        this.excessao = excessao;
    }

    public ManejoMelhoramento(Builder builder) {
        this.idManejoMelhoramento = builder.idManejoMelhoramento;
        this.idMelhoramento = builder.idMelhoramento;
        this.idCaracteristica = builder.idCaracteristica;
        this.nota = builder.nota;
        this.excessao = builder.excessao;
    }

    public static Builder builder() {
        return new Builder();
    }

    @NonNull
    public Long getIdManejoMelhoramento() { return idManejoMelhoramento; }

    @NonNull
    public Long getIdMelhoramento() { return idMelhoramento; }

    public Long getIdCaracteristica() { return idCaracteristica; }

    public Integer getNota() { return nota; }

    public String getExcessao() { return excessao; }


    public void setIdManejoMelhoramento(@NonNull Long idManejoMelhoramento) {
        this.idManejoMelhoramento = idManejoMelhoramento;
    }

    public void setIdMelhoramento(@NonNull Long idMelhoramento) {
        this.idMelhoramento = idMelhoramento;
    }

    public void setIdCaracteristica(Long idCaracteristica) {
        this.idCaracteristica = idCaracteristica;
    }

    public void setNota(Integer nota) {
        this.nota = nota;
    }

    public void setExcessao(String excessao) {
        this.excessao = excessao;
    }

    public static class Builder {
        private Long idManejoMelhoramento;
        private Long idMelhoramento;
        private Long idCaracteristica;
        private Long idObservacao;
        private Integer nota;
        private String excessao;
        public Builder idManejoMelhoramento(Long idManejoMelhoramento) {
            this.idManejoMelhoramento = idManejoMelhoramento;
            return this;
        }

        public Builder idMelhoramento(Long idMelhoramento) {
            this.idMelhoramento = idMelhoramento;
            return this;
        }

        public Builder idCaracteristica(Long idCaracteristica) {
            this.idCaracteristica = idCaracteristica;
            return this;
        }

        public Builder idObservacao(Long idObservacao) {
            this.idObservacao = idObservacao;
            return this;
        }

        public Builder nota(Integer nota) {
            this.nota = nota;
            return this;
        }

        public Builder excessao(String excessao) {
            this.excessao = excessao;
            return this;
        }

        public ManejoMelhoramento build() {
            return new ManejoMelhoramento(this);
        }
    }
}