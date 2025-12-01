import java.util.*;

public class ConversorSimplesArvore {
    
    static class No {
        char valor;
        No esquerda, direita;
        
        No(char valor) {
            this.valor = valor;
        }
    }
    
    public static No criarArvoreSimples(String infixa) {
        infixa = infixa.replaceAll("\\s+", "");
        return criarArvoreRecursivo(infixa, 0, infixa.length() - 1);
    }
    
    private static No criarArvoreRecursivo(String expr, int inicio, int fim) {
        if (inicio > fim) return null;
        
        int indiceOperador = -1;
        int parenteses = 0;
        int menorPrecedencia = Integer.MAX_VALUE;
        
        for (int i = inicio; i <= fim; i++) {
            char c = expr.charAt(i);
            
            if (c == '(') parenteses++;
            else if (c == ')') parenteses--;
            else if (parenteses == 0 && ehOperador(c)) {
                int prec = precedencia(c);
                if (prec <= menorPrecedencia) {
                    menorPrecedencia = prec;
                    indiceOperador = i;
                }
            }
        }
        
        if (indiceOperador != -1) {
            No no = new No(expr.charAt(indiceOperador));
            no.esquerda = criarArvoreRecursivo(expr, inicio, indiceOperador - 1);
            no.direita = criarArvoreRecursivo(expr, indiceOperador + 1, fim);
            return no;
        }
        
        if (expr.charAt(inicio) == '(' && expr.charAt(fim) == ')') {
            return criarArvoreRecursivo(expr, inicio + 1, fim - 1);
        }
        
        return new No(expr.charAt(inicio));
    }
    
    public static String paraPosfixa(No no) {
        if (no == null) return "";
        return paraPosfixa(no.esquerda) + paraPosfixa(no.direita) + no.valor;
    }
    
    private static boolean ehOperador(char c) {
        return "+-*/^".indexOf(c) != -1;
    }
    
    private static int precedencia(char op) {
        switch (op) {
            case '+': case '-': return 1;
            case '*': case '/': return 2;
            case '^': return 3;
            default: return 4;
        }
    }
    
    public static void imprimirArvore(No raiz) {
        imprimirArvoreRec(raiz, 0);
    }
    
    private static void imprimirArvoreRec(No no, int nivel) {
        if (no == null) return;
        
        imprimirArvoreRec(no.direita, nivel + 1);
        
        for (int i = 0; i < nivel; i++) System.out.print("    ");
        System.out.println(no.valor);
        
        imprimirArvoreRec(no.esquerda, nivel + 1);
    }
    
    public static void main(String[] args) {
        // Teste rápido
        String expressao = "(A*B+2*C^3)/2*A";
        System.out.println("Expressão infixa: " + expressao);
        
        No arvore = criarArvoreSimples(expressao);
        
        System.out.println("\nÁrvore de expressão:");
        imprimirArvore(arvore);
        
        String posfixa = paraPosfixa(arvore);
        System.out.println("\nExpressão posfixa: " + posfixa);
    }
}