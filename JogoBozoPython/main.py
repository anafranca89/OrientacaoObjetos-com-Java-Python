from rolarDado import rolarDado
from placar import placar
import random
import sys


'''
    FUNCAO CALCULO DE PONTUAÇÃO 
    SE posicao =1 pontuacao = soma quantos dados.valor ==1 
    se posicao = 2 pontuacao = soma quantos daod.valo == 2
    SE posicao =3 pontuacao = soma quantos dados.valor ==3 
    se posicao = 4 pontuacao =  soma quantos daod.valo == 4
    SE posicao =5 pontuacao = soma quantos dados.valor ==5 
    se posicao = 6 pontuacao =  soma quantos daod.valo == 6
    ---- SENAO pontuacao =0
    SE posicao = 7 FULL HAND   pontuacao == 15
        -> 3 dados.valor iguais e 2 dados.valor iguais
        -> OU 5 dados.valor iguais
        -> ELSE pontuacao = 0
    SE posicao = 8 SEQUENCIA pontuacao = 20
        -> se tiver seq. de 1 2 3 4 5 
        -> OU seq de 2 3 4 5 6 
        -> ELSE pontuacao =0
    SE posicao = 9 QUADRA pontuacao = 30
        -> 4 valores iguais
        ->ELSE pontuacao = 0
    SE posicao = 10 QUINA pontuacao =40
        -> 5 valores iguais
        ->ELSE pontuacao == 0
'''



def calculo_pontuacao(posicao, listadado):
    try:
        posicao = int(posicao)
    except (ValueError, TypeError):
        return 0
    points = 0
    if 0< posicao <7:
        for d in listadado:
            if d.getLados() == posicao:
                points += posicao

    if posicao == 7:
        count1 =0
        count2 =0
        v1 = listadado[0].getLados()
        v2  =0
        for d in listadado:
            var = d.getLados()
            if var == v1 :
                count1 += 1
            elif v2 ==0:
                v2 = var
                count2 =1
            elif var == v2:
                count2 +=1
            else: 
                #apareceu outro valor
                break

        if (count1 ==5) or (count1== 3 and count2 ==2 ) or (count1== 2 and count2 ==3 ):
            points = 15


    if posicao == 8:
        # sequencia! 1 a 5 ou 2 a 6
        seq = [False] *7

        for d in listadado:
            var =d.getLados()
            seq[var] = True
            
        if (seq[1] and seq[2] and seq[3] and seq[4] and seq[5]) or (seq[2] and seq[3] and seq[4] and seq[5] and seq[6]):
            points = 20
    

    if posicao ==9:
        for exemplo in listadado:
            count = 0
            for d in listadado:
                if exemplo.getLados() == d.getLados():
                    count += 1
            if count >= 4:
                points = 30
                break



    if posicao ==10:
        v = listadado[0].getLados()
        count =0
        for d in listadado:
            if d.getLados() == v:
                count +=1
        if count ==5:
            points = 40 


    return points














'''
INICIO DO MAIN : JOGO BOZÓ
#cria rolaDados e Placar
'''
s = input("Digite a semente (zero para aleatório): ")
cinco_dados = rolarDado(int(s)) 
placar_inicial = placar()
print(placar_inicial)

for i in range(10):
    print("****** Rodada", i+1)
    pontuacao =0
    print("Pressione ENTER para lançar os dados")
    input()
    #rolar dados   xxx  1 vez
    cinco_dados.rolar()
    print(cinco_dados) 
    print()
    
    # trocar 2 vezes
    for i in range(2):
        troca = input("Digite os números dos dados que quiser TROCAR. Separados por espaços.\n")

        
        
        #cria lista e marca 1 o que foi escolhido
        escolhidos = [int(n) for n in troca.split() if n.isdigit()]
        selecao = [0] * 5
        for num in escolhidos:
            if 1 <= num <= 5:
                selecao[num - 1] = 1

        #rolar dados x x x  2 vez
        cinco_dados.rolar(selecao)
        print(cinco_dados)
        print()

    print("\n\n")

    #exibir placar atual
    print(placar_inicial)
    #pedir e receber a posição para atualizar o placar
    while True:
        posicao_bruta = input("Escolha a posição que quer ocupar com essa jogada ===> ")
        
        if not posicao_bruta.isdigit():
            print("Valor inválido. Posição ocupada ou inexistente.")
            continue

        # Extrai todos os números da linha (caso venha "1 abc 3")
        partes = [n for n in posicao_bruta.split() if n.isdigit()]
        
        if partes: # se partes não é vazia
            posicao = int(partes[0]) # Pega o primeiro número válido
            posicao = int(posicao)
            pontuacao = calculo_pontuacao(posicao, cinco_dados.dados)

            ok = placar_inicial.addPonto(posicao, pontuacao)
            if ok:
                break
            else:
                continue
        else:
            print("Valor inválido. Posição ocupada ou inexistente.")

    
    

    #mostrar o placar atualizado
    print("\n\n")   
    print(placar_inicial)



#mostra o numero de pontos
placar_inicial.getScore()
