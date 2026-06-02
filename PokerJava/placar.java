import java.util.*;
public class placar {
/*
Calcula a pontuação baseado na jogada e o valor da aposta
*/ 
public static int calculoPontos(Vector<carta> mao, int aposta) {
    int pontos = pontuacao(mao);
    return pontos * aposta;
}



/*   lógica para entender as combinações na mao
     Retornar um valor inteiro representando a pontuação da combinação,
     depois deve multiplicar pelo valor da aposta
*/ 

    public static int pontuacao(Vector<carta> mao) {
        //VERIFICA PRIMEIROS AS COMBINAÇÕES MAIS VALIOSAS
        int pontos =0;
        if(royalflush(mao)) return 200;
        if(straightflush(mao)) return 100;

        int[] frequencia = new int[15]; // indices 0 e 1 não será usado,inicia tudo com zero: índices 2-14 representam os valores das cartas
        
        for (carta c : mao) {
            int valor = c.getValorNumerico(); // Extrai o valor numérico da carta
            frequencia[valor]++;
        }



        //Verifica FLUSH e STRAIGHT e guarda a pontuação.
        // Se caso tiver Quadra ou full hand que valem mais
        if(flush(mao)) pontos = 10;
        else{
            if(straight(mao)) pontos = 5;
        }


        //Dois pares: 2 pares de cartas com o mesmo valor retorna 1 
        //Trinca : 3 cartas com o mesmo valor retorna 2
        //Quadra: 4 cartas com o mesmo valor retorna 50
        int pares = 0;
        int trinca =0;
        int quadra = 0;
        for(int contar : frequencia){
            if(contar == 2)pares++;
            if(contar == 3)trinca++;
            if(contar == 4)quadra++;
        }
        //QUADRA - mesmo que for flush quadra vale mais
        if(quadra == 1) return 50;
        //FULLHAND - 1 trinca e 1 par : mesmo que for flush fullhand vale mais
        if(trinca == 1 && pares == 1) return 20;

        //TRINCA - confere se houve pontuação maior antes
        if(trinca == 1){
            if(pontos>2) return pontos; 
            else return 2; 
        } 
        if (pares == 2){
            if(pontos>1) return pontos; 
            else return 1; 
        }
        return pontos; // Se não tiver nenhuma combinação, retorna 0 
    }



    public static boolean royalflush(Vector<carta> mao) {
        //PRECISA DA SEQUENCIA 10-A e DO MESMO NAIPE
        if(flush(mao)){
            if(sequencia_crescente(mao)){
                if(mao.get(0).getValorNumerico() == 10) return true;
            }
        }
        return false;
    }


    public static boolean straightflush(Vector<carta> mao) {
    //TEM QUE SER STRAIGHT E FLUSH AO MESMO TEMPO
    //SE TIVER NAIPES IGUAIS VERIFICA OS VALORES ESTAO EM SEQUENCIA CRESCENTE
        if(flush(mao)){
            if(sequencia_crescente(mao)) return true; 
        }
        return false;
    }





    public static boolean flush(Vector<carta> mao) {
        //Todas as cartas do mesmo naipe, nao seguidas
        String naipe = mao.get(0).getNaipe();
        for (carta c : mao) {
            // Se encontrar um naipe diferente, não é um flush
            if (!c.getNaipe().equals(naipe)) return false;
        }
        return true; 
    }


    public static boolean straight(Vector<carta> mao) {
        //Todas as cartas em sequência, nao do mesmo naipe

        if(flush(mao)) return false; // se é um flush nao pode ser um straight
        if(sequencia_crescente(mao)) return true; // Se for em sequência crescente, é um straight
        return false;
    }





    public static boolean sequencia_crescente(Vector<carta> mao) {
        //Todas as cartas em sequência, nao do mesmo naipe
        mao.sort((c1, c2) -> Integer.compare(c1.getValorNumerico(), c2.getValorNumerico()));
        int contagem = mao.get(0).getValorNumerico();
        //Se a primeira carta é A, testa a sequencia A2345
        if (contagem == 14) contagem =2; 
        for (carta c : mao) {
            int valor_carta =  c.getValorNumerico();
            if(valor_carta != contagem) return false; // Se não estiver em sequência, não é um straight
            contagem++;         
        }
        return true;
    }  

}