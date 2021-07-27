package com.adriano.cursomc.domain.enums;

public enum TipoCliente {
	
	/*Tipo enumerado é a implementaçãomais básica, é colocar os valores separados por , 
	 * Voce pode salvar a string inteira ou criar inteiro para cada string. Melhor salvar int, oculpa menos espaço no banco
	 * Vamos fazer um controle manual para evitar problemas na enumeração das strings*/
	
	PESSOAFISICA(1, "Pessoa Física"), //Vou colocar a numeracao manual e um string caso queira imprimir alguma coisa
	PESSOAJURIDICA(2, "Pessoa jurídica");
	
	//Depois temos que criar um construtor para atribuir esses valores do nosso enumerada
	
	//Criando dois atributos	
	private int cod;
	private String descricao;
	
	
	//criando um construtor de tipo enum é private
	private TipoCliente(int cod, String descricao) {
		this.cod = cod;
		this.descricao = descricao;
	}

	//cirando apenas o metodo get. Enum não muda o tipo ou seja não podemosusar o set
	public int getCod() {
		return cod;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	/*Vamos fazer uma operacao que ela recebe o codigo e me retorna um objeto do tipo tipocliente já instanciado conforme
	 * o codigo que eu passar
	 * 
	 * Vai ser umpublic static - vai ser possivel de ser executada mesmo sem instanciar o objeto. */
	
	
	public static TipoCliente toEnum(Integer cod) { //Converte para o enum passando o codigo como parametro
		
		if(cod == null) {//testando se o cod que foi passado for nulo, não é ninguem
			return null;
		}
		
		/*Fazendo uma busca entre, todo o objeto x nos valores possiveis do tipocliente */
		for(TipoCliente x : TipoCliente.values()) {
			if(cod.equals(x.getCod())) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("Id inválido: " + cod);
	}
}
