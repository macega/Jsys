package br.sql.acesso.ini;

import br.sql.log.Log;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Juliano Alves Medina
 */
public class IniFiles {

    private List<String> StringList;
    private String fileName;

    public IniFiles(String FileName) {
        fileName = FileName;
        this.ReadStringList(FileName);
    }

    private void ReadStringList(String FileName) {
        try {
            fileName = FileName;
            boolean fileexists = (new File(FileName)).exists();
            if (fileexists) {
                try (InputStream is = new FileInputStream(FileName)) {
                    Scanner entrada = new Scanner(is);
                    StringList = new ArrayList<>();
                    String str;
                    while (entrada.hasNextLine()) {
                        str = entrada.nextLine().trim();
                        if (str.length() > 1) {
                            StringList.add(str);
                        }
                    }
                }
            } else {
                StringList = new ArrayList<>();
            }
        } catch (IOException e) {
            Log.registraErro(this.getClass().getName(), "ReadStringList", e);
        }
    }

    private void WriteStringList(String FileName) {
        try {
            OutputStream os = new FileOutputStream(FileName);
            OutputStreamWriter osw = new OutputStreamWriter(os);
            try (BufferedWriter bw = new BufferedWriter(osw)) {
                for (String line : StringList) {
                    bw.write(line);
                    if (StringList.indexOf(line) < StringList.size() - 1) {
                        bw.newLine();
                    }
                }
            }
        } catch (IOException e) {
            Log.registraErro(this.getClass().getName(), "WriteStringList", e);
        }
    }

    private void updateLine(int Index, String str) {
        StringList.set(Index, str);
    }

    private int indexFromValue(String str) {
        return StringList.indexOf(str);
    }

    private String getLine(int Index) {
        if (StringList.size() > Index) {
            return StringList.get(Index);
        } else {
            return null;
        }
    }

    private List<String> readIdents(String section) {
        int i = indexFromValue("[" + section + "]");
        if (i >= 0) {
            List<String> lista = new ArrayList<>();
            for (int j = i + 1; j <= (StringList.size() - 1); j++) {
                if (getLine(j).indexOf("[") == 0) {
                    break;
                }
                lista.add(getLine(j));
            }
            return lista;
        } else {
            return null;
        }
    }

    public String getString(String section, String ident) {
        List<String> lista = readIdents(section);
        String val = null;
        if (lista != null) {
            for (String line : lista) {
                if (line.indexOf(ident + "=") == 0) {
                    val = line.substring(ident.length() + 1);
                    break;
                }
            }
        }
        return val;
    }

    public void setString(String section, String ident, String value) {
        List<String> lista = readIdents(section);
        if (lista != null) {
            int isct = indexFromValue("[" + section + "]") + 1;
            int lst = lista.size() - 1;
            for (String line : lista) {
                int idx = lista.indexOf(line);
                if (line.indexOf(ident + "=") == 0) {
                    updateLine(isct + idx, ident + "=" + value);
                    break;
                }
                if (idx == lst) {
                    StringList.add(isct + lst + 1, ident + "=" + value);
                }
            }
        } else {
            StringList.add("[" + section + "]");
            StringList.add(ident + "=" + value);
        }
        WriteStringList(fileName);
    }
}
