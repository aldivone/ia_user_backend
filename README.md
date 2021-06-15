## Instruções para execução dos projetos.
1. Copiar para C:\Users\aldivone\.m2(windows) se for linux procurar o ".m2" no diretório do usuário, copiar o arquivo "toolchains.xml" e configurar o diretório de instalação do openjdk 15 ou superior, não precisa fazer isso caso a jvm padrão seja 15 ou superior
2. Subir os containers com as dependências no projeto usuario-backend -> docker-compose up -d
3. baixar os repositórios: aldivone/ia-fila-kafka, aldivone/ia_user_backend e aldivone/-ia_user_frontend
4. aldivone/-ia_user_frontend -> yarn && yarn start
5. aldivone/ia-fila-kafka -> mvn spring-boot:run
6. aldivone/ia_user_backend -> mvn spring-boot:run
7. Usar o arquivo de configuração do insomnia para testar as apis de envio de e-mail, arquivo no raiz do projeto usuario-backend (Insomnia_2021-06-14-teste-envio-email.json). Link da ferramenta: https://insomnia.rest/download
8. Link para acesso da documentação das apis: http://localhost:8080/api/swagger-ui/#/
9. Senha para acesso do administrador geral => user: admin, password: qazxsw123, tudo está autenticado, inclusive a documentação.
10. As apis estarão acessíveis pelo link http://localhost:8080/api
11. A aplicação angular estará acessível pelo link http://localhost:4200

## Repositórios no Github:
1. Mensageria Kafka => [https://github.com/aldivone/ia-fila-kafka](https://github.com/aldivone/ia-fila-kafka)
2. Backend => [https://github.com/aldivone/ia_user_backend/tree/master](https://github.com/aldivone/ia_user_backend/tree/master)
3. Frontend => [https://github.com/aldivone/-ia_user_frontend/tree/master](https://github.com/aldivone/-ia_user_frontend/tree/master) 



### Atividades do projeto

1. Criar a aplicação spring boot => ok
2. Criar api rest para gravar as informações do usuário => ok
3. Testes unitários para api rest =>  ok
4. Colocar a autenticação spring security => ok
5. Colocar o cache da autenticação no redis do spring security => ok
6. Criar a interface com o angular => ok
7. Buscar os usuários da autenticação do banco de dados gravar a senha criptografada => ok
8. Criar fila com rabbitmq => ok
9. Criar implementação para consumir a fila rabbitmq => ok
10. Implementar o envio de e-mail => ok
11. Criar fila com kafka => ok
12. Criar implementação para consumir a fila kafka => ok

