package com.app.fragments.data.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;

import java.util.Date;

@Entity(
        tableName = "XGP_MELHORAMENTO_DET",
        primaryKeys = {"Id_Melhoramento_Det", "Id_Melhoramento"},
        foreignKeys = {
                @ForeignKey(
                        entity = XgpMelhoramento.class,
                        parentColumns = {"Id_Melhoramento"},
                        childColumns = {"Id_Melhoramento"}
                )
        },
        indices = {
                @Index(value = {"Id_Melhoramento"}),
                @Index(value = {"Id_Melhoramento_Det"})
        }
)
public class XgpMelhoramentoDetalhes {
    @ColumnInfo(name = "Id_Melhoramento_Det")
    private long idMelhoramentoDet;
    @ColumnInfo(name = "Id_Melhoramento")
    private long idMelhoramento;
    @ColumnInfo(name = "Descricao")
    private String descricao;
    @ColumnInfo(name = "Sigla")
    private String sigla;
    @ColumnInfo(name = "Nota_Inicial")
    private String notaInicial;
    @ColumnInfo(name = "Nota_Final")
    private String notaFinal;
    @ColumnInfo(name = "Excesssao")
    private String excessao;
    @ColumnInfo(name = "Usuario_Created")
    private Date usuarioCreated;
    @ColumnInfo(name = "Data_Created")
    private Date dataCreated;
    @ColumnInfo(name = "Usuario_Changed")
    private Date usuarioChanged;
    @ColumnInfo(name = "Data_Changed")
    private Date dataChanged;

    @Ignore
    public XgpMelhoramentoDetalhes() {
    }

    @Ignore
    public XgpMelhoramentoDetalhes(long idMelhoramento, String descricao, String sigla, String notaInicial, String notaFinal, String excessao, Date usuarioCreated, Date dataCreated, Date usuarioChanged, Date dataChanged) {
        this.idMelhoramento = idMelhoramento;
        this.descricao = descricao;
        this.sigla = sigla;
        this.notaInicial = notaInicial;
        this.notaFinal = notaFinal;
        this.excessao = excessao;
        this.usuarioCreated = usuarioCreated;
        this.dataCreated = dataCreated;
        this.usuarioChanged = usuarioChanged;
        this.dataChanged = dataChanged;
    }

    public XgpMelhoramentoDetalhes(long idMelhoramentoDet, long idMelhoramento, String descricao, String sigla, String notaInicial, String notaFinal, String excessao, Date usuarioCreated, Date dataCreated, Date usuarioChanged, Date dataChanged) {
        this.idMelhoramentoDet = idMelhoramentoDet;
        this.idMelhoramento = idMelhoramento;
        this.descricao = descricao;
        this.sigla = sigla;
        this.notaInicial = notaInicial;
        this.notaFinal = notaFinal;
        this.excessao = excessao;
        this.usuarioCreated = usuarioCreated;
        this.dataCreated = dataCreated;
        this.usuarioChanged = usuarioChanged;
        this.dataChanged = dataChanged;
    }

    public long getIdMelhoramentoDet() {
        return idMelhoramentoDet;
    }

    public void setIdMelhoramentoDet(long idMelhoramentoDet) {
        this.idMelhoramentoDet = idMelhoramentoDet;
    }

    public long getIdMelhoramento() {
        return idMelhoramento;
    }

    public void setIdMelhoramento(long idMelhoramento) {
        this.idMelhoramento = idMelhoramento;
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

    public String getNotaInicial() {
        return notaInicial;
    }

    public void setNotaInicial(String notaInicial) {
        this.notaInicial = notaInicial;
    }

    public String getNotaFinal() {
        return notaFinal;
    }

    public void setNotaFinal(String notaFinal) {
        this.notaFinal = notaFinal;
    }

    public String getExcessao() {
        return excessao;
    }

    public void setExcessao(String excessao) {
        this.excessao = excessao;
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


