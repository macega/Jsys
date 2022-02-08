package br.sql.util;

import br.sql.log.Log;
import java.text.DateFormat;
import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

/**
 *
 * @author Juliano Alves Medina
 */
public class ManagerData {

    public static final long DAY = 24L * 60L * 60L * 1000L;
    public static final long HOUR = 60L * 60L * 1000L;
    public static final long MINUTES = 1000L * 60L;
    public static final long SECONDS = 1000L;
    public static final String TIME_ZONE_ID = "America/La_Paz";
    public static final int INICIO = 0;
    public static final int FIM = 1;

    public static final String FORMATO_NFE = "yyyy-MM-dd'T'HH:mm:ssXXX";

    /**
     * novo gregorian calendar, onde temos a data atual
     *
     * @return
     */
    public static GregorianCalendar getGregorianCalendar() {
        GregorianCalendar gc = (GregorianCalendar) GregorianCalendar.getInstance();
        gc.setTime(new Date(System.currentTimeMillis()));
        gc.setTimeZone(TimeZone.getTimeZone(TIME_ZONE_ID));
        return gc;
    }

    /**
     *
     * @return Retorna a Data do sistema Tipo "Java.util.Date"
     */
    public static Date getDate() {
        return getGregorianCalendar().getTime();
    }

    /**
     *
     * @return
     */
    public static LocalDateTime now() {
        return LocalDateTime.now(ZoneId.of(TIME_ZONE_ID));
    }

    /**
     *
     * @param date
     * @param diaAdd adiciona dias
     * @param mesAdd adiciona meses
     * @param anoAdd adiciona anos
     * @return
     */
    public static Date getDate(Date date, int diaAdd, int mesAdd, int anoAdd) {
        GregorianCalendar gc = getGregorianCalendar();
        gc.setTime(date);
        if (diaAdd > 0) {
            gc.add(Calendar.DAY_OF_MONTH, diaAdd);
        }
        if (mesAdd > 0) {
            gc.add(Calendar.MONTH, mesAdd);
        }
        if (anoAdd > 0) {
            gc.add(Calendar.YEAR, anoAdd);
        }
        gc.set(Calendar.HOUR_OF_DAY, 0);
        gc.set(Calendar.MINUTE, 0);
        gc.set(Calendar.SECOND, 0);
        gc.set(Calendar.MILLISECOND, 0);
        return gc.getTime();
    }

    /**
     *
     * @return Retorna a data do sistema Tipo "String"
     */
    public static String getDataAtualTypeString() {
        return getDataAtualTypeString("dd/MM/yyyy");
    }

    /**
     *
     * @param formato "dd/MM/yyyy"
     * @return Retorna a data do sistema Tipo "String"
     */
    public static String getDataAtualTypeString(String formato) {
        try {
            SimpleDateFormat formatarDate = new SimpleDateFormat(formato);
            return formatarDate.format(getDate());
        } catch (Exception e) {
            return "";
        }
    }

    /**
     *
     * @param periodo 0 retorna a data com a hora 00:00.00.000 1 retorna a hora
     * 23:59.59.000
     * @return
     */
    public static Date getDataAtualTypeDate(int periodo) {
        GregorianCalendar gc = getGregorianCalendar();
        switch (periodo) {
            case 0:
                return setHoraInicioDia(gc.getTime());
            case 1:
                return setHoraFimDia(gc.getTime());
            default:
                return gc.getTime();
        }
    }

    /**
     * @param formato; 1 para retronar no formato HH:MM:ss e 0 para formato
     * HH:MM
     * @return Retorna a hora do sistema Tipo "String"
     */
    public static String getHoraAtualTypeString(int formato) {
        try {
            GregorianCalendar gc = getGregorianCalendar();
            int hora = gc.get(Calendar.HOUR_OF_DAY);//pega as horas
            int minuto = gc.get(Calendar.MINUTE);//pega os minutos
            int segundo = gc.get(Calendar.SECOND);//pega os segundos
            String horaString;//nova string horas
            String minString;//nova string minutos
            String segundoString;//nova string segundos
            if (hora < 10) {//se hora for menor que 10 precisa colocar um 0 à esquerda
                horaString = "0" + hora;
            } else {
                horaString = "" + hora;
            }
            if (minuto < 10) {//se minuto for menor que 10 precisa colocar um 0 à esquerda
                minString = "0" + minuto;
            } else {
                minString = "" + minuto;
            }
            if (segundo < 10) {//se segundo for menor que 10 precisa colocar um 0 à esquerda
                segundoString = "0" + segundo;
            } else {
                segundoString = "" + segundo;
            }
            if (formato == 0) {
                return horaString + ":" + minString + ":" + segundoString;
            } else {
                return horaString + ":" + minString;
            }
        } catch (Exception e) {
            Log.registraErro("ManagerData", "getHoraAtualTypeString", e);
            return "00:00";
        }
    }

