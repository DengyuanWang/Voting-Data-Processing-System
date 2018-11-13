import java.util.Scanner;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.filechooser.FileSystemView;

    public class OpenFile {
        JFileChooser fileChooser = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        StringBuilder sb = new StringBuilder();
        String s = "";

        public void PickMe() throws Exception{
            // if(fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){

            //     //get the file
            //     java.io.File file = fileChooser.getSelectedFile();
            //     s = file.getAbsolutePath();
            //     System.out.print(s);
            //     //create a scanner for the file
            //     Scanner input = new Scanner(file);

            //     //read text from file
            //     while(input.hasNext()){
            //         sb.append(input.nextLine());
            //         sb.append("\n");
            //     }

            //     input.close();

            int r = fileChooser.showOpenDialog(null);
            
            if(r == JFileChooser.APPROVE_OPTION){
                    java.io.File file = fileChooser.getSelectedFile();
                    // s will store the selected file's path
                    s = file.getAbsolutePath();
                    System.out.print(s);
                    System.out.print("-----------------");
                    
                    Scanner input = new Scanner(file);

                    while(input.hasNext()){
                        sb.append(input.nextLine());
                        sb.append("\n");
                    }

            }
            else{
                sb.append("No file was selected");
            }
        }
    }