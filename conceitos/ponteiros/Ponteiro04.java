class Ponteiro04 {
    public static void main (String[] args){
        Cliente c1 = null, c2 = null, c3 = null;
        System.out.println("ADDRs:\nc1(" + c1 + ")\nc2(" + c2 + ")\nc3(" + c3 + ")");
        c1 = new Cliente(1, "aa"); c2 = c1; c3 = new Cliente(2, "bb");
        System.out.println("ADDRs:\nc1(" + c1 + ")\nc2(" + c2 + ")\nc3(" + c3 + ")");
        c2.setCodigo(3);
        System.out.println("ATRIBUTOs:");
        System.out.println("c1(" + c1.getCodigo() + " / " + c1.getNome() + ")");
        System.out.println("c2(" + c2.getCodigo() + " / " + c2.getNome() + ")");
        System.out.println("c3(" + c3.getCodigo() + " / " + c3.getNome() + ")");
    } 
}
