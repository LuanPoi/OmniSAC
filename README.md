# OmniSAC

Respostas ao questionário e diagramas estão na raiz do projeto.
- `respostas.txt` são as respostas do questionário;
- `C4_3.drawio.svg` é o que eu considerei como o diagrama "para apresentar a visão arquitetural da infraestrutura" (ele é um misto de Context e Container);
- `C2.drawio.svg` é o que eu considerei como o diagrama "para apresentar a organização do software, as responsabilidades de cada elemento identificado e o relacionamento entre eles" (ele é um Component escorregando para Code e contem apenas um exemplo do que seria o core de cada um dos modulos na camada de negócio);

## Execução
- Faça o clone da aplicação;
- Com o docker intalado execute o comando: `docker-compose up --build -d`;
    - Caso o flyway não execute as migrations automaticamente é possivel ir até a pasta `database` e executar o comando: `.\gradlew flywayMigrate` para fazer a migração manualmente ou mesmo acessar o banco de dados (PostgreSQL) com a ferramenta de sua preferência e executar os scripts em ordem manualmente;

## O que acontece ao executar `docker-compose up --build -d` neste projeto
- O frontend sobe em um servidor Nginx;
- O backend sobe em um servidor Tomcat;
- O banco de dados PostgreSQL sobe;
- Após o banco de dados subir o flyway sobe e executa automaticamente as migrations;
    - Caso o front seja acessado antes da migration ocorrer um objeto necessário para o cadastro não terá sido carregado, então é recomendado esperar o flyway "desligar" primeiro ou atualizar a página após ele desligar e tudo funcionará normalmente.