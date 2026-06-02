# Variáveis para uso LOCAL
VENV = .venv
PYTHON_LOCAL = $(VENV)/bin/python3
PIP = $(VENV)/bin/pip

.PHONY: install run test clean

install:
	python3 -m venv $(VENV)
	$(PIP) install -r requirements.txt

test:
	$(VENV)/bin/pytest

# COMANDO PARA O RUNCODES: 
# Use 'python3' direto, sem o caminho do .venv
run:
	python3 main.py

clean:
	rm -rf $(VENV)
	rm -rf __pycache__
	