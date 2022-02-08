package br.sql.nfe.util;

public class Verifica {
 
    public static boolean csStat(String csStat) {
        return "100".equals(csStat) //  Autorizado o uso da NF-e
                || "101".equals(csStat) //  Cancelamento de NF-e homologado
                || "102".equals(csStat) //  Inutilização de número homologado
                || "103".equals(csStat) //  Lote recebido com sucesso
                || "104".equals(csStat) //  Lote processado
                || "105".equals(csStat) //  Lote em processamento
                || "106".equals(csStat) //  Lote não localizado
                || "107".equals(csStat) //  Serviço em Operação
                || "108".equals(csStat) //  Serviço Paralisado Momentaneamente (curto prazo)
                || "109".equals(csStat) //  Serviço Paralisado sem Previsão
                || "110".equals(csStat) //  Uso Denegado
                || "111".equals(csStat) //  Consulta cadastro com uma ocorrência
                || "112".equals(csStat) //  Consulta cadastro com mais de uma ocorrência
                || "124".equals(csStat) //  EPEC Autorizado
                || "128".equals(csStat) //  Lote de Evento Processado
                || "135".equals(csStat) //  Evento registrado e vinculado a NF-e
                || "136".equals(csStat) //  Evento registrado, mas não vinculado a NF-e
                || "137".equals(csStat) //  Nenhum documento localizado para o Destinatário
                || "138".equals(csStat) //  Documento localizado para o Destinatário
                || "139".equals(csStat) //  Pedido de Download processado
                || "140".equals(csStat) //  Download disponibilizado
                || "142".equals(csStat) //  Ambiente de Contingência EPEC bloqueado para o Emitente
                || "150".equals(csStat) //  Autorizado o uso da NF-e, autorização fora de prazo
                || "151".equals(csStat) //  Cancelamento de NF-e homologado fora de prazo
                || "301".equals(csStat) //  Uso Denegado: Irregularidade fiscal do emitente
                || "302".equals(csStat) //  Rejeição: Irregularidade fiscal do destinatário
                || "303".equals(csStat);//  Uso Denegado: Destinatário não habilitado a operar na UF
    }
}
