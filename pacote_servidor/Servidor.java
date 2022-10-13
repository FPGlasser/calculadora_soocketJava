
package pacote_servidor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor{
    /**
     * @param args
     * @throws Exception
     */
    public static void main(String[] args){
        
        int porta;
        ServerSocket socketServidor = null;
        Socket server = null;

        DataInputStream streamEntrada = null;
        DataOutputStream streamSaida = null;

        double valorParaSoma;
        double soma = 0;

        try{

            porta  = 7000;
            socketServidor = new ServerSocket(porta);
            System.out.println("Servidor rodando na porta: "+porta+" aguardando conexão");

            server = socketServidor.accept();
            System.out.println("Conexão estabelecida com sucesso com:"
            +server.getInetAddress()+" "+server.getPort());

            streamEntrada = new DataInputStream(server.getInputStream());
            streamSaida = new DataOutputStream(server.getOutputStream());

            
            System.out.println("Aguardando valores para realizar soma....");

            do{//laço que vai rodar rebendo valor enviado por cliente
                valorParaSoma = streamEntrada.readDouble();
                soma += valorParaSoma;

            }while(valorParaSoma != 0);

            streamSaida.writeDouble(soma);
            System.out.println();
            System.out.println("Resultado da somatoria:" +soma);

            
        }catch(IOException ioExcept){
            System.out.println(ioExcept.getMessage());
        }
        
        finally{
            try{
                socketServidor.close();
                server.close();
                System.out.println();
                System.out.println("conexão encerrada com sucesso");
            }catch(IOException ioExcep){
                System.out.println(ioExcep.getLocalizedMessage());
            }  
        }   
    }
}