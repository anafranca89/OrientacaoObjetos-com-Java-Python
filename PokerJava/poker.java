import java.util.Scanner;
import java.util.Vector;
import java.util.*;
public class poker{
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Semente: ");
        int seed = scan.nextInt();
        Baralho baralho = new Baralho(seed);
        baralho.embaralhar();
        Vector<carta> mao = new Vector<carta>();
        ArrayList<carta> descarte = new ArrayList<carta>(); 

        System.out.print("Saldo inicial: ");
        int saldo = scan.nextInt();
        scan.nextLine(); // Limpa o buffer do scanner


       //TESTES!!!!!!!!!! baralho.imprimir_baralho();

        
        while (true) {
            if (saldo == 0) {
                System.out.println("Seu saldo acabou. Tente jogar outra vez.");
                return;
            }

            System.out.println("Saldo atual: $"+saldo);
            System.out.print("Digite o valor da aposta of 'F' para terminar ==> ");
            String entrada = scan.nextLine();
            

            if (entrada.equals("F")) {
                System.out.print("Terminando o jogo... Parabéns você ainda tem saldo de $" + saldo);
                break;
            }
            int aposta = Integer.parseInt(entrada);
            if (aposta > saldo) {
                System.out.println("Saldo insuficiente. Tecle enter para continuar");
                scan.nextLine(); // Limpa o buffer do scanner
                continue;
            }
            // Aqui começa o jogo:atualiza o saldo e faz 2 tentativas de troca das cartas
            saldo -= aposta;
            // mostra 5 cartas e pergunta quais cartas o jogador quer trocar 

            for (int j = 0; j < 5; j++) {
                carta nova = baralho.comprar();
                mao.add(nova);
            }
            baralho.imprimir_jogada(mao);
            for (int i = 0; i < 2; i++) {
                //mostrar cartas
                System.out.print("Digite o número das cartas que você deseja trocar, separados por espaços: ");
                String cartasParaTrocar = scan.nextLine().trim();

                // Processar as cartas para trocar e atualizar a mão do jogador
                if (!cartasParaTrocar.isEmpty()) {
                    String[] indice_digitado = cartasParaTrocar.split("\\s+"); // Separa por um ou mais espaços
                    Arrays.sort(indice_digitado); // Ordena os índices pq SIM !
                    for (String s : indice_digitado) {
                            int indice = Integer.parseInt(s) - 1; // -1 porque o usuário vê 1-5, mas o array é 0-4
                            if (indice >= 0 && indice < 5) {
                                carta descartada = mao.get(indice);
                                carta nova = baralho.comprar();
                                mao.set(indice, nova);
                                //começa a pilha de descarte
                                descarte.add(descartada);
                            }
                    }
                }
                baralho.imprimir_jogada(mao);

             }
            //atualiza o baralho colocando a mao atual no fim e a pilha de descarte no fim
            for (carta c : mao) {
                baralho.devolvefim(c);
            }
            for (carta c : descarte) {
                baralho.devolvefim(c);
            }
            int ganhou = placar.calculoPontos(mao, aposta);
            mao.clear(); // Limpa a mão do jogador para a próxima rodada
            descarte.clear(); // Limpa a pilha de descarte para a próxima rodada
            //embaralha ao fim de cada rodada
            baralho.embaralhar();
            //TESTES....baralho.imprimir_baralho();

            //calcula a pontuação
            if(ganhou >0 ){
                System.out.println("Parabéns. Você acrescentou $" + ganhou +" ao seu saldo");
                saldo += ganhou;
            } else {
                System.out.println("Peninha... não ganhou nada nessa rodada");
            }
            System.out.println("Tecle enter para continuar"); 
            scan.nextLine(); // Limpa o buffer do scanner
        }
    }
}