/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Validacoes;

/**
 *
 * @author Samuel Lima
 */
public class Validacoes {
    public static String regexCaracteres(){
        String regex = "^[(?=.*[@!#$%^&*()/\\\\])[@!#$%^&*()/\\\\]{}]*$";
        return regex;
    }
    
    public static String regexNumeros(){
        String regex = "^[0-9]*$";
        return regex;
    }
    
    public static  String regexLetras(){
        String regex = "^[a-z A-Z]*$";
        return regex;
    }
}
