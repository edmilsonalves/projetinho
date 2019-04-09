package br.com.appquer.common.exception;

public enum QuerErrorCode
{

	GEN_1("GEN-1", "Parâmetros inválidos."),
	GEN_2("GEN-2", "O campo {0} é obrigatório."),
	GEN_5("GEN-5", "O campo {0} não é uma data válida ({1})."),
	GEN_8("GEN-8", "Ocorreu um erro de comunicação em nossos serviços."),
	GEN_999("GEN-999", "{0}"), // TODO: Usado para mensagens ainda não padronizadas.
	INT_1("INT-1", "{0}"), // Erro genérico de integração
	INT_2("INT-2", "{0}"),
	EXP_1("EXP-1", "Ocorreu um erro desconhecido."),

	SAU_60("SAU-60", "Já existe uma conta com este e-mail. Caso esse e-mail seja seu tente a recuperação de senha."),
	SAU_61("SAU-61", "A senha é obrigatória e deve conter pelo menos 6 caracteres!"),
	SAU_62("SAU_62", "Ocorreu um erro na criação do usuário."),
	SAU_63("SAU_63", "Usuário não encontrado."),
	SAU_64("SAU_64", "A senha atual não confere."),
	SAU_65("SAU_65", "Ocorreu um erro ao obter dados do usuário."),
	SAU_66("SAU_66", "Nenhuma pessoa encontrada para esse e-mail."),
	SAU_67("SAU_67", "Pessoa não encontrada."),
	SAU_68("SAU_68", "Ocorreu um erro ao enviar convite para o cuidador."),
	SAU_69("SAU_69", "Não é permitido remover cuidador de outras pessoas."),
	SAU_70("SAU_70", "Convite não encontrado."),
	SAU_71("SAU_71", "Não é permitido responder a convites de outras pessoas."),
	SAU_72("SAU_72", "Já existe um convite para esse cuidador."),
	SAU_73("SAU_73", "Ocorreu um erro ao atualizar o e-mail do usuário."),

	ADM_1("ADM-1", "Registro não localizado"),
	ADM_2("ADM-2", "A empresa informada já é dona de um grupo de empresas."),
	ADM_3("ADM-3", "A empresa {0} já faz parte de outro grupo de empresas."),
	ADM_4("ADM-4", "A imagem é menor do que o mínimo permitido."),
	ADM_5("ADM-5", "A imagem está com a proporção incorreta."),
	ADM_6("ADM-6", "A situação atual não permite alteração para ''{0}''"),
	ADM_7("ADM-7", "A situação atual é ''{0}'' e por isso o registro não pode ser editado."),
	ADM_8("ADM-8", "Essa coleta já foi iniciada e não pode mais ser alterada"),
	ADM_9("ADM-9", "Já existe um registro de coleta para essa singular."),
	ADM_10("ADM-10", "Não é possível iniciar a coleta com data anterior à data atual."),
	ADM_11("ADM-11", "A data de início não pode ser maior que a data fim."),
	ADM_12("ADM-12", "Já existe outra coleta sem data final de vigência."),
	ADM_13("ADM-13", "Existe outra coleta com um período de vigência em conflito com esta."),
	ADM_14("ADM-14", "Ocorreu um erro na leitura do arquivo com a lista de pessoas."),
	ADM_15("ADM-15", "O tipo de aplicação {0} não é permitido para {1}."),
	ADM_16("ADM-16", "A amostra para coleta deve possuir ao menos um registro de pessoa."),
	ADM_17("ADM-17", "Para criar uma coleta {0} deve estar aprovado"),
	ADM_18("ADM-18", "Para solicitar a aprovação o questionário deve possuir ao menos uma pergunta."),
	ADM_19("ADM-19", "Para finalizar uma coleta é necessário que a mesma esteja com status Iniciado"),
	ADM_20("ADM-20", "Para alterar a ordem de uma coleta é necessário que a mesma esteja com status Agendado"),
	ADM_21("ADM-21", "Para solicitar aprovação, a situação atual deve ser ''Rascunho'' ou ''Reprovado''. A situação atual é ''{0}''."),
	ADM_22("ADM-22", "Para aprovar ou rejeitar, a situação atual deve ser ''Pendente de aprovação''. A situação atual é ''{0}''."),
	ADM_23("ADM-23", "Para publicar, a situação atual deve ser ''Ativo/Aprovado''. A situação atual é ''{0}''."),
	ADM_24("ADM-24", "Para ativar um programa de saúde é necessário informar o título e descrição do programa de saúde."),
	ADM_25("ADM-25", "Já existe um questionário com situação Ativo/Aprovado, favor inativa-lo antes dessa alteração"),

