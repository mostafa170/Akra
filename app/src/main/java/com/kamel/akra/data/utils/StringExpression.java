package com.kamel.akra.data.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringExpression {
    /**
     * <h3>isEmailValid</h3>
     * <p>checks whether an email is valid or not.</p>
     *
     * @param email String
     * @return boolean
     * */
    public static boolean isEmailValid(String email){
        String regExp =
                "^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]|[\\w-]{2,}))@"
                        +"((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                        +"[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                        +"([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                        +"[0-9]{1,2}|25[0-5]|2[0-4][0-9]))|"
                        +"([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$";

        Pattern pattern = Pattern.compile(regExp,Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);

        return matcher.matches() && !isStringContainsArabicLetters(email);
    }

    /**
     * <h3>isPasswordValid</h3>
     * <p>checks whether a password is valid or not.</p>
     *
     * @param password String
     * @return boolean
     * */
    public static boolean isPasswordValid(String password){
        /*String regExp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,19}$";

        Pattern pattern = Pattern.compile(regExp);
        Matcher matcher = pattern.matcher(password);

        return matcher.matches();*/
        return password.length() >= 8;
    }

    /**
     * <h3>isTextValid</h3>
     * <p>checks whether a text is valid or not.</p>
     *
     * @param text String
     * @return boolean
     * */
    public static boolean isTextValid(String text){
        return text != null && !text.isEmpty();
    }

    public static Boolean isEqual(String str1, String str2) {
        return (str1==null||str2==null) ?false : str1.equals(str2) ? true : false;
    }

    public static boolean isNameValid(String name) {

        String replaceAll = name.replaceAll(" ", "");
        String regExpENG = "(?i)(^[a-z])((?![ .,'-]$)[a-z .,'-]){2,24}$";
        String regExpAR = "(^[\u0621-\u064A]+$)";

        Pattern patternENG = Pattern.compile(regExpENG);

        Pattern patternAR = Pattern.compile(regExpAR);

        return replaceAll.length() > 2 && (patternENG.matcher(replaceAll).matches() || patternAR.matcher(replaceAll).matches());
    }

    public static boolean isEnglish(String text){
        String regExp = "[a-zA-Z]*";
        Pattern pattern = Pattern.compile(regExp);

        return pattern.matcher(text).matches();
    }

    public static boolean isArabic(String text){
        text = text.replaceAll("\\s+","");

        String regExp = "(^[\u0621-\u064A]+$)";
        Pattern pattern = Pattern.compile(regExp);

        return pattern.matcher(text).matches();
    }

    private static boolean isStringContainsArabicLetters(String text){
        final Pattern arabicLettersPattern = Pattern.compile("[\\u0600-\\u06FF\\u0750-\\u077F]",
                Pattern.UNICODE_CASE | Pattern.CASE_INSENSITIVE);
        final Matcher arabicLettersMatcher = arabicLettersPattern.matcher(text);
        return arabicLettersMatcher.find();
    }

    public static List<String> getSubstringsBetweenTwoDelimiters(String str){
        // Stores the indices of
        List<String> keys = new ArrayList<>();
        Stack<Integer> delimiters = new Stack<Integer>();
        for(int i = 0; i < str.length(); i++)
        {
            // If opening delimiter
            // is encountered
            if (str.charAt(i) == '(')
                delimiters.add(i);

            // If closing delimiter
            // is encountered
            else if (str.charAt(i) == ')' &&
                    !delimiters.isEmpty())
            {
                // Extract the position
                // of opening delimiter
                int pos = delimiters.peek();
                delimiters.pop();
                // Length of subString
                int len = i - 1 - pos;
                // Extract the subString
                String ans = str.substring(
                        pos + 1, pos + 1 + len);
                try {
                    Integer.parseInt(ans);
                    keys.add("(" + ans + ")");
                }catch (NumberFormatException | NullPointerException ignored){ }
            }
        }
        return keys;
    }

    public static String replaceArabicNumbers(CharSequence original) {
        return original.toString().
                replaceAll("1","١")
                .replaceAll("2","٢")
                .replaceAll("3","٣")
                .replaceAll("4","٤")
                .replaceAll("5","٥")
                .replaceAll("6","٦")
                .replaceAll("7","٧")
                .replaceAll("8","٨")
                .replaceAll("9","٩")
                .replaceAll("0", "٠");
    }
}
