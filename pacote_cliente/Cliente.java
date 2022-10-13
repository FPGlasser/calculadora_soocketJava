package pacote_cliente;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ConnectException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Cliente {
    /**
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException{
        /*
        * Declarações das variaveis e instancias
        */
        
        InetAddress endereco = null;
        int porta;                         
        Socket socketCliente = null;

        DataOutputStream streamSaida = null;
        DataInputStream streamEntrada = null;

        Double numeroParaSoma;
        Double resultadoSoma;
        Scanner entradaIn = null;

        try{
        /*
        * inicialização das variaveis e  intancias 
        */
            porta = 7000;
            endereco = InetAddress.getLocalHost();
            
            socketCliente = new Socket(endereco, porta);

            if(socketCliente.isConnected()){
                System.out.println("Conexão com servidor realizada com sucesso");
                streamSaida = new DataOutputStream(socketCliente.getOutputStream());
                streamEntrada = new DataInputStream(socketCliente.getInputStream());
                entradaIn = new Scanner(System.in);
            }
            System.out.println("Enviar numeros para servidor realizar soma");
            System.out.println("0 para terminar operação");
            
            do{
                System.out.print("Enviar numero: ");
                numeroParaSoma = entradaIn.nextDouble();
                streamSaida.writeDouble(numeroParaSoma);

            }while(numeroParaSoma != 0);

            resultadoSoma = streamEntrada.readDouble();
            System.out.println();
            System.out.println("Resultado da somatoria: "+resultadoSoma);
            
        }
        catch(ConnectException connectExept){
            /*
            * só vai entrar neste bloco de ocorrer uma 
            * excepção do tipo ConnectException "Falha de 
            *conexão coom servidor
            */
            System.out.println(connectExept.getMessage()+" tente novamnte...");
        }
        catch(UnknownHostException unknowhostExcept){
            /*
            * só vai entrar neste bloco de ocorrer uma 
            * excepção do tipo UnknowHostException" host não encontrado"
            * 
            */
            System.out.println(unknowhostExcept.getMessage()+" tente novamnte...");
        }
        catch(IOException ioExcept){
            /*
            * só vai entrar neste bloco de ocorrer uma 
            * excepção do tipo IOException" input Output Exception"
            * erro de entrada ou de saida
            */
            System.out.println(ioExcept.getMessage()+" tente novamnte...");
        }
        finally{
           if(socketCliente != null){
            socketCliente.close();
            System.out.println();
            System.out.println("conexão encerrada com sucesso");
           } 
        }   
    }
}
