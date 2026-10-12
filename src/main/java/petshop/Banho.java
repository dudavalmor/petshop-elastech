package petshop;

public class Banho implements Servico{

    @Override
    public void executar(String nomePet){
        System.out.println("Dando banho no pet " + nomePet);
    }
}
