package br.sql.util;

import br.sql.log.Log;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import javax.swing.JOptionPane;

/**
 *
 * @author Juliano Alves Medina
 */
public class GravaNoArquivo {

    private String str;
    private char[] cbuf;
    private int c;
    private byte[] b;

    public GravaNoArquivo() {
    }

    public File salvarArquivo(StringBuilder dados, String pasta, String arq, String extencao) {
        setStr(dados.toString());
        return salvarArquivo(pasta, arq, extencao);
    }

    public File salvarArquivo(String dados, String pasta, String arq, String extencao) {
        setStr(dados);
        return salvarArquivo(pasta, arq, extencao);
    }

    public File salvarArquivo(char[] dados, String pasta, String arq, String extencao) {
        setCbuf(dados);
        return salvarArquivo(pasta, arq, extencao);
    }

    public File salvarArquivo(int c, String pasta, String arq, String extencao) {
        setC(c);
        return salvarArquivo(pasta, arq, extencao);
    }

    public File salvarArquivo(byte[] b, String pasta, String arq, String extencao) {
        setB(b);
        return salvarArquivo(pasta, arq, extencao);
    }

    public void setB(byte[] b) {
        this.b = b;
        this.str = null;
        this.cbuf = null;
        this.c = 0;
    }

    public void setStr(String str) {
        this.str = str;
        this.cbuf = null;
        this.c = 0;
        this.b = null;
    }

    public void setCbuf(char[] cbuf) {
        this.str = null;
        this.cbuf = cbuf;
        this.c = 0;
        this.b = null;
    }

    public void setC(int c) {
        this.str = null;
        this.cbuf = null;
        this.c = c;
        this.b = null;
    }

    public void salvarDados(String texto, String arquivoDados) {
        File arq = new File(arquivoDados);
        try {
            if (arq.exists()) {
                try (FileWriter arquivo = new FileWriter(arq);
                        BufferedWriter grava = new BufferedWriter(arquivo)) {
                    grava.write(texto);
                }
            }
        } catch (IOException | SecurityException e) {
            Log.registraErro("GravaNoArquivo", "salvarDados", e);
        }
    }

    private File salvarArquivo(String pasta, String arq, String extencao) {
        FileOutputStream fw = null;
        OutputStreamWriter bw = null;
        try {
            File arquivo = getFile(pasta, arq, extencao);
            fw = new FileOutputStream(arquivo);
            bw = new OutputStreamWriter(fw, "UTF-8");
            if (str != null) {
                bw.write(str);
            } else if (cbuf != null) {
                bw.write(cbuf);
            } else if (c != 0) {
                bw.write(c);
            } else if (b != null) {
                fw.write(b);
            }
            bw.close();
            fw.close();
            return arquivo;
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
            Log.registraErro("GravaNoArquivo", "SalvarArquivo", e);
        } catch (IOException e) {
            Log.registraErro("GravaNoArquivo", "IOException|SalvarArquivo", e);
        } 
        return null;
    }

    private File getFile(String pasta, String arq, String extencao) {
        File arquivo = new File(pasta + "/" + arq + "." + extencao); //se já existir, será sobreescrito  
        if (!arquivo.exists()) {
            try {
                arquivo.createNewFile();
            } catch (IOException e1) {
                criaDiretorio(pasta);
                getFile(pasta, arq, extencao);
            }
        }
        return arquivo;
    }

    private void criaDiretorio(String novoDiretorio) {
        try {
            // Verifica se o diretório existe. 
            if (!new File(novoDiretorio).exists()) {
                if (novoDiretorio.contains("/") | novoDiretorio.contains("\\")) {
                    (new File(novoDiretorio)).mkdirs(); // Cria o diretórios 
                } else {
                    (new File(novoDiretorio)).mkdir(); // Cria o diretórios 
                }
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Err", "Erro ao criar o diretório" + ex.toString(), JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
    }
}
