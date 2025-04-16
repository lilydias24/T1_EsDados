package src.model;

import java.time.LocalDate;

import src.estrutura.LDE;
import src.estrutura.Noh;

public class Locacao {
    private String cnhCliente;
    private String placaVeiculo;
    private LocalDate dataRetirada;
    private LocalDate dataDevolucao;
    private double valor;

    private static LDE<Locacao> listaLocacoes = new LDE<>();

    public Locacao(String cnhCliente, String placaVeiculo, LocalDate dataRetirada, LocalDate dataDevolucao, double valor) {
        this.cnhCliente = cnhCliente;
        this.placaVeiculo = placaVeiculo;
        this.dataRetirada = dataRetirada;
        this.dataDevolucao = dataDevolucao;
        this.valor = valor;
    }

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

    // Métodos estáticos para manipulação de locações
    public static void cadastrarLocacao(String cnhCliente, String placaVeiculo, LocalDate dataRetirada, LocalDate dataDevolucao, double valor) {
        Cliente cliente = Cliente.buscarPorCNH(cnhCliente).getInfo();
        if (cliente == null) {
            System.out.println("Cliente não encontrado!");
            return;
        }

        Veiculo veiculo = Veiculo.buscarVeiculo(placaVeiculo);
        if (veiculo == null) {
            System.out.println("Veículo não encontrado!");
            return;
        }

        if (!veiculoDisponivel(placaVeiculo)) {
            System.out.println("Veículo já está locado!");
            return;
        }

        Locacao novaLocacao = new Locacao(cnhCliente, placaVeiculo, dataRetirada, dataDevolucao, valor);
        listaLocacoes.insereFim(novaLocacao);
        System.out.println("Locação cadastrada com sucesso!");
    }

    public static void listarLocacoes(boolean ordemNormal) {
        if (listaLocacoes.estahVazia()) {
            System.out.println("Nenhuma locação cadastrada.");
            return;
        }

        System.out.println("==== Lista de Locações ====");
        if (ordemNormal) {
            listaLocacoes.imprimeInicioFim();
        } else {
            listaLocacoes.imprimeFimInicio();
        }
    }

    public static boolean veiculoDisponivel(String placaVeiculo) {
        Locacao temp = new Locacao(null, placaVeiculo, null, null, 0);
        return listaLocacoes.buscar(temp) == null;
    }

    public static void devolverVeiculo(String placaVeiculo) {
        Locacao temp = new Locacao(null, placaVeiculo, null, null, 0);
        boolean removido = listaLocacoes.remove(temp);

        if (removido) {
            System.out.println("Veículo devolvido com sucesso!");
        } else {
            System.out.println("Locação não encontrada.");
        }
    }

    public static Locacao buscarLocacao(String placaVeiculo) {
        Locacao temp = new Locacao(null, placaVeiculo, null, null, 0);
        Noh<Locacao> noh = listaLocacoes.buscar(temp);

        if (noh != null) {
            return noh.getInfo();
        }
        return null;
    }
}