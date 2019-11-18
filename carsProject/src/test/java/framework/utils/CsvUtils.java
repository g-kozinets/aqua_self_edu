package framework.utils;

import org.supercsv.io.CsvListReader;
import org.supercsv.io.CsvListWriter;
import org.supercsv.io.ICsvListReader;
import org.supercsv.io.ICsvListWriter;
import org.supercsv.prefs.CsvPreference;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class CsvUtils {
    private static final String FILE = PropertyReader.getProp("OutputFilePath");

    public static void writeCsvList(ArrayList<String> data) throws Exception {
        try (ICsvListWriter listWriter = new CsvListWriter(new FileWriter(FILE),
                CsvPreference.STANDARD_PREFERENCE)) {

            final String[] header = new String[]{"Все категории:"};

            // write the header
            listWriter.writeHeader(header);

            // write the customer lists
            for (String datum : data) {
                listWriter.write(datum);
            }
        }
    }


    public static List readCsvList() throws Exception {
        List<String> list2 = new ArrayList<>();

        ICsvListReader listReader = null;
        try {
            List<String> list;
            listReader = new CsvListReader(new FileReader(FILE), CsvPreference.STANDARD_PREFERENCE);

            listReader.getHeader(true); // skip the header (can't be used with CsvListReader)

            while ((list = listReader.read()) != null) {
                list2.add(list.get(0));
            }
        } finally {
            if (listReader != null) {
                listReader.close();
            }
        }
        return list2;
    }

    public static boolean compareCsvToList(ArrayList<String> list) throws Exception {
        return list.containsAll(readCsvList());
    }
}
