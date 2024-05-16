package support;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

//Classe para geração da data e hora para as Screenshots
public class Generator {
    public static String dateTimeForFile(){
        Timestamp ts = new Timestamp(System.currentTimeMillis());
        return new SimpleDateFormat("yyyy-MM-dd hh-mm-ss ").format(ts);
    }
}
