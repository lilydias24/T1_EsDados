public class Veiculo {

    private String placa;
    private String modelo;
    private String marca;
    private int ano;
    private int potencia;
    private int lugares;
    private Categoria categoria; 

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
}
