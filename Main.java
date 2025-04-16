import java.util.Scanner;
import java.time.LocalDate;
import java.util.InputMismatchException;

public class Main {

    public static String formatarCPF(String cpf) {
        try {
            if (cpf == null) return null;
            cpf = cpf.replaceAll("\\D", ""); // remove tudo que não é número
            if (cpf.length() != 11) {
                throw new IllegalArgumentException("CPF inválido! Deve conter 11 dígitos.");
            }
            return cpf.substring(0, 3) + "." +
                   cpf.substring(3, 6) + "." +
                   cpf.substring(6, 9) + "-" +
                   cpf.substring(9);
        } catch (Exception e) {
            System.out.println("Erro ao formatar CPF: " + e.getMessage());
            return null;
        }
    }

    public static String formatarCNH(String cnh) {
        try {
            if (cnh == null) return null;
            cnh = cnh.replaceAll("\\D", "");
            if (cnh.length() < 7 || cnh.length() > 11) {
                throw new IllegalArgumentException("CNH inválida! Deve conter entre 7 e 11 dígitos.");
            }
            return cnh;
        } catch (Exception e) {
            System.out.println("Erro ao formatar CNH: " + e.getMessage());
            return null;
        }
    }

    public static String formatarTelefone(String telefone) {
        try {
            if (telefone == null) return null;
            telefone = telefone.replaceAll("\\D", "");
            if (telefone.length() != 10 && telefone.length() != 11) {
                throw new IllegalArgumentException("Telefone inválido! Deve conter 10 ou 11 dígitos.");
            }
            return telefone;
        } catch (Exception e) {
            System.out.println("Erro ao formatar telefone: " + e.getMessage());
            return null;
        }
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcao = -1;

        do {
            System.out.println(Estilo.negrito + Estilo.azul + "\n<<<----------- LOCADORA DE VEÍCULOS ---------->> \n" + Estilo.reset);
            System.out.println(Estilo.roxo + "\t 1. Gerenciar Clientes");
            System.out.println("\n\t 2. Gerenciar Veículos");
            System.out.println("\n\t 3. Gerenciar Categorias");
            System.out.println("\n\t 4. Gerenciar Locações");
            System.out.println("\n\t 0. <- Sair" + Estilo.reset);
            System.out.println(Estilo.negrito + Estilo.azul + "\n <<------------------------------------------->> \n" + Estilo.reset);
            System.out.print(Estilo.negrito + Estilo.amarelo + ">> Escolha uma das opções acima : " + Estilo.reset);
            try {
                opcao = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println(Estilo.negrito + Estilo.vermelho + "Entrada inválida! Digite apenas números." + Estilo.reset);
                scanner.nextLine();
                continue;
            }
            scanner.nextLine(); 

            switch (opcao) {
                case 1:
                    menuClientes(scanner);
                    break;
                case 2:
                    menuVeiculos(scanner);
                    break;
                case 3:
                    menuCategorias(scanner);
                    break;
                case 4:
                    menuLocacoes(scanner);
                    break;
                case 0:
                    System.out.println(Estilo.negrito + Estilo.branco +"\nEncerrando o programa..." + Estilo.reset);
                    break;
                default:
                    System.out.println(Estilo.negrito + Estilo.vermelho + "Opção inválida!" + Estilo.reset);
            }
        } while (opcao != 0);

        scanner.close();
    }

