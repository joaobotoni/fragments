package com.app.fragments.data.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.app.fragments.utils.DateConverter;

import java.util.Date;

@Entity(tableName = "XGP_MELHORAMENTO")
public class XgpMelhoramento {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "Id_Melhoramento")
    private long idMelhoramento;

    @ColumnInfo(name = "Nome")
    private String nome;

    @ColumnInfo(name = "Usuario_Created")
    @TypeConverters(DateConverter.class)
    private Date usuarioCreated;

    @ColumnInfo(name = "Data_Created")
    @TypeConverters(DateConverter.class)
    private Date dataCreated;

    @ColumnInfo(name = "Usuario_Changed")
    @TypeConverters(DateConverter.class)
    private Date usuarioChanged;

    @ColumnInfo(name = "Data_Changed")
    @TypeConverters(DateConverter.class)
    private Date dataChanged;

    @Ignore
    public XgpMelhoramento() {
    }

    @Ignore
    public XgpMelhoramento(String nome, Date usuarioCreated, Date dataCreated, Date usuarioChanged, Date dataChanged) {
        this.nome = nome;
        this.usuarioCreated = usuarioCreated;
        this.dataCreated = dataCreated;
        this.usuarioChanged = usuarioChanged;
        this.dataChanged = dataChanged;
    }

    public XgpMelhoramento(long idMelhoramento, String nome, Date usuarioCreated, Date dataCreated, Date usuarioChanged, Date dataChanged) {
        this.idMelhoramento = idMelhoramento;
        this.nome = nome;
        this.usuarioCreated = usuarioCreated;
        this.dataCreated = dataCreated;
        this.usuarioChanged = usuarioChanged;
        this.dataChanged = dataChanged;
    }

    public long getIdMelhoramento() {
        return idMelhoramento;
    }

    public void setIdMelhoramento(long idMelhoramento) {
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