    /**
     *
     * @param date uma data
     * @return retorna o primero dia do Ano da data passada como parametro
     */
    public static Date getPrimeiroDiaDoAno(Date date) {
        GregorianCalendar gc = getGregorianCalendar();
        gc.setTime(date);
        gc.set(Calendar.MONTH, 0);
        gc.set(Calendar.DAY_OF_MONTH, 1);
        return gc.getTime();
    }

    /**
     *
     * @param date uma data
     * @return retorna o primero dia do mes da data passada como parametro
     */
    public static Date getPrimeiroDiaDoMes(Date date) {
        GregorianCalendar gc = getGregorianCalendar();
        gc.setTime(date);
        gc.set(Calendar.DAY_OF_MONTH, 1);
        gc.set(Calendar.HOUR_OF_DAY, 0);
        gc.set(Calendar.MINUTE, 0);
        gc.set(Calendar.SECOND, 0);
        gc.set(Calendar.MILLISECOND, 0);
        return gc.getTime();
    }

    /**
     *
     * @param mes passar o valor para calcular com o mes atual
     * @return retorna o primero dia do mes atual
     */
    public static Date getPrimeiroDiaDoMes(int mes) {
        GregorianCalendar gc = getGregorianCalendar();
        gc.setTime(getDate());
        gc.set(Calendar.MONTH, gc.get(Calendar.MONTH) + mes);
        gc.set(Calendar.DAY_OF_MONTH, 1);
        gc.set(Calendar.HOUR_OF_DAY, 0);
        gc.set(Calendar.MINUTE, 0);
        gc.set(Calendar.SECOND, 0);
        gc.set(Calendar.MILLISECOND, 0);
        return gc.getTime();
    }

    /**
     *
     * @return retorna o primero dia do mes atual
     */
    public static Date getPrimeiroDiaDoMes() {
        return getPrimeiroDiaDoMes(0);
    }

    /**
     *
     * @param mes O primeiro mês do ano nos calendários Gregorian e Julian é
     * janeiro, que é 0; o último depende do número de meses em um ano.
     * @param ano
     * @return retorna o primero dia do mes atual
     */
    public static Date getPrimeiroDiaDoMes(int mes, int ano) {
        GregorianCalendar gc = getGregorianCalendar();
        gc.set(Calendar.YEAR, ano);
        gc.set(Calendar.MONTH, mes);
        gc.set(Calendar.DAY_OF_MONTH, 1);
        gc.set(Calendar.HOUR_OF_DAY, 0);
        gc.set(Calendar.MINUTE, 0);
        gc.set(Calendar.SECOND, 0);
        gc.set(Calendar.MILLISECOND, 0);
        return gc.getTime();
    }

    /**
     *
     * @param d
     * @return vai retornar o ultimo dia do proximo mes passado no @param d
     */
    public static Date getUltimoDiaDoMes(Date d) {
        GregorianCalendar gc = getGregorianCalendar();
        gc.setTime(d);
        gc.set(Calendar.HOUR_OF_DAY, 0);
        gc.set(Calendar.MINUTE, 0);
        gc.set(Calendar.SECOND, 0);
        gc.set(Calendar.MILLISECOND, 0);
        gc.set(Calendar.DAY_OF_MONTH, gc.getActualMaximum(Calendar.DAY_OF_MONTH));
        return gc.getTime();
    }

    /**
     *
     * @param mes O primeiro mês do ano nos calendários Gregorian e Julian é
     * janeiro, que é 0; o último depende do número de meses em um ano.
     * @param ano
     * @return vai retornar o ultimo dia do proximo mes passado no parametro
     */
    public static Date getUltimoDiaDoMes(int mes, int ano) {
        GregorianCalendar gc = getGregorianCalendar();
        gc.set(Calendar.YEAR, ano);
        gc.set(Calendar.MONTH, mes);
        gc.set(Calendar.HOUR_OF_DAY, 0);
        gc.set(Calendar.MINUTE, 0);
        gc.set(Calendar.SECOND, 0);
        gc.set(Calendar.MILLISECOND, 0);
        gc.set(Calendar.DAY_OF_MONTH, gc.getActualMaximum(Calendar.DAY_OF_MONTH));
        return gc.getTime();
    }