    public static void menuClientes(Scanner scanner) {
        int opcao = -1;
        do {
            System.out.println("\n -.-.-.-.-.-.-.-.- MENU CLIENTES -.-.-.-.-.-.-.-.- \n");
            System.out.println("\t 1. Inserir cliente");
            System.out.println("\t 2. Excluir cliente");
            System.out.println("\t 3. Editar cliente");
            System.out.println("\t 4. Listar clientes (início -> fim)");
            System.out.println("\t 5. Listar clientes (fim -> início)");
            System.out.println("\t 0. <- Voltar");
            System.out.println("\n -.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.- \n");
            System.out.print(">> Escolha uma das opções acima : ");
            try {
                opcao = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida! Digite apenas números.");
                scanner.nextLine();
                continue;
            }            
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    System.out.print("\nNome: ");
                    String nome = scanner.nextLine();
                    
                    System.out.print("CNH: ");
                    String cnhEntrada = scanner.nextLine();
                    String cnh = formatarCNH(cnhEntrada);
                    if (cnh == null) {
                        System.out.println("CNH inválida. Cancelando cadastro.");
                        break;
                    }
                    
                    System.out.print("Telefone: ");
                    String telefoneEntrada = scanner.nextLine();
                    String telefone = formatarTelefone(telefoneEntrada);
                    if (telefone == null) {
                        System.out.println("Telefone inválido. Cancelando cadastro.");
                        break;
                    }

                    System.out.print("CPF (somente números): ");
                    String cpfEntrada = scanner.nextLine();
                    String cpf = formatarCPF(cpfEntrada);
                    if (cpf == null) {
                        System.out.println("Não foi possível cadastrar o cliente. CPF inválido.");
                        break;
                    }
                    
                    Cliente.cadastrarCliente(nome, cnh, telefone, cpf);
                    
                    break;
                case 2:
                    System.out.print("\nCPF do cliente a remover: ");
                    String cpfRemoverEntrada = scanner.nextLine();
                    String cpfRemover = formatarCPF(cpfRemoverEntrada);
                    if (cpfRemover == null) {
                        System.out.println("CPF inválido. Cancelando remoção.");
                        break;
                    }
                    Cliente.removerCliente(cpfRemover);
                    break;
                case 3:
                    System.out.print("\nCPF do cliente a editar: ");
                    String cpfEditarEntrada = scanner.nextLine();
                    String cpfEditar = formatarCPF(cpfEditarEntrada);
                    if (cpfEditar == null) {
                        System.out.println("CPF inválido. Cancelando edição.");
                        break;
                    }
                    
                    System.out.print("Novo nome: ");
                    String nomeNovo = scanner.nextLine();
                    
                    System.out.print("Nova CNH: ");
                    String cnhNovaEntrada = scanner.nextLine();
                    String cnhNova = formatarCNH(cnhNovaEntrada);
                    if (cnhNova == null) {
                        System.out.println("CNH inválida. Cancelando cadastro.");
                        break;
                    }
                    
                    System.out.print("Novo telefone: ");
                    String telefoneNovoEntrada = scanner.nextLine();
                    String telefoneNovo = formatarTelefone(telefoneNovoEntrada);
                    if (telefoneNovo == null) {
                        System.out.println("Telefone inválido. Cancelando cadastro.");
                        break;
                    }                    
                    Cliente.editarCliente(cpfEditar, nomeNovo, cnhNova, telefoneNovo);
                    break;
                case 4:
                    Cliente.listarClientes(true);
                    break;
                case 5:
                    Cliente.listarClientes(false);
                    break;
                case 0:
                    break;
                default:
                    System.out.println("\nOpção inválida! ");
            }
        } while (opcao != 0);
    }

    public static void menuVeiculos(Scanner scanner) {
        int opcao = -1;
        do {
            System.out.println("\n .-.-.-.-.-.-.-.-. MENU VEÍCULOS .-.-.-.-.-.-.-.-. \n");
            System.out.println("\t 1. Inserir veículo");
            System.out.println("\t 2. Excluir veículo");
            System.out.println("\t 3. Editar veículo");
            System.out.println("\t 4. Listar veículos (início -> fim)");
            System.out.println("\t 5. Listar veículos (fim -> início)");
            System.out.println("\t 0. <- Voltar");
            System.out.println("\n .-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-.-. \n");
            System.out.print(">> Escolha uma das opções acima : ");
            try {
                opcao = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida! Digite apenas números.");
                scanner.nextLine();
                continue;
            }
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    System.out.print("\nPlaca: ");
                    String placa = scanner.nextLine();
                    System.out.print("Modelo: ");
                    String modelo = scanner.nextLine();
                    System.out.print("Marca: ");
                    String marca = scanner.nextLine();
                    System.out.print("Ano: ");
                    int ano = scanner.nextInt();
                    System.out.print("Potência: ");
                    int potencia = scanner.nextInt();
                    System.out.print("Número de lugares: ");
                    int lugares = scanner.nextInt();
                    System.out.print("ID da Categoria: ");
                    int idCategoria = scanner.nextInt();
                    scanner.nextLine();
                    Categoria categoria = Categoria.buscarCategoria(idCategoria);
                    
                    if (categoria != null) {
                        Veiculo.cadastrarVeiculo(placa, modelo, marca, ano, potencia, lugares, categoria);
                    } else {
                        System.out.println("\nCategoria não encontrada!");
                    }
                    
                    break;
                case 2:
                    System.out.print("\nPlaca do veículo a remover: ");
                    String placaRemover = scanner.nextLine();
                    Veiculo.removerVeiculo(placaRemover);
                    break;
                case 3:
                    System.out.print("\nPlaca do veículo a editar: ");
                    String placaEditar = scanner.nextLine();
                    System.out.print("Novo modelo: ");
                    String novoModelo = scanner.nextLine();
                    System.out.print("Nova marca: ");
                    String novaMarca = scanner.nextLine();
                    System.out.print("Novo ano: ");
                    int novoAno = scanner.nextInt();
                    System.out.print("Nova potência: ");
                    int novaPotencia = scanner.nextInt();
                    System.out.print("Novo número de lugares: ");
                    int novosLugares = scanner.nextInt();
                    System.out.print("Nova categoria (ID): ");
                    int novaCategoriaId = scanner.nextInt();
                    scanner.nextLine();
                    Categoria novaCategoria = Categoria.buscarCategoria(novaCategoriaId);
                    
                    if (novaCategoria != null) {
                        Veiculo.editarVeiculo(placaEditar, novoModelo, novaMarca, novoAno, novaPotencia, novosLugares, novaCategoria);
                    } else {
                        System.out.println("\nCategoria não encontrada!");
                    }
                    break;
                case 4:
                    Veiculo.listarVeiculos(true);
                    break;
                case 5:
                    Veiculo.listarVeiculos(false);
                    break;
                case 0:
                    break;
                default:
                    System.out.println("\nOpção inválida!");
            }
        } while (opcao != 0);
    }
    
    public static void menuCategorias(Scanner scanner) {
        int opcao = -1;
        do {
            System.out.println("\n ._._._._._._._._. MENU CATEGORIAS ._._._._._._._._. \n");
            System.out.println("\t 1. Inserir categoria");
            System.out.println("\t 2. Excluir categoria");
            System.out.println("\t 3. Editar categoria");
            System.out.println("\t 4. Listar categorias");
            System.out.println("\t 0. <- Voltar");
            System.out.println("\n ._._._._._._._._._._._._._._._._._._._._._._._._. \n");
            System.out.print(">> Escolha uma das opções acima : ");
            try {
                opcao = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida! Digite apenas números.");
                scanner.nextLine();
                continue;
            }            
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    System.out.print("\nNome da categoria: ");
                    String nome = scanner.nextLine();
                    System.out.print("Identificador: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    Categoria.cadastrarCategoria(nome, id);
                    break;
                case 2:
                    System.out.print("\nIdentificador da categoria a remover: ");
                    int idRemover = scanner.nextInt();
                    scanner.nextLine();
                    Categoria.removerCategoria(idRemover);
                    break;
                case 3:
                    System.out.print("\nIdentificador da categoria a editar: ");
                    int idEditar = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Novo nome: ");
                    String novoNome = scanner.nextLine();
                    Categoria.editarCategoria(idEditar, novoNome);
                    break;
                case 4:
                    Categoria.listarCategorias(true);
                    break;
                case 0:
                    break;
                default:
                    System.out.println("\nOpção inválida!");
            }
        } while (opcao != 0);
    }

    public static void menuLocacoes(Scanner scanner) {
        int opcao = -1;
        do {
            System.out.println("\n _._._._._._._._._ MENU LOCAÇÕES _._._._._._._._._ \n");
            System.out.println("\t 1. Locar veículo");
            System.out.println("\t 2. Devolver veículo");
            System.out.println("\t 3. Listar locações ativas");
            System.out.println("\t 0. <- Voltar");
            System.out.println("\n _._._._._._._._._._._._._._._._._._._._._._._._._ \n");
            System.out.print(">> Escolha uma das opções acima : ");
            try {
                opcao = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida! Digite apenas números.");
                scanner.nextLine();
                continue;
            }            
            scanner.nextLine();

            switch (opcao) {
                case 1:
                    System.out.print("\nCNH do cliente: ");
                    String cnh = scanner.nextLine();
                    System.out.print("Placa do veículo: ");
                    String placa = scanner.nextLine();
                    System.out.print("Data de retirada (AAAA-MM-DD): ");
                    LocalDate retirada = LocalDate.parse(scanner.nextLine());
                    System.out.print("Data de devolução (AAAA-MM-DD): ");
                    LocalDate devolucao = LocalDate.parse(scanner.nextLine());
                    System.out.print("Valor: ");
                    double valor = scanner.nextDouble();
                    scanner.nextLine();
                    //Locacao.locarVeiculo(cnh, placa, retirada, devolucao, valor);
                    break;
                case 2:
                    System.out.print("\nPlaca do veículo para devolução: ");
                    String placaDevolver = scanner.nextLine();
                    //Locacao.devolverVeiculo(placaDevolver);
                    break;
                case 3:
                    //Locacao.listarLocacoesAtivas();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("\nOpção inválida!");
            }
        } while (opcao != 0);
    }
}
