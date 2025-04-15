public class CrudCliente {

    private LDE<Cliente> listaClientes;

    public CrudCliente() {
        this.listaClientes = new LDE<>();
    }

    public Noh<Cliente> buscar(String cnh) {
        Cliente temp = new Cliente("", cnh, "", "");
        return listaClientes.buscarPersonalizado(temp, (c1, c2) -> c1.getCNH().equals(c2.getCNH()));
    }

    // Inserção de cliente no final da lista
    public void cadastrarCliente(String nome, String cnh, String telefone, String cpf) {
        Cliente novo = new Cliente(nome, cnh, telefone, cpf);
        if (listaClientes.buscar(novo) != null) {
            System.out.println("Já existe um cliente com este CPF.");
            return;
        }
        listaClientes.insereFim(novo);
        System.out.println("Cliente cadastrado com sucesso!");
    }

    // Listagem de todos os clientes
    public void listarClientes(boolean ordemNormal) {
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

    // Buscar cliente pelo CPF
    public Cliente buscarCliente(String cpf) {
        Cliente temp = new Cliente("", "", "", cpf);
        Noh<Cliente> noh = listaClientes.buscar(temp);

        if (noh != null) {
            return noh.getInfo();
        }
        return null;
    }

    // Alterar dados de um cliente
    public void editarCliente(String cpf, String novoNome, String novaCNH, String novoTelefone) {
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

    // Remover cliente pelo CPF (supondo que a verificação de locação ativa será feita fora)
    public void removerCliente(String cpf) {
        Cliente temp = new Cliente("", "", "", cpf);
        boolean removido = listaClientes.remove(temp);

        if (removido) {
            System.out.println("Cliente removido.");
        } else {
            System.out.println("Cliente não encontrado.");
        }
    }
}
