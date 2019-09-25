/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.sql.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author Juliano Alves Medina
 */
public class TextFile {

    private String sBody;
    private String Caminho;
    private FileInputStream Arquivo;
    private BufferedReader LinhaArquivo;
    private BufferedWriter sFilePath;

    public TextFile() {
    }

    public TextFile(String Caminho) {
        this.Caminho = Caminho;
    }

    public void openTextFile() throws IOException {
        Arquivo = new FileInputStream(this.Caminho);
        LinhaArquivo = new BufferedReader(new InputStreamReader(Arquivo, "ISO-8859-1"));
    }
    
    public void openTextFile(String encoding) throws IOException {
        Arquivo = new FileInputStream(this.Caminho);
        LinhaArquivo = new BufferedReader(new InputStreamReader(Arquivo, encoding));
    }

    public void openTextFileWriter() throws IOException {
        openTextFile();
        sBody = getContent();
    }

    public void openBuffered() throws IOException {
        sFilePath = new BufferedWriter(new FileWriter(Caminho));
    }

    public boolean next() throws IOException {
        return LinhaArquivo.ready();
    }

    public final String readLine() throws IOException {
        return LinhaArquivo.readLine();
    }

    public void closeTextFile() throws IOException {
        LinhaArquivo.close();
    }

    public void closeBuffered() throws IOException {
        sFilePath.flush();  // Valida o fluxo
        sFilePath.close();
    }

    public String getContent() throws IOException {
        String sContent = "";
        while (LinhaArquivo.ready()) {
            sContent += LinhaArquivo.readLine() + System.getProperty("line.separator");
        }
        return sContent;
    }

    public void Writeln(String Texto) throws IOException {
        sBody += Texto + System.getProperty("line.separator");
    }

    public void append(String Texto) throws IOException {
        sBody = Texto;
    }

    public void Write(String Destino, String Texto) throws IOException {
        sFilePath = new BufferedWriter(new FileWriter(Destino));
        sFilePath.write(Texto);
        sFilePath.flush();  // Valida o fluxo
        sFilePath.close();

    }

    public void WriteBuffered(String Texto) throws IOException {
        sFilePath.write(Texto);
    }

    public void WritelnBuffered(String Texto) throws IOException {
        sFilePath.write(Texto + System.getProperty("line.separator"));
    }

    public void Write(String Destino) throws IOException {
        sFilePath = new BufferedWriter(new FileWriter(Destino));
        sFilePath.write(sBody);
        sFilePath.flush();  // Valida o fluxo
        sFilePath.close();
    }

}
