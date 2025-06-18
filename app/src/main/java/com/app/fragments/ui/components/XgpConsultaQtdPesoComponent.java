package com.app.fragments.ui.components;

public class XgpConsultaQtdPesoComponent {

    private String nomeTouroPai;
    private int machoQuantidade;
    private double machoPeso;
    private int femeaQuantidade;
    private double femeaPeso;
    private double total;

    public XgpConsultaQtdPesoComponent(String nomeTouroPai, int machoQuantidade, double machoPeso, int femeaQuantidade, double femeaPeso, double total) {
        this.nomeTouroPai = nomeTouroPai;
        this.machoQuantidade = machoQuantidade;
        this.machoPeso = machoPeso;
        this.femeaQuantidade = femeaQuantidade;
        this.femeaPeso = femeaPeso;
        this.total = total;
    }

    public String getNomeTouroPai() {
        return nomeTouroPai;
    }

    public void setNomeTouroPai(String nomeTouroPai) {
        this.nomeTouroPai = nomeTouroPai;
    }

    public int getMachoQuantidade() {
        return machoQuantidade;
    }

    public void setMachoQuantidade(int machoQuantidade) {
        this.machoQuantidade = machoQuantidade;
    }

    public double getMachoPeso() {
        return machoPeso;
    }

    public void setMachoPeso(double machoPeso) {
        this.machoPeso = machoPeso;
    }

    public int getFemeaQuantidade() {
        return femeaQuantidade;
    }

    public void setFemeaQuantidade(int femeaQuantidade) {
        this.femeaQuantidade = femeaQuantidade;
    }

    public double getFemeaPeso() {
        return femeaPeso;
    }

    public void setFemeaPeso(double femeaPeso) {
        this.femeaPeso = femeaPeso;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
