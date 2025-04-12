public class TesteCrud1 {

    public static void main(String[] args) {
        CrudCliente crud = new CrudCliente();

        crud.cadastrarCliente("João", "123456", "99999-9999", "11111111111");
        crud.cadastrarCliente("Maria", "654321", "88888-8888", "22222222222");

        crud.listarClientes(true);

        crud.editarCliente("11111111111", "João da Silva", "123456", "99999-0000");

        crud.listarClientes(false);

        crud.removerCliente("22222222222");

        crud.listarClientes(true);
    }
}
