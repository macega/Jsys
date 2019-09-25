/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.adp.util;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Juliano Alves Medina
 */
public class FileEx extends File {

    public FileEx(String pathname) {
        super(pathname);
    }

    public String stringread() throws FileNotFoundException {
        return new String(byteread());
    }
    
    public byte[] byteread() throws FileNotFoundException {
        FileInputStream file_input = new FileInputStream(this);
        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        DataInputStream data_in = new DataInputStream(file_input);
        try {
            try {
                while (true) {
                    int read = data_in.read();
                    if (read < 0) {
                        break;
                    }
                    bout.write(read);
                }
            } catch (EOFException eof) {
            }
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        } finally {
            try {
                data_in.close();
                file_input.close();
                bout.close();
            } catch (IOException ex) {
                Logger.getLogger(FileEx.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        byte[] ret = bout.toByteArray();
        return ret;
    }
}
