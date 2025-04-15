public class TesteCategoria {
    public static void main(String[] args) {
        CrudCategoria crud = new CrudCategoria();

        crud.cadastrarCategoria("SUV", 1);
        crud.cadastrarCategoria("Sedan", 2);

        crud.listarCategorias(true);

        crud.editarCategoria(1, "SUV Atualizado");

        crud.listarCategorias(false);

        crud.removerCategoria(2);

        crud.listarCategorias(true);
    }
}
