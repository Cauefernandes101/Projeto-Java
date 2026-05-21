# Projeto-Java

Projeto Streaming de Animes
Sobre o Projeto

O projeto consiste em uma plataforma desktop desenvolvida em Java com interface gráfica Swing, focada no gerenciamento de usuários e favoritos de animes. O sistema permite que usuários realizem login, mantenham sessão ativa e salvem seus animes favoritos utilizando integração com banco de dados. O projeto segue uma estrutura baseada no padrão MVC (Model-View-Controller), separando responsabilidades entre interface, lógica e dados.

 Funcionalidades

Cadastro de usuários
Login de usuários
Controle de sessão
Sistema de favoritos
Integração com banco de dados
Interface gráfica com Java Swing
Atualização dinâmica da lista de favoritos
Organização utilizando MVC

Tecnologias Utilizadas
Java
Java Swing
JDBC
MySQL
NetBeans
MVC (Model View Controller)
Projeto/
│
├── Controller/
│   ├── Controller.java
│   └── UsuarioLogado.java
│
├── Model/
│   └── Usuario.java
│
├── ModelDAO/
│   ├── Conexao.java
│   ├── ControllerDAO.java
│   └── UsuarioDAO.java
│
├── View/
│   └── Streaming.java
│
└── Projeto.java

🔹 Model
Responsável pelos dados.

Usuario.java

Classe responsável por representar os dados do usuário:

Nome de usuário
Nome
Ano de nascimento
Email
Senha
🔹 View

Responsável pela interface gráfica do sistema.

Streaming.java

Tela principal do sistema desenvolvida com Java Swing.

🔹 Controller

Responsável pela comunicação entre View e Model.

Controller.java

Controla:

Atualização dos favoritos
Comunicação com banco de dados
Manipulação da interface
UsuarioLogado.java

🔹 ModelDAO

Responsável pela comunicação entre o sistema e o banco de dados utilizando JDBC.

A camada DAO (Data Access Object) separa a lógica de acesso ao banco da lógica principal do sistema, deixando o código mais organizado, reutilizável e fácil de manter.

O ModelDAO é responsável por:

Executar comandos SQL
Inserir dados no banco
Buscar informações
Atualizar registros
Remover dados
Gerenciar conexões com o banco

Responsável pelo controle de sessão do usuário utilizando variável estática.

Funcionamento do Sistema

Ao iniciar o projeto, a interface principal é criada, alguns painéis são ocultados inicialmente e o sistema realiza conexão com o banco de dados. Após isso, o usuário pode realizar login no sistema. Quando autenticado corretamente, a sessão é armazenada, os favoritos são carregados do banco de dados e a interface é atualizada dinamicamente.

Banco de Dados

O sistema utiliza banco de dados relacional para armazenar usuários, validar login, salvar favoritos e buscar animes favoritados. A conexão é realizada utilizando JDBC.

Como Executar
1. Clone o repositório
git clone https://github.com/seuusuario/seurepositorio.git
2. Abra no NetBeans

Importe o projeto normalmente no NetBeans.

3. Configure o banco de dados

Configure:

URL
Usuário
Senha
Driver JDBC

na classe de conexão do projeto.

4. Execute

Execute a classe:

Projeto.java
Exemplo de Fluxo
Usuário → Login → Sessão criada → Busca favoritos no banco → Atualiza JList automaticamente
Controle de Sessão

metodos principais:

🔹UsuarioLogado

para armazenar o usuário autenticado durante a execução do programa.

Métodos disponíveis:

setUsuarioLogado()
getUsuarioLogado()
encerrarSessao()

 🔹ControllerDAO.java
buscarTodosFavoritos()

Busca todos os animes favoritados pelo usuário logado.

salvarFavorito()

Salva um anime como favorito no banco de dados.

removerFavorito()

Remove um anime da lista de favoritos

🔹UsuarioDAO.java
cadastrarUsuario()

Insere um novo usuário no banco de dados.

validarLogin()

Verifica se o login e senha existem no banco.
🔹 Controller.java
atualizarListaVisualFavoritos()

Atualiza a lista de favoritos exibida na interface buscando os dados do banco de dados.

getModeloFavoritos()

Retorna o modelo da lista de favoritos utilizado pela interface gráfica.

┌──────────────────────────┐
│         Usuario          │
├──────────────────────────┤
│ - nomeUsuario : String   │
│ - nome : String          │
│ - nascimento : int       │
│ - email : String         │
│ - senha : String         │
├──────────────────────────┤
│ + getNomeUsuario()       │
│ + getNome()              │
│ + getNascimento()        │
│ + getEmail()             │
│ + getSenha()             │
│ + setNomeUsuario()       │
│ + setNome()              │
│ + setNascimento()        │
│ + setEmail()             │
│ + setSenha()             │
└──────────────────────────┘



┌──────────────────────────┐
│      UsuarioLogado       │
├──────────────────────────┤
│ - usuarioLogado:String   │
├──────────────────────────┤
│ + setUsuarioLogado()     │
│ + getUsuarioLogado()     │
│ + encerrarSessao()       │
└──────────────────────────┘



┌──────────────────────────┐
│        Controller        │
├──────────────────────────┤
│ - dao : ControllerDAO    │
│ - view : Streaming       │
├──────────────────────────┤
│ + atualizarLista()       │
│ + getModeloFavoritos()   │
└──────────────────────────┘



┌──────────────────────────┐
│       ControllerDAO      │
├──────────────────────────┤
│ - model : UsuarioDAO     │
│ - view : Streaming       │
├──────────────────────────┤
│ + cadastro()             │
│ + login()                │
│ + adicionarFavorito()    │
│ + removerFavoritoC()     │
│ + limparFavoritos()      │
│ + buscarFavoritos()      │
└──────────────────────────┘



┌──────────────────────────┐
│        UsuarioDAO        │
├──────────────────────────┤
│ - conn : Connection      │
├──────────────────────────┤
│ + inserir()              │
│ + retornarLogin()        │
│ + inserirFavorito()      │
│ + retirarFavorito()      │
│ + buscarFavoritos()      │
│ + limparFavoritos()      │
└──────────────────────────┘



┌──────────────────────────┐
│         Conexao          │
├──────────────────────────┤
│ - conn : Connection      │
├──────────────────────────┤
│ + getConnection()        │
└──────────────────────────┘



┌──────────────────────────┐
│         Streaming        │
├──────────────────────────┤
│ + getTfNome()            │
│ + getTfSenha()           │
│ + getUsuarioLogin()      │
│ + getSenhaLogin()        │
│ + setVisible()           │
└──────────────────────────┘



================ RELACIONAMENTOS ================

Controller
     │
     ├───────────────► Streaming
     │
     └───────────────► ControllerDAO


ControllerDAO
     │
     ├───────────────► UsuarioDAO
     │
     ├───────────────► Streaming
     │
     └───────────────► Usuario


UsuarioDAO
     │
     └───────────────► Conexao


UsuarioLogado
     │
     └───────────────► Controller

┌────────────────────────────┐
│          usuario           │
├────────────────────────────┤
│ PK usuario : TEXT          │
│ nome : TEXT                │
│ senha : TEXT               │
│ email : TEXT               │
│ nascimento : INT           │
└────────────────────────────┘



                1
usuario ─────────────────────── favoritos
                N



┌────────────────────────────┐
│         favoritos          │
├────────────────────────────┤
│ FK usuario : TEXT          │
│ anime : TEXT               │
└────────────────────────────┘
