package src.model;
import java.time.LocalDate;

public class Locacao {
    private String cnhCliente;
    private String placaVeiculo;
    private LocalDate dataRetirada;
    private LocalDate dataDevolucao;
    private double valor;

    public Locacao(String cnhCliente, String placaVeiculo, LocalDate dataRetirada, LocalDate dataDevolucao, double valor) {
        this.cnhCliente = cnhCliente;
        this.placaVeiculo = placaVeiculo;
        this.dataRetirada = dataRetirada;
        this.dataDevolucao = dataDevolucao;
        this.valor = valor;
    }

    // Getters e Setters
    public String getCnhCliente() {
        return cnhCliente;
    }

    public void setCnhCliente(String cnhCliente) {
        this.cnhCliente = cnhCliente;
    }

    public String getPlacaVeiculo() {
        return placaVeiculo;
    }

    public void setPlacaVeiculo(String placaVeiculo) {
        this.placaVeiculo = placaVeiculo;
    }

    public LocalDate getDataRetirada() {
        return dataRetirada;
    }

    public void setDataRetirada(LocalDate dataRetirada) {
        this.dataRetirada = dataRetirada;
    }

    public LocalDate getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(LocalDate dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return "Locação{" +
                "CNH Cliente='" + cnhCliente + '\'' +
                ", Placa Veículo='" + placaVeiculo + '\'' +
                ", Data Retirada=" + dataRetirada +
                ", Data Devolução=" + dataDevolucao +
                ", Valor=" + valor +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Locacao locacao = (Locacao) obj;
        return placaVeiculo.equals(locacao.placaVeiculo);
    }
} 