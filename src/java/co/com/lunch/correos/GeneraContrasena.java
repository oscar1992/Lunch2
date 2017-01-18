/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.lunch.correos;

/**
 *
 * @author oscarramirez
 */
public class GeneraContrasena {
    
    char[] abcedario;

    public GeneraContrasena() {
        this.abcedario = new char[]{'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z','A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
    }
    
    public String generaContrasena(String email){
        String retorna = "#";
        
        for(char letra: email.toCharArray()){
            int p = 0;
            for(char abc: abcedario){
                if(letra==abc){
                    int p2 = p+3;
                    if(p2>abcedario.length){
                        p2 = p2-abcedario.length;
                    }
                    retorna += abcedario[p2];
                }
                p += 1;
            }
        }
        if(retorna.length()>5){
            retorna = retorna.substring(0, 5);
        }
        System.out.println("Retorna: "+retorna);
        return retorna;
    }
    
}
