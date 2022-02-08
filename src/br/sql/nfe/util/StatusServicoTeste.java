package br.sql.nfe.util;

import br.com.swconsultoria.certificado.exception.CertificadoException;
import br.com.swconsultoria.nfe.Nfe;
import br.com.swconsultoria.nfe.exception.NfeException;
import br.com.swconsultoria.nfe.dom.ConfiguracoesNfe;
import br.com.swconsultoria.nfe.dom.enuns.DocumentoEnum;
//import br.inf.portalfiscal.nfe.schema_4.retConsStatServ.TRetConsStatServ;
import br.com.swconsultoria.nfe.schema_4.retConsStatServ.TRetConsStatServ;
import br.sql.bean.JsysParametros;
import br.sql.log.Log;
import br.sql.util.Retorna;
import java.io.FileNotFoundException;

/**
 * @author Samuel Oliveira
 */
public class StatusServicoTeste {

    private static final JsysParametros PAR = Retorna.JsysParametros();

    public static TRetConsStatServ verificaStatusServico(DocumentoEnum tipoDocumento) throws NfeException, FileNotFoundException {
        try {
            ConfiguracoesNfe config = br.JavaApplicationJsys.iniciaConfigurações(PAR);
            TRetConsStatServ retorno = Nfe.statusServico(config, tipoDocumento);
            return retorno;
        } catch (CertificadoException ex) {
            Log.registraErro(StatusServicoTeste.class, "verificaStatusServico", ex);
        }
        return null;
    }
}
