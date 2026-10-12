
package petshop;

import java.util.*;

public class PetShop {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        ArrayList<Pet> listaPet = new ArrayList<>();
        ArrayDeque<String> fila = new ArrayDeque<>();

        int opcao = -1;

        while (opcao != 0) {
            System.out.println("\n=== PETSHOP ===");
            System.out.println("1 - Cadastrar seu pet");
            System.out.println("2 - Listar pets cadastrados");
            System.out.println("3 - Buscar pelo nome");
            System.out.println("4 - Ver espécies cadastradas");
            System.out.println("5 - Entrar na fila de banho");
            System.out.println("6 - Atender próximo da fila");
            System.out.println("7 - Executar serviços do petshop");
            System.out.println("8 - Quantidade de pets cadastrados");
            System.out.println("9 - Filtrar por porte");
            System.out.println("10 - Atender todos");
            System.out.println("0 - Sair");
            System.out.print("Digite a opção desejada: ");

            try {
                opcao = sc.nextInt();
                sc.nextLine();

                switch (opcao) {

                    case 1: {
                        System.out.print("Nome do pet: ");
                        String nome = sc.nextLine();

                        System.out.print("Espécie: ");
                        String especie = sc.nextLine();

                        Pet novoPet;

                        if (especie.equalsIgnoreCase("Cachorro")) {
                            novoPet = new Cachorro();
                        } else if (especie.equalsIgnoreCase("Gato")) {
                            novoPet = new Gato();
                        } else {
                            novoPet = new Pet();
                        }

                        novoPet.nome = nome;
                        novoPet.especie = especie;

                        System.out.print("Peso (kg): ");
                        novoPet.peso = sc.nextDouble();
                        sc.nextLine();

                        novoPet.porte = novoPet.definirPorte(novoPet.peso);

                        listaPet.add(novoPet);

                        System.out.println("Pet cadastrado! Agora temos " + listaPet.size() + " pets cadastrados.");
                        break;
                    }

                    case 2: {
                        if (listaPet.isEmpty()) {
                            System.out.println("Não há pets cadastrados.");
                        } else {
                            System.out.println("\n--- PETS CADASTRADOS ---");

                            for (Pet pet : listaPet) {
                                System.out.println("Nome: " + pet.nome + " | Espécie: " + pet.especie + " | Peso: " + pet.peso + " kg" + " | Porte: " + pet.porte);
                                pet.emitirSom();
                            }
                        }
                        break;
                    }

                    case 3: {
                        System.out.print("Digite o nome do pet que você procura: ");
                        String busca = sc.nextLine();

                        boolean achou = false;

                        for (Pet pet : listaPet) {
                            if (pet.nome.equalsIgnoreCase(busca)) {
                                System.out.println("Pet encontrado: " + pet.nome + " (" + pet.especie + ") - " + pet.peso + " kg");

                                achou = true;
                                break;
                            }
                        }

                        if (!achou) {
                            System.out.println("Pet não encontrado.");
                        }
                        break;
                    }

                    case 4: {
                        HashSet<String> especies = new HashSet<>();

                        for (Pet pet : listaPet) {
                            String especie = pet.especie.trim().toLowerCase();

                            if (!especie.isEmpty()) {
                                especie = especie.substring(0, 1).toUpperCase() + especie.substring(1);
                                especies.add(especie);
                            }
                        }

                        System.out.println("\n--- ESPÉCIES CADASTRADAS ---");

                        for (String especie : especies) {
                            System.out.println("- " + especie);
                        }

                        System.out.println("Total de espécies diferentes: " + especies.size());
                        break;
                    }

                    case 5: {
                        System.out.print("Nome do pet para entrar na fila do banho: ");
                        String nomeFila = sc.nextLine();

                        fila.add(nomeFila);

                        System.out.println(nomeFila + " entrou na fila do banho.");
                        System.out.println("Fila agora: " + fila);
                        break;
                    }

                    case 6: {
                        if (fila.isEmpty()) {
                            System.out.println("A fila do banho está vazia.");
                        } else {
                            String proximo = fila.peek();

                            System.out.println("Próximo da fila: " + proximo);

                            String atendido = fila.poll();

                            System.out.println("Atendendo o pet " + atendido);
                            System.out.println("Fila agora: " + fila);
                        }
                        break;
                    }

                    case 7: {
                        System.out.print("Nome do pet: ");
                        String nomePet = sc.nextLine();

                        ArrayList<Servico> servicos = new ArrayList<>();

                        servicos.add(new Banho());
                        servicos.add(new Tosa());
                        servicos.add(new Consulta());
                        servicos.add(new Vacina());

                        for (Servico servico : servicos) {
                            servico.executar(nomePet);
                        }
                        break;
                    }

                    case 8:
                        System.out.println("Quantidade de pets cadastrados: " + listaPet.size());
                        break;

                    case 9: {
                        System.out.print("Qual porte deseja consultar? ");
                        String buscaPorte = sc.nextLine();

                        boolean encontrou = false;

                        for (Pet pet : listaPet) {
                            if (pet.porte.equalsIgnoreCase(buscaPorte)) {
                                System.out.println(pet.nome + " (" + pet.especie + ") - " + pet.peso + " kg - Porte " + pet.porte);
                                encontrou = true;
                            }
                        }

                        if (!encontrou) {
                            System.out.println("Nenhum pet foi encontrado.");
                        }
                        break;
                    }

                    case 10: {
                        if (fila.isEmpty()) {
                            System.out.println("A fila do banho está vazia.");
                        } else {
                            while (!fila.isEmpty()) {
                                String atendido = fila.poll();

                                System.out.println("Atendendo " + atendido);
                            }

                            System.out.println("Todos os pets foram atendidos.");
                            System.out.println("Fila agora: " + fila);
                        }
                        break;
                    }

                    case 0:
                        System.out.println("Encerrando programa.");
                        break;

                    default:
                        System.out.println("Opção inválida!");
                        break;
                }

            } catch (InputMismatchException e) {
                System.out.println("Digite apenas números!");
                sc.nextLine();
            }
        }

        if (!listaPet.isEmpty()) {
            System.out.println();

            mostrarFicha(listaPet.get(0));

            System.out.println("---");

            mostrarFicha(listaPet.get(0), "=== FICHA DO PET ===");
        }

        if (!listaPet.isEmpty() && listaPet.get(0) instanceof Cachorro) {
            Cachorro cachorro = (Cachorro) listaPet.get(0);
            cachorro.buscarBolinha();
        }

        sc.close();
    }

    static void mostrarFicha(Pet pet) {
        System.out.println("Nome: " + pet.nome);
    }

    static void mostrarFicha(Pet pet, String titulo) {
        System.out.println(titulo);
        System.out.println("Nome: " + pet.nome);
        System.out.println("Espécie: " + pet.especie);
        System.out.println("Peso: " + pet.peso + " kg");
        System.out.println("Porte: " + pet.porte);
    }
}
