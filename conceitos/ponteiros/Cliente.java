class Cliente {
   private int codigo;
   private String nome;
   
   Cliente() {
    codigo = 0;
    nome = "";
   }

   Cliente(int codigo, String nome){
    this.codigo = codigo;
    this.nome = nome;
   }

   public void setCodigo(int codigo) {
       this.codigo = codigo;
   }

   public void setNome(String nome) {
       this.nome = nome;
   }

   public int getCodigo() {
       return codigo;
   }

   public String getNome() {
       return nome;
   }

    public Cliente clone (){
        return new Cliente(this.codigo, this.nome);
    }

}
