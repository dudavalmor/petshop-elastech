package petshop;

public class Vacina implements Servico{

    @Override
    public void executar(String nomePet){
        System.out.println("Aplicando vacina no pet " + nomePet);
    }
}
