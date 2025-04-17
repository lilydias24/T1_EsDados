package src.model;
import src.estrutura.LDE;
import src.estrutura.Noh;

public class Cliente {

    private String nome;
    private String cnh;
    private String telefone;
    private String cpf;
    private static LDE<Cliente> listaClientes = new LDE<>();

    public Cliente(String nome, String cnh, String telefone, String cpf) {
        this.nome = nome;
        this.cnh = cnh;
        this.telefone = telefone;
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCNH() {
        return cnh;
    }

    public void setCNH(String cnh) {
        this.cnh = cnh;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCPF() {
        return cpf;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Cliente outro = (Cliente) obj;
        return cpf.equals(outro.cpf);
    }

    // Método para comparar apenas por CNH
    public boolean equalsCNH(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Cliente outro = (Cliente) obj;
        return cnh.equals(outro.cnh);
    }

    @Override
    public String toString() {
        return "Nome: " + nome + ", CNH: " + cnh + ", Telefone: " + telefone + ", CPF: " + cpf;
    }

    public static void cadastrarCliente(String nome, String cnh, String telefone, String cpf) {
        Cliente novo = new Cliente(nome, cnh, telefone, cpf);
        if (listaClientes.buscar(novo) != null) {
            System.out.println("Já existe um cliente com este CPF.");
            return;
        }
        listaClientes.insereFim(novo);
        System.out.println("Cliente cadastrado com sucesso!");
    }

    public static void listarClientes(boolean ordemNormal) {
        if (listaClientes.estahVazia()) {
            System.out.println("Nenhum cliente cadastrado.");
            return;
        }

        System.out.println("==== Lista de Clientes ====");
        if (ordemNormal) {
            listaClientes.imprimeInicioFim();
        } else {
            listaClientes.imprimeFimInicio();
        }
    }

    public static Cliente buscarCliente(String cpf) {
        Cliente temp = new Cliente("", "", "", cpf);
        Noh<Cliente> noh = listaClientes.buscar(temp);

        if (noh != null) {
            return noh.getInfo();
        }
        return null;
    }

    public static void editarCliente(String cpf, String novoNome, String novaCNH, String novoTelefone) {
        Cliente temp = new Cliente("", "", "", cpf);
        Noh<Cliente> noh = listaClientes.buscar(temp);

        if (noh == null) {
            System.out.println("Cliente não encontrado.");
            return;
        }

        Cliente c = noh.getInfo();
        c.setNome(novoNome);
        c.setCNH(novaCNH);
        c.setTelefone(novoTelefone);

        System.out.println("Dados do cliente atualizados.");
    }

    public static void removerCliente(String cpf) {
        // Busca o cliente pelo CPF
        Cliente temp = new Cliente("", "", "", cpf);
        Noh<Cliente> nohCliente = listaClientes.buscar(temp);
    
        if (nohCliente == null) {
            System.out.println("Cliente não encontrado.");
            return;
        }
    
        Cliente cliente = nohCliente.getInfo();
    
        // Verifica se há locações associadas ao cliente
        for (Noh<Locacao> noh = Locacao.listaLocacoes.getInicio(); noh != null; noh = noh.getProx()) {
            if (noh.getInfo().getCnhCliente().equals(cliente.getCNH())) {
                System.out.println("Não é possível remover o cliente. Existem locações associadas a ele.");
                return;
            }
        }
    
        // Remove o cliente se não houver locações associadas
        boolean removido = listaClientes.remove(temp);
    
        if (removido) {
            System.out.println("Cliente removido com sucesso!");
        } else {
            System.out.println("Erro ao remover o cliente.");
        }
    }

    public static Noh<Cliente> buscarPorCNH(String cnh) {
        Cliente temp = new Cliente("", cnh, "", "");
        return listaClientes.buscarPersonalizado(temp, (c1, c2) -> c1.getCNH().equals(c2.getCNH()));
    }
}