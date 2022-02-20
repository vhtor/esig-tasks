# ESIG Tasks App

<img src="./frontend/src/assets/img/esig-group.png" alt="exemplo imagem">

&nbsp;

> Um pequeno aplicativo para gestão de tarefas feito com Spring Boot + Angular. Nele você poderá adicionar tarefas, filtrar por prioridade e deletá-las.

&nbsp;

## 💻 Pré-requisitos

Antes de começar, verifique se você possui os seguintes softwares instalados em sua máquina:
*  `Node.js (16.14.0 LTS) ou superior`
*  `Angular 13+`
*  `Visual Studio Code ou semelhtantes`
*  `IntelliJ IDEA ou outra IDE para Java`
*  `Instância MySQL 8+`

&nbsp;


## 🚀 Execução

O passo a passo para rodar a aplicação está dividido nas seções backend e frontend abaixo. Sendo necessário rodar primeiramente o backend com uma IDE para que ao executar o frontend em seguida, os dados no banco sejam carregados.

&nbsp;

## 1º Passo: Backend

O backend deste projeto foi gerado com o [Spring Boot Framework](https://spring.io/) (Maven) versão 2.6.3

### Banco de dados

Para executar o projeto é necessário ter instalado e em execução uma instância do banco MySQL 8+. As configurações para a conexão com o banco estão configuradas no arquivo `src/main/resources/application.yml`. 

### Execução

Para executar a aplicação, abra a pasta do projeto com IntelliJ IDEA ou IDE semelhante e clique em `Run` (Shift + F10 no IntelliJ).

&nbsp;

## 2º Passo: Frontend

O frontend deste projeto foi gerado com o [Angular CLI](https://github.com/angular/angular-cli) versão 13.2.3.

## Execução

Execute o comando
```
ng serve
```
para um servidor de desenvolvimento
. Vá até o endereço `http://localhost:4200/`. A aplicação irá recarregar se houver mudanças em quaisquer dos arquivos fonte.


## Build

Execute o comando 
```
ng build
```
para buildar o projeto. Os artefatos da build serão armazenados no diretório `dist/`.

## Rodando unidades de teste

Execute o comando
```
ng teste
```
para executar as unidades de teste via [Karma](https://karma-runner.github.io).

## Ajuda adicional

Para saber mais sobre o Angular CLI execute
```
ng help
```
ou confira a página [Angular CLI Overview and Command Reference](https://angular.io/cli).