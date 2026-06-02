from dado import dado
from random import Random

class rolarDado:
    def __init__(self, seed = 0):
        self.nDados = 5
        self.seed = seed  # valor default: 0 que retorna aleatorio
        self.dados = []
        
        if self.seed != 0:
            mestre = Random()  # gerador com seed
            mestre.seed(self.seed)
            
        for i in range(self.nDados):
            if self.seed == 0:
                self.dados.append(dado())
            else:
                semente_filha = mestre.randint(1, 10000)
                self.dados.append(dado(6, semente_filha))



    def rolar(self, lista = [1,1,1,1,1]):
        
         # se a lista estiver vazia, rola todos os dados
        # rola o dado, apenas se ele esta marcado como 1 usando a semente fornecida
        # usando o enumerate para ter o indice da lista
        for i, d in enumerate(self.dados):
            if lista[i] == 1:
                d.rolar()


    
   
    '''
    def __str__(self):
        #print(rolarDado)   deve mostrar o estado de todos os 5 dados
        r = []
        cabecalho = ""
        for i in range(1, 6):
            cabecalho += f"{i}".ljust(11)


        for d in self.dados:
           r.append(str(d))
        
        resultado = "    ".join(r)
        return resultado
    '''



    def __str__(self):
            # cabeçalho com largura fixa
        todas = [d.obter_fatias() for d in self.dados]

        linhas = []

        linhas.append("1          2          3          4          5")

        for i in range(5):
            linha = ""
            for fatia in todas:
                linha += fatia[i] + "    "
            linhas.append(linha)

        return "\n".join(linhas)

