import java.util.*;

public class GrafoPondMatrizAdj extends Grafo{
    private int[][] matriz;
    private int Nulo= -1;
    
    public GrafoPondMatrizAdj(){
        super();//construtor dos metodos da herança
        this.matriz = new int[0][0]; //inicia matriz 0x0
        for (int[] linha : matriz) {
             Arrays.fill(linha, -1);
        }
    }

    public void aumentaGrafo(){
        int n = matriz.length +1;
        int[][] novamatriz = new int[n][n];
        for (int[] linha : novamatriz) {
            Arrays.fill(linha, -1);
        }
        for(int i=0; i<matriz.length; i++){
            System.arraycopy(matriz[i], 0, novamatriz[i],0,matriz[i].length);
        }
        this.matriz = novamatriz;
    }



    @Override
    public void adicionarAresta(String origem, String destino){
        //aresta padrao - se n tiver peso
        adicionarAresta(origem,destino, -1);
    }


    public void adicionarAresta(String origem, String destino, int peso){
        adicionarVertice(origem);
        aumentaGrafo();
        adicionarVertice(destino);
        aumentaGrafo();
        int i = vertmap.get(origem);
        int j = vertmap.get(destino);

        matriz[i][j]=peso;
        matriz[j][i]=peso;
    }



    @Override
    public boolean existeAresta(String origem, String destino){
        if (!existeVertice(origem) || !existeVertice(destino)) return false;
        int a =vertmap.get(origem);
        int b= vertmap.get(destino);
        return matriz[a][b] != Nulo;
    }


    @Override
    public void removerVertice(String vertice){
        //vertice existe? se n, n faz nada
        if (!existeVertice(vertice)) return;

        int index_vertice = vertmap.get(vertice);
        for (int j = 0; j < matriz.length; j++) {
            matriz[index_vertice][j] = Nulo; 
            matriz[j][index_vertice] = Nulo;
        }
        vertmap.remove(vertice);
        
    }

    @Override
    public void removerAresta(String origem, String destino){
        if (!existeVertice(origem) || !existeVertice(destino)) return;
        int i = vertmap.get(origem);
        int j = vertmap.get(destino);
        matriz[i][j] = Nulo;
        matriz[j][i] =  Nulo;
    }

    @Override
    public int tamanho(){
        //conta as arestas n repetidas
        // ou seja, conta apenas o triângulo superior ou inferior divido pela diagonal
        int tam =0;
        for(int i=0; i< matriz.length; i++){
            for(int j=i; j< matriz[i].length; j++){
                if(matriz[i][j] != Nulo) tam++;
            }
        }
        return tam;
    }


    @Override
    public int grau(String vertice){
        if (!existeVertice(vertice)) return 0;
        int a = vertmap.get(vertice);
        int count = 0;
        for (int qtd : matriz[a]){
            //peso da aresta é diferente de nulo?
            if (qtd!=Nulo) count++;
        }
        return count;
    }


    @Override
    public String toString(){
        
        List<String> isolados = new ArrayList<>();
        List<String> aresta = new ArrayList<>();
        List<String> chavesOrdenadas = new ArrayList<>(vertmap.keySet());
    
        for (String v : chavesOrdenadas) {
            //se n tem vizinhos ... imprime o vertice sozinho
            if (grau(v)==0) {
                isolados.add("    \"" + v + "\";"); 
            }
        }

        for (int i = 0; i < chavesOrdenadas.size(); i++) {
            String v1 = chavesOrdenadas.get(i);
            for (int j = 0; j < chavesOrdenadas.size(); j++) {
                String v2 = chavesOrdenadas.get(j);
                if (v1.compareTo(v2) < 0) {
                    int peso = matriz[vertmap.get(v1)][vertmap.get(v2)];
                    if (peso!= Nulo) {
                        aresta.add("    \"" + v1 + "\" -- \"" + v2 + "\" [label=\"" + peso + "\"];");
                        }
                }
            }
        }
        Collections.sort(isolados);
        Collections.sort(aresta);

        StringBuilder sb = new StringBuilder("graph {\n");
        
        for (String s : isolados) sb.append(s).append("\n");
        for (String l : aresta) sb.append(l).append("\n");

        sb.append("}");
        return sb.toString();
    }
}