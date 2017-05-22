package file;

import java.io.*;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

/**
 * Created by pc on 2017/5/22.
 */
public class ZipUtils {
    public static void main(String[] args){
        String in = "";
        String out ="";

//        zipFile(in,out);
        zipDir(in,out);
    }

    /**
     * 文件压缩
     * @param in
     * @param out
     */
    public static void zipFile(String in,String out){
        try {

            File file = new File(in);
            if (file.isFile()){

            }

            BufferedInputStream bin = new BufferedInputStream(new FileInputStream(in));

            ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(out));
            BufferedOutputStream bos = new BufferedOutputStream(zos);

            zos.putNextEntry(new ZipEntry(file.getName()));

//            zos.setComment("123456");
            byte[] b = new byte[1024];
            int len = 0;
            while ((len = bin.read(b)) != -1){
                bos.write(b,0,len);
            }

            bos.close();
            zos.close();
            bin.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * 文件夹压缩
     * to do:文件夹下的子目录的文件一起压缩
     * @param in
     * @param out
     */
    public static void zipDir(String in, String out){
        try {

            File file = new File(in);
            if (file.isDirectory()){

                ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(out));
                BufferedOutputStream bos = new BufferedOutputStream(zos);
                byte[] b = new byte[1024];
                int len = 0;

                File[] files = file.listFiles();
                for (int i=0;i<files.length;i++){
                    /*File temp = files[i];
                    if (temp.isDirectory()){
                        zipDir(in,out);
                    }*/


                    BufferedInputStream bis = new BufferedInputStream(new FileInputStream(files[i]));

                    ZipEntry zipEntry = new ZipEntry(file.getName()+file.separator+files[i].getName());

                    zos.putNextEntry(zipEntry);

                    while ((len = bis.read(b)) != -1){
                        bos.write(b,0,len);
                    }
                    bis.close();
                }

                bos.close();
                zos.close();

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
