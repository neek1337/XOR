import com.sun.org.apache.xpath.internal.SourceTree;
import javafx.scene.control.Alert;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Scanner;

public class XOR {

    // Алфавит
    public static String alphabet = "абвгдежзийклмнопрстуфхцчшщъыьэюя .,!?0123456789abcdefghijklmnopr";
    public static int deep = 31 - Integer.numberOfLeadingZeros(alphabet.length());

    public static void main(String[] args) throws FileNotFoundException {
        // Организация считывания и записи данных
        Scanner keyScanner = new Scanner(new File("key.txt"));
        Scanner scanner = new Scanner(new File("input.txt"));
        PrintWriter printWriter = new PrintWriter(new File("output.txt"));

        // Считывание ключа и слова, превращая все буквы в строчные
        String text = "";
        String key = "";


        text = scanner.nextLine().toLowerCase();
        checkInput(text, "Открытый текст");

        key = keyScanner.nextLine().toLowerCase();
        checkInput(key, "Ключ");
        String ciphertext = "";

        key = chengeKeyLength(key, text);

        // Шифрование/дешифрование методом Гаммирования (псевдослучайные последовательности)
        for (int i = 0; i < text.length(); i++) {
            int index = alphabet.indexOf(text.charAt(i)) ^ alphabet.indexOf(key.charAt(i));
            ciphertext += alphabet.charAt(index);
        }
        System.out.print("Ключ:        ");
        System.out.println(getBinaryRepresentation(key));
        System.out.print("Текст:       ");
        System.out.println(getBinaryRepresentation(text));
        System.out.print("Криптограмма:");
        System.out.println(getBinaryRepresentation(ciphertext));

        printWriter.println(ciphertext);
        printWriter.flush();
    }

    public static String chengeKeyLength(String key, String text) {
        String result = "";
        while (result.length() + key.length() <= text.length()) {
            result += key;
        }
        int resultLength = result.length();
        for (int i = 0; i < text.length() - resultLength; i++) {
            result += key.charAt(i);
        }
        return result;
    }

    public static void checkInput(String input, String kindOfInput) throws InvalidParameterException {

        for (Character c : input.toCharArray()) {
            if (!alphabet.contains("" + c)) {
                throw new InvalidParameterException("Ошибка! " + kindOfInput + " содержит недопустмый символ " + c);
            }
        }

    }

    public static String getBinaryRepresentation(String s) {
        String result = "";
        for (Character c : s.toCharArray()) {
            int index = alphabet.indexOf(c);
            String indexStr = Integer.toBinaryString(index);
            int indexStrLength = indexStr.length();
            for (int i = 0; i < deep - indexStrLength; i++) {
                indexStr = "0" + indexStr;
            }
            result += indexStr;
        }

        return result;
    }
}