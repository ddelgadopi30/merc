package viewModel;





import java.io.IOException;
import java.sql.SQLException;
import model.FirebaseConnection;
import model.ReadData;
import view.Inventario;
import view.Splash;

/**
 *
 * @author juand
 */
public class Principal {

    public static void main(String[] args) throws IOException, SQLException {
        FirebaseConnection.conectar();
        Splash Splash = new Splash();
        Splash.setVisible(true);
        ProductoDAO.actualizarInv();
        ReadData.leer();
        ReadData.leerpro();

          
        
        
        
    }

}
