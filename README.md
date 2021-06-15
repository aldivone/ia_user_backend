## Instru��es para execu��o dos projetos.
1. Copiar para C:\Users\aldivone\.m2(windows) se for linux procurar o ".m2" no diret�rio do usu�rio, copiar o arquivo "toolchains.xml" e configurar o diret�rio de instala��o do openjdk 15 ou superior, n�o precisa fazer isso caso a jvm padr�o seja 15 ou superior
2. Subir os containers com as depend�ncias no projeto usuario-backend -> docker-compose up -d
3. baixar os reposit�rios: aldivone/ia-fila-kafka, aldivone/ia_user_backend e aldivone/-ia_user_frontend
4. aldivone/-ia_user_frontend -> yarn && yarn start
5. aldivone/ia-fila-kafka -> mvn spring-boot:run
6. aldivone/ia_user_backend -> mvn spring-boot:run
7. Usar o arquivo de configura��o do insomnia para testar as apis de envio de e-mail, arquivo no raiz do projeto usuario-backend (Insomnia_2021-06-14-teste-envio-email.json). Link da ferramenta: https://insomnia.rest/download
8. Link para acesso da documenta��o das apis: http://localhost:8080/api/swagger-ui/#/
9. Senha para acesso do administrador geral => user: admin, password: qazxsw123, tudo est� autenticado, inclusive a documenta��o.
10. As apis estar�o acess�veis pelo link http://localhost:8080/api
11. A aplica��o angular estar� acess�vel pelo link http://localhost:4200

## Reposit�rios no Github:
1. Mensageria Kafka => [https://github.com/aldivone/ia-fila-kafka](https://github.com/aldivone/ia-fila-kafka)
2. Backend => [https://github.com/aldivone/ia_user_backend/tree/master](https://github.com/aldivone/ia_user_backend/tree/master)
3. Frontend => [https://github.com/aldivone/-ia_user_frontend/tree/master](https://github.com/aldivone/-ia_user_frontend/tree/master) 



### Atividades do projeto

1. Criar a aplica��o spring boot => ok
2. Criar api rest para gravar as informa��es do usu�rio => ok
3. Testes unit�rios para api rest =>  ok
4. Colocar a autentica��o spring security => ok
5. Colocar o cache da autentica��o no redis do spring security => ok
6. Criar a interface com o angular => ok
7. Buscar os usu�rios da autentica��o do banco de dados gravar a senha criptografada => ok
8. Criar fila com rabbitmq => ok
9. Criar implementa��o para consumir a fila rabbitmq => ok
10. Implementar o envio de e-mail => ok
11. Criar fila com kafka => ok
12. Criar implementa��o para consumir a fila kafka => ok

