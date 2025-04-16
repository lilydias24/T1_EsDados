package src.tests;
import src.model.Categoria;

public class TesteCategoria {
    public static void main(String[] args) {

        Categoria.cadastrarCategoria("SUV", 1);
        Categoria.cadastrarCategoria("Sedan", 2);

        Categoria.listarCategorias(true);

        Categoria.editarCategoria(1, "SUV Atualizado");

        Categoria.listarCategorias(false);

        Categoria.removerCategoria(2);

        Categoria.listarCategorias(true);

        Categoria.buscarCategoria(1);
    }
}
