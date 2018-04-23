package panaderia;

import panaderia.modelo.panaderia.Panaderia;

import java.io.IOException;
import java.util.logging.*;

public class Aplicacion {

    private final static Logger logger = Logger.getLogger("panaderia");

    public static void main(String[] args) {
        initLoggers();
        Panaderia pana = new Panaderia();
        pana.abrir();
        logger.log(Level.INFO, "panaderia.modelo.panaderia.Panaderia cierra.");
    }

    private static void initLoggers() {
        logger.setUseParentHandlers(false); //Para que solo tenga los de aca
        //Console channel
        initConsoleChannel();
        //File channel
        initFileChannel();
    }

    private static void initConsoleChannel() {
        Handler consoleHandler = new ConsoleHandler();
        logger.addHandler(consoleHandler);
        consoleHandler.setLevel(Level.ALL);
    }

    private static void initFileChannel() {
        Handler fileHandler = null;
        try {
            fileHandler = new FileHandler("./panaderia.log", false);
        } catch (IOException e) {
            logger.log(Level.WARNING, "Error al crear el archivo de logs.");
            e.printStackTrace();
        }
        SimpleFormatter simpleFormatter = new SimpleFormatter();
        fileHandler.setFormatter(simpleFormatter);
        logger.addHandler(fileHandler);
        fileHandler.setLevel(Level.ALL);
    }
}
