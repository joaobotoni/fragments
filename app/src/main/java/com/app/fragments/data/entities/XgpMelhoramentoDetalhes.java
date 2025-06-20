package com.app.fragments.data.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.TypeConverters;

import com.app.fragments.utils.DateConverter;

import java.util.Date;

@Entity(
        tableName = "XGP_MELHORAMENTO_DET",
        primaryKeys = {"Id_Melhoramento", "Id_Melhoramento_Det"},
        foreignKeys = @ForeignKey(
                entity = XgpMelhoramento.class,
                parentColumns = "Id_Melhoramento",
                childColumns = "Id_Melhoramento",
                onDelete = ForeignKey.CASCADE,
                onUpdate = ForeignKey.CASCADE
        ),
        indices = {@Index(value = "Id_Melhoramento")}
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
    private Integer notaInicial;

    @ColumnInfo(name = "Nota_Final")
    private Integer notaFinal;

    @ColumnInfo(name = "Excessao")
    private String excessao;

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

    // Construtor vazio para o Room
    @Ignore
    public XgpMelhoramentoDetalhes() {
    }

    // Construtor sem idMelhoramentoDet (auto gerado)
    @Ignore
    public XgpMelhoramentoDetalhes(long idMelhoramento, String descricao, String sigla, Integer notaInicial, Integer notaFinal, String excessao, Date usuarioCreated, Date dataCreated, Date usuarioChanged, Date dataChanged) {
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

    public XgpMelhoramentoDetalhes(long idMelhoramento, long idMelhoramentoDet, String descricao, String sigla, Integer notaInicial, Integer notaFinal, String excessao, Date usuarioCreated, Date dataCreated, Date usuarioChanged, Date dataChanged) {
        this.idMelhoramento = idMelhoramento;
        this.idMelhoramentoDet = idMelhoramentoDet;
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
