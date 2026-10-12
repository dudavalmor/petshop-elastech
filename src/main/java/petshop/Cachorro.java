package petshop;

public class Cachorro extends Pet{

    @Override
    void emitirSom(){
        System.out.println(nome + " está latindo. Au au.");
    }

    void buscarBolinha(){
        System.out.println(nome + " foi buscar a bolinha");
    }
}
