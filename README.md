# Testes de integração utlizando [Environments](https://github.com/angeliferreira/environment-core)

Estou construindo neste projeto um exemplo de teste de integração utilizando a ferramenta https://github.com/angeliferreira/environment-core

Essa ferramenta visa desenvolver testes sem utilizar arquivos de .sql ou .xml para popular os dados nos testes, sendo assim facilita a reutilização de código/dados e melhora a cobertura de testes, uma vez que para montar os ambientes com os dados utilizam os próprios serviços e repositórios, para que os dados sejam criados exatamente como são criados no sistema, utilizando as mesmas regras. Tudo é feito via código java. Este primeiro projeto estou estudando como funciona a ferramenta junto com a persistência de dados, por enquanto ainda não implementei testes utilizando injeção de dependências, ferei isso após finalizar esse primeiro passo.
