package br.sql.log;

import br.sql.util.ManagerData;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Juliano Alves Medina
 */
public final class Log {

    private static File arquivo;
    private static FileReader fileReader;
    private static BufferedReader bufferedReader;
    private static FileWriter fileWriter;
    private static BufferedWriter bufferedWriter;

//    public static void registraErro(Class<?> t, String metodo, Throwable e) {
//        registraErro(t.getName(), metodo, e.getMessage());
//    }
    /**
     * Ex; br.sql.log.Log.registraErro(this.getClass().getName(), "Metodo", e);
     *
     * @param clase
     * @param metodo
     * @param e
     */
    public static void registraErro(String clase, String metodo, Exception e) {
        StringBuilder log = new StringBuilder();
        log.append("Classe: ").append(clase).append("; ")
                .append("Método: ").append(metodo).append("; ")
                .append("Exceção: ").append(e.getMessage())
                .append(";").append(e.toString()).append(";");
        escreverLog(log.toString());
        Toolkit.getDefaultToolkit().beep();
        print(e);
    }

    public static void registraErro(String clase, String metodo, Throwable e) {
        StringBuilder log = new StringBuilder();
        log.append("Classe: ").append(clase).append("; ")
                .append("Método: ").append(metodo).append("; ")
                .append("Exceção: ").append(e.getMessage())
                .append(";").append(e.toString()).append(";");
        escreverLog(log.toString());
        Toolkit.getDefaultToolkit().beep();
        print(e);
    }

    public static void registraErro(Class<?> t, String metodo, Exception e) {
        registraErro(t.getName(), metodo, e);
    }

    public static void registraErro(Object t, String metodo, Exception e) {
        registraErro(((Class<?>) t).getClass().getSimpleName(), metodo, e);
    }

    public static void registraErro(String clase, String metodo, String e) {
        StringBuilder log = new StringBuilder();
        log.append("Classe: ").append(clase).append("; ")
                .append("Método: ").append(metodo).append("; ")
                .append("Exceção: ").append(e).append(";");
        escreverLog(log.toString());
        Toolkit.getDefaultToolkit().beep();
        print(e);
    }

    /**
     * Ex; br.sql.log.Log.registraErro(this.getClass().getName(), "Metodo", e);
     *
     * @param clase
     * @param metodo
     * @param e
     */
    public static void registraErro(String clase, String metodo, java.sql.SQLException e) {
        StringBuilder log = new StringBuilder();
        log.append("Classe: ").append(clase).append("; ")
                .append("Método: ").append(metodo).append("; ")
                .append("Exceção: ").append(e.toString()).append(";");
        escreverLog(log.toString());
        Toolkit.getDefaultToolkit().beep();
        print(e);
    }

    /**
     * Ex; br.sql.Log.salvaLog.registraErro(this.getClass().getName(), "Metodo",
     * e);
     *
     * @param clase
     * @param metodo
     * @param e
     */
    public static void registraErro(String clase, String metodo, Error e) {
        StringBuilder log = new StringBuilder();
        log.append("Classe: ").append(clase).append("; ")
                .append("Método: ").append(metodo).append("; ")
                .append("Exceção: ").append(e.toString()).append(";");
        escreverLog(log.toString());
        Toolkit.getDefaultToolkit().beep();
        print(e);
    }

    /**
     * Ex; br.sql.log.Log.registraUsuario(this.getClass().getName(), "nome do
     * usuario logado");
     *
     * @param Usuario
     */
    public static void registraUsuario(String Usuario) {
        StringBuilder log = new StringBuilder();
        log.append("Usuario: ").append(Usuario).append(";");
        escreverLog(log.toString());
    }

    /**
     * Ex; br.sql.log.Log.registraErro(this.getClass().getName(), "Metodo", e,
     * "mensagem para Usuario");
     *
     * @param clase
     * @param metodo
     * @param e
     * @param mensUsuario
     */
    public static void registraErro(String clase, String metodo, Exception e, String mensUsuario) {
        StringBuilder log = new StringBuilder();
        log.append("Classe: ").append(clase).append("; ")
                .append("Método: ").append(metodo).append("; ")
                .append("Exceção: ").append(e.toString()).append(";");
        escreverLog(log.toString());
        Toolkit.getDefaultToolkit().beep();
        print(e);
        JOptionPane.showMessageDialog(
                null,
                mensUsuario.equals("") ? "Erro Inesperado no Programa" : mensUsuario,
                "ERRO",
                JOptionPane.ERROR_MESSAGE);
    }

    private static void escreverLog(String err) {
        try {
            arquivo = new File("log" + java.io.File.separator + ManagerData.convertDate(ManagerData.getDate(), "dd-MM-yyyy") + ".log");
            fileReader = new FileReader(arquivo);
            bufferedReader = new BufferedReader(fileReader);
            List<StringBuffer> texto = new ArrayList<>();
            while (bufferedReader.ready()) {
                texto.add(new StringBuffer(bufferedReader.readLine()));
            }
            fileWriter = new FileWriter(arquivo);
            bufferedWriter = new BufferedWriter(fileWriter);
            for (StringBuffer t : texto) {
                bufferedWriter.write(t.toString());
                bufferedWriter.newLine();
            }
            bufferedWriter.write(ManagerData.getHoraAtualTypeString(1) + " - " + err);
            bufferedWriter.close();
            bufferedReader.close();
        } catch (FileNotFoundException e) {
            try {
                arquivo.createNewFile();
                escreverLog(err);
            } catch (IOException e0) {
                criaDiretorio("log");
                escreverLog(err);
            }
        } catch (IOException e1) {
            JOptionPane.showMessageDialog(null, "Erro indeterminado o sistema será finalizado.", "ERRO", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
    }

    private static void criaDiretorio(String novoDiretorio) {
        try {
            String nomeDiretorio = novoDiretorio;
            if (!new File(nomeDiretorio).exists()) { // Verifica se o diretório existe. 
                (new File(nomeDiretorio)).mkdir();   // Cria o diretório 
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Err", "Erro ao criar o diretório" + ex.toString(), JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
    }

    private static void print(Exception e) {
        if (br.JavaApplicationJsys.LOG) {
            e.printStackTrace();
        }
    }

    private static void print(String e) {
        if (br.JavaApplicationJsys.LOG) {
            System.err.println(e);
        }
    }

    private static void print(Error e) {
        if (br.JavaApplicationJsys.LOG) {
            e.printStackTrace();
        }
    }

    private static void print(Throwable e) {
        if (br.JavaApplicationJsys.LOG) {
            e.printStackTrace();
        }
    }

}
