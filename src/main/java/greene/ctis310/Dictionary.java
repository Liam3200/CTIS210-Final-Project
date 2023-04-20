package greene.ctis310;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author l.greene32
 */
public class Dictionary {

    private String word;

    public Dictionary() {
    }

    public Dictionary(String word) {
        this.word = word;
    }

    public String getDefinition() {
        try {
            URL dictionary = new URL("https://api.dictionaryapi.dev/api/v2/entries/en/" + word);

            HttpsURLConnection connection = (HttpsURLConnection) dictionary.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("User-Agent", "Chrome");

            int responseCode = connection.getResponseCode();
//            System.out.println("Response code: "+responseCode);

            if (responseCode != 200) {
                System.out.println("Error reading web page");
                System.out.println(connection.getResponseMessage());
                return "";
            }

            BufferedReader inputReader = new BufferedReader(
                    new InputStreamReader(connection.getInputStream()));
            String line;
            String[] spiltLines = null;
            while ((line = inputReader.readLine()) != null) {
                spiltLines = line.split("\"");
//                System.out.println(line);
            }
            inputReader.close();

            StringBuilder sb = new StringBuilder();
//            int count = 0;

            assert spiltLines != null;
            for (int i = 0; i < spiltLines.length; i++) {
                String s = spiltLines[i];
                if (s.equals("definition")) {
                    s = spiltLines[i+2];
//                    System.out.println(s);
                    sb.append(s).append(" ");
//                    count++;
//                    System.out.println(sb);
                    break;
                }
            }
            return sb.toString();

        } catch (MalformedURLException e) {
            System.out.println("Malformed Url: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IOException: " + e.getMessage());
        }
        return "";
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

}
