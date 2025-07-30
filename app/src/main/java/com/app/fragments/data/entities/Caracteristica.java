package com.app.fragments.data.entities;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Index;

import java.io.Serializable;

@Entity(
        tableName = "xgp_caracteristica",
        primaryKeys = {"id_melhoramento", "id_caracteristica"},
        indices = {@Index(value = "id_caracteristica", unique = true)}
)
public class Caracteristica {

    @NonNull
    @ColumnInfo(name = "id_melhoramento")
    private Long idMelhoramento;

    @ColumnInfo(name = "id_caracteristica")
    @NonNull
    private Long idCharacteristic;

    @ColumnInfo(name = "descricao")
    private String description;

    @ColumnInfo(name = "sigla")
    private String sigla;

    @ColumnInfo(name = "lista_opcoes")
    private String listOptions;

    @ColumnInfo(name = "tipo_dado")
    private String type;

    @ColumnInfo(name = "nota_inicial")
    private Double initialValue;

    @ColumnInfo(name = "nota_final")
    private Double finalValue;

    @ColumnInfo(name = "eh_observacao")
    private String isObservation;


    public Caracteristica() {
    }

    public Caracteristica(@NonNull Long idMelhoramento, @NonNull Long idCharacteristic, String description, String sigla, String listOptions, String type, Double initialValue, Double finalValue, String isObservation) {
        this.idMelhoramento = idMelhoramento;
        this.idCharacteristic = idCharacteristic;
        this.description = description;
        this.sigla = sigla;
        this.listOptions = listOptions;
        this.type = type;
        this.initialValue = initialValue;
        this.finalValue = finalValue;
        this.isObservation = isObservation;
    }

    @NonNull
    public Long getIdMelhoramento() {
        return idMelhoramento;
    }

    public void setIdMelhoramento(@NonNull Long idMelhoramento) {
        this.idMelhoramento = idMelhoramento;
    }

    @NonNull
    public Long getIdCharacteristic() {
        return idCharacteristic;
    }

    public void setIdCharacteristic(@NonNull Long idCharacteristic) {
        this.idCharacteristic = idCharacteristic;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public String getListOptions() {
        return listOptions;
    }

    public void setListOptions(String listOptions) {
        this.listOptions = listOptions;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getInitialValue() {
        return initialValue;
    }

    public void setInitialValue(Double initialValue) {
        this.initialValue = initialValue;
    }

    public Double getFinalValue() {
        return finalValue;
    }

    public void setFinalValue(Double finalValue) {
        this.finalValue = finalValue;
    }

    public String getIsObservation() {
        return isObservation;
    }

    public void setIsObservation(String isObservation) {
        this.isObservation = isObservation;
    }
}
