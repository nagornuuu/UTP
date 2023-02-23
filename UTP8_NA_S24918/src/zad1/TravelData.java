package zad1;


import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class TravelData {

    public ArrayList<Offer> offers = new ArrayList<>();             // lista ofert
    private Scanner scanner;

    public static class Offer {

        public Locale locale;
        public String country;
        public Date dateFrom;
        public Date dateTo;
        public String place;
        public Double price;
        public String currency;

        public Offer(Locale locale, String country, Date dateFrom, Date dateTo, String place, Double price, String currency) {
            this.locale = locale;
            this.country = country;
            this.dateFrom = dateFrom;
            this.dateTo = dateTo;
            this.place = place;
            this.price = price;
            this.currency = currency;
        }

        public String toString() {
            return "Offer{" +
                    "locale=" + locale + '\'' +
                    ", country=" + country + '\'' +
                    ", dateFrom=" + dateFrom + '\'' +
                    ", dateTo=" + dateTo + '\'' +
                    ", place=" + place + '\'' +
                    ", price=" + price + '\'' +
                    ", currency=" + currency + '}';
        }

        public String getCountry() {
            return country;
        }
        public Date getDateFrom() {
            return dateFrom;
        }
        public Date getDateTo() {
            return dateTo;
        }
        public Double getPrice() {
            return price;
        }
        public String getPlace() {
            return place;
        }
        public Locale getLocale() {
            return locale;
        }
        public String getCurrency() {
            return currency;
        }
    }

    public TravelData(File dataDir) {
        try {
            File[] tmp = dataDir.listFiles();                                                                     // lista plików w folderze data
            if (tmp != null) {                                                                                    // jeśli folder nie jest pusty
                for (File file : tmp) {                                                                           // iteracja po plikach
                    scanner = new Scanner(file);                                                                  // otwarcie pliku
                    while (scanner.hasNextLine()) {                                                               // iteracja po liniach
                        String[] string = scanner.nextLine().split("\t");                                   // podział linii na tablicę stringów
                        Locale locale = Locale.forLanguageTag(string[0].replace("_", "-"));      // tworzenie locale z stringa
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");           // tworzenie formatu daty
                        Date dateFrom = new Date(simpleDateFormat.parse(string[2]).getTime());                   // tworzenie daty z stringa
                        Date dateTo = new Date(simpleDateFormat.parse(string[3]).getTime());                     // tworzenie daty z stringa
                        if (Objects.equals(string[6], "PLN")) {                                               // jeśli waluta to złotówki
                            string[5] = string[5].replace(",", ".");                             // zamiana przecinka na kropkę
                        } else {
                            string[5] = string[5].replace(",", "");                              // zamiana przecinka na pusty string
                        }
                        Double price = Double.parseDouble(string[5]);

                        // tworzenie ceny z stringa
                        this.offers.add(new Offer(locale, string[1], dateFrom, dateTo, string[4], price, string[6]));
                    }
                }
            }
            scanner.close();
        } catch (FileNotFoundException | ParseException e) {
            e.printStackTrace();
        }
    }

    // metoda zwracająca listę ofert
    public ArrayList<Offer> getOffers() {
        return offers;
    }

    public List<String> getOffersDescriptionsList(String locale, String dateFormat) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dateFormat);                       // tworzenie formatu daty
        ArrayList<String> offersDescriptionsList = new ArrayList<>();                               // lista opisów ofert
        StringBuilder tmp = new StringBuilder();                                                    // tworzenie stringbuildera do tworzenia opisu oferty

        for (Offer value : offers) {                                                                // iteracja po ofertach
            switch (locale) {
                case "en_GB":                                                                       // jeśli locale to en_GB
                    if (Objects.equals(value.getCountry(), "Japonia")) {                         // jeśli kraj to Japonia
                        tmp.append("Japan ");                                                       // dodanie do stringbuildera "Japan "
                    } else if (Objects.equals(value.getCountry(), "Włochy")) {                   // jeśli kraj to Włochy
                        tmp.append("Italy ");                                                       // dodanie do stringbuildera "Italy "
                    } else {                                                                        // w przeciwnym wypadku
                        tmp.append("United States ");                                               // dodanie do stringbuildera "United States "
                    }
                    break;
                case "pl":
                case "pl_PL":
                    if (Objects.equals(value.getCountry(), "United States")) {                   // jeśli kraj to USA
                        tmp.append("Stany Zjednoczone Ameryki ");                                   // dodanie do stringbuildera "Stany Zjednoczone Ameryki "
                    } else {                                                                        // w przeciwnym wypadku
                        tmp.append(value.getCountry()).append(" ");                                 // dodanie do stringbuildera nazwy kraju i spacji
                    }
                    break;
            }
            // dodanie do stringbuildera daty od i do, miejsca, ceny i waluty
            tmp.append(simpleDateFormat.format(value.getDateFrom())).append(" ").append(simpleDateFormat.format(value.getDateTo())).append(" ");
            if (locale.equals("en_GB")) {                                               // jeśli locale to en_GB
                if (Objects.equals(value.getPlace(), "jezioro")) {                   // jeśli miejsce to jezioro
                    tmp.append("lake ");                                                // dodanie do stringbuildera "lake "
                } else if (Objects.equals(value.getPlace(), "mountains")) {          // jeśli miejsce to mountains
                    tmp.append("mountains ");                                           // dodanie do stringbuildera "mountains "
                } else if (Objects.equals(value.getPlace(), "morze")) {              // jeśli miejsce to morze
                    tmp.append("sea ");                                                 // dodanie do stringbuildera "sea "
                }
            }
            else {                                                                      // w przeciwnym wypadku
                if (Objects.equals(value.getPlace(), "jezioro")) {                   // jeśli miejsce to jezioro
                    tmp.append("jezioro ");                                             // dodanie do stringbuildera "jezioro "
                } else if (Objects.equals(value.getPlace(), "morze")) {              // jeśli miejsce to morze
                    tmp.append("morze ");                                               // dodanie do stringbuildera "morze "
                } else if (Objects.equals(value.getPlace(), "mountains")) {          // jeśli miejsce to góry
                    tmp.append("góry ");                                                // dodanie do stringbuildera "góry "
                }
            }
            tmp.append(value.getPrice()).append(" ").append(value.getCurrency());      // dodanie do stringbuildera ceny i waluty
            offersDescriptionsList.add(tmp.toString());                                // dodanie do listy opisu oferty
            tmp = new StringBuilder();                                                 // czyszczenie stringbuildera
        }
        return offersDescriptionsList;                                                // zwrócenie listy opisów ofert
    }

}