    /**
     *
     * @param mes passar o valor para calcular com o mes atual
     * @return vai retornar o ultimo dia do proximo mes passado no @param d
     */
    public static Date getUltimoDiaDoMes(int mes) {
        GregorianCalendar gc = getGregorianCalendar();
        //gc.setTime(getDataAtualTypeDate());
        gc.set(Calendar.MONTH, gc.get(Calendar.MONTH) + mes);
        gc.set(Calendar.DAY_OF_MONTH, gc.getActualMaximum(Calendar.DAY_OF_MONTH));
        gc.set(Calendar.HOUR_OF_DAY, 0);
        gc.set(Calendar.MINUTE, 0);
        gc.set(Calendar.SECOND, 0);
        gc.set(Calendar.MILLISECOND, 0);
        return gc.getTime();
    }

    public static Date getUltimoDiaDoMes() {
        return getUltimoDiaDoMes(0);
    }

    /**
     *
     * @param date
     * @return converte a data para string com o Formato "dd/MM/yyyy"
     */
    public static String convertBrDate(Date date) {
        return convertDate(date, "dd/MM/yyyy");
    }

    /**
     *
     * Usado para o formato do banco de dados SQL Server 2005
     *
     * @param date
     * @return converte a data para string com o Formato "yyyy-MM-dd"
     */
    public static String convertUsDate(Date date) {
        return convertDate(date, "yyyy-MM-dd");
    }

