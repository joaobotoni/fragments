package com.app.fragments.data.entities;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "xgp_melhoramento")
public class Melhoramento {
    @NonNull
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id_melhoramento")
    private Long idMelhoramento;

    @ColumnInfo(name = "nome")
    private String nome;

    public Melhoramento() {
    }

    public Melhoramento(Long idMelhoramento, String nome) {
        this.idMelhoramento = idMelhoramento;
        this.nome = nome;
    }

    public Long getIdMelhoramento() {
        return idMelhoramento;
    }

    public void setIdMelhoramento(Long idMelhoramento) {
        this.idMelhoramento = idMelhoramento;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}