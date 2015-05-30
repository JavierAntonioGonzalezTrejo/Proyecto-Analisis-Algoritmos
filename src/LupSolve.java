
public class LupSolve {
	
	private double[] vectorY;
	private double[] vectorX;
	private double[] vectorB;
	private double[][] matrizLUP;
	private double[][] matrizL;
	private double[][] matrizU;
	private double[][] matrizP;
	
	private LUPDescomposicion matriz; 

	public LupSolve(double[] vectorB, double[][] matrizLUP){
		matriz = new LUPDescomposicion();
		this.vectorB = vectorB;
		this.matrizLUP = matrizLUP;
		this.vectorY = new double[vectorB.length];
		this.vectorX = new double[vectorB.length];
		
	}
	
	public void LUPSolve() throws Exception
	{
		matriz.LUP(matrizLUP);
		matrizL = matriz.obtenerMatrizL();
		matrizU = matriz.obtenerMatrizU();
		matrizP = matriz.obtenerMatrizP();
		
		int n = matrizL.length;
		
		for(int i = 0; i < n; i++)
		{
			vectorY[i] = obtenerValorVectorB(i) - sumatoriaY(i);
		}
		
		for(int i = n - 1; i >= 0; i--)
		{
			vectorX[i] = (vectorY[i] - sumatoriaX(i)) / matrizU[i][i];
		}
	}
	
	private double sumatoriaY(int i)
	{
		double resultado = 0; 
		
		for(int j = 0; j < i ; j++)
		{
			resultado = resultado + (matrizL[i][j] * vectorY[j]);
		}
		return resultado;
	}
	
	private double obtenerValorVectorB(int i)
	{
		double valor = 0;
		
		for(int j = 0; j < matrizL.length; j++)
		{
			valor = valor + matrizP[i][j] * vectorB[j];
		}
		return valor;	
	}
	
	private double sumatoriaX(int i)
	{
		double resultado = 0; 
		
		for(int j = i ; j < matrizU.length; j++)
		{
			resultado = resultado + (matrizU[i][j] * vectorX[j]);
		}
		return resultado;
	}
	
	public double[] getVectorX()
	{
		return vectorX;
	}
}
