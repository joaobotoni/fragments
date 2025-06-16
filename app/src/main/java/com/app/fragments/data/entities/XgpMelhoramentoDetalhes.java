package com.app.fragments.data.entities;

import androidx.room.ColumnInfo;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.Date;
import java.util.UUID;

public class XgpMelhoramentoDetalhes {
    @PrimaryKey
    private UUID idMelhoramento;
    @PrimaryKey
    private UUID idMelhoramentoDetalhes;
    @ColumnInfo(name = "descricao")
    private String descricao;
    @ColumnInfo(name = "sigla")
    private String sigla;
    @ColumnInfo(name = "nota_inicial")
    private Integer notaInicial;
    @ColumnInfo(name = "nota_final")
    private Integer notaFinal;
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
    public XgpMelhoramentoDetalhes(String descricao, String sigla, Integer notaInicial, Integer notaFinal, Date usuarioCreated, Date dataCreated, Date usuarioChanged, Date dataChanged) {
        this.descricao = descricao;
        this.sigla = sigla;
        this.notaInicial = notaInicial;
        this.notaFinal = notaFinal;
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

    public UUID getIdMelhoramentoDetalhes() {
        return idMelhoramentoDetalhes;
    }

    public void setIdMelhoramentoDetalhes(UUID idMelhoramentoDetalhes) {
        this.idMelhoramentoDetalhes = idMelhoramentoDetalhes;
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
