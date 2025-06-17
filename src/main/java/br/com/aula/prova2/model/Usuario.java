package br.com.aula.prova2.model;


public class Usuario {
    private Integer id;
    private String email;
    private String senha;
    private Integer clienteId;

    public Usuario() { }

    public Usuario(Integer id, String email, String senha, Integer clienteId) {
        this.id = id;
        this.email = email;
        this.senha = senha;
        this.clienteId = clienteId;
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Integer getClienteId() {
        return clienteId;
    }
    public void setClienteId(Integer clienteId) {
        this.clienteId = clienteId;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", clienteId=" + clienteId +
                '}';
    }
}