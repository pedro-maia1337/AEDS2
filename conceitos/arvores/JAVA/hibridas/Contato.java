class Contato {
    String nome;
    String telefone;
    String email;
    int cpf;
    
    Contatos(String nome, String telefone, String email, int cpf) {
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.cpf = cpf;
    }

    public String toString(){
        return "Nome: " + nome + " Telefone: " + telefone + " Email: " + email + "CPF: " + cpf;
    }

}
