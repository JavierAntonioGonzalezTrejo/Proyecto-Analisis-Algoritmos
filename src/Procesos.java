import java.io.IOException;

public class Procesos {
	public void cargarArchivo(){
		abrir();
	}
	
	private void abrir(){
		String file = new String("manual.pdf");
		
		try{
			Runtime.getRuntime().exec("cmd /c start "+file);
		
		}catch(IOException e){
			e.printStackTrace();
		}
		
	}
}