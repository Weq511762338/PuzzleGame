package util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class CodeUtil {

    public static String getCode(){
        ArrayList<Character> list = new ArrayList<>();
        for (int i = 0; i < 26; i++) {
            list.add((char)('a' + i));//a - z
            list.add((char)('A' + i));//A - Z
        }
        String result = "";
        Random r = new Random();
        for (int i = 0; i < 4; i++) {
            int randomIndex = r.nextInt(list.size());
            char c = list.get(randomIndex);
            result = result + c;
        }

        int number = r.nextInt(10);
        result = result + number;

        ArrayList<Character> l = new ArrayList<>();
        for(int i = 0; i < result.length(); i++){
            l.add(result.charAt(i));
        }
        Collections.shuffle(l);

        String code = "";

        for(var a : l){
            code += a;
        }
        return code;
    }
}
