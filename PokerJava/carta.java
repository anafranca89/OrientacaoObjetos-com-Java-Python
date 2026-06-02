import java.util.Random;

public class carta {
  
    private Naipe naipe ;
    private int valor;

    public carta(int valor, Naipe naipe) {
           this.naipe = naipe;
           this.valor = valor;
    }

    public String getNaipe() {
        if (naipe == Naipe.Copas) {
            return "♥";
        } else if (naipe == Naipe.Ouros) {
            return "♦";
        } else if (naipe == Naipe.Paus) {
            return "♣";
        } else {
            return "♠";
        }
    }

    public String getValor() {
        if (valor == 11) {
            return "J"; // Valete
        } else if (valor == 12) {
            return "Q"; // Dama
        } else if (valor == 13) {
            return "K"; // Rei
        } else if (valor == 14) {
            return "A"; // Ás
        }
        return String.valueOf(valor);
    }
    public int getValorNumerico() {
        return valor;
    }

   public String[] obter_fatias() {
        String[] fatias = new String[5];
        String v = getValor();
        String borda = "+-----+";
        String linha1 = "|     |";
        String linha2;
        if (v.equals("10")) {
            linha2 = String.format("| %s%s |", getValor(), getNaipe());
        }else{
            linha2 = String.format("| %s %s |", getValor(), getNaipe());
        }
        String linha3 = "|     |";
        fatias[0] = borda;
        fatias[1] = linha1;
        fatias[2] = linha2;
        fatias[3] = linha3;
        fatias[4] = borda;
        return fatias;

    
    }
}