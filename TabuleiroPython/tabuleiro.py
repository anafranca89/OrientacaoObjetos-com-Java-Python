class Tabuleiro:
    #criar as caracteristicas da classe
    def __init__(self, lista):
        self.tamanho = int((len(lista) ** 0.5))
        self.matriz = [[lista[i * self.tamanho + j] for j in range(self.tamanho)] for i in range(self.tamanho)]

        for i in range(self.tamanho):
            for j in range(self.tamanho):
                if self.matriz[i][j] == 0:
                    self.posxZERO = i
                    self.posyZERO = j
                    break




    def mover(self, direcao = 'c'):
        #se o valor vazio estiver na primeira linha, não pode mover pra cima
        # SE FOR UP -> move o vazio para BAIXO!!!

        # swap  a,b = b,a !!!
        if direcao == 'u' and self.tamanho -1 > self.posxZERO :
            self.matriz[self.posxZERO][self.posyZERO], self.matriz[self.posxZERO + 1][self.posyZERO] = self.matriz[self.posxZERO + 1][self.posyZERO], self.matriz[self.posxZERO][self.posyZERO]
            self.posxZERO += 1
        #se o valor vazio estiver na última linha, não pode mover pra baixo
        # se for DOWN -> move o vazio pra cima !!!
        elif direcao == 'd' and self.posxZERO > 0:
            self.matriz[self.posxZERO][self.posyZERO], self.matriz[self.posxZERO - 1][self.posyZERO] = self.matriz[self.posxZERO - 1][self.posyZERO], self.matriz[self.posxZERO][self.posyZERO]
            self.posxZERO -= 1
        #se o valor vazio estiver na primeira coluna, não pode mover pra esquerda
        #Se LEFT0-> move o vazio pra direita !!!
        elif direcao == 'l' and self.posyZERO < self.tamanho - 1:
            self.matriz[self.posxZERO][self.posyZERO], self.matriz[self.posxZERO][self.posyZERO + 1] = self.matriz[self.posxZERO][self.posyZERO + 1], self.matriz[self.posxZERO][self.posyZERO]
            self.posyZERO += 1
        #se o valor vazio estiver na última coluna, não pode mover pra direita
        #se for RIGHT -> move o vazio pra esquerda !!!
        elif direcao == 'r' and self.posyZERO > 0:
            self.matriz[self.posxZERO][self.posyZERO], self.matriz[self.posxZERO][self.posyZERO - 1] = self.matriz[self.posxZERO][self.posyZERO - 1], self.matriz[self.posxZERO][self.posyZERO]
            self.posyZERO -= 1







    def resolvido(self):
        num_esperado =0

        for i in range(self.tamanho):
            for j in range(self.tamanho):
                if (num_esperado != self.matriz[i][j]):
                    #elemento nao esta na ordem
                    return False
            
                num_esperado+=1
    
        return True










    def printar(self):
        separador = '+'
        for i in range(self.tamanho):
            separador += "------+"
        print(separador)
        for i in range(self.tamanho):
            linha = '|'
            for j in range(self.tamanho):
                if self.matriz[i][j] == 0:
                    linha += '      |'
                else:
                    linha += f'  {self.matriz[i][j]:2d}  |'
            print(linha)
            print(separador)
        
        print("\n", end="") # print apenas 1 \n
        