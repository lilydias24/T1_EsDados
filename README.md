# T1_EsDados

1. Estruturas Básica
Implementar a estrutura de lista duplamente encadeada (para permitir navegação em ambos os sentidos) ✅
Implementar as classes básicas:
Cliente (nome, CNH, telefone, CPF) ✅
Veiculo (placa, modelo, marca, ano, potencia, lugares, categoria). ✅
Categoria (nome, identificador) ✅
Locacao (CNH cliente, placa veículo, datas, valor)

2. Operações CRUD
Implementar as operações para cada entidade:
Clientes: incluir, excluir, editar, listar (ambos sentidos) ✅
Veículos: incluir, excluir editar listar (ambos sentidos) ✅
Categorias: incluir, excluir, editar, listar ✅
Locações: locar, devolver, listar ativas ✅

3. Funcionalidades Avançadas
Implementar o menu principal e submenus ✅
Implementar leitura de arquivos CSV ❌
Implementar filtros para veículos disponíveis (por potência, lugares, categoria) 
Implementar ordenação dos veículos disponíveis (crescente/decrescente) ✅

###### ###### ###### ###### ###### ###### ###### ###### ###### ###### ###### ######

4. PROXIMOS PASSOS : 
-> Gerar dados em CSV
-> Resolver a questão do CRUD de Locação e acertar na MAIN
-> Organizar a estrutura dos códigos
    -> Sugestões de melhorias : 
   
    1. Criar pacotes para modularizar por entidade | No Java, uma forma clássica de organizar é dividir por pacotes:

        src/
        ├── model/         → Cliente.java, Veiculo.java, Categoria.java, Locacao.java
        ├── controller/    → CrudLocacao.java (e outros CRUDs se quiser separar)
        ├── estrutura/     → LDE.java, Noh.java
        ├── utils/         → métodos de formatação e leitura de CSV
        └── Main.java      → classe principal (pode ficar na raiz ou num pacote tipo app/)

    2. Extrair os métodos de formatação (formatarCPF, etc.) da MAIN para uma classe utilitária

        // utils/Formatador.java
        public class Formatador {
            public static String formatarCPF(String cpf) { ... }
            public static String formatarCNH(String cnh) { ... }
            public static String formatarTelefone(String telefone) { ... }
        }

        E no Main.java:
        import utils.Formatador;

        String cpf = Formatador.formatarCPF(cpfEntrada);

    3. Criar classes específicas para CrudCliente, CrudVeiculo, etc. ;-;-;-;-;-;-; @eric
    Parece que a lógica está toda no Main. Pode-se mover blocos como esse:

        Cliente.cadastrarCliente(nome, cnh, telefone, cpf);

        Para uma classe tipo:
        // controller/CrudCliente.java
        public class CrudCliente {
            public static void menuClientes(Scanner scanner) {
                ...
            }
        }

        E então no Main:

        CrudCliente.menuClientes(scanner);

    4. Criar um carregador de CSV separado (IDEIA)

        // utils/LeitorCSV.java
        public class LeitorCSV {
            public static void carregarCategorias(String caminho) { ... }
            public static void carregarVeiculos(String caminho) { ... }
        }


###### ###### ###### ###### ###### ###### ###### ###### ###### ###### ###### ######

-> Pontos principais do trabalho a serem conferidos : 

1. Uso correto das estruturas de dados implementadas do zero (sem ArrayList, LinkedList, etc.) [OK]

2. Cobertura de todos os requisitos funcionais: clientes, veículos, categorias, locações, ordenações, filtros. 

3. Restrições implementadas (não excluir itens atrelados, busca pelas chaves certas, etc.) 

4. Leitura de arquivos CSV

5. Menu funcional e interface no terminal

6. Organização e clareza do código

