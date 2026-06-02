import java.util.Random;
import java.util.Collections;
import java.util.Vector;

public class Baralho extends Vector<carta> {
    private Random rand;

    public Baralho(int seed){
        for (Naipe p : Naipe.values()) {
            for (int i=2; i<=14; i++) {
                carta c = new carta(i, p);
                this.add(c);
            }
        }
        if (seed == 0) {
            rand = new Random();
        }else {
            rand = new Random(seed);
        }
        this.embaralhar();
    }

    public void embaralhar() { 
        Collections.shuffle(this, rand);
    }

    //retira a primeira carta do baralho e retorna para o 'jogador'
    public carta comprar(){
        if (this.isEmpty()) return null; 
        return this.remove(0);
    }

    // devolve a carta para o baralho, colocando no final do vetor
    public void devolvefim(carta c) {
        if (c != null) {
            this.add(c);
        }
    }
    
   

    public void imprimir_jogada(Vector<carta> carta) {
        System.out.println();
       for (int i = 0; i < 5; i++) {
            StringBuilder linha = new StringBuilder();
            for (carta c : carta) {
                String[] fatias = c.obter_fatias();
                linha.append(fatias[i]).append(" "); //pega a fatia i de cada carta para imprimir horizontalmente
            }
            
             System.out.println(linha);
        }
        for (int i = 1; i <=5; i++) {
            if(i==5)System.out.printf("  (%d)", i);
            else System.out.printf("  (%d)   ", i);
        }
        // Imprime os índices embaixo
        System.out.println();
    }



    //função de teste para entender o que tem no baralho
    public void imprimir_baralho() {
        int reset=0;
        for (carta c : this) {
             if (reset == 5) {
                System.out.println();
                reset = 0;
            }
            System.out.print(c.getValor() + c.getNaipe()+"  -  ");
            reset++;
        }
    }
}
