import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class FileClient {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        try {
            Socket socket = new Socket("localhost", 5555);

            // Receive file from the server

                receiveFile(socket.getInputStream());



            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void receiveFile(InputStream inputStream) throws IOException {
        Scanner sc=new Scanner(System.in);
        while(inputStream.available()==0);

        FileOutputStream fos = new FileOutputStream("abc.txt");
        System.out.println();
        byte[] b = new byte[inputStream.available()];

        inputStream.read(b);

        int key;
        System.out.print("Enter key: ");
        key=sc.nextInt();

        if(key==2){
            for(int j=0;j<b.length;j++){
                b[j]= (byte) (b[j]-key);
            }
            System.out.println("File Received");
        }else{
            System.out.println("Wrong key");
            System.exit(0);
        }

        fos.write(b);

        String fileName="abc.txt";
        BufferedReader br=new BufferedReader(new FileReader(fileName));
        String line;
        int wordCount=0;
        while((line=br.readLine())!=null){
            String[] words=line.split(" ");
            wordCount+=words.length;
        }
        br.close();
        System.out.println("Word Count: "+ wordCount);
    }
}