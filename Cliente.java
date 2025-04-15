public class Cliente {

    private String nome;
    private String cnh;
    private String telefone;
    private String cpf;

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
}
