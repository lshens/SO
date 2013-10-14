package multiplicacao;


public class MatrizSilberschatzs implements Runnable {
	private int linha;
	private int coluna;
	private int [][] A;
	private int [][] B;
	private int [][] C;

	public MatrizSilberschatzs (int linha, int coluna, int [][] A, int [][] B, int [][] C) {
		this.linha = linha;
		this.coluna = coluna;
		this.A = A;
		this.B = B;
		this.C = C;		
	}
	
	@Override
	public void run() {		
		int colunasA = A[0].length;
		
		if (linha != coluna) {
			throw new IllegalArgumentException("Matrizes não correspondem : "+ coluna + " diferente de " + linha);
		}
		
		for (int i = 0; i < linha; i++) { // Percorre a linha
			for (int j = 0; j < coluna; j++) { // Percorre a coluna
				for (int k = 0; k < colunasA; k++) {
					C [i][j] += A[i][k] * B[k][j];
				}
			 }
		 }
		
		matrizPrint(C);
	}
	
	public static void matrizPrint(int[][] pMatriz) {  
	      int linhas = pMatriz.length;  
	      int colunas = pMatriz[0].length;  
	      System.out.println("Resultado da Matriz = [" + linhas + "][" + colunas + "] = (");  
	      for (int i = 0; i < linhas; i++) {  
	         System.out.print("|");  
	         for (int j = 0; j < colunas; j++) {  
	            System.out.print(" " + pMatriz[i][j] + ",");  
	         }  
	         System.out.println("|,");  
	      }  
	      System.out.println(");");  
	   } 
	
	public static void main(String[] args) {
		
		 int aMatrix[][] = {{1, 4}, {2, 5}, {3, 6}};
		 int bMatrix[][] = {{7, 8, 9}, {10, 11, 12}};
		 int product[][] = {{0, 0, 0}, {0, 0, 0}, {0, 0, 0}};

		 (new Thread(new MatrizSilberschatzs(aMatrix.length, bMatrix[0].length, aMatrix, bMatrix, product))).start();	
//		 System.out.println(Thread.currentThread());
	}
}
