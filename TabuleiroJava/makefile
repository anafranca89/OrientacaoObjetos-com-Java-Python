# Variáveis
JC = javac
JVM = java
MAIN = usandoTabuleiro
SOURCES = tabuleiro.java usandoTabuleiro.java
CLASSES = $(SOURCES:.java=.class)


all: $(CLASSES)

%.class: %.java
	rm -f $@
	$(JC) $<

run: all
	$(JVM) $(MAIN)

clean:
	rm -f *.class
