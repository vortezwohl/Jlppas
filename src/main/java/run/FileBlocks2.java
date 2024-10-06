package run;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class FileBlocks2 {

    public void testChunk(String inputfile ,String outputfile,long chunkFileSize) throws IOException {
        File sourceFile = new File(inputfile);
        //块文件目录
        String chunkFileFolder = outputfile;
        //先定义块文件大小
        //块数
        long chunkFileNum = (long) Math.ceil(sourceFile.length() * 1.0 / chunkFileSize);
        //创建读文件的对象
        RandomAccessFile raf_read = new RandomAccessFile(sourceFile, "r");
        //缓冲区
        byte[] b = new byte[(int)chunkFileSize];
        for (int i = 0; i < chunkFileNum; i++) {
            //块文件
            File chunkFile = new File(chunkFileFolder + i);
            //创建向块文件的写对象
            RandomAccessFile raf_write = new RandomAccessFile(chunkFile, "rw");
            int len = -1;

            while ((len = raf_read.read(b)) != -1) {

                raf_write.write(b, 0, len);
                //如果块文件的大小达到 设定大小开始写下一块儿
                if (chunkFile.length() >= chunkFileSize) {
                    break;
                }
            }
            raf_write.close();
        }
        raf_read.close();
    }
}
