package com.faj.aula4carroapp.model;

import android.os.Bundle;

public class CarroModel {

    private int id;
    private String placa;
    private String modelo;
    private String cor;
    private String ano;

    private String estadoConservacao;

    public CarroModel(Bundle bundle) {
        this.placa = bundle.getString("placa");
        this.modelo = bundle.getString("modelo");
        this.cor = bundle.getString("cor");
        this.ano = bundle.getString("ano");
        this.estadoConservacao = bundle.getString("state");
    }
    public CarroModel(String placa, String modelo, String cor, String ano, String estadoConservacao) {
        this.placa = placa;
        this.modelo = modelo;
        this.cor = cor;
        this.ano = ano;
        this.estadoConservacao = estadoConservacao;
    }

    public CarroModel(int id, String placa, String modelo, String cor, String ano, String estadoConservacao) {
        this.id = id;
        this.placa = placa;
        this.modelo = modelo;
        this.cor = cor;
        this.ano = ano;
        this.estadoConservacao = estadoConservacao;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    public String getEstadoConservacao() {
        return estadoConservacao;
    }

    public void setEstadoConservacao(String estadoConservacao) {
        this.estadoConservacao = estadoConservacao;
    }
}
