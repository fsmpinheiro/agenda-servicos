### As entidades desse sistema

- O Agendamento de um serviço
- Um Cliente, aquele que agenda o serviço
- Funcionário, aquele encarregado por um serviço agendado


#### O agendamento deve ter:
 - um nome de cliente (nomeCliente)
 - o telefonde de um cliente (telefoneCliente)
 - um horário de agendamento (localDateTime)
 - o nome do profissional
 - o tipo de serviço

### Create de Agendamento

```json
{
    "cliente": {
        "nome": "Samuel Pinheiro",
        "telefoneCliente": "88988776655",
    },
    "funcionario": {
        "nome": "Antonio Biaggi"
    },
    "descricaoServico": "Corte de cabelo",
    "dataHora": "2026-04-13T08:30:00"
}
```

##### Delete de Agendamento - Funcionario

```json
{
    "funcionario": {
        "nome": "Antonio Biaggi"
    },
    "dataHora": "2026-04-13T08:30:00"
}
```


##### Delete de Agendamento - Cliente

```json
{
    "cliente": {
        "nome": "Samuel Pinheiro",
        "telefoneCliente": "88988776655",
    },
    "dataHora": "2026-04-13T08:30:00"
}
```


#### O cliente deve ter:

 - um nome
 - um telefone para contato

```json
{
    "nome": "Samuel Pinheiro",
    "telefone": "88988776655"
}
```

- o nome do cliente não pode ser vazio
- o telefone do cliente não pode ser vazio
- o cliente é encontrado pelo seu telefone
- o cliente pode atualizar seu nome, a partir do telefone
- um novo cliente não deve ser cadastrado se o telefone informado já existir no sistema
- um cliente só pode ser removido/deletado caso informe seus dados(nome e telefone)

#### O funcionário deve ter:

 - um nome
 - período estimado para cada serviço 

```json
{
    "nome": "Antonio Biaggi",
    "periodo": "35"
}
```

Serviço:
- Se cliente não existir, será criado no momento da
solicitação do agendamento
- A data e hora do agendamento não pode estar no passado
- 



