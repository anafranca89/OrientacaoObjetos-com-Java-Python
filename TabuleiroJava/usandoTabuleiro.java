import java.util.Scanner;

public class usandoTabuleiro {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        // Leitura da configuração inicial
        String linhaInicial = scanner.nextLine();
        String[] partes = linhaInicial.split(" ");
        int[] numeros = new int[partes.length];
        for (int i = 0; i < partes.length; i++) {
            numeros[i] = Integer.parseInt(partes[i]);
        }

       

        tabuleiro t = new tabuleiro();
        t.criar(numeros);

        // print do tabuleiro inicial
        t.print();

        
        if (scanner.hasNext()) {
            String movimentos = scanner.next(); // Lê a string de movimentos completa
            for (char mov : movimentos.toCharArray()) {
                
                if (mov == 'r') {
                    t.move_right();
                } else if (mov == 'l') {
                    t.move_left();
                } else if (mov == 'u') {
                    t.move_up();
                } else if (mov == 'd') {
                    t.move_down();
                }
                t.print(); // print do tabuleiro após cada movimento
                }
        }
        

        // Verificação final
        if (t.resolvido()) {
            System.out.println("Posicao final: true");
        } else {
            System.out.println("Posicao final: false");
        }

        scanner.close();
    }
}