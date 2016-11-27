import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.Writer;
import java.security.InvalidParameterException;
import java.util.Scanner;

public class XOR {

    public static String alphabet = "абвгдежзийклмнопрстуфхцчшщъыьэюя .,!?0123456789<>|/@#$%^&*()_-+=";
    public static int deep = 31 - Integer.numberOfLeadingZeros(alphabet.length());

    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("input.txt"));
        Scanner scannerKey = new Scanner(new File("key.txt"));
        PrintWriter printWriter = new PrintWriter(new File("output.txt"));

        String text = "";
        String key = "";

        //System.out.println("Введите открытый текст");
        text = scanner.nextLine().toLowerCase();
        text = removeText(text);
        //checkInput(text, "Открытый текст");
        //System.out.println("Введите ключ");

        key = scannerKey.nextLine().toLowerCase();
        checkKey(key);
        String ciphertext = "";

        key = chengeKeyLength(key, text);

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

        System.out.println("Криптограмма:");
        System.out.println(ciphertext);
       // printWriter.println(ciphertext);
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

    public static void checkKey(String input) throws InvalidParameterException {

        for (Character c : input.toCharArray()) {
            if (!alphabet.contains(""+c)) {
                throw new InvalidParameterException("Ошибка! Ключ содержит недопустмый символ " + c);
            }
        }

    }

    public static String removeText(String input) throws InvalidParameterException {
            char[] mas = input.toCharArray();
        for (Character c : mas) {
            if (!alphabet.contains("" + c)) {
                input = input.replaceAll("" + c, "");
            }
        }
        return input;

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