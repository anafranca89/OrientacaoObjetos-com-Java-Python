import java.util.*;
public class GrafoListaAdj extends Grafo{

    private List<List<Integer>> adjacencia;

    public GrafoListaAdj(){
        super();
        this.adjacencia = new ArrayList<>();
    }

    private void aumentaGrafo() {
        int n = ordem();
        while (adjacencia.size() <= n) {
            adjacencia.add(new ArrayList<>());
        }
    }

    @Override
    public void adicionarAresta(String origem, String destino){
        adicionarVertice(origem);
        adicionarVertice(destino);
        int a = vertmap.get(origem);
        int b = vertmap.get(destino);
    
        aumentaGrafo();
        if (!adjacencia.get(a).contains(b)) {
            adjacencia.get(a).add(b);
            adjacencia.get(b).add(a);
        }
    }

    @Override
    public void removerAresta(String origem, String destino){
        if(!existeVertice(origem) || !existeVertice(destino)) return;
        int a = vertmap.get(origem);
        int b = vertmap.get(destino);
        adjacencia.get(a).remove(Integer.valueOf(b)); 
        adjacencia.get(b).remove(Integer.valueOf(a));
    }


    @Override
    public void removerVertice(String vertice){
        if (!existeVertice(vertice)) return;
        int index = vertmap.get(vertice);
        for (List<Integer> v : adjacencia) {
            v.remove(Integer.valueOf(index));
        }
        //limpa as arestas desse vertice
        adjacencia.get(index).clear();
        vertmap.remove(vertice);
    }


    @Override
    public boolean existeAresta(String origem, String destino){
        if (!existeVertice(origem) || !existeVertice(destino)) return false;
        int a = vertmap.get(origem);
        int b = vertmap.get(destino);
        return adjacencia.get(a).contains(b);
    }


    @Override
    public int grau(String vertice){
        //qtd de vizinhos do vertice
        if(!existeVertice(vertice)) return 0;
        return adjacencia.get(vertmap.get(vertice)).size();
    }
    @Override
    public int tamanho(){
        //qtd de arestas
        int arestas =0;
        for(List<Integer> v: adjacencia){
            arestas +=v.size();
        }
        return arestas/2;
    }

    @Override
    public String toString(){
        List<String> isolados = new ArrayList<>();
        List<String> arestasFormatadas = new ArrayList<>();
        List<String> chavesOrdenadas = new ArrayList<>(vertmap.keySet());
        

        for (String v : chavesOrdenadas) {
            //se n tem vizinhos ... imprime o vertice sozinho
            if (grau(v)==0) {
                isolados.add("    \"" + v + "\";"); 
            }
        }

        for (int i = 0; i < chavesOrdenadas.size(); i++) {
            String v1 = chavesOrdenadas.get(i);
            int idx1 = vertmap.get(v1);

            for (int j = i + 1; j < chavesOrdenadas.size(); j++) {
                String v2 = chavesOrdenadas.get(j);
                int idx2 = vertmap.get(v2);

                if (adjacencia.get(idx1).contains(idx2)) {
                    String menor = v1.compareTo(v2) < 0 ? v1 : v2;
                    String maior = v1.compareTo(v2) < 0 ? v2 : v1;
                    arestasFormatadas.add(String.format("    \"%s\" -- \"%s\";", menor, maior));
                }
            }
        }

        //organiza no formato pedido de impressao
        Collections.sort(isolados);
        Collections.sort(arestasFormatadas);

        StringBuilder sb = new StringBuilder("graph {\n");
        for (String s : isolados) sb.append(s).append("\n");
        for (String l : arestasFormatadas) sb.append(l).append("\n");
    
        
        sb.append("}");
        return sb.toString();
    }



}