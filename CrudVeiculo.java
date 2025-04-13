public class CrudVeiculo {

    private LDE<Veiculo> listaVeiculos;

    public CrudVeiculo() {
        this.listaVeiculos = new LDE<>();
    }

    public void cadastrarVeiculo(String placa, String modelo, String marca, int ano, int potencia, int lugares, Categoria categoria) {
        Veiculo novo = new Veiculo(placa, modelo, marca, ano, potencia, lugares, categoria);

        if (listaVeiculos.buscar(novo) != null) {
            System.out.println("Já existe veículo com esta placa.");
            return;
        }

        listaVeiculos.insereFim(novo);
        System.out.println("Veículo cadastrado com sucesso.");
    }

    public void listarVeiculos(boolean ordemNormal) {
        if (listaVeiculos.estahVazia()) {
            System.out.println("Nenhum veículo cadastrado.");
            return;
        }

        System.out.println("==== Lista de Veículos ====");
        if (ordemNormal) {
            listaVeiculos.imprimeInicioFim();
        } else {
            listaVeiculos.imprimeFimInicio();
        }
    }

    public Veiculo buscarVeiculo(String placa) {
        Veiculo temp = new Veiculo(placa, "", "", 0, 0, 0, null);
        Noh<Veiculo> noh = listaVeiculos.buscar(temp);

        if (noh != null) {
            return noh.getInfo();
        }
        return null;
    }

    public void editarVeiculo(String placa, String novoModelo, String novaMarca, int novoAno, int novaPotencia, int novosLugares, Categoria novaCategoria) {
        Veiculo temp = new Veiculo(placa, "", "", 0, 0, 0, null);
        Noh<Veiculo> noh = listaVeiculos.buscar(temp);

        if (noh == null) {
            System.out.println("Veículo não encontrado.");
            return;
        }

        Veiculo v = noh.getInfo();
        v.setModelo(novoModelo);
        v.setMarca(novaMarca);
        v.setAno(novoAno);
        v.setPotencia(novaPotencia);
        v.setLugares(novosLugares);
        v.setCategoria(novaCategoria);

        System.out.println("Dados do veículo atualizados.");
    }

    public void removerVeiculo(String placa) {
        Veiculo temp = new Veiculo(placa, "", "", 0, 0, 0, null);
        boolean removido = listaVeiculos.remove(temp);

        if (removido) {
            System.out.println("Veículo removido.");
        } else {
            System.out.println("Veículo não encontrado.");
        }
    }
}
