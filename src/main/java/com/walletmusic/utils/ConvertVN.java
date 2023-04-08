package com.walletmusic.utils;

import java.text.Normalizer;
import java.util.regex.Pattern;

public class ConvertVN {
    public static void main(String[] args) {
//        System.out.println(Normalizer.normalize("Nguyễn Anh Quốc",Normalizer.Form.NFD));
        System.out.print(removeAccent("Sinh đông    Viên Công Nghệ Thông Tin"));
    }


    public static String removeAccent(String s) {

        String temp = Normalizer.normalize(s, Normalizer.Form.NFD);
        Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
        return pattern.matcher(temp).replaceAll("").replaceAll("Đ", "D").replace("đ", "d");
    }
}
