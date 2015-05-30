
public class LupSolve {
	
	private double[] vectorY;
	private double[] vectorX;
	private double[] vectorB;
	private double[][] matrizLUP;
	private double[][] matrizL;
	private double[][] matrizU;
	private double[][] matrizP;
	public int[] vectorPasos;
	private LUPDescomposicion matriz; 
	
	public LupSolve(double[] vectorB, double[][] matrizLUP){
		matriz = new LUPDescomposicion();
		this.vectorB = vectorB;
		this.matrizLUP = matrizLUP;
		this.vectorY = new double[vectorB.length];
		this.vectorX = new double[vectorB.length];
		
	}
	
	public void LUPSolve()
	{
		vectorPasos = new int [6]; //Contar instrucciones
		for(int i=0; i<6;i++)//Inicializar el valor de los pasos en 0
			vectorPasos[i] = 0;
		vectorPasos[0] += 1; //Contar instrucciones
		try {
			matriz.LUP(matrizLUP);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		matrizL = matriz.obtenerMatrizL();
		matrizU = matriz.obtenerMatrizU();
		matrizP = matriz.obtenerMatrizP();
		
		int n = matrizL.length;
		vectorPasos[1] += 1;//Contar instrucciones
		for(int i = 0; i < n; i++)
		{
			vectorY[i] = obtenerValorVectorB(i) - sumatoriaY(i);
			vectorPasos[2] += 1;
		}
		vectorPasos[1] += 1;//Contar instrucciones
		vectorPasos[3] += 1;//Contar instrucciones
		for(int i = n - 1; i >= 0; i--)
		{
			vectorX[i] = (vectorY[i] - sumatoriaX(i)) / matrizU[i][i];
			vectorPasos[4] += 1;//Contar instrucciones
		}
		vectorPasos[3] += 1;//Contar instrucciones
		vectorPasos[4] += 1;//Contar instrucciones
		vectorPasos[5] += 1;//Contar instrucciones
		matriz.pasosInst(2);
		
	}
	
	public String pasosLUPD(int x)
	{
		return matriz.pasosInst(x);
	}
	
	public int pasosLUPSolve(int x)
	{
		return vectorPasos[x];
	}
	
	public int pasosInstrucciones(int variable)
	{
		int var = vectorPasos[variable];
		return var;
		
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
