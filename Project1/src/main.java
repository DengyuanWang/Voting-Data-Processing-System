import java.io.IOException;

public class main {
    public static void main(String[] args) throws IOException {
        System.out.print("hello world\n");
        System.out.println("Working Directory = " +
                System.getProperty("user.dir"));
        Data_IO D_IO = new Data_IO();
        if(D_IO.specify_path_in("./test.csv")==false)
        {
            System.out.print("Specify path fail\n");
        }

        if(D_IO.load_data()==false)
            System.out.print("Load data fail\n");

        Voting_System VO=new Voting_System();
        boolean voting_res = VO.process_voting(D_IO);
    }
}
