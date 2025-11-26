class VetorOrdenado {
    public static int[] vetorOrdenado(int[] vetA, int[] vetB) {
        int len = (vetA.length + vetB.length);
        int[] vetC = new int[len]; 

        int i = vetA.length - 1;
        int j = vetB.length - 1;
        int k = 0;

        while(i > 0 && j > 0) {
            vetC[k++] = vetA[i];
            vetC[k++] = vetB[j];
            i = i - 1;
            j = j - 1;

        }

        while(i >= 0) {
            vetC[k++] = vetA[i];
            i = i - 1;
        }

        while(j >= 0) {
            vetC[k++] = vetB[j];
            j = j - 1;
        }

        return vetC;
    }
    public static void main(String[] args){
        int[] vetA = {46,38,22,10};
        int[] vetB = {57,33,21};

        int len = (vetA.length + vetB.length);
        int[] vetC = new int[len]; 

        vetC = vetorOrdenado(vetA, vetB);

        for(int i : vetC) {
            System.out.print(i + " ");
        }
    }
}