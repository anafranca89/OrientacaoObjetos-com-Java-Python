import random
from random import Random
class dado:
    #criar as caracteristicas da classe
    # e junta com rolar!O valor do dado é atualizado 
   def __init__(self, nlados =6 , seed=0):
        self.nlados = nlados
        if seed == 0:
            self.gerador = Random() # Aleatório real
        else:
            self.gerador = Random(seed) # gerador com seed
            
        self.valor = 0
        self.rolar()
   
   def getLados(self):
        return self.valor

   def rolar(self):
        self.valor = self.gerador.randint(1, 6)
        


   '''
   def __str__(self):
        # append() adiciona elemnto no fim de uma lista
        # join() junta os elemntos de uma lista com um separador, nesse caso \n para pular linha
        #            +-----+     
         #           |*    |      
         #           |     |      
         #           |    *|       
         #           +-----+    
      
      dado = []

      borda = "+-----+"
      dado.append(borda)

      if self.valor == 1:
         dado.append("|     |")
         dado.append("|  *  |")
         dado.append("|     |")
      elif self.valor == 2:
         dado.append("|*    |")
         dado.append("|     |")
         dado.append("|    *|")
      elif self.valor == 3:
         dado.append("|*    |")
         dado.append("|  *  |")
         dado.append("|    *|")
      elif self.valor == 4:
         dado.append("|*   *|")
         dado.append("|     |")
         dado.append("|*   *|")
      elif self.valor == 5:
         dado.append("|*   *|")
         dado.append("|  *  |")
         dado.append("|*   *|")
      elif self.valor == 6:
         dado.append("|*   *|")
         dado.append("|*   *|")
         dado.append("|*   *|")
      
      dado.append(borda)
        
      return "\n ".join(dado)
   '''





   def obter_fatias(self):
      dado = []
      borda = "+-----+"
      dado.append(borda)

      if self.valor == 1:
         dado.append("|     |")
         dado.append("|  *  |")
         dado.append("|     |")
      elif self.valor == 2:
         dado.append("|*    |")
         dado.append("|     |")
         dado.append("|    *|")
      elif self.valor == 3:
         dado.append("|*    |")
         dado.append("|  *  |")
         dado.append("|    *|")
      elif self.valor == 4:
         dado.append("|*   *|")
         dado.append("|     |")
         dado.append("|*   *|")
      elif self.valor == 5:
         dado.append("|*   *|")
         dado.append("|  *  |")
         dado.append("|*   *|")
      elif self.valor == 6:
         dado.append("|* * *|")
         dado.append("|     |")
         dado.append("|* * *|")
      else:
         # Caso o dado não tenha sido rolado ainda (valor 0 ou None)
         dado.append("|     |")
         dado.append("|     |")
         dado.append("|     |")
      
      dado.append(borda)
      return dado