    /**
     * <code>SimpleDateFormat</code> is a concrete class for formatting and
     * parsing dates in a locale-sensitive manner. It allows for formatting
     * (date - text), parsing (text - date), and normalization.
     *
     * <p>
     * <code>SimpleDateFormat</code> allows you to start by choosing any
     * user-defined patterns for date-time formatting. However, you are
     * encouraged to create a date-time formatter with either
     * <code>getTimeInstance</code>, <code>getDateInstance</code>, or
     * <code>getDateTimeInstance</code> in <code>DateFormat</code>. Each of
     * these class methods can return a date/time formatter initialized with a
     * default format pattern. You may modify the format pattern using the
     * <code>applyPattern</code> methods as desired. For more information on
     * using these methods, see {@link DateFormat}.
     *
     * Date and Time Patterns
     * <p>
     * Date and time formats are specified by <em>date and time pattern</em>
     * strings. Within date and time pattern strings, unquoted letters from
     * <code>'A'</code> to <code>'Z'</code> and from <code>'a'</code> to
     * <code>'z'</code> are interpreted as pattern letters representing the
     * components of a date or time string. Text can be quoted using single
     * quotes (<code>'</code>) to avoid interpretation. <code>"''"</code>
     * represents a single quote. All other characters are not interpreted;
     * they're simply copied into the output string during formatting or matched
     * against the input string during parsing.
     * <p>
     * The following pattern letters are defined (all other characters from
     * <code>'A'</code> to <code>'Z'</code> and from <code>'a'</code> to
     * <code>'z'</code> are reserved):
     * <blockquote>
     * <table border=0 cellspacing=3 cellpadding=0 summary="Chart shows pattern
     * letters, date/time component, presentation, and examples.">
     * <tr bgcolor="#ccccff">
     * <th align=left>Letter
     * <th align=left>Date or Time Component
     * <th align=left>Presentation
     * <th align=left>Examples
     * <tr>
     * <td><code>G</code>
     * <td>Era designator
     * <td><a href="#text">Text</a>
     * <td><code>AD</code>
     * <tr bgcolor="#eeeeff">
     * <td><code>y</code>
     * <td>Year
     * <td><a href="#year">Year</a>
     * <td><code>1996</code>; <code>96</code>
     * <tr>
     * <td><code>Y</code>
     * <td>Week year
     * <td><a href="#year">Year</a>
     * <td><code>2009</code>; <code>09</code>
     * <tr bgcolor="#eeeeff">
     * <td><code>M</code>
     * <td>Month in year
     * <td><a href="#month">Month</a>
     * <td><code>July</code>; <code>Jul</code>; <code>07</code>
     * <tr>
     * <td><code>w</code>
     * <td>Week in year
     * <td><a href="#number">Number</a>
     * <td><code>27</code>
     * <tr bgcolor="#eeeeff">
     * <td><code>W</code>
     * <td>Week in month
     * <td><a href="#number">Number</a>
     * <td><code>2</code>
     * <tr>
     * <td><code>D</code>
     * <td>Day in year
     * <td><a href="#number">Number</a>
     * <td><code>189</code>
     * <tr bgcolor="#eeeeff">
     * <td><code>d</code>
     * <td>Day in month
     * <td><a href="#number">Number</a>
     * <td><code>10</code>
     * <tr>
     * <td><code>F</code>
     * <td>Day of week in month
     * <td><a href="#number">Number</a>
     * <td><code>2</code>
     * <tr bgcolor="#eeeeff">
     * <td><code>E</code>
     * <td>Day name in week
     * <td><a href="#text">Text</a>
     * <td><code>Tuesday</code>; <code>Tue</code>
     * <tr>
     * <td><code>u</code>
     * <td>Day number of week (1 = Monday, ..., 7 = Sunday)
     * <td><a href="#number">Number</a>
     * <td><code>1</code>
     * <tr bgcolor="#eeeeff">
     * <td><code>a</code>
     * <td>Am/pm marker
     * <td><a href="#text">Text</a>
     * <td><code>PM</code>
     * <tr>
     * <td><code>H</code>
     * <td>Hour in day (0-23)
     * <td><a href="#number">Number</a>
     * <td><code>0</code>
     * <tr bgcolor="#eeeeff">
     * <td><code>k</code>
     * <td>Hour in day (1-24)
     * <td><a href="#number">Number</a>
     * <td><code>24</code>
     * <tr>
     * <td><code>K</code>
     * <td>Hour in am/pm (0-11)
     * <td><a href="#number">Number</a>
     * <td><code>0</code>
     * <tr bgcolor="#eeeeff">
     * <td><code>h</code>
     * <td>Hour in am/pm (1-12)
     * <td><a href="#number">Number</a>
     * <td><code>12</code>
     * <tr>
     * <td><code>m</code>
     * <td>Minute in hour
     * <td><a href="#number">Number</a>
     * <td><code>30</code>
     * <tr bgcolor="#eeeeff">
     * <td><code>s</code>
     * <td>Second in minute
     * <td><a href="#number">Number</a>
     * <td><code>55</code>
     * <tr>
     * <td><code>S</code>
     * <td>Millisecond
     * <td><a href="#number">Number</a>
     * <td><code>978</code>
     * <tr bgcolor="#eeeeff">
     * <td><code>z</code>
     * <td>Time zone
     * <td><a href="#timezone">General time zone</a>
     * <td><code>Pacific Standard Time</code>; <code>PST</code>;
     * <code>GMT-08:00</code>
     * <tr>
     * <td><code>Z</code>
     * <td>Time zone
     * <td><a href="#rfc822timezone">RFC 822 time zone</a>
     * <td><code>-0800</code>
     * <tr bgcolor="#eeeeff">
     * <td><code>X</code>
     * <td>Time zone
     * <td><a href="#iso8601timezone">ISO 8601 time zone</a>
     * <td><code>-08</code>; <code>-0800</code>;  <code>-08:00</code>
     * </table>
     * </blockquote>
     * Pattern letters are usually repeated, as their number determines the
     * exact presentation:
     * <ul>
     * <li><strong><a name="text">Text:</a></strong>
     * For formatting, if the number of pattern letters is 4 or more, the full
     * form is used; otherwise a short or abbreviated form is used if available.
     * For parsing, both forms are accepted, independent of the number of
     * pattern letters.<br><br></li>
     * <li><strong><a name="number">Number:</a></strong>
     * For formatting, the number of pattern letters is the minimum number of
     * digits, and shorter numbers are zero-padded to this amount. For parsing,
     * the number of pattern letters is ignored unless it's needed to separate
     * two adjacent fields.<br><br></li>
     * <li><strong><a name="year">Year:</a></strong>
     * If the formatter's {link #getCalendar() Calendar} is the Gregorian
     * calendar, the following rules are applied.<br>
     * <ul>
     * <li>For formatting, if the number of pattern letters is 2, the year is
     * truncated to 2 digits; otherwise it is interpreted as a
     * <a href="#number">number</a>.
     * <li>For parsing, if the number of pattern letters is more than 2, the
     * year is interpreted literally, regardless of the number of digits. So
     * using the pattern "MM/dd/yyyy", "01/11/12" parses to Jan 11, 12 A.D.
     * <li>For parsing with the abbreviated year pattern ("y" or "yy"),
     * <code>SimpleDateFormat</code> must interpret the abbreviated year
     * relative to some century. It does this by adjusting dates to be within 80
     * years before and 20 years after the time the
     * <code>SimpleDateFormat</code> instance is created. For example, using a
     * pattern of "MM/dd/yy" and a <code>SimpleDateFormat</code> instance
     * created on Jan 1, 1997, the string "01/11/12" would be interpreted as Jan
     * 11, 2012 while the string "05/04/64" would be interpreted as May 4, 1964.
     * During parsing, only strings consisting of exactly two digits, as defined
     * by {@link Character#isDigit(char)}, will be parsed into the default
     * century. Any other numeric string, such as a one digit string, a three or
     * more digit string, or a two digit string that isn't all digits (for
     * example, "-1"), is interpreted literally. So "01/02/3" or "01/02/003" are
     * parsed, using the same pattern, as Jan 2, 3 AD. Likewise, "01/02/-3" is
     * parsed as Jan 2, 4 BC.
     * </ul>
     * Otherwise, calendar system specific forms are applied. For both
     * formatting and parsing, if the number of pattern letters is 4 or more, a
     * calendar specific {@linkplain
     *     Calendar#LONG long form} is used. Otherwise, a calendar specific
     * {@linkplain Calendar#SHORT short or abbreviated form} is used.<br>
     * <br>
     * If week year {@code 'Y'} is specified and the {linkplain #getCalendar()
     * calendar} doesn't support any <a
     * href="../util/GregorianCalendar.html#week_year"> week years</a>, the
     * calendar year ({@code 'y'}) is used instead. The support of week years
     * can be tested with a call to {@link
     *     DateFormat#getCalendar() getCalendar()}.{@link
     *     java.util.Calendar#isWeekDateSupported()
     *     isWeekDateSupported()}.<br><br></li>
     * <li><strong><a name="month">Month:</a></strong>
     * If the number of pattern letters is 3 or more, the month is interpreted
     * as <a href="#text">text</a>; otherwise, it is interpreted as a <a
     * href="#number">number</a>.<br><br></li>
     * <li><strong><a name="timezone">General time zone:</a></strong>
     * Time zones are interpreted as <a href="#text">text</a> if they have
     * names. For time zones representing a GMT offset value, the following
     * syntax is used:
     * <pre>
     *     <a name="GMTOffsetTimeZone"><i>GMTOffsetTimeZone:</i></a>
     *             <code>GMT</code> <i>Sign</i> <i>Hours</i> <code>:</code> <i>Minutes</i>
     *     <i>Sign:</i> one of
     *             <code>+ -</code>
     *     <i>Hours:</i>
     *             <i>Digit</i>
     *             <i>Digit</i> <i>Digit</i>
     *     <i>Minutes:</i>
     *             <i>Digit</i> <i>Digit</i>
     *     <i>Digit:</i> one of
     *             <code>0 1 2 3 4 5 6 7 8 9</code></pre>
     * <i>Hours</i> must be between 0 and 23, and <i>Minutes</i> must be between
     * 00 and 59. The format is locale independent and digits must be taken from
     * the Basic Latin block of the Unicode standard.
     * <p>
     * For parsing, <a href="#rfc822timezone">RFC 822 time zones</a> are also
     * accepted.<br><br></li>
     * <li><strong><a name="rfc822timezone">RFC 822 time zone:</a></strong>
     * For formatting, the RFC 822 4-digit time zone format is used:
     *
     * <pre>
     *     <i>RFC822TimeZone:</i>
     *             <i>Sign</i> <i>TwoDigitHours</i> <i>Minutes</i>
     *     <i>TwoDigitHours:</i>
     *             <i>Digit Digit</i></pre>
     * <i>TwoDigitHours</i> must be between 00 and 23. Other definitions are as
     * for <a href="#timezone">general time zones</a>.
     *
     * <p>
     * For parsing, <a href="#timezone">general time zones</a> are also
     * accepted.
     * <li><strong><a name="iso8601timezone">ISO 8601 Time zone:</a></strong>
     * The number of pattern letters designates the format for both formatting
     * and parsing as follows:
     * <pre>
     *     <i>ISO8601TimeZone:</i>
     *             <i>OneLetterISO8601TimeZone</i>
     *             <i>TwoLetterISO8601TimeZone</i>
     *             <i>ThreeLetterISO8601TimeZone</i>
     *     <i>OneLetterISO8601TimeZone:</i>
     *             <i>Sign</i> <i>TwoDigitHours</i>
     *             {@code Z}
     *     <i>TwoLetterISO8601TimeZone:</i>
     *             <i>Sign</i> <i>TwoDigitHours</i> <i>Minutes</i>
     *             {@code Z}
     *     <i>ThreeLetterISO8601TimeZone:</i>
     *             <i>Sign</i> <i>TwoDigitHours</i> {@code :} <i>Minutes</i>
     *             {@code Z}</pre> Other definitions are as for <a href="#timezone">general
     * time zones</a> or
     * <a href="#rfc822timezone">RFC 822 time zones</a>.
     *
     * <p>
     * For formatting, if the offset value from GMT is 0, {@code "Z"} is
     * produced. If the number of pattern letters is 1, any fraction of an hour
     * is ignored. For example, if the pattern is {@code "X"} and the time zone
     * is {@code "GMT+05:30"}, {@code "+05"} is produced.
     *
     * <p>
     * For parsing, {@code "Z"} is parsed as the UTC time zone designator.
     * <a href="#timezone">General time zones</a> are <em>not</em> accepted.
     *
     * <p>
     * If the number of pattern letters is 4 or more, {@link
     *     IllegalArgumentException} is thrown when constructing a {@code
     *     SimpleDateFormat} or {linkplain #applyPattern(String) applying a
     * pattern}.
     * </ul>
     * <code>SimpleDateFormat</code> also supports <em>localized date and time
     * pattern</em> strings. In these strings, the pattern letters described
     * above may be replaced with other, locale dependent, pattern letters.
     * <code>SimpleDateFormat</code> does not deal with the localization of text
     * other than the pattern letters; that's up to the client of the class.
     * <p>
     *
     * <h4>Examples</h4>
     *
     * The following examples show how date and time patterns are interpreted in
     * the U.S. locale. The given date and time are 2001-07-04 12:08:56 local
     * time in the U.S. Pacific Time time zone.
     * <blockquote>
     * <table border=0 cellspacing=3 cellpadding=0 summary="Examples of date and
     * time patterns interpreted in the U.S. locale">
     * <tr bgcolor="#ccccff">
     * <th align=left>Date and Time Pattern
     * <th align=left>Result
     * <tr>
     * <td><code>"yyyy.MM.dd G 'at' HH:mm:ss z"</code>
     * <td><code>2001.07.04 AD at 12:08:56 PDT</code>
     * <tr bgcolor="#eeeeff">
     * <td><code>"EEE, MMM d, ''yy"</code>
     * <td><code>Wed, Jul 4, '01</code>
     * <tr>
     * <td><code>"h:mm a"</code>
     * <td><code>12:08 PM</code>
     * <tr bgcolor="#eeeeff">
     * <td><code>"hh 'o''clock' a, zzzz"</code>
     * <td><code>12 o'clock PM, Pacific Daylight Time</code>
     * <tr>
     * <td><code>"K:mm a, z"</code>
     * <td><code>0:08 PM, PDT</code>
     * <tr bgcolor="#eeeeff">
     * <td><code>"yyyyy.MMMMM.dd GGG hh:mm aaa"</code>
     * <td><code>02001.July.04 AD 12:08 PM</code>
     * <tr>
     * <td><code>"EEE, d MMM yyyy HH:mm:ss Z"</code>
     * <td><code>Wed, 4 Jul 2001 12:08:56 -0700</code>
     * <tr bgcolor="#eeeeff">
     * <td><code>"yyMMddHHmmssZ"</code>
     * <td><code>010704120856-0700</code>
     * <tr>
     * <td><code>"yyyy-MM-dd'T'HH:mm:ss.SSSZ"</code>
     * <td><code>2001-07-04T12:08:56.235-0700</code>
     * <tr bgcolor="#eeeeff">
     * <td><code>"yyyy-MM-dd'T'HH:mm:ss.SSSXXX"</code>
     * <td><code>2001-07-04T12:08:56.235-07:00</code>
     * <tr>
     * <td><code>"YYYY-'W'ww-u"</code>
     * <td><code>2001-W27-3</code>
     * </table>
     * </blockquote>
     *
     * <h4><a name="synchronization">Synchronization</a></h4>
     *
     * <p>
     * Date formats are not synchronized. It is recommended to create separate
     * format instances for each thread. If multiple threads access a format
     * concurrently, it must be synchronized externally.
     *
     * @param dtConsulta
     * @param format
     * @return
     * @see <a
     * href="http://java.sun.com/docs/books/tutorial/i18n/format/simpleDateFormat.html">Java
     * Tutorial</a>
     * @see java.util.Calendar
     * @see java.util.TimeZone
     * @see DateFormat
     * @see DateFormatSymbols
     * @author Mark Davis, Chen-Lieh Huang, Alan Liu
     */
    public static String convertDate(Date dtConsulta, String format) {
        try {
            if (dtConsulta != null) {
                SimpleDateFormat formatter = new SimpleDateFormat(format, new Locale("pt", "BR"));
                return formatter.format(dtConsulta);
            }
        } catch (Exception e) {
            Log.registraErro("ManagerData", "convertDate", e);
        }
        return null;
    }

