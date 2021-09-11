/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package balancedbrackets;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

/**
 *
 * @author Gustavo Guimarães
 */
public class BalancedBrackets {
    public static void main(String[] args) throws Exception {
        
        
        File arquivo = new File("java gera prog.txt");        
        ArrayList<String> inputs = new ArrayList<String>();
        StringBuilder builder = new StringBuilder();

        if (arquivo.exists()) 
        {

            try (Scanner scanner = new Scanner(arquivo)) {
                while (scanner.hasNext())
                {
                    inputs.add(scanner.nextLine());
                }
            }

            try{
            for (String s : inputs)
            {
                builder.append(s);
                if (analisaString(s.trim())){                    
                    builder.append(" Válido");                    
                }
                else
                {
                    builder.append(" Inválido");
                }
                builder.append(System.lineSeparator());
            }
            }
            catch (Exception erro)
            {
                System.out.println(erro);
                System.exit(0);
            }       
            
            try
            {
                arquivo = new File("prog-check.txt");
                arquivo.createNewFile();
                try (FileWriter escritor = new FileWriter("prog-check.txt")) {
                    escritor.write(builder.toString());
                }
                System.out.print(builder.toString());
                System.out.println("\nArquivo de saída criado!");
            }
            catch (IOException erro){
                System.out.println("Ocorreu um erro!");                
            }                           
        } 
        else 
        {
            System.out.println("Arquivo de entrada não encontrado! O arquivo "
                    + "deve ser nomeado \'java gera prog.txt\', deve ser "
                    + "colocado na pasta raiz do projeto e deve incluir as "
                    + "sequencias de caracteres a ser analizada, uma por linha.");

        }
        
        
    }
    
    public static boolean analisaString (String s) throws Exception
    {
        Stack<Character> pilha = new Stack<>();
        
        for(char c : s.toCharArray())
        {
            if (c == '{' || c == '[' || c == '(')
            {
                pilha.add(c);
            }
            else if (c == '}' )
            {
                if (!pilha.isEmpty() && pilha.peek() == '{')
                    pilha.pop();
                else
                    return false;
            }
            else if (!pilha.isEmpty() && c == ']')
            {
                if (pilha.peek() == '[')
                    pilha.pop();
                else
                    return false;
            }
            else if (!pilha.isEmpty() && c == ')')
            {
                if (pilha.peek() == '(')
                    pilha.pop();
                else
                    return false;
            }
            else
            {
                throw new Exception("Há caracteres Não aceitos no arquivo. "
                        + "Apenas (, [, {, ), ] e } São aceitos");
            }
        }
        
        if (pilha.empty())
            return true;
        else
            return false;
    }
}
