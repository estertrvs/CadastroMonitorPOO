package cadastroMonitores;

import telas.*;

public class MainTelas{
    public static void main(String[] args) {
        CentralDeInformacoes central = new CentralDeInformacoes();
        Persistencia persistencia = new Persistencia();

        try {
            central = persistencia.recuperarCentral("central.xml");
            if (central.getAdmin() == null){
                new TelaCadastroADM(central);
            }else{
                new TelaLogin(central);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
