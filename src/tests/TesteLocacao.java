package src.tests;
import java.time.LocalDate;
import src.controller.CrudLocacao;
import src.estrutura.LDE;
import src.model.Categoria;
import src.model.Cliente;
import src.model.Veiculo;

public class TesteLocacao {
    public static void main(String[] args) {
        // instan listas
        LDE<Veiculo> veiculos = new LDE<>();
        LDE<Cliente> clientes = new LDE<>();
        CrudLocacao crudLocacao = new CrudLocacao(veiculos, clientes);
        
        // instan categorias
        Categoria.cadastrarCategoria("Sedan", 1);
        Categoria.cadastrarCategoria("SUV", 2);
        
        // instan veiculos
        System.out.println("\n=== Cadastrando Veículos ===");
        Veiculo.cadastrarVeiculo("ABC1234", "Civic", "Honda", 2020, 150, 5, Categoria.buscarCategoria(1));
        Veiculo.cadastrarVeiculo("DEF5678", "CR-V", "Honda", 2021, 180, 5, Categoria.buscarCategoria(2));
        Veiculo.cadastrarVeiculo("GHI9012", "Fit", "Honda", 2019, 120, 5, Categoria.buscarCategoria(1));
        
        // instan clientes
        System.out.println("\n=== Cadastrando Clientes ===");
        Cliente.cadastrarCliente("João Silva", "123456", "11999999999", "123.456.789-00");
        Cliente.cadastrarCliente("Maria Oliveira", "654321", "11988888888", "987.654.321-00");
        
        // teste listagem de veiculos disponiveis
        System.out.println("\n=== Listando Veículos Disponíveis (Inicial) ===");
        LDE<Veiculo> veiculosDisponiveis = crudLocacao.listarVeiculosDisponiveis(null, null, null, true);
        System.out.println("Veículos disponíveis (ordenados por potência crescente):");
        veiculosDisponiveis.imprimeInicioFim();
        System.out.println("Total de veículos disponíveis: " + veiculosDisponiveis.tamanho());
        
        // testando locação de veículos
        System.out.println("\n=== Testando Locação de Veículos ===");
        LocalDate hoje = LocalDate.now();
        LocalDate amanha = hoje.plusDays(1);
        
        // aqui, loca o primeiro veiculo pro joao da silva
        System.out.println("Tentando locar veículo ABC1234 para João Silva:");
        boolean locacao1 = crudLocacao.locarVeiculo("123456", "ABC1234", hoje, amanha, 150.0);
        System.out.println("Locação realizada: " + (locacao1 ? "Sim" : "Não"));
        
        // aqui, loca o segundo veiculo pro joao da silva
        System.out.println("\nTentando locar outro veículo para o mesmo cliente (deve funcionar):");
        boolean locacao2 = crudLocacao.locarVeiculo("123456", "GHI9012", hoje, amanha, 120.0);
        System.out.println("Locação realizada: " + (locacao2 ? "Sim" : "Não"));
        
        // aqui, loca o terceiro veiculo pro maria oliveira
        System.out.println("\nTentando locar veículo DEF5678 para Maria Oliveira:");
        boolean locacao3 = crudLocacao.locarVeiculo("654321", "DEF5678", hoje, amanha, 180.0);
        System.out.println("Locação realizada: " + (locacao3 ? "Sim" : "Não"));
        
        // aqui, lista as locações ativas
        System.out.println("\n=== Listando Locações Ativas ===");
        crudLocacao.listarLocacoesAtivas();
        
        // aqui, lista os veiculos disponiveis apos as locações
        System.out.println("\n=== Listando Veículos Disponíveis (Após Locações) ===");
        LDE<Veiculo> veiculosDisponiveisApos = crudLocacao.listarVeiculosDisponiveis(null, null, null, true);
        System.out.println("Veículos disponíveis (ordenados por potência crescente):");
        veiculosDisponiveisApos.imprimeInicioFim();
        System.out.println("Total de veículos disponíveis: " + veiculosDisponiveisApos.tamanho());
        
        // aqui, tenta locar um veiculo ja locado (deve falhar)
        System.out.println("\nTentando locar um veículo já locado (deve falhar):");
        boolean locacao4 = crudLocacao.locarVeiculo("654321", "ABC1234", hoje, amanha, 150.0);
        System.out.println("Locação realizada: " + (locacao4 ? "Sim" : "Não"));
        
        // aqui, devolve um dos veiculos de joao da silva
        System.out.println("\n=== Devolvendo um dos veículos de João Silva ===");
        boolean devolucao = crudLocacao.devolverVeiculo("ABC1234");
        System.out.println("Devolução realizada: " + (devolucao ? "Sim" : "Não"));
        
        // aqui, lista as locações ativas apos a devolucao
        System.out.println("\n=== Listando Locações Ativas (Após Devolução) ===");
        crudLocacao.listarLocacoesAtivas();
        
        // aqui, lista os veiculos disponiveis apos a devolucao
        System.out.println("\n=== Listando Veículos Disponíveis (Após Devolução) ===");
        LDE<Veiculo> veiculosDisponiveisAposDevolucao = crudLocacao.listarVeiculosDisponiveis(null, null, null, true);
        System.out.println("Veículos disponíveis (ordenados por potência crescente):");
        veiculosDisponiveisAposDevolucao.imprimeInicioFim();
        System.out.println("Total de veículos disponíveis: " + veiculosDisponiveisAposDevolucao.tamanho());
    }
} 