    public static int getDifDay(Date date1, Date date2) {
        return (int) ((setHoraInicioDia(date2).getTime() - setHoraInicioDia(date1).getTime()) / DAY + 1);
    }

    /**
     *
     * @param date
     * @return retorna data com a hora zerada 00:00.000
     */
    public static Date setHoraInicioDia(Date date) {
        GregorianCalendar gc = getGregorianCalendar();

        gc.setTime(date);
        gc.set(GregorianCalendar.HOUR_OF_DAY, 0);
        //gc.set(GregorianCalendar.HOUR, 0);
        gc.set(GregorianCalendar.MINUTE, 0);
        gc.set(GregorianCalendar.SECOND, 0);
        gc.set(GregorianCalendar.MILLISECOND, 0);
        return gc.getTime();
    }

    /**
     *
     * @param date
     * @return retorna data com a hora 23:59.000
     */
    public static Date setHoraFimDia(Date date) {
        GregorianCalendar gc = getGregorianCalendar();
        gc.setTime(date);
        gc.set(GregorianCalendar.HOUR_OF_DAY, 23);
        //gc.set(GregorianCalendar.HOUR, 0);
        gc.set(GregorianCalendar.MINUTE, 59);
        gc.set(GregorianCalendar.SECOND, 59);
        gc.set(GregorianCalendar.MILLISECOND, 0);
        return gc.getTime();
    }

    /**
     * Retorna uma data com a soma dos dias passados como parametro.
     *
     * @param date
     * @param days Dias a serem somados com a data.
     * @return
     */
    public static Date sumDays(Date date, int days) {
        GregorianCalendar gc = getGregorianCalendar();
        gc.setTime(date);
        gc.add(GregorianCalendar.DAY_OF_MONTH, days);
        return gc.getTime();
    }

    /**
     * long diffSeconds = diff / 1000 % 60; long diffMinutes = diff / (60 *
     * 1000) % 60; long diffHours = diff / (60 * 60 * 1000) % 24; long diffDays
     * = diff / (24 * 60 * 60 * 1000);
     *
     * ex de uso: dateDiff(ManagerData.HOUR, firstDate, lastDate) % 24;
     *
     * @param parteData
     * @param firstDate
     * @param lastDate
     * @return
     */
    public static double dateDiff(long parteData, Date firstDate, Date lastDate) {
        long diff = lastDate.getTime() - firstDate.getTime();
        return diff / parteData;
    }

    /**
     *
     * @param firstDate
     * @param lastDate
     * @return a diferença da firstDate e lastDate
     */
    public static double dateDiffDay(Date firstDate, Date lastDate) {
        return dateDiff(DAY, firstDate, lastDate);
    }