	QUER_1("QUER-1", "O usuário não tem permissão para acessar os dados dessa pessoa."),
	QUER_2("QUER-2", "Questionário ou respostas não permitidos."),
	QUER_3("QUER-3", "Questionário já respondido."),
	QUER_4("QUER-4", "Usuário não tem acesso ao questionário."),
	QUER_5("QUER-5", "É obrigatório escolher uma alternativa para a pergunta ''{0}''."),
	QUER_6("QUER-6", "O valor da resposta da pergunta ''{0}'' deve estar entre {1} e {2}."),
	QUER_7("QUER-7", "A resposta da pergunta ''{0}'' não pode ter mais que {0} caracteres."),
	QUER_8("QUER-8", "O formato da data da pergunta ''{0}'' deve ser ''{1}''."),
	QUER_9("QUER-9", "Questionário ainda não foi respondido."),
	QUER_10("QUER-10", "Ocorreu um erro ao excluir lista de pessoas da operadora."),
	QUER_11("QUER-11", "Ocorreu um erro ao incluir lista de pessoas da operadora."),
	QUER_12("QUER-12", "Ocorreu um erro ao ler o arquivo de clientes."),
	QUER_13("QUER-13", "Não foi possível enviar os dados de contato para inscrição no programa de saúde."),
	QUER_14("QUER-14", "Empresa não encontrada ou não possui acesso a esse recurso."),
	QUER_15("QUER-15", "Usuário não localizado na empresa informada."),
	QUER_16("QUER-16", "Dados do usuário não conferem."),
	QUER_17("QUER-17", "Falha ao remover cuidador."),
	QUER_18("QUER-18", "Falha ao atualizar operadoras do usuário."),
	QUER_19("QUER-19", "Esse usuário já usou outro CPF para se integrar a uma operadora."),
	QUER_20("QUER-20", "Usuário não está integrado a uma operadora"),
	QUER_21("QUER-21", "Usuário não tem uma operadora principal."),
	QUER_22("QUER-22", "Usuário não tem um CPF verificado."),
	QUER_23("QUER-23", "A vigência da coleta precisa estar dentro da vigência({0}  - {1}) do questionário. "),
	QUER_24("QUER-24", "A vigência da coleta precisa estar dentro da vigência({0}  - {1}) da orientação de saúde. "),
	QUER_25("QUER-25", "A Fórmula contém variáveis que não estão na lista de atributos - variáveis inválidas {0}."),
	QUER_26("QUER-26", "Fórmula inválida"),
	QUER_27("QUER-27", "Questionário não existe ou não foi respondido pelo usuário. ");

	private final String codigo;
	private final String mensagem;

	private QuerErrorCode (final String codigo, final String mensagem)
	{
		this.codigo = codigo;
		this.mensagem = mensagem;
	}

	public static QuerErrorCode findByCodigo (final String codigo)
	{
		if (codigo == null)
		{
			return null;
		}

		for (final QuerErrorCode errorCode : QuerErrorCode.values())
		{
			if (errorCode.getCodigo().equalsIgnoreCase(codigo))
			{
				return errorCode;
			}
		}

		return null;

	}

	@Override
	public String toString ()
	{
		return codigo + ": " + mensagem;
	}

	public String getCodigo ()
	{
		return codigo;
	}

	public String getMensagem ()
	{
		return mensagem;
	}

}
