package cadastroMonitores;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.thoughtworks.xstream.security.AnyTypePermission;

import java.io.File;
import java.io.FileInputStream;
import java.io.PrintWriter;

public class Persistencia{
	private XStream xstream = new XStream(new DomDriver("UTF-8"));
	private File arquivo;
	
	public Persistencia() {
		xstream.addPermission(AnyTypePermission.ANY);
	}
	
	public void salvarCentral(CentralDeInformacoes obj, String nomeXML) throws Exception{
		arquivo = new File(nomeXML);
		arquivo.createNewFile();
		PrintWriter gravar = new PrintWriter(arquivo);
		String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n";
		xml += xstream.toXML(obj);
		gravar.print(xml);
		gravar.close();
	}
	
	public CentralDeInformacoes recuperarCentral(String nomeXML) throws Exception {
		File arquivo = new File(nomeXML);
		if (arquivo.exists()) {
			FileInputStream fis = new FileInputStream(arquivo);
			return (CentralDeInformacoes) xstream.fromXML(fis);
		}
		return new CentralDeInformacoes();
	}
}