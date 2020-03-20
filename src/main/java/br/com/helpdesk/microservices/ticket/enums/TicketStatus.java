package br.com.helpdesk.microservices.ticket.enums;

public enum TicketStatus {
	
	OPEN(1, "Aberto"),
	CLOSED(2, "Fechado");
	
	private Integer cod;
	private String description;

	private TicketStatus(Integer cod, String description) {
		this.cod = cod;
		this.description = description;
	}

	public Integer getCod() {
		return cod;
	}

	public void setCod(Integer cod) {
		this.cod = cod;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public static TicketStatus toEnum (Integer cod) {
		if (cod == null) {
			return null;
		}
		
		for (TicketStatus x : TicketStatus.values()) {
			if (cod.equals(x.getCod())) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("Id inválido: " + cod);
	}
	
}
