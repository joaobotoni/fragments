package com.app.fragments.data.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.app.fragments.utils.DateConverter;

import java.util.Date;

@Entity(
        tableName = "XGP_MANEJO_MELHORAMENTO",
        foreignKeys = {
                @ForeignKey(
                        entity = XgpMelhoramento.class,
                        parentColumns = "Id_Melhoramento",
                        childColumns = "Id_Melhoramento",
                        onDelete = ForeignKey.CASCADE,
                        onUpdate = ForeignKey.CASCADE
                ),
                @ForeignKey(
                        entity = XgpMelhoramentoDetalhes.class,
                        parentColumns = {"Id_Melhoramento", "Id_Melhoramento_Det"},
                        childColumns = {"Id_Melhoramento", "Id_Melhoramento_Det"},
                        onDelete = ForeignKey.CASCADE,
                        onUpdate = ForeignKey.CASCADE
                )
        },
        indices = {
                @Index(value = "Id_Melhoramento"),
                @Index(value = {"Id_Melhoramento", "Id_Melhoramento_Det"})
        }
)
public class XgpManejoMelhoramento {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "Id_Manejo_Melhoramento")
    private long idManejoMelhoramento;

    @ColumnInfo(name = "Id_Melhoramento")
    private long idMelhoramento;

    @ColumnInfo(name = "Id_Melhoramento_Det")
    private long idMelhoramentoDet;

    @ColumnInfo(name = "Nota")
    private String nota;

    @ColumnInfo(name = "Excessao")
    private String excessao;

    @ColumnInfo(name = "Observacao")
    private String observacao;

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
    public XgpManejoMelhoramento() {
    }
    @Ignore
    public XgpManejoMelhoramento( long idMelhoramento, long idMelhoramentoDet, String nota, String excessao, String observacao) {
        this.idMelhoramento = idMelhoramento;
        this.idMelhoramentoDet = idMelhoramentoDet;
        this.nota = nota;
        this.excessao = excessao;
        this.observacao = observacao;
    }
    @Ignore
    public XgpManejoMelhoramento(long idMelhoramento, long idMelhoramentoDet, String nota, String excessao, String observacao, Date usuarioCreated, Date dataCreated, Date usuarioChanged, Date dataChanged) {
        this.idMelhoramento = idMelhoramento;
        this.idMelhoramentoDet = idMelhoramentoDet;
        this.nota = nota;
        this.excessao = excessao;
        this.observacao = observacao;
        this.usuarioCreated = usuarioCreated;
        this.dataCreated = dataCreated;
        this.usuarioChanged = usuarioChanged;
        this.dataChanged = dataChanged;
    }

    public XgpManejoMelhoramento(long idManejoMelhoramento, long idMelhoramento, long idMelhoramentoDet, String nota, String excessao, String observacao, Date usuarioCreated, Date dataCreated, Date usuarioChanged, Date dataChanged) {
        this.idManejoMelhoramento = idManejoMelhoramento;
        this.idMelhoramento = idMelhoramento;
        this.idMelhoramentoDet = idMelhoramentoDet;
        this.nota = nota;
        this.excessao = excessao;
        this.observacao = observacao;
        this.usuarioCreated = usuarioCreated;
        this.dataCreated = dataCreated;
        this.usuarioChanged = usuarioChanged;
        this.dataChanged = dataChanged;
    }


    public long getIdManejoMelhoramento() {
        return idManejoMelhoramento;
    }

    public void setIdManejoMelhoramento(long idManejoMelhoramento) {
        this.idManejoMelhoramento = idManejoMelhoramento;
    }

    public long getIdMelhoramento() {
        return idMelhoramento;
    }

    public void setIdMelhoramento(long idMelhoramento) {
        this.idMelhoramento = idMelhoramento;
    }

    public long getIdMelhoramentoDet() {
        return idMelhoramentoDet;
    }

    public void setIdMelhoramentoDet(long idMelhoramentoDet) {
        this.idMelhoramentoDet = idMelhoramentoDet;
    }

    public String getNota() {
        return nota;
    }

    public void setNota(String nota) {
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