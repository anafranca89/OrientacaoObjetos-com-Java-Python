import java.util.*;
public class GrafoMatrizAdj extends Grafo{

    private int[][] matriz;
    
    public GrafoMatrizAdj(){
        super();//construtor dos metodos da herança
        this.matriz = new int[0][0]; //inicia matriz 0x0
    }

    @Override
    public void adicionarAresta(String origem, String destino){
        adicionarVertice(origem);
        aumentaGrafo();
        adicionarVertice(destino);
        aumentaGrafo();
        int i = vertmap.get(origem);
        int j = vertmap.get(destino);
        matriz[i][j] = 1;        // 1 existe aresta. 0 não existe
        matriz[j][i] = 1;
    }


    @Override
    public void removerVertice(String vertice){
        //remove o vertice e todas as arestas a ele conectadas
        if(!existeVertice(vertice)) return;
        int index_vertice = vertmap.get(vertice);
        for (int j = 0; j < matriz.length; j++) {
            matriz[index_vertice][j] = 0; 
            matriz[j][index_vertice] = 0;
        }
        vertmap.remove(vertice);
        

    }
    @Override
    public void removerAresta(String origem, String destino){
        if (!existeVertice(origem) || !existeVertice(destino)) return;
        int a = vertmap.get(origem);
        int b = vertmap.get(destino);

        matriz[a][b] = 0;
        matriz[b][a] = 0;
    }
    @Override
    public boolean existeAresta(String origem, String destino){
        //existe os vertices?
        if(!existeVertice(origem) || !existeVertice(destino)) return false;
        //existe o vertice, então verifica se existe a aresta
        if(matriz[vertmap.get(origem)][vertmap.get(destino)] == 1){
            return true;
        }else return false;

    }
    @Override
    public int tamanho(){
        //conta as arestas n repetidas
        // ou seja, conta apenas o triângulo superior ou inferior divido pela diagonal
        int tam =0;
        for(int i=0; i< matriz.length; i++){
            for(int j=i; j< matriz[i].length; j++){
                if(matriz[i][j] ==1) tam++;
            }
        }
        return tam;
    }

    @Override
    public int grau(String vertice){
        //vertice existe?
        if(!existeVertice(vertice)) return 0;
        int a = vertmap.get(vertice);
        int count=0;
        for (int qtd : matriz[a]){
            if (qtd==1) count++;
        }
        return count;
    }
    
    public void aumentaGrafo(){
        int n = matriz.length+1;
        int[][] novamatriz = new int[n][n];
        for(int i=0; i<matriz.length; i++){
            System.arraycopy(matriz[i], 0, novamatriz[i],0,matriz[i].length);
    
        }
        this.matriz = novamatriz;

    }

    @Override
    public String toString(){

        List<String> isolados = new ArrayList<>();
        List<String> linhasArestas = new ArrayList<>();
        List<String> chavesOrdenadas = new ArrayList<>(vertmap.keySet());
        for (String v : chavesOrdenadas) {
            //se n tem vizinhos ... imprime o vertice sozinho
            if (grau(v)==0) {
                isolados.add("    \"" + v + "\";"); 
            }
        }

        for (int i = 0; i < chavesOrdenadas.size(); i++) {
            String v1 = chavesOrdenadas.get(i);
            for (int j = i; j < chavesOrdenadas.size(); j++) {
                String v2 = chavesOrdenadas.get(j);
                if (matriz[vertmap.get(v1)][vertmap.get(v2)]==1) {
                    linhasArestas.add("    \"" + v1 + "\" -- \"" + v2 + "\";");
                }
            }
        }

        Collections.sort(isolados);
        Collections.sort(linhasArestas);
        StringBuilder sb = new StringBuilder("graph {\n");
        
        for (String s : isolados) sb.append(s).append("\n");
        for (String l : linhasArestas) sb.append(l).append("\n");
    
        sb.append("}");
        return sb.toString();
    }



}

