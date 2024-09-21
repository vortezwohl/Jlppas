import Run.FileBlocks2;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileblockTest {

    public static void main(String[] args) throws IOException {

        FileBlocks2 fileBlocks2 = new FileBlocks2();

        fileBlocks2.testChunk("D:\\competition\\input\\4.rar","D:\\competition\\output\\temp\\",1024*1024);


        File file = new File("D:\\competition\\output\\temp\\");
        String[] list = file.list();


        for (int i = 0; i < list.length ; i++) {

            Path path = Paths.get("D:\\competition\\output\\output\\"+i);
            Path pathCreate = Files.createDirectories(path);
            fileBlocks2.testChunk("D:\\competition\\output\\temp\\" + i,"D:\\competition\\output\\output\\"+i+"\\",20);
        }



    }



}
