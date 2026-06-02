JC = javac
JVM = java
MAIN = poker.java
SOURCES = Naipe.java carta.java Baralho.java placar.java poker.java
CLASSES = $(SOURCES:.java=.class)


all: $(CLASSES)

%.class: %.java
	rm -f $@
	$(JC) $<

run: all
	$(JVM) $(MAIN)

clean:
	rm -f *.class