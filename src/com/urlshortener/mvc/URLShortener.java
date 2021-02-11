package com.urlshortener.mvc;
import java.util.ArrayList;
import java.util.Collections;

public class URLShortener {

    ArrayList<Integer> generateHashBaseK(int number, int k)
    {
        ArrayList<Integer> hash = new ArrayList<>();
        while (number != 0)
        {
            hash.add(number % 62);
            number = number / 62;
        }
        Collections.reverse(hash);
        return hash;
    }

    public String idToString(int id)
    {
        ArrayList<Integer> hash = generateHashBaseK(id,62);
        char[] mp = new char[62];
        int i = 0;
        for (char z = 'a'; z <= 'z'; z++)
            mp[i++] = z;
        for (char z = 'A'; z <= 'Z'; z++)
            mp[i++] = z;
        for (char z = '0'; z <= '9'; z++)
            mp[i++] = z;
        StringBuilder str = new StringBuilder();
        for (int k = 0 ; k < hash.size(); k++)
            str.append(mp[hash.get(k)]);
        return str.toString();
    }


}
