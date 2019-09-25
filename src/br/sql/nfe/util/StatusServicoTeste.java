package br.sql.nfe.util;

import br.com.samuelweb.certificado.exception.CertificadoException;
import br.com.samuelweb.nfe.Nfe;
import br.com.samuelweb.nfe.exception.NfeException;
import br.com.samuelweb.nfe.util.ConstantesUtil;
import br.inf.portalfiscal.nfe.schema_4.retConsStatServ.TRetConsStatServ;
import br.sql.bean.JsysParametros;
import br.sql.log.Log;
import br.sql.util.Retorna;

/**
 * @author Samuel Oliveira
 */
public class StatusServicoTeste {

    private static final JsysParametros PAR = Retorna.JsysParametros();

    public static TRetConsStatServ verificaStatusServico() {
        try {
            br.JavaApplicationJsys.iniciaConfigurações(PAR);
            TRetConsStatServ retorno = Nfe.statusServico(ConstantesUtil.NFE);
            return retorno;
        } catch (NfeException | CertificadoException ex) {
            Log.registraErro(StatusServicoTeste.class, "verificaStatusServico", ex);
        }
        return null;
    }
}
