package br.sql.util;

import br.sql.log.Log;
import java.awt.BorderLayout;
import java.awt.print.PrinterException;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Copies;
import javax.swing.JFrame;
import javax.swing.SwingWorker;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.export.JRPrintServiceExporter;
import net.sf.jasperreports.engine.export.JRPrintServiceExporterParameter;
import net.sf.jasperreports.swing.JRViewer;

/**
 *
 * @author Juliano Alves Medina
 */
public final class ReportUtils implements PropertyChangeListener {

    /**
     * Abre um relatório.
     *
     * @param titulo Título usado na janela do relatório.
     * @param localRelatorio local no CLASSPATH do relatorio EX:
     * "/br/rel/clientes/ListaClientes.jasper"
     */
    @SuppressWarnings("unchecked")
    public static void openReport(String titulo, String localRelatorio) {
        openReport(titulo, localRelatorio, new HashMap());
    }

    /**
     * Abre um relatório usando uma conexão como datasource.
     *
     * @param titulo Título usado na janela do relatório.
     * @param localRelatorio local no CLASSPATH do relatorio EX:
     * "/br/rel/clientes/ListaClientes.jasper"
     * @param parametros Parâmetros utilizados pelo relatório.
     */
    @SuppressWarnings("unchecked")
    public static void openReport(String titulo, String localRelatorio, final Map parametros) {
        TaskReport task = new TaskReport(titulo, localRelatorio, parametros);
        task.execute();
        p.setVisible(true);
    }

    /**
     * Abre um relatório usando uma conexão como datasource.
     *
     * @param titulo Título usado na janela do relatório.
     * @param localRelatorio local no CLASSPATH do relatorio EX:
     * "/br/rel/clientes/ListaClientes.jasper"
     * @param parametros Parâmetros utilizados pelo relatório.
     * @param conexao Conexão utilizada para a execução da query.
     */
    @SuppressWarnings("unchecked")
    public static void openReport(String titulo, String localRelatorio, Map parametros, Connection conexao) {
        TaskReport task = new TaskReport(titulo, localRelatorio, parametros, conexao);
        task.execute();
        p.setVisible(true);
    }

    /**
     * Abre um relatório usando um datasource genérico.
     *
     * @param titulo Título usado na janela do relatório.
     * @param localRelatorio local no CLASSPATH do relatorio EX:
     * "/br/rel/clientes/ListaClientes.jasper"
     * @param dataSource Datasource a ser utilizado pelo relatório.
     */
    @SuppressWarnings("unchecked")
    public static void openReport(String titulo, String localRelatorio, JRDataSource dataSource) {
        TaskReport task = new TaskReport(titulo, localRelatorio, new HashMap(), dataSource);
        task.execute();
        p.setVisible(true);
    }

    /**
     * Abre um relatório usando um datasource genérico.
     *
     * @param titulo Título usado na janela do relatório.
     * @param localRelatorio local no CLASSPATH do relatorio EX:
     * "/br/rel/clientes/ListaClientes.jasper"
     * @param parametros Parâmetros utilizados pelo relatório.
     * @param dataSource Datasource a ser utilizado pelo relatório.
     */
    @SuppressWarnings("unchecked")
    public static void openReport(String titulo, String localRelatorio, Map parametros, JRDataSource dataSource) {
        TaskReport task = new TaskReport(titulo, localRelatorio, parametros, dataSource);
        task.execute();
        p.setVisible(true);
    }

    /**
     * imprime um relatório.
     *
     * @param localRelatrorio local no CLASSPATH do relatorio EX:
     * "/br/rel/clientes/ListaClientes.jasper"
     * @param parametros Parâmetros utilizados pelo relatório.
     * @param solicitaImpressora mostra a janela para selecionar a impressora.
     */
    @SuppressWarnings("unchecked")
    public static void printReport(String localRelatrorio, Map parametros, Boolean solicitaImpressora) {
        TaskReportPrint taskReportPrint = new TaskReportPrint(localRelatrorio, parametros, solicitaImpressora);
        taskReportPrint.execute();
        p.setVisible(true);
    }

    /**
     * imprime um relatório.
     *
     * @param localRelatrorio local no CLASSPATH do relatorio EX:
     * "/br/rel/clientes/ListaClientes.jasper"
     * @param parametros Parâmetros utilizados pelo relatório.
     * @param dataSource
     * @param solicitaImpressora mostra a janela para selecionar a impressora.
     */
    @SuppressWarnings("unchecked")
    public static void printReport(String localRelatrorio, Map parametros, JRDataSource dataSource, Boolean solicitaImpressora) {
        TaskReportPrint taskReportPrint = new TaskReportPrint(localRelatrorio, parametros, dataSource, solicitaImpressora);
        taskReportPrint.execute();
        p.setVisible(true);
    }

