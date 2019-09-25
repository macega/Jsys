package br.sql.util;

import br.sql.log.Log;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

/**
 *
 * @author Juliano Alves Medina
 */
public class FileEx extends File {

    public FileEx(String pathname) {
        super(pathname);
    }

    public String stringRead() throws FileNotFoundException {
        return new String(byteread());
    }

    /**
     *
     * @param charsetName
     * @return
     * @throws FileNotFoundException
     */
    public String stringRead(String charsetName) throws FileNotFoundException {
        try {
            return new String(byteread(), charsetName);
        } catch (UnsupportedEncodingException e) {
            Log.registraErro("FileEx", "stringRead", e);
        }
        return null;
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
        } catch (IOException e) {
            Log.registraErro(this.getClass().getName(), "byteread", e);
            return null;
        }
        byte[] ret = bout.toByteArray();
        try {
            data_in.close();
        } catch (IOException e) {
            Log.registraErro(this.getClass().getName(), "byteread", e);
        }
        try {
            file_input.close();
        } catch (IOException e) {
            Log.registraErro(this.getClass().getName(), "byteread", e);
        }
        try {
            bout.close();
        } catch (IOException e) {
            Log.registraErro(this.getClass().getName(), "byteread", e);
        }
        return ret;
    }

    public final static String jpeg = "jpeg";
    public final static String jpg = "jpg";
    public final static String gif = "gif";
    public final static String tiff = "tiff";
    public final static String tif = "tif";
    public final static String png = "png";
    public final static String xml = "xml";

    public static String getExtension(FileEx f) {
        return getExtension((File) f);
    }

    /*
     * Get the extension of a file.
     */
    public static String getExtension(File f) {
        String ext = null;
        String s = f.getName();
        int i = s.lastIndexOf('.');

        if (i > 0 && i < s.length() - 1) {
            ext = s.substring(i + 1).toLowerCase();
        }
        return ext;
    }
}
