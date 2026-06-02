import java.util.Arrays;


public class tabuleiro {                
  
    private int[][] matriz;     // tabuleiro de nxn tamanho
    private int tamanho;        // N da matriz
    private int posxZero;       // A posição x e y do vazio Zero.
    private int posyZero;



    public void criar(int[] configInicial) {
        // Recebe um array com a configuração inicial do tabuleiro e o converte para uma matriz
        // N = raiz quadrada da qtd de elementos do array
        this.tamanho = (int) Math.sqrt(configInicial.length);
        this.matriz = new int[tamanho][tamanho];
        
        int k = 0; // contador para percorrer o array configInicial
        for (int i = 0; i < tamanho; i++) {
            for (int j = 0; j < tamanho; j++) {
                this.matriz[i][j] = configInicial[k];
                if (configInicial[k] == 0) {
                    //é o zero? guarda a posição do zero
                    this.posxZero = i;
                    this.posyZero = j;
                }
                k++;
            }
        }
    }





// os movimentos estao trocados!!
    public void move_right( ) {
    
        // só não move se o zero estiver na ultima coluna
            if (posyZero > 0) {
                // Troca o zero com o elemento à direita
                int temp = matriz[posxZero][posyZero-1];
                matriz[posxZero][posyZero-1] = 0;
                matriz[posxZero][posyZero] = temp;
                posyZero--; // Atualiza a posição do zero
            }
        
 }

    public void move_left() {
         // só não move se o zero estiver na primeira coluna
            if (posyZero <tamanho - 1) {
                // Troca o zero com o elemento à esquerda
                int temp = matriz[posxZero][posyZero+1];
                matriz[posxZero][posyZero+1] = 0;
                matriz[posxZero][posyZero] = temp;
                posyZero++; // Atualiza a posição do zero
            }
    }


    public void move_up() {
            // só não move se o zero estiver na primeira linha
            if (posxZero <tamanho - 1) {
                // Troca o zero com o elemento à esquerda
                int temp = matriz[posxZero+1][posyZero];
                matriz[posxZero+1][posyZero] = 0;
                matriz[posxZero][posyZero] = temp;
                posxZero++; // Atualiza a posição do zero
            }
    }


    public void move_down( ) {
    // só não move se o zero estiver na ultima linha
            if (posxZero > 0) {
                // Troca o zero com o elemento à esquerda
                int temp = matriz[posxZero-1][posyZero];
                matriz[posxZero-1][posyZero] = 0;
                matriz[posxZero][posyZero] = temp;
                posxZero--; // Atualiza a posição do zero
            }
        
    }



    public boolean resolvido() {

        int num_esperado =0;

        for (int i = 0; i < tamanho; i++) {
            for (int j = 0; j < tamanho; j++) {
                if (num_esperado != matriz[i][j]) {
                    //elemento nao esta na ordem 
                    return false;
                }
                
                num_esperado++;
            }
            
        }
        return true;
        }    


public void print (){
            String separador = "+";
    for (int i = 0; i < tamanho; i++) {
        separador += "------+";
    }

    for (int i = 0; i < tamanho; i++) {
        System.out.println(separador); // Linha superior da célula
        
        System.out.print("|"); // Início da linha de números
        for (int j = 0; j < tamanho; j++) {
            // Formata o número para ocupar 4 espaços e centralizar minimamente
            // O %2d garante que números ocupem 2 espaços, espaços extras para alinhar
            System.out.printf("  %2d  |", matriz[i][j]);
        }
            System.out.println(); // Pula linha após imprimir os números da linha
        }
        System.out.println(separador); // Linha final do tabuleiro
        System.out.println (); // Pula linha 
        
        
        } 

}
