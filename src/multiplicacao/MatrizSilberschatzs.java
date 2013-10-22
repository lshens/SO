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
		int K = A[0].length;
		for (int k = 0; k < K; k++) {
			C [linha][coluna] += A[linha][k] * B[k][coluna];
			System.out.println(Thread.currentThread().getName() + " Calculando " + A[linha][k] + " * " + B[k][coluna] + " = " +  A[linha][k] * B[k][coluna]);
		}
		System.out.println(Thread.currentThread().getName() + " Resultado " + C [linha][coluna]);
		matrizPrint(C);
	}
	
	public static void matrizPrint(int[][] a) {  
	      int lines = a.length;  
	      int cols = a[0].length;  
	      System.out.println("array[" + lines + "][" + cols + "] = ");  
	      for (int i = 0; i < lines; i++) {  
	         System.out.print("|");  
	         for (int j = 0; j < cols; j++) {  
	            System.out.print(" " + a[i][j] + ",");  
	         }  
	         System.out.println("|,");  
	      }  
	      System.out.println(";");  
	}  
	
	public static void main(String[] args) {
		
		 int aMatriz[][] = {{1, 4}, {2, 5}, {3, 6}};
		 int bMatriz[][] = {{7, 8, 9}, {10, 11, 12}};
		 int produto[][] = {{0, 0, 0}, {0, 0, 0}, {0, 0, 0}};
		 int M = aMatriz.length;
		 int N = bMatriz[0].length;
		 
		 if (M != N) {
			 throw new IllegalArgumentException("Matrizes Diferentes: " + M + "!=" + N);
		 }
		 
		 int NUM_THREADS = M * N;
		 Thread[] workers = new Thread[NUM_THREADS];
		 int AUX_THREADS = 0;
		 
		 for (int i = 0; i < M; i++) { // Percorre a linha
				for (int j = 0; j < N; j++) { // Percorre a coluna							
							workers[AUX_THREADS] = new Thread (new MatrizSilberschatzs(i, j, aMatriz, bMatriz, produto));
							workers[AUX_THREADS].start();
							try {
								workers[AUX_THREADS].join();
							} catch (InterruptedException e) {								
								e.printStackTrace();
							}
							AUX_THREADS++;		
				}
		 }		 
	}
}
