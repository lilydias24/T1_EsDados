import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class CrudLocacao {
    private LDE<Locacao> locacoes;
    private LDE<Veiculo> veiculos;
    private LDE<Cliente> clientes;

    public CrudLocacao(LDE<Veiculo> veiculos, LDE<Cliente> clientes) {
        this.locacoes = new LDE<>();
        this.veiculos = veiculos;
        this.clientes = clientes;
    }

    // metodo para verificar se um veiculo ta disponivel
    public boolean veiculoDisponivel(String placa) {
        Noh<Locacao> locacao = locacoes.buscar(new Locacao(null, placa, null, null, 0));
        return locacao == null;
    }

    // metodo para listar veiculos disponiveis com filtros
    public List<Veiculo> listarVeiculosDisponiveis(Integer potenciaMinima, Integer lugares, String categoriaNome, boolean ordemCrescente) {
        List<Veiculo> veiculosDisponiveis = new ArrayList<>();
        
        // pega todos os veiculos
        Noh<Veiculo> atual = veiculos.getInicio();
        
        while (atual != null) {
            Veiculo veiculo = atual.getInfo();
            if (veiculoDisponivel(veiculo.getPlaca())) {
                // aplica os filtros
                if ((potenciaMinima == null || veiculo.getPotencia() >= potenciaMinima) &&
                    (lugares == null || veiculo.getLugares() == lugares) &&
                    (categoriaNome == null || veiculo.getCategoria().getNome().equals(categoriaNome))) {
                    veiculosDisponiveis.add(veiculo);
                }
            }
            atual = atual.getProx();
        }

        // ordena os veiculos
        if (ordemCrescente) {
            veiculosDisponiveis.sort(Comparator.comparing(Veiculo::getPotencia));
        } else {
            veiculosDisponiveis.sort(Comparator.comparing(Veiculo::getPotencia).reversed());
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
        Noh<Cliente> atual = clientes.getInicio();
        while (atual != null) {
            if (atual.getInfo().getCNH().equals(cnh)) {
                return atual.getInfo();
            }
            atual = atual.getProx();
        }
        return null;
    }
} 