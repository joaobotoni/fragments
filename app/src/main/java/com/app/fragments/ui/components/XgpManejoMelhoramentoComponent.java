package com.app.fragments.ui.components;

import java.util.Optional;
import java.util.function.Function;

public class XgpManejoMelhoramentoComponent {
    private Long idCharacteristic;
    private String characteristic;
    private String sigla;
    private String type;
    private String value;
    private Optional<String> listOptions;
    private Optional<Number> initialValue;
    private Optional<Number> finalValue;
    private String isObservation;

    public XgpManejoMelhoramentoComponent(Long idCharacteristic, String characteristic, String sigla, String type, String value, Optional<String> listOptions, Optional<Number> initialValue, Optional<Number> finalValue, String isObservation) {
        this.idCharacteristic = idCharacteristic;
        this.characteristic = characteristic;
        this.sigla = sigla;
        this.type = type;
        this.value = value;
        this.listOptions = listOptions;
        this.initialValue = initialValue;
        this.finalValue = finalValue;
        this.isObservation = isObservation;
    }

    public Long getIdCharacteristic() {
        return idCharacteristic;
    }

    public void setIdCharacteristic(Long idCharacteristic) {
        this.idCharacteristic = idCharacteristic;
    }

    public String getCharacteristic() {
        return characteristic;
    }

    public void setCharacteristic(String characteristic) {
        this.characteristic = characteristic;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Optional<String> getListOptions() {
        return listOptions;
    }

    public void setListOptions(Optional<String> listOptions) {
        this.listOptions = listOptions;
    }

    public Optional<Number> getInitialValue() {
        return initialValue;
    }

    public void setInitialValue(Optional<Number> initialValue) {
        this.initialValue = initialValue;
    }

    public Optional<Number> getFinalValue() {
        return finalValue;
    }

    public void setFinalValue(Optional<Number> finalValue) {
        this.finalValue = finalValue;
    }

    public String getIsObservation() {
        return isObservation;
    }

    public void setIsObservation(String isObservation) {
        this.isObservation = isObservation;
    }

    public <T> T[] mapOptions(Function<String[], T[]> converter) {
        return this.listOptions
                .map(options -> converter.apply(options.split(",")))
                .orElseGet(() -> converter.apply(new String[0]));
    }
}