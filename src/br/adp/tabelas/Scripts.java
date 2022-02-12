package br.adp.tabelas;

import br.adp.geral.Geral;
import br.adp.util.ExecutaQuery;
import br.sql.log.Log;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Juliano Alves Medina
 */
public class Scripts {

    public void rodar(Geral aThis) {
        String base = aThis.bases[aThis.bancoDadosJC.getSelectedIndex()];
        try {
            for (int i = 1; i <= br.JavaApplicationJsys.VERCAO_DB; i++) {
                ResultSet rs = ExecutaQuery.executesqlScriptRS("select top 1 vercaoDB from jsysParametros", base);
                rs.next();
                int vercaoDB = rs.getInt("vercaoDB");
                ExecutaQuery.close();
                if (vercaoDB < i) {
                    try {
                        ExecutaQuery.executeSqlScriptList(br.adp.script.Update.update(i), base, aThis);
                    } catch (SQLException ex) {
                        Log.registraErro(this, "Rodar", ex); 
                    }
                    ExecutaQuery.executeSqlScript("update jsysParametros set vercaoDB = " + i, base);
                }
            }
        } catch (SQLException | ClassNotFoundException ex) {
            Log.registraErro(this, "rodar", ex);
            //ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Erro na execução do Script;"
                    + System.lineSeparator() + "Parametro não encontrado;"
                    + System.lineSeparator() + ex.getMessage());
        }
    }
}
