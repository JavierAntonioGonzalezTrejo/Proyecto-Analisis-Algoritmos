import javax.swing.*;

public class LUPDescomposicion {
	
	private double[][] matrizLUP;
	private int[] vectorM; //Arreglo que almacena posicion de los '1' para matriz de permutacion
	private int K; //Variable para verificar posicion de intercambio elemento y renglon
	
	public void LUP(double[][] matrizA)
	{
		int n = matrizA.length;
		matrizLUP = new double[n][n]; 
		matrizLUP = matrizA;
		vectorM = new int[n];
		double pivote;
		
		for(int i = 0; i < n; i++)
		{
			vectorM[i] = i;
		}
		
		for(int k = 0; k < n-1; k++)
		{
			pivote = 0;
			
			for(int i = k; i < n; i++)
			{
				if( Math.abs(matrizLUP[i][k]) > pivote)
				{
					pivote = Math.abs(matrizLUP[i][k]);
					K = i;
				}

				if(pivote == 0)
				{
					JOptionPane.showMessageDialog(null, "EL SISTEMA NO TIENE SOLUCION");
				}								
			}

			intercambiarElementos(k, K);	
			intercambiarRenglones(k, K);
			pivote(k);	
		}
	}

	public void pivote(int k)
	{	
		int n = matrizLUP.length;
		
		matrizLUP[k][k] = matrizLUP[k][k];
		
		for(int i = k+1; i < n; i++)
		{
			matrizLUP[i][k] = matrizLUP[i][k] / matrizLUP[k][k];
			matrizLUP[k][i] = matrizLUP[k][i];
		}
		
		for(int i = k+1; i < n; i++)
		{
			for(int j = k+1; j < n; j++)
			{
				matrizLUP[i][j] = matrizLUP[i][j] - matrizLUP[i][k] * matrizLUP[k][j];
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
	public void intercambiarRenglones(int fila1, int fila2)
	{
		double aux;
		
		for (int i = 0; i < matrizLUP.length; i++)
		{     
			aux = matrizLUP[fila1][i];
			matrizLUP[fila1][i] = matrizLUP[fila2][i];
			matrizLUP[fila2][i] = aux;
		}
	}
}