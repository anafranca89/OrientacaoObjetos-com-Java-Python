import java.util.*;
public class main{
    public static void main(String[] args) {

        /*Ana Julia França 16838230*/ 
        GrafoMatrizAdj grafoMatriz = new GrafoMatrizAdj();
        GrafoListaAdj grafoLista = new GrafoListaAdj();
        GrafoPondMatrizAdj grafoPonderado = new GrafoPondMatrizAdj();
        Scanner scanner = new Scanner(java.util.Objects.requireNonNull(System.in));
        while (scanner.hasNextLine()) { 
            String linha = scanner.nextLine().trim();
            if (linha.isEmpty()) continue;

            String[] partes = linha.split("\\s+");
            String comando = partes[0];

            switch (comando) {
                case "i": 
                    if (partes.length >= 4) {
                        String v1 = partes[1];
                        String v2 = partes[2];
                        int peso = Integer.parseInt(partes[3]);

                        grafoLista.adicionarAresta(v1, v2);
                        grafoMatriz.adicionarAresta(v1, v2);
                        grafoPonderado.adicionarAresta(v1, v2, peso);
                    }
                    break;



                    case "d": 
                    if (partes.length == 3) { 
                        String v1 = partes[1];
                        String v2 = partes[2];
                        grafoLista.removerAresta(v1, v2);
                        grafoMatriz.removerAresta(v1, v2);
                        grafoPonderado.removerAresta(v1, v2);
                    } else if (partes.length == 2) { 
                        String v = partes[1];
                        grafoLista.removerVertice(v);
                        grafoMatriz.removerVertice(v);
                        grafoPonderado.removerVertice(v);
                    }
                    break;



                    case "p": 
                    System.out.println("Lista de Adjacencia"); 
                    System.out.println(grafoLista.toString());
                    System.out.println("Matriz de Adjacencia"); 
                    System.out.println(grafoMatriz.toString());
                    System.out.println("Ponderado - Matriz de Adjacencia"); 
                    System.out.println(grafoPonderado.toString());
                    break;
            }
        }
        scanner.close();
    
    }
}