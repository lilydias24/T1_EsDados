package src.controller;
import java.time.LocalDate;
import src.estrutura.LDE;
import src.estrutura.Noh;
import src.model.Cliente;
import src.model.Locacao;
import src.model.Veiculo;

public class CrudLocacao {
    private LDE<Locacao> locacoes;

    public CrudLocacao(LDE<Veiculo> veiculos, LDE<Cliente> clientes) {
        this.locacoes = new LDE<>();
    }

    // metodo para verificar se um veiculo ta disponivel
    public boolean veiculoDisponivel(String placa) {
        Noh<Locacao> locacao = locacoes.buscar(new Locacao(null, placa, null, null, 0));
        return locacao == null;
    }

    // metodo para listar veiculos disponiveis com filtros
    public LDE<Veiculo> listarVeiculosDisponiveis(Integer potenciaMinima, Integer lugares, String categoriaNome, boolean ordemCrescente) {
        LDE<Veiculo> veiculosDisponiveis = new LDE<>();
        
        // pega todos os veiculos
        Noh<Veiculo> atual = Veiculo.getListaVeiculos().getInicio();
        
        while (atual != null) {
            Veiculo veiculo = atual.getInfo();
            if (veiculoDisponivel(veiculo.getPlaca())) {
                // aplica os filtros
                if ((potenciaMinima == null || veiculo.getPotencia() >= potenciaMinima) &&
                    (lugares == null || veiculo.getLugares() == lugares) &&
                    (categoriaNome == null || veiculo.getCategoria().getNome().equals(categoriaNome))) {
                    
                    // Insere o veículo na posição correta para manter a ordenação
                    if (veiculosDisponiveis.estahVazia()) {
                        veiculosDisponiveis.insereFim(veiculo);
                    } else {
                        Noh<Veiculo> nohAtual = veiculosDisponiveis.getInicio();
                        boolean inserido = false;
                        
                        while (nohAtual != null && !inserido) {
                            Veiculo veiculoAtual = nohAtual.getInfo();
                            boolean deveInserirAntes = ordemCrescente ? 
                                veiculo.getPotencia() < veiculoAtual.getPotencia() :
                                veiculo.getPotencia() > veiculoAtual.getPotencia();
                            
                            if (deveInserirAntes) {
                                if (nohAtual == veiculosDisponiveis.getInicio()) {
                                    veiculosDisponiveis.insereInicio(veiculo);
                                } else {
                                    Noh<Veiculo> novo = new Noh<>(veiculo);
                                    novo.setProx(nohAtual);
                                    novo.setAnt(nohAtual.getAnt());
                                    nohAtual.getAnt().setProx(novo);
                                    nohAtual.setAnt(novo);
                                }
                                inserido = true;
                            }
                            nohAtual = nohAtual.getProx();
                        }
                        
                        if (!inserido) {
                            veiculosDisponiveis.insereFim(veiculo);
                        }
                    }
                }
            }
            atual = atual.getProx();
        }

        return veiculosDisponiveis;
    }

    // metodo para locar um veiculo
    public boolean locarVeiculo(String cnhCliente, String placaVeiculo, LocalDate dataRetirada, LocalDate dataDevolucao, double valor) {
        // verifica se o cliente existe
        Cliente cliente = buscarClientePorCNH(cnhCliente);
        if (cliente == null) {
            System.out.println("Cliente não encontrado!");
            return false;
        }

        // verifica se o veiculo ta disponivel
        if (!veiculoDisponivel(placaVeiculo)) {
            System.out.println("Veículo já está locado!");
            return false;
        }

        // cria e adiciona a locacao
        Locacao locacao = new Locacao(cnhCliente, placaVeiculo, dataRetirada, dataDevolucao, valor);
        locacoes.insereFim(locacao);
        return true;
    }

    // metodo para devolver um veiculo
    public boolean devolverVeiculo(String placaVeiculo) {
        Locacao locacaoParaRemover = new Locacao(null, placaVeiculo, null, null, 0);
        return locacoes.remove(locacaoParaRemover);
    }

    // metodo para listar locacoes ativas
    public void listarLocacoesAtivas() {
        if (locacoes.estahVazia()) {
            System.out.println("Não há locações ativas.");
            return;
        }

        System.out.println("Locações Ativas:");
        locacoes.imprimeInicioFim();
    }

    // metodo para obter todas as locacoes
    public LDE<Locacao> getLocacoes() {
        return locacoes;
    }
    
    // metodo para buscar cliente por CNH
    private Cliente buscarClientePorCNH(String cnh) {
        Cliente temp = new Cliente("", cnh, "", "");
        Noh<Cliente> noh = Cliente.buscarPorCNH(cnh);
        return noh != null ? noh.getInfo() : null;
    }
} 