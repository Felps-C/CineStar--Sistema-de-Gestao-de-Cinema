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
4. No código do projeto, abra a classe responsável pela conexão com o banco de dados `db.propieties`.
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

## 🧪 Como Testar e Entender o Sistema

Para vivenciar a experiência completa do simulador, recomendamos que você teste o sistema seguindo o fluxo de integração abaixo:

### 1. Perfil: Gerente (Backoffice)
* Inicie o sistema e faça login como **Gerente** (você pode usar as credenciais padrão geradas pelo `banco_de_dados.sql`).
* **Catálogo:** Acesse a aba de filmes, cadastre um novo filme preenchendo todos os dados (nome, duração, sessões, etc.) e clique em "Adicionar Novo".
* **Estoque:** Vá para a aba da Bomboniere e adicione produtos iniciais (ex: Pipoca, Refrigerante) com suas respectivas quantidades.

### 2. Perfil: Cliente (Autoatendimento)
* Saia da conta do gerente e acesse a tela do **Cliente**.
* Observe que os filmes cadastrados pelo gerente já aparecem na lista com o layout claro (Light Theme).
* Clique em um filme, selecione o horário no menu, mude a quantidade de ingressos e veja o **cálculo automático** do valor total funcionando em tempo real antes de confirmar a compra.

### 3. Perfil: Vendedor (Ponto de Venda)
* Acesse o sistema como **Vendedor** para ver o layout em Modo Escuro (Dark Theme), projetado para não cansar a vista durante o expediente.
* Simule uma venda selecionando um produto da lanchonete e clicando em "Atualizar Estoque (Venda)".
* **Teste de Comunicação:** Selecione um produto que esteja acabando e clique no botão **"Solicitar Reposição"**. O sistema fará a comunicação direta com o banco de dados.

### 4. Fechando o Ciclo (Retorno ao Gerente)
* Volte para a tela do **Gerente**, acesse a aba de Estoque e verifique que o pedido de reposição feito pelo Vendedor já reflete no painel administrativo.
* Atualize a quantidade do produto, limpando o alerta e completando o ciclo do sistema!