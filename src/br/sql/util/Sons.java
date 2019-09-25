package br.sql.util;

import br.sql.log.Log;
import java.io.InputStream;
import javazoom.jl.decoder.JavaLayerException;

/**
 *
 * @author Juliano Alves Medina
 */
public class Sons {

    public void aPagar() {
        tocar(getClass().getResourceAsStream("/br/sql/music/Siren.mp3"));
    }

    public void naoEncontrado() {
        tocar(getClass().getResourceAsStream("/br/sql/music/Crash.mp3"));
    }

    public void pago() {
        tocar(getClass().getResourceAsStream("/br/sql/music/Wood.mp3"));
    }

    private void tocar(InputStream in) {
        try {
            javazoom.jl.player.Player p = new javazoom.jl.player.Player(in);
            p.play();
            p.close();
        } catch (JavaLayerException e) {
            Log.registraErro(this.getClass().getName(), "tocar", e);
        }
    }
}