    /**
     *
     * @param firstDate
     * @param lastDate
     * @return a diferença da firstDate e lastDate
     */
    public static double dateDiffHour(Date firstDate, Date lastDate) {
        return dateDiff(HOUR, firstDate, lastDate) % 24;
    }

    /**
     *
     * @param firstDate
     * @param lastDate
     * @return a diferença da firstDate e lastDate
     */
    public static double dateDiffMinutes(Date firstDate, Date lastDate) {
        return dateDiff(MINUTES, firstDate, lastDate) % 60;
    }

    /**
     *
     * @param firstDate
     * @param lastDate
     * @return a diferença da firstDate e lastDate
     */
    public static double dateDiffSeconds(Date firstDate, Date lastDate) {
        return dateDiff(SECONDS, firstDate, lastDate) % 60;
    }

    /**
     * Converte uma String para um objeto Date. Caso a String seja vazia ou
     * nula, retorna null - para facilitar em casos onde formulários podem ter
     * campos de datas vazios.
     *
     * @param data String no formato dd/MM/yyyy a ser formatada
     * @param formato fortato de entrada da Data
     * @return Date Objeto Date ou null caso receba uma String vazia ou nula
     */
    public static Date formataData(String data, String formato) {
        try {
            if (Validar.isNotNullOrWhite(data, formato)) {
                DateFormat formatter = new SimpleDateFormat(formato);
                return (java.util.Date) formatter.parse(data);
            }
        } catch (ParseException e) {
            Log.registraErro("ManagerData", "formataData", e);

        }
        return null;
    }

