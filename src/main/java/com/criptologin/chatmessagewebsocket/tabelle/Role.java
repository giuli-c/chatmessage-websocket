package com.criptologin.chatmessagewebsocket.tabelle;

	import javax.persistence.Entity;
	import javax.persistence.GeneratedValue;
	import javax.persistence.GenerationType;
	import javax.persistence.Id;
	import javax.persistence.Table;

	@Entity
	@Table(name = "role")
	public class Role {

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private int id;
		private String nome;

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getNome() {
			return nome;
		}

		public void setNome(String nome) {
			this.nome = nome;
		}

		public Role(int id, String nome) {
			super();
			this.id = id;
			this.nome = nome;
		}

		public Role() {
			
		}

		public Role(String nome) {
			super();
			this.nome = nome;
		}

		
	}