    /**
     * imprime um relatório.
     *
     * @param localRelatrorio local no CLASSPATH do relatorio EX:
     * "/br/rel/clientes/ListaClientes.jasper"
     * @param parametros Parâmetros utilizados pelo relatório.
     * @param dataSource
     * @param nomeImpressora
     */
    @SuppressWarnings("unchecked")
    public static void printReport(String localRelatrorio, Map parametros, JRDataSource dataSource, String nomeImpressora) {
        TaskReportPrint taskReportPrint = new TaskReportPrint(localRelatrorio, parametros, dataSource, nomeImpressora);
        taskReportPrint.execute();
        p.setVisible(true);
    }

    /**
     *
     * Metodo para impressao de relatorio
     *
     * @param titulo Titulo para o relatorio a ser impresso Ex: "Relatorio
     * Teste"
     * @param inputStream com o local do Relatorio ex:
     * Menu.class.getResourceAsStream("/br/rel/produtos/ProdutosNegativos.jasper"),
     * @param parametros do relatorio a ser impresso ex: Map parametros = new
     * HashMap();
     * @param dataSource caso tenha um
     * @param NomeImpressora endereço da impressoa ex: "\\\\SERVER-IBM\\EPSON
     * LX-300+ /II"
     * @throws JRException
     * @throws SQLException
     * @throws URISyntaxException
     * @throws PrinterException
     */
    @SuppressWarnings("unchecked")
    public static void printReport(
            final String titulo,
            final InputStream inputStream,
            final Map parametros,
            final JRDataSource dataSource,
            final String NomeImpressora) throws JRException, SQLException, URISyntaxException, PrinterException {
        /* Scan found services to see if anyone suits our needs */
        for (PrintService printService : PrintServiceLookup.lookupPrintServices(null, null)) {
            if (printService.getName().toUpperCase().equals(NomeImpressora.toUpperCase())) {
                JRPrintServiceExporter exporter = new JRPrintServiceExporter();
                JasperPrint JPrint;
                if (dataSource != null) {
                    JPrint = JasperFillManager.fillReport(inputStream, parametros, dataSource);
                } else {
                    JPrint = JasperFillManager.fillReport(inputStream, parametros, br.sql.acesso.ConnectionFactory.getSakilaConnection());
                }
                exporter.setParameter(JRExporterParameter.JASPER_PRINT, JPrint);
                /* We set the selected service and pass it as a paramenter */
                exporter.setParameter(JRPrintServiceExporterParameter.PRINT_SERVICE, printService);
                exporter.setParameter(JRPrintServiceExporterParameter.PRINT_SERVICE_ATTRIBUTE_SET, printService.getAttributes());
                PrintRequestAttributeSet printRequestAttributeSet = new HashPrintRequestAttributeSet();
                //printRequestAttributeSet.add(MediaSize.findMedia(4, 4, MediaPrintableArea.INCH));
                printRequestAttributeSet.add(new Copies(1));
                exporter.setParameter(JRPrintServiceExporterParameter.PRINT_REQUEST_ATTRIBUTE_SET, printRequestAttributeSet);
                exporter.setParameter(JRPrintServiceExporterParameter.DISPLAY_PAGE_DIALOG, Boolean.FALSE);
                exporter.setParameter(JRPrintServiceExporterParameter.DISPLAY_PRINT_DIALOG, Boolean.FALSE);
                //--- Print the document
                exporter.exportReport();
            }
        }
    }
//    /**
//     * imprime um relatório usando um datasource genérico.
//     *
//     * @param localRelatorio
//     * @param parametros Parâmetros utilizados pelo relatório.
//     * @param conexao Conexão utilizada para a execução da query.
//     * @param solicitaImpr solicitar impressora para enviar o relatorio
//     * @throws JRException Caso ocorra algum problema na execução do relatório
//     */
//    @SuppressWarnings("unchecked")
//    public static void pirntReport(
//            final String localRelatorio,
//            final Map parametros,
//            final Connection conexao,
//            final Boolean solicitaImpr) throws JRException {
//        TaskReportPrint taskReportPrint = new TaskReportPrint(localRelatorio, parametros, conexao, solicitaImpr);
//        taskReportPrint.execute();
//        p.setVisible(true);
//    }
//        /**
//     * imprime um relatório usando um datasource genérico.
//     *
//     * @param localRelatorio
//     * @param parametros Parâmetros utilizados pelo relatório.
//     * @param solicitaImpr solicitar impressora para enviar o relatorio
//     * @throws JRException Caso ocorra algum problema na execução do relatório
//     */
//    @SuppressWarnings("unchecked")
//    public static void pirntReport(
//            final String localRelatorio,
//            final Map parametros,
//            final Boolean solicitaImpr) throws JRException {
//        TaskReportPrint taskReportPrint = new TaskReportPrint(localRelatorio, parametros, solicitaImpr);
//        taskReportPrint.execute();
//        p.setVisible(true);
//    }