    public static String diaSemana(Calendar cal) {
        return new DateFormatSymbols().getWeekdays()[cal.get(Calendar.DAY_OF_WEEK)];
    }

    public static String diaSemanaPequeno(Calendar cal) {
        return new DateFormatSymbols().getShortWeekdays()[cal.get(Calendar.DAY_OF_WEEK)];
    }

    public static String diaMes(Calendar cal) {
        return String.valueOf(cal.get(Calendar.DAY_OF_MONTH));
    }

    public static List<String> getDiasSemana(Date firstDate, Date lastDate) {
        List<String> retorno = new ArrayList<>();
        int dias = ManagerData.getDifDay(firstDate, lastDate);
        for (int i = 0; i < dias; i++) {
            Date date = ManagerData.sumDays(firstDate, i);
            GregorianCalendar gc = getGregorianCalendar();
            gc.setTime(date);
            StringBuilder add = new StringBuilder();
            add.append(diaMes(gc)).append(" - ").append(diaSemanaPequeno(gc));
            retorno.add(add.toString());
        }
        return retorno;
    }

    public static List<Date> getList(Date dataInicio, Date dataFim) {
        int dias = ManagerData.getDifDay(dataInicio, dataFim);
        List<Date> ret = new ArrayList<>();
        for (int i = 0; i < dias; i++) {
            ret.add(ManagerData.sumDays(dataInicio, i));
        }
        return ret;
    }

    /*
     terminar de pois 
     @param ano o ano para calcular 
     */
    public void dataPascoaCarnaval(int ano) {
        GregorianCalendar data_Pascoa = ManagerData.getGregorianCalendar();
        GregorianCalendar data_Carnaval = ManagerData.getGregorianCalendar();
        GregorianCalendar data_CorpusChristi = ManagerData.getGregorianCalendar();
        GregorianCalendar data_SextaFeiraSanta = ManagerData.getGregorianCalendar();

        int a = ano % 19;
        int b = ano / 100;
        int c = ano % 100;
        int d = b / 4;
        int e = b % 4;
        int f = (b + 8) / 25;
        int g = (b - f + 1) / 3;
        int h = (19 * a + b - d - g + 15) % 30;
        int i = c / 4;
        int k = c % 4;
        int l = (32 + 2 * e + 2 * i - h - k) % 7;
        int m = (a + 11 * h + 22 * l) / 451;
        int mes = (h + l - 7 * m + 114) / 31;
        int dia = ((h + l - 7 * m + 114) % 31) + 1;

        data_Pascoa.set(Calendar.YEAR, ano);
        data_Pascoa.set(Calendar.MONTH, mes - 1);
        data_Pascoa.set(Calendar.DAY_OF_MONTH, dia);

        //Carnaval 47 dias antes da pascoa
        data_Carnaval.setTimeInMillis(data_Pascoa.getTimeInMillis());
        data_Carnaval.add(Calendar.DAY_OF_MONTH, -47);
        //CorpusChristi 60 dias apos a pascoa
        data_CorpusChristi.setTimeInMillis(data_Pascoa.getTimeInMillis());
        data_CorpusChristi.add(Calendar.DAY_OF_MONTH, 60);

        data_SextaFeiraSanta.setTimeInMillis(data_Pascoa.getTimeInMillis());
        data_SextaFeiraSanta.add(Calendar.DAY_OF_MONTH, -2);
    }

    public static Long horaToLong(String hora) {
        try {
            if (!"".equals(hora) & hora != null) {
                Long seg = (new Long(hora.substring(6, 8)) * SECONDS);
                Long min = (new Long(hora.substring(3, 5)) * MINUTES);
                Long hor = (new Long(hora.substring(0, 2)) * HOUR);
                return seg + min + hor;
            }
        } catch (NumberFormatException e) {
            Log.registraErro("ManagerData", "horaToLong", e);
        }
        return 0L;
    }
}
