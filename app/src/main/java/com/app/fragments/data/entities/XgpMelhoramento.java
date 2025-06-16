package com.app.fragments.data.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.Date;
import java.util.UUID;

@Entity
public class XgpMelhoramento {
    @PrimaryKey
    private UUID idMelhoramento;
    private String nome;
    @ColumnInfo(name = "Usuario_Created")
    private Date usuarioCreated;
    @ColumnInfo(name = "Data_Created")
    private Date dataCreated;
    @ColumnInfo(name = "Usuario_Changed")
    private Date usuarioChanged;
    @ColumnInfo(name = "Data_Changed")
    private Date dataChanged;

    @Ignore
    public XgpMelhoramento() {
    }
    @Ignore
    public XgpMelhoramento(String nome) {
        this.nome = nome;
    }
    public XgpMelhoramento(String nome, Date usuarioCreated, Date dataCreated, Date usuarioChanged, Date dataChanged) {
        this.nome = nome;
        this.usuarioCreated = usuarioCreated;
        this.dataCreated = dataCreated;
        this.usuarioChanged = usuarioChanged;
        this.dataChanged = dataChanged;
    }

    public UUID getIdMelhoramento() {
        return idMelhoramento;
    }

    public void setIdMelhoramento(UUID idMelhoramento) {
        this.idMelhoramento = idMelhoramento;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getUsuarioCreated() {
        return usuarioCreated;
    }

    public void setUsuarioCreated(Date usuarioCreated) {
        this.usuarioCreated = usuarioCreated;
    }

    public Date getDataCreated() {
        return dataCreated;
    }

    public void setDataCreated(Date dataCreated) {
        this.dataCreated = dataCreated;
    }

    public Date getUsuarioChanged() {
        return usuarioChanged;
    }

    public void setUsuarioChanged(Date usuarioChanged) {
        this.usuarioChanged = usuarioChanged;
    }

    public Date getDataChanged() {
        return dataChanged;
    }

    public void setDataChanged(Date dataChanged) {
        this.dataChanged = dataChanged;
    }
}