    /**
     *
     * @param inputStream
     * @param parametros
     * @param conexao
     * @return
     */
    @SuppressWarnings("unchecked")
    public static byte[] getPdfReport(final InputStream inputStream,
            final Map parametros,
            final Connection conexao) {
        byte[] pdf = null;
        try {
            JasperPrint print = JasperFillManager.fillReport(inputStream, parametros, conexao);
            pdf = JasperExportManager.exportReportToPdf(print);
        } catch (Exception e) {
            Log.registraErro("ImprimirDanfe", "nfePdf", e);
        }
        return pdf;
    }

    private static final Progress p = new Progress("Preparando Relatorio", true, true);

    @SuppressWarnings("unchecked")
    private static JasperPrint getJPrint(InputStream inputStream, Map parametros, Connection conexao) throws JRException {
        return JasperFillManager.fillReport(inputStream, parametros, conexao);
    }

    @SuppressWarnings("unchecked")
    private static JasperPrint getJPrint(InputStream inputStream, Map parametros, JRDataSource dataSource) throws JRException {
        return JasperFillManager.fillReport(inputStream, parametros, dataSource);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if ("progress".equals(evt.getPropertyName())) {
            p.setValue((Integer) evt.getNewValue());
        }
    }

    private static class TaskReportPrint extends SwingWorker<Void, String> {

        private String localRelatorio;
        private final Map parametros;
        private Connection conexao = null;
        private JRDataSource dataSource = null;
        private boolean solicitaImpressora = true;
        private String nomeImpressora;

        public TaskReportPrint(String localRelatorio, Map parametros, boolean solicitaImpressora) {
            this.localRelatorio = localRelatorio;
            this.parametros = parametros;
            this.solicitaImpressora = solicitaImpressora;
        }

        public TaskReportPrint(String localRelatorio, Map parametros, String nomeImpressora) {
            this.localRelatorio = localRelatorio;
            this.parametros = parametros;
            this.nomeImpressora = nomeImpressora;
        }

        public TaskReportPrint(String localRelatorio, Map parametros, Connection conexao, boolean solicitaImpressora) {
            this.localRelatorio = localRelatorio;
            this.parametros = parametros;
            this.conexao = conexao;
            this.solicitaImpressora = solicitaImpressora;
        }

        public TaskReportPrint(String localRelatorio, Map parametros, JRDataSource dataSource, boolean solicitaImpressora) {
            this.localRelatorio = localRelatorio;
            this.parametros = parametros;
            this.dataSource = dataSource;
            this.solicitaImpressora = solicitaImpressora;
        }

        public TaskReportPrint(String localRelatorio, Map parametros, Connection conexao, String nomeImpressora) {
            this.localRelatorio = localRelatorio;
            this.parametros = parametros;
            this.conexao = conexao;
            this.nomeImpressora = nomeImpressora;
        }

        public TaskReportPrint(String localRelatorio, Map parametros, JRDataSource dataSource, String nomeImpressora) {
            this.localRelatorio = localRelatorio;
            this.parametros = parametros;
            this.dataSource = dataSource;
            this.nomeImpressora = nomeImpressora;
        }

