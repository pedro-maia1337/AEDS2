import java.util.*;
import java.math.*;

class No {
    public No esq, dir;
    public int elemento;
    public int nivel;
    
    public No(int elemento) {
        this.elemento = elemento;
        this.esq = null;
        this.dir = null;
        nivel = 1;
    }
    
    public No(int elemento, No esq, No dir, int nivel) {
        this.elemento = elemento;
        this.esq = esq;
        this.dir = dir;
        this.nivel = nivel;
    }
    
    public void setNivel(){
        this.nivel = 1 + Math.max(getNivel(esq), getNivel(dir));
    }
    
    public static int getNivel(No no) {
        return (no == null) ? 0 : no.nivel;
    }
}