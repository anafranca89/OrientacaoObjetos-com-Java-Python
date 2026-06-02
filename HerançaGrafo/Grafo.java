import java.util.*;
public abstract class Grafo{
    protected Map<String, Integer> vertmap;

    //construtor 
    public Grafo(){
        //mantem relação entre a string vertice e uma chave ordenada.
        this.vertmap = new TreeMap<>();
    }
    //metodos q sao iguais em todas as classes
    //metodos CONCRETOS
    public boolean existeVertice(String vertice){
        return vertmap.containsKey(vertice);
    }
    public int ordem(){
        return vertmap.size();
    }
    public void adicionarVertice(String vertice){
        if(!existeVertice(vertice)){
            //chave inicia com 0, se size =0.
            vertmap.put(vertice, vertmap.size()) ;
        }
    }
    


    //métodos q a descrição e diferente p/ cada grafo
    //metodos abstratos
    public abstract void removerVertice(String vertice); 
    public abstract void adicionarAresta(String origem, String destino);
    public abstract void removerAresta(String origem, String destino);
    public abstract boolean existeAresta(String origem, String destino);
    public abstract int tamanho();
    public abstract int grau(String vertice);
    public abstract String toString();


}