# 🎬 CineStar - Sistema de Gestão de Cinema

Bem-vindo ao repositório do **CineStar**, um simulador completo de vendas e gestão de cinema desenvolvido com interface gráfica em JavaFX. O sistema simula um ambiente real com áreas exclusivas para Clientes (compra de ingressos e produtos), Vendedores (PDV da lanchonete) e Gerentes (gestão de estoque e catálogo de filmes).

---

## 👥 Integrantes do Grupo

* **Caique Gama Nunes**
* **Felipe Carvalho de Souza**

---

## ⚙️ Pré-requisitos

Para rodar este projeto na sua máquina, certifique-se de ter os seguintes itens instalados:
* **Java JDK 17** (ou versão superior)
* **JavaFX SDK** (configurado na sua IDE)
* **MySQL Server** (para o banco de dados)
* Uma IDE de sua preferência (recomendamos IntelliJ IDEA ou Eclipse)

---

## 🚀 Passo a Passo: Configurando o Banco de Dados

1. Abra o seu gerenciador do MySQL (como o MySQL Workbench, DBeaver ou via terminal).
2. Localize o arquivo **`banco_de_dados.sql`** na raiz deste repositório.
3. Copie todo o conteúdo do arquivo e execute no seu console SQL. O script irá criar:
    * O banco de dados chamado `cinema`.
    * Todas as tabelas necessárias (`usuarios`, `filmes`, `produtos`, etc).
    * Os dados iniciais para o primeiro acesso ao sistema.
4. No código do projeto, abra a classe responsável pela conexão com o banco de dados (geralmente chamada de `Conexao`, `Database` ou similar).
5. Certifique-se de alterar as variáveis de **usuário** e **senha** para os dados correspondentes ao banco local da sua máquina (por exemplo, usuário `root` e a sua senha pessoal).

---

## ▶️ Como Rodar a Aplicação

1. **Clone o repositório** para a sua máquina ou baixe o arquivo `.zip` e extraia.
2. **Abra o projeto** na sua IDE.
3. **Configure as dependências**:
    * Se o projeto utilizar o **Maven** (`pom.xml`), atualize o projeto para baixar as dependências do JavaFX e o driver do MySQL (`mysql-connector-java`).
    * Se não utilizar Maven, adicione as bibliotecas do JavaFX e o arquivo `.jar` do conector do MySQL manualmente no *Build Path* do projeto.
4. Adicione as **VM Options** do JavaFX nas configurações de execução (Run Configuration) da sua IDE, apontando para a pasta `lib` do seu JavaFX SDK. Exemplo:
   `--module-path "C:\caminho\para\javafx-sdk\lib" --add-modules javafx.controls,javafx.fxml`
5. Localize o pacote principal e execute a classe **`Main`** (aquela que contém os métodos `start` e `main`).
6. A tela inicial de boas-vindas do **CineStar** será aberta e o sistema estará pronto para uso!