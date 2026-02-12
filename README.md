# agenda-servicos


Um agendamento deve ter:
 - um nome de cliente (nomeCliente)
 - o telefonde de um cliente (telefoneCliente)
 - um horário de agendamento (localDateTime)
 - o tipo de serviço
 - o nome do profissional

Um cliente deve ter:
 - um nome
 - um telefone para contato

```json
{
    "cliente": "Samuel Pinheiro",
    "telefone": "88988776655"
}
```

- o nome do cliente não pode ser vazio
- o telefone do cliente não pode ser vazio
- o cliente é encontrado pelo seu telefone
- o cliente pode atualizar seu nome, a partir do telefone
- um novo cliente não deve ser cadastrado se o telefone informado já existir no sistema
- um cliente só pode ser removido/deletado caso informe seus dados(nome e telefone)

Um funcionário deve ter:
 - um nome
 - tempo para cada serviço 

Serviço:
- Se cliente não existir, será criado no momento da
solicitação do agendamento



