package panaderia.modelo.impresor;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

public class ArchivoImpresor implements Impresor {

    private File archivo;

    public ArchivoImpresor(String filename) {
        this.archivo = new File(filename);
    }

    @Override
    public void escribir(String texto) {
        FileWriter fw;
        try {
            fw = new FileWriter(archivo, true);
            fw.write(new Date().toString() + " " + texto + "\n");
            fw.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
