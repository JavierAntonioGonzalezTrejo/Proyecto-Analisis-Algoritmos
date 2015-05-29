import javax.swing.*;

public class LUPDescomposicion {
	
	private double[][] matrizU; //Matriz triangular inferior(U)
	private double[][] matrizL; //Matriz trangular superior
	private int[] vectorM; //Arreglo que almacena posicion de los '1' para matriz de permutacion
	private int K; //Variable para verificar posicion de intercambio elemento y renglon
	
	public void LUP(double[][] matrizA)
	{
		int n = matrizA.length; 
		double pivote;
		matrizU = new double[n][n];
		matrizL = new double[n][n];
		vectorM = new int[n];		
		
		for(int i = 0; i < n; i++)
		{
			vectorM[i] = i;
		}
		
		for(int k = 0; k < n-1; k++)
		{
			pivote = 0;
			
			for(int i = k; i < n; i++)
			{
				if( Math.abs(matrizA[i][k]) > pivote)
				{
					pivote = Math.abs(matrizA[i][k]);
					K = i;
				}

				if(pivote == 0)
				{
					JOptionPane.showMessageDialog(null, "EL SISTEMA NO TIENE SOLUCION");
				}								
			}

			intercambiarElementos(k, K);	
			intercambiarRenglones(k, K, matrizA);
			pivote(k, matrizA);	
		}
	}
		
	public void pivote(int k, double[][] matriz)
	{	
		int n = matriz.length;
		
		matrizU[k][k] = matriz[k][k];
		
		for(int i = k+1; i < n; i++)
		{
			matrizL[i][k] = matriz[i][k] / matrizU[k][k];
			matrizU[k][i] = matriz[k][i];
		}
		
		for(int i = k+1; i < n; i++)
		{
			for(int j = k+1; j < n; j++)
			{
				matriz[i][j] = matriz[i][j] - matrizL[i][k] * matrizU[k][j];
			}
		}
	}
	
	//Metodo para intercambiar elementos en el vectorM
	public void intercambiarElementos(int elemento1, int elemento2)
	{
		int aux;

		aux = vectorM[elemento1];
		vectorM[elemento1] = vectorM[elemento2];
		vectorM[elemento2] = aux;
	}
	
	//Metodo para intercambiar renglones en la matriz
	public void intercambiarRenglones(int fila1, int fila2, double[][] matriz)
	{
		double aux;
		
		for (int i = 0; i < matriz.length; i++)
		{     
			aux = matriz[fila1][i];
			matriz[fila1][i] = matriz[fila2][i];
			matriz[fila2][i] = aux;
		}
	}
}