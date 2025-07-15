package com.app.fragments.ui.components;

public class ObservacaoComponent {
    private Long id;
    private String observacao;

    public ObservacaoComponent(Long id, String observacao) {
        this.id = id;
        this.observacao = observacao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    @Override
    public String toString() {
        return observacao;
    }
}
