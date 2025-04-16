package src.tests;
import src.model.Categoria;
import src.model.Veiculo;

public class TesteVeiculo {
    public static void main(String[] args) {

        Categoria.cadastrarCategoria("SUV", 1);
        Categoria.cadastrarCategoria("Sedan", 2);
        Categoria.cadastrarCategoria("Hatch", 3);


        Veiculo.cadastrarVeiculo("ABC1234", "Modelo A", "Marca A", 2020, 150, 5, Categoria.buscarCategoria(1));
        Veiculo.cadastrarVeiculo("XYZ5678", "Modelo B", "Marca B", 2021, 200, 4, Categoria.buscarCategoria(2));

        Veiculo.listarVeiculos(true);

        Veiculo.editarVeiculo("ABC1234", "Modelo A Atualizado", "Marca A Atualizada", 2022, 160, 6, Categoria.buscarCategoria(3));

        Veiculo.listarVeiculos(false);

        Veiculo.removerVeiculo("XYZ5678");

        Veiculo.listarVeiculos(true);
    }
}