        @Override
        @SuppressWarnings("unchecked")
        protected Void doInBackground() {
            try {
                publish("Abrindo o banco de dados");
                JasperPrint JPrint;
                InputStream inputStream = getClass().getResourceAsStream(localRelatorio);
                if (conexao != null) {
                    JPrint = JasperFillManager.fillReport(inputStream, parametros, conexao);
                } else if (dataSource != null) {
                    JPrint = JasperFillManager.fillReport(inputStream, parametros, dataSource);
                } else {
                    JPrint = JasperFillManager.fillReport(inputStream, parametros, br.sql.acesso.ConnectionFactory.getSakilaConnection());
                }
                if ("".equals(nomeImpressora)) {
                    JasperPrintManager.printReport(JPrint, solicitaImpressora);
                } else {
                    for (PrintService printService : PrintServiceLookup.lookupPrintServices(null, null)) {
                        publish("Enviando para Impressora");
                        // .replace("\\", "\\\\")
                        if (printService.getName().toUpperCase().equals(nomeImpressora.toUpperCase())) {
                            JRPrintServiceExporter exporter = new JRPrintServiceExporter();
                            exporter.setParameter(JRExporterParameter.JASPER_PRINT, JPrint);
                            /* We set the selected service and pass it as a paramenter */
                            exporter.setParameter(JRPrintServiceExporterParameter.PRINT_SERVICE, printService);
                            exporter.setParameter(JRPrintServiceExporterParameter.PRINT_SERVICE_ATTRIBUTE_SET, printService.getAttributes());
                            PrintRequestAttributeSet printRequestAttributeSet = new HashPrintRequestAttributeSet();
                            //printRequestAttributeSet.add(MediaSize.findMedia(4, 4, MediaPrintableArea.INCH));
                            printRequestAttributeSet.add(new Copies(1));
                            exporter.setParameter(JRPrintServiceExporterParameter.PRINT_REQUEST_ATTRIBUTE_SET, printRequestAttributeSet);
                            exporter.setParameter(JRPrintServiceExporterParameter.DISPLAY_PAGE_DIALOG, Boolean.FALSE);
                            exporter.setParameter(JRPrintServiceExporterParameter.DISPLAY_PRINT_DIALOG, Boolean.FALSE);
                            //--- Print the document
                            exporter.exportReport();
                            break;
                        }
                    }
                }
            } catch (JRException | SQLException e) {
                Log.registraErro("ReportUtils.TaskReportPrint", "doInBackground", localRelatorio);
                Log.registraErro("ReportUtils.TaskReportPrint", "doInBackground", e);
            }
            return null;
        }

        @Override
        protected void process(List<String> chunks) {
            super.process(chunks);
            chunks.stream().forEach((s) -> {
                p.setMensagen(String.format(s));
            });
        }

        @Override
        protected void done() {
            super.done();
            p.dispose();
        }
    }

    private static class TaskReport extends SwingWorker<Void, String> {

        private JasperPrint JPrint;
        private final String titulo;
        private final String localRelatorio;
        private final Map parametros;
        private Connection conexao = null;
        private JRDataSource dataSource = null;

        public TaskReport(String titulo, String localRelatorio, Map parametros, Connection conexao) {
            this.titulo = titulo;
            this.localRelatorio = localRelatorio;
            this.parametros = parametros;
            this.conexao = conexao;
        }

        public TaskReport(String titulo, String localRelatorio, Map parametros, JRDataSource dataSource) {
            this.titulo = titulo;
            this.localRelatorio = localRelatorio;
            this.parametros = parametros;
            this.dataSource = dataSource;
        }

        public TaskReport(String titulo, String localRelatorio, Map parametros) {
            this.titulo = titulo;
            this.localRelatorio = localRelatorio;
            this.parametros = parametros;
        }

        @Override
        protected void process(List<String> chunks) {
            super.process(chunks);
            chunks.stream().forEach((s) -> {
                p.setMensagen(String.format(s));
            });
        }

        @Override
        protected Void doInBackground() {
            try {
                publish("Abrindo o banco de dados");
                if (conexao != null) {
                    JPrint = getJPrint(getClass().getResourceAsStream(localRelatorio), parametros, conexao);
                } else if (dataSource != null) {
                    JPrint = getJPrint(getClass().getResourceAsStream(localRelatorio), parametros, dataSource);
                } else {
                    JPrint = getJPrint(getClass().getResourceAsStream(localRelatorio), parametros, br.sql.acesso.ConnectionFactory.getSakilaConnection());
                }
                publish("Finalizando");
            } catch (SQLException | JRException e) {
                Log.registraErro("ReportUtils.TaskReport", "doInBackground", localRelatorio);
                Log.registraErro("ReportUtils.TaskReport", "doInBackground", e);
            }
            return null;
        }

        @Override
        protected void done() {
            super.done();
            JRViewer viewer = new JRViewer(JPrint);
            JFrame frameRelatorio = new JFrame(titulo);
            frameRelatorio.add(viewer, BorderLayout.CENTER);
            frameRelatorio.setSize(500, 500);
            frameRelatorio.setExtendedState(JFrame.MAXIMIZED_BOTH);
            frameRelatorio.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            p.dispose();
            frameRelatorio.setVisible(true);
            frameRelatorio.requestFocus();
        }
    }
}
