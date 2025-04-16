package src.model;
import src.estrutura.LDE;
import src.estrutura.Noh;

public class Veiculo {

    private String placa;
    private String modelo;
    private String marca;
    private int ano;
    private int potencia;
    private int lugares;
    private Categoria categoria;
    private static LDE<Veiculo> listaVeiculos = new LDE<>();

    public Veiculo(String placa, String modelo, String marca, int ano, int potencia, int lugares, Categoria categoria) {
        this.placa = placa;
        this.modelo = modelo;
        this.marca = marca;
        this.ano = ano;
        this.potencia = potencia;
        this.lugares = lugares;
        this.categoria = categoria;
    }

    public String getPlaca() {
        return placa;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public int getPotencia() {
        return potencia;
    }

    public void setPotencia(int potencia) {
        this.potencia = potencia;
    }

    public int getLugares() {
        return lugares;
    }

    public void setLugares(int lugares) {
        this.lugares = lugares;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Veiculo)) return false;
        Veiculo outro = (Veiculo) obj;
        return placa.equals(outro.placa);
    }

    @Override
    public String toString() {
        return "Placa: " + placa + ", Modelo: " + modelo + ", Marca: " + marca +
               ", Ano: " + ano + ", Potência: " + potencia + ", Lugares: " + lugares +
               ", Categoria: " + categoria.getNome();
    }

    public static void cadastrarVeiculo(String placa, String modelo, String marca, int ano, int potencia, int lugares, Categoria categoria) {
        Veiculo novo = new Veiculo(placa, modelo, marca, ano, potencia, lugares, categoria);

        if (listaVeiculos.buscar(novo) != null) {
            System.out.println("Já existe veículo com esta placa.");
            return;
        }

        listaVeiculos.insereFim(novo);
        System.out.println("Veículo cadastrado com sucesso.");
    }

    public static void listarVeiculos(boolean ordemNormal) {
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

    public static Veiculo buscarVeiculo(String placa) {
        Veiculo temp = new Veiculo(placa, "", "", 0, 0, 0, null);
        Noh<Veiculo> noh = listaVeiculos.buscar(temp);

        if (noh != null) {
            return noh.getInfo();
        }
        return null;
    }

    public static void editarVeiculo(String placa, String novoModelo, String novaMarca, int novoAno, int novaPotencia, int novosLugares, Categoria novaCategoria) {
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

    public static void removerVeiculo(String placa) {
        Veiculo temp = new Veiculo(placa, "", "", 0, 0, 0, null);
        boolean removido = listaVeiculos.remove(temp);

        if (removido) {
            System.out.println("Veículo removido.");
        } else {
            System.out.println("Veículo não encontrado.");
        }
    }
}