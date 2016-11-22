import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class XOR {

    // Алфавит
    public static String alphabet = "абвгдежзийклмнопрстуфхцчшщъыьэюя .,!?0123456789abcdefghijklmnopr";

    public static void main(String[] args) throws FileNotFoundException {
        // Организация считывания и записи данных
        Scanner keyScanner = new Scanner(new File("key.txt"));
        Scanner scanner = new Scanner(new File("input.txt"));
        PrintWriter printWriter = new PrintWriter(new File("output.txt"));

        // Считывание ключа и слова, превращая все буквы в строчные
        String text = scanner.nextLine().toLowerCase();

        String key = keyScanner.nextLine().toLowerCase();
        String ciphertext = "";

        key = chengeKeyLength(key, text);

        // Шифрование/дешифрование методом Гаммирования (псевдослучайные последовательности)
        for (int i = 0; i < text.length(); i++) {
            int index = alphabet.indexOf(text.charAt(i)) ^ alphabet.indexOf(key.charAt(i));
            ciphertext += alphabet.charAt(index);
        }

        printWriter.println(ciphertext);
        printWriter.flush();
    }

    public static String chengeKeyLength(String key, String text) {
        String result = "";
        while (result.length() + key.length() <= text.length()) {
            result += key;
        }
        for (int i = 0; i < text.length() - result.length(); i++) {
            result += key.charAt(i);
        }
        return result;
    }
}