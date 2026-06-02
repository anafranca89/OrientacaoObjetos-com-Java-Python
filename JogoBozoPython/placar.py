class placar:
    #criar um placar sem pontuação
    def __init__(self):
        #4 linhas e 3 colunas
        self.matriz = [
            ["(1)", "(7)", "(4)"],
            ["(2)", "(8)", "(5)"],
            ["(3)", "(9)", "(6)"],
            [ "", "(10)", ""]
        ]





    '''
    # definir o str para fazer print(placar)
    def __str__(self):
        #larguras das colunas = 7, 10, 7
        larguras = [7, 10, 7]
        resultado =[]
        for li in range(4):
            linha = []
            for col in range(3):
                valor = str(self.matriz[li][col])
                # ajusta as larguras diferentes das colunas
                # se for a 1 colun, alinha a esquerda, se nao centraliza
                if col == 0:
                    linha.append(valor.ljust(larguras[col]))
                else:
                    linha.append(valor.center(larguras[col]))

            # ultima linha a formatação é diferente
            if li == 3:
                valor_linha = str(self.matriz[3][1]).center(10)
                linha_formatada = " " * 7 + "|" + valor_linha.center(10) + "|" + " " *7
            else:
                linha_formatada = "|".join(linha)

            resultado.append(linha_formatada)
                

            # Separadores : Linha 3 é diferente das outras
            if li < 3:
                resultado.append("-" * 7 + "|" + "-" * 10 + "|" + "-" * 7)  
            elif li == 3:
                resultado.append(" " * 7 + "+" + "-" * 10 + "+" +"\n")

            

        return "\n".join(resultado)    
    '''
    def __str__(self):
        out = []

        for li in range(4):

            if li < 3:
                # Na primeira coluna tem diferenca no alinhamento entre () e apenas numeros
                #  2    |....
                #(2)    |....
                #c1 coluna 1 - c2 coluna 2- c3 coluna 3
                v1 = str(self.matriz[li][0]).strip()

                if v1.startswith("("):
                    c1 = v1.ljust(7)
                else:
                    c1 = (" " + v1).ljust(7)
                    
                c2 = str(self.matriz[li][1]).center(10)
                c3 = str(self.matriz[li][2]).strip().center(5)

                out.append(f"{c1}|{c2}| {c3}")
                out.append("-------|----------|-------")
            else:
                c2 = str(self.matriz[3][1]).strip().center(10)
                out.append(f"{' '*7}|{c2}|")
                out.append(f"{' '*7}+----------+")

        return "\n".join(out) + "\n"






    def getScore(self):
        #retorna o escore atual - somando todos os valores preenchidos
        score = 0
        for linha in self.matriz: # itera sobre cada linha
            for valor in linha:   # valor é cada dado iterado pela  linha
                if isinstance(valor, int):
                    score += valor

            # se for string numérica
                elif isinstance(valor, str):

                    txt = valor.strip()

                    # casa vazia "(1)" ignora
                    if txt.startswith("(") and txt.endswith(")"):
                        continue

                    # se string número
                    if txt.isdigit():
                        score += int(txt)


        # print da formatação pedida
        print("***********************************")
        print("***")
        print(f"*** Seu escore final foi:{score:3}")
        print("***")
        print("***********************************")






    def addPonto(self, entrada, valor):
        ok = 1    #flag de posicao é valida
        #atualiza o placar na posição escolhida apenas se a posição estiver vazia e for válida
        #posicao é um numero de 1 a 10. Se posicao for diferente disso, OK ==0
       

        if entrada >= 1 and entrada < 4:
            if self.matriz[entrada-1][0] != f"({entrada})":
                #posicao já preenchida
                ok = 0
            else:
                self.matriz[entrada-1][0] = f"{valor}"
        elif entrada > 3 and entrada <= 6:
            if self.matriz[entrada-4][2] != f"({entrada})":
                ok = 0
            else:
                self.matriz[entrada -4][2] = f"{valor}"
        elif entrada >= 7 and entrada <= 9:
            if self.matriz[entrada-7][1] != f"({entrada})":
                ok = 0
            else:
                self.matriz[entrada-7][1] = f"{valor}"
        elif entrada == 10:
            if self.matriz[3][1] != f"({entrada})":
                ok = 0
            else:
                self.matriz[3][1] = f"{valor}"
        else:
            ok =0

        if ok == 0:
            print("Valor inválido. Posição ocupada ou inexistente.")
        
        return ok


        