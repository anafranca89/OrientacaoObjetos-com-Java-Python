import sys
from tabuleiro import Tabuleiro 
def main():

    lista = list(map(int, input().split()))
    comandos = input()
    tabuleiro = Tabuleiro(lista)
    tabuleiro.printar()
    #mover o tabuleiro
    for comando in comandos:
        tabuleiro.mover(comando)
        tabuleiro.printar()
    #verificar se o tabuleiro esta resolvido
    # Verificar se o tabuleiro está resolvido
    if tabuleiro.resolvido():
        print("Posicao final: True")
    else:
        print("Posicao final: False")








if __name__ == "__main__":
    main()