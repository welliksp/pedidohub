# Documentação do Projeto

## Visão Geral
Este é um projeto Java desenvolvido com Spring Boot e utiliza Java 21. O projeto está estruturado como uma aplicação web moderna seguindo as melhores práticas de desenvolvimento.

## Estrutura do Projeto
```
├── src/ # Código fonte │
    ├── main
        ├── java/ # Código Java │
        └── resources/ # Recursos e configurações 
├── pom.xml # Configuração Maven 
├── compose.yaml # Configuração Docker Compose 
└── README.md # Documentação principal

```

## Requisitos do Sistema
- Java Development Kit (JDK) 21
- Maven
- Docker (opcional, para containers)

## Configuração do Ambiente

### Instalação
1. Clone o repositório: https://github.com/welliksp/pedidohub
2. Entre no diretório do projeto:
   bash cd  [PEDIDOHUB]
3. Compile o projeto:
   bash mvn clean install

### Execução
Para executar a aplicação localmente:
bash mvn spring-boot:run

### Docker (Opcional)
O projeto inclui configuração Docker via `compose.yaml`. O container será criado no momento que iniciar a aplicação.

## Desenvolvimento

### Estrutura de Pacotes
- `src/main/java/br/` - Pacote base da aplicação
- `src/main/resources/` - Arquivos de configuração e recursos

### Ferramentas Utilizadas
- Spring Boot
- Maven (Gerenciamento de dependências)
- Docker (Containerização)

## Build e Deploy

### Build
Para criar um pacote da aplicação:
```bash
  mvn package
```

O arquivo JAR será gerado no diretório `target/`.
### Deploy
Instruções específicas de deploy dependerão do ambiente alvo.
## Suporte
Para suporte ou dúvidas, entre em contato com a equipe de desenvolvimento.
