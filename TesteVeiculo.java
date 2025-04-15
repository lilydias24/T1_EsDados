public class TesteVeiculo {
    public static void main(String[] args) {
        CrudVeiculo crud = new CrudVeiculo();

        Categoria categoria1 = new Categoria("SUV", 1);
        Categoria categoria2 = new Categoria("Sedan", 2);

        crud.cadastrarVeiculo("ABC1234", "Modelo A", "Marca A", 2020, 150, 5, categoria1);
        crud.cadastrarVeiculo("XYZ5678", "Modelo B", "Marca B", 2021, 200, 4, categoria2);

        crud.listarVeiculos(true);

        crud.editarVeiculo("ABC1234", "Modelo A Atualizado", "Marca A Atualizada", 2022, 160, 6, categoria2);

        crud.listarVeiculos(false);

        crud.removerVeiculo("XYZ5678");

        crud.listarVeiculos(true);
    }
}
