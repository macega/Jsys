package br.sql.util;

import br.sql.acesso.SQLDatabaseConnection;
import br.sql.bean.views.JsysIdProdutos;
import br.sql.log.Log;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;

/**
 *
 * @author Juliano Alves Medina
 */
public class Reconta {
    
    private static final SQLDatabaseConnection DADOS = new SQLDatabaseConnection();

    public static void estoque(final String codigo, final boolean mensagem) {

        final Progress p = new Progress();
        SwingWorker worker = new SwingWorker() {
            @Override
            protected Object doInBackground() throws Exception {
                Map<Object, Object> filtro = new HashMap<>();
                filtro.put("codigoBarra", codigo.trim());
                JsysIdProdutos idp = (JsysIdProdutos) Retorna.findOneResult("JsysIdProdutos.findByCodigoBarra", filtro);
                StringBuilder proc = new StringBuilder();
                proc.append("recontaEstoque('");
                proc.append(idp.getIdProduto().getIdProduto());
                proc.append("')");
                return DADOS.execProcudureRetInt(proc.toString());
            }

            @Override
            protected void done() {
                super.done();
                try {
                    if (Integer.parseInt(get().toString()) == 0) {
                        if (mensagem) {
                        JOptionPane.showMessageDialog(null, "Estoque Recontado com Sucesso", "Mensagem", JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                } catch (InterruptedException | ExecutionException e) {
                    Log.registraErro(this.getClass().getName(), "estoque", e);
                }
                p.dispose();
            }
        };
        worker.execute();
        p.setVisible(true);

    }

    public static void estoque(final boolean mensagem) {
        final Progress p = new Progress();
        SwingWorker worker;
        worker = new SwingWorker() {
            @Override
            protected Object doInBackground() throws Exception {
                return DADOS.execProcudureRetInt("recontaEstoque");
            }

            @Override
            protected void done() {
                super.done();
                try {
                    if (Integer.parseInt(get().toString()) == 0) {
                        if (mensagem) {
                            JOptionPane.showMessageDialog(null, "Estoque Recontado com Sucesso", "Mensagem", JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                } catch (InterruptedException | ExecutionException e) {
                    Log.registraErro(this.getClass().getName(), "estoque", e);
                }
                p.dispose();
                //p.finalize();
            }
        };
        worker.execute();
        p.setVisible(true);
    }
}
