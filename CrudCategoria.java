public class CrudCategoria {

    private LDE<Categoria> listaCategorias;

    public CrudCategoria() {
        this.listaCategorias = new LDE<>();
    }

    public void cadastrarCategoria(String nome, int identificador) {
        Categoria novaCategoria = new Categoria(nome, identificador);
        if (listaCategorias.buscar(novaCategoria) != null) {
            System.out.println("Já existe uma categoria com este nome. Não é possível cadastrar.");
            return;
        }
        listaCategorias.insereFim(novaCategoria);
        System.out.println("Categoria cadastrada com sucesso!");
    }
    
    public void listarCategorias(boolean ordemNormal) {
        if (listaCategorias.estahVazia()) {
            System.out.println("Nenhuma categoria cadastrada. Não é possível listar.");
            return;
        }

        System.out.println("==== Lista de Categorias ====");
        if (ordemNormal) {
            listaCategorias.imprimeInicioFim();
        } else {
            listaCategorias.imprimeFimInicio();
        }
    }

    public Categoria buscarCategoria(String nome) {
        Categoria temp = new Categoria(nome, 0);
        Noh<Categoria> noh = listaCategorias.buscar(temp);

        if (noh != null) {
            return noh.getInfo();
        }
        return null;
    }

    public void editarCategoria(int identificador, String novoNome) {
        Categoria temp = new Categoria("", identificador);
        Noh<Categoria> noh = listaCategorias.buscar(temp);

        if (noh == null) {
            System.out.println("Categoria não encontrada. Não foi possível editar.");
            return;
        }

        Categoria c = noh.getInfo();
        c.setNome(novoNome);
        System.out.println("Categoria editada com sucesso!");
    }

    public void removerCategoria(int identificador) {
        Categoria temp = new Categoria("", identificador);
        boolean removido = listaCategorias.remove(temp);
        
        if (removido) {
            System.out.println("Categoria removida com sucesso!");
        }
        else {
            System.out.println("Categoria não encontrada. Não foi possível remover.");
        }
    }
}
