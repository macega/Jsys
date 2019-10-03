/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.testes;

/**
 *
 * @author julia
 */
public class Main {

    public static void main(String[] args) {

        int[] Lista = new int[10];
        for (int i = 0; i < Lista.length; i++) {
            System.out.println(i);
            Lista[i] = i * 100;
            if (Lista[i] > 1000) {
                Lista[1] = i;
            }
        }
//        String erro = "javax.persistence.PersistenceException: Exception [EclipseLink-4002] (Eclipse Persistence Services - 2.5.2.v20140319-9ad6abd): org.eclipse.persistence.exceptions.DatabaseException"
//                + "Internal Exception: com.microsoft.sqlserver.jdbc.SQLServerException: The TCP/IP connection to the host MACEGA, port 1433 has failed. Error: \"Connection refused: no further informa"
//                + "Internal Exception: com.microsoft.sqlserver.jdbc.SQLServerException: The TCP/IP connection to the host MACEGA, port 1433 has failed. Error: \"Connection refused: no further informa"
//                + "Internal Exception: com.microsoft.sqlserver.jdbc.SQLServerException: The TCP/IP connection to the host MACEGA, port 1433 has failed. Error: \"Connection refused: no further informa"
//                + "Internal Exception: com.microsoft.sqlserver.jdbc.SQLServerException: The TCP/IP connection to the host MACEGA, port 1433 has failed. Error: \"Connection refused: no further informa";
//        String mensagen = "teste 123";
//        
//        for (int i = 0; i < erro.length(); i++) {
//            System.out.println(i);
//            System.out.println(erro.length());
//            if (i <= 50) {
//                mensagen += System.getProperty("line.separator");
//                mensagen += erro.substring(i, erro.length());
//            }

        ///System.out.println(mensagen);
    }

}
