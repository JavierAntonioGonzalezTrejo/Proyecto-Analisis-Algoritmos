

public class LUPDescomposicion {
	
	private double[][] matrizLUP;
	private double[][] matrizL;
	private double[][] matrizU;
	private double[][] matrizP;
	private int[] vectorM; //Arreglo que almacena posicion de los '1' para matriz de permutacion	
	private int K; //Variable para verificar posicion de intercambio elemento y renglon
	public int[] vectorIns; //Vector donde se almacenara el numero de pasos realizados por cada instruccion
	private int i;
	public void LUP(double[][] matrizA) throws Exception
	{
		int n = matrizA.length;
		matrizLUP = new double[n][n]; 
		matrizLUP = matrizA;
		vectorM = new int[n];
		vectorIns = new int[21];//Instrucciones
		double pivote;
		
		for(i=0; i<21;i++)//Inicializar el valor de los pasos en 0
			vectorIns[i] = 0;
		vectorIns[0] = 1;//Instrucciones
		for(int i = 0; i < n; i++)
		{
			vectorIns[1] +=1;//Instrucciones
			vectorIns[2] +=1;//Instrucciones
			vectorM[i] = i;
		}
		vectorIns[1] +=1;//Instrucciones
			
		for(int k = 0; k < n-1; k++)
		{
			vectorIns[3] +=1;//Instrucciones
			pivote = 0;
			vectorIns[4] +=1;//Instrucciones
			for(int i = k; i < n; i++)
			{
				vectorIns[5] +=1;//Instrucciones
				vectorIns[6] +=1;//Instrucciones
				if( Math.abs(matrizLUP[i][k]) > pivote)
				{
					pivote = Math.abs(matrizLUP[i][k]);
					vectorIns[7] +=1;//Instrucciones
					K = i;
					vectorIns[8] +=1;//Instrucciones
				}
				vectorIns[9] +=1;//Instrucciones
				if(pivote == 0)
				{
					vectorIns[10] +=1;//Instrucciones
					throw new Exception();
				}								
			}
			vectorIns[5] +=1;//Instrucciones

			intercambiarElementos(k, K);
			vectorIns[11] +=1;//Instrucciones
			intercambiarRenglones(k, K);
			vectorIns[12] +=1;//Instrucciones
			pivote(k);
			vectorIns[13] +=1;//Instrucciones
		}
		vectorIns[3] +=1;//Instrucciones
	}

	public double[][] getMatrizLUP()
	{
		return matrizLUP;
	}
	
	private void pivote(int k)
	{	
		int n = matrizLUP.length;
		vectorIns[14] +=1;//Instrucciones
		matrizLUP[k][k] = matrizLUP[k][k];
		
		for(int i = k+1; i < n; i++)
		{
			vectorIns[15] +=1;//Instrucciones
			matrizLUP[i][k] = matrizLUP[i][k] / matrizLUP[k][k];
			vectorIns[16] +=1;//Instrucciones
			matrizLUP[k][i] = matrizLUP[k][i];
			vectorIns[17] +=1;//Instrucciones
		}
		vectorIns[15] +=1;//Instrucciones
		for(int i = k+1; i < n; i++)
		{
			vectorIns[18] +=1;//Instrucciones
			for(int j = k+1; j < n; j++)
			{
				vectorIns[19] +=1;//Instrucciones
				matrizLUP[i][j] = matrizLUP[i][j] - matrizLUP[i][k] * matrizLUP[k][j];
				vectorIns[20] +=1;//Instrucciones
			}
			vectorIns[19] +=1;//Instrucciones
		}
		vectorIns[18] +=1;//Instrucciones
	}
	
	//Metodo para intercambiar elementos en el vectorM
	private void intercambiarElementos(int elemento1, int elemento2)
	{
		int aux;

		aux = vectorM[elemento1];
		vectorM[elemento1] = vectorM[elemento2];
		vectorM[elemento2] = aux;
	}
	
	//Metodo para intercambiar renglones en la matriz
	private void intercambiarRenglones(int fila1, int fila2)
	{
		double aux;
		
		for (int i = 0; i < matrizLUP.length; i++)
		{     
			aux = matrizLUP[fila1][i];
			matrizLUP[fila1][i] = matrizLUP[fila2][i];
			matrizLUP[fila2][i] = aux;
		}
	}
	
	//Metodo para obtener la matriz L a partir de la matriz LUP
		public double[][] obtenerMatrizL()
		{
			int n = matrizLUP.length;
			matrizL = new double[n][n];
			
			for(int i = 0; i < n; i++)
			{
				for(int j = 0; j < n; j++)
				{
					if(i > j)
					{
						matrizL[i][j] = matrizLUP[i][j];
					}
					
					if(i == j)
					{
						matrizL[i][j] = 1;
					}

					if(i < j)
					{
						matrizL[i][j] = 0;
					}	
				}
			}
			return matrizL;
		}
	
	//Metodo para obtener la matriz U a partir de la matriz LUP
	public double[][] obtenerMatrizU()
	{
		int n = matrizLUP.length;
		matrizU = new double[n][n];
		
		for(int i = 0; i < n; i++)
		{
			for(int j = 0; j < n; j++)
			{
				if(i > j)
				{
					matrizU[i][j] = 0;
				}
				
				else
				{
					matrizU[i][j] = matrizLUP[i][j];
				}
			}
		}
		return matrizU;
	}

	//Metodo para obtener la matriz P usando vectorM
	public double[][] obtenerMatrizP()
	{
		int n = matrizLUP.length;
		matrizP = new double[n][n];
		
		for(int i = 0; i < n; i++)
		{
			matrizP[i][vectorM[i]] = 1;
		}
		return matrizP;
	}

}