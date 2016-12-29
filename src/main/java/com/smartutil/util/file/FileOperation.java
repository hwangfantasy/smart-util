package com.smartutil.util.file;

import java.io.*;

/**
 * Created by hwangfantasy on 2016/12/29.
 */

/**
 * 文本读写操作
 */
public class FileOperation {
    /**
     * 写入指定的文本文件，append为true表示追加，false表示重头开始写，text是要写入的文本字符串，text为null时直接返回
     * @param filePath
     * @param append
     * @param text
     */
    public static void write(String filePath, boolean append, String text) {
        if (text == null)
            return;
        try {
            BufferedWriter out = new BufferedWriter(new FileWriter(filePath,
                    append));
            try {
                out.write(text);
                out.newLine();
            } finally {
                out.close();
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    /**
     * 读取指定路径文本文件
     * @param filePath
     * @return
     */
    public static String read(String filePath) {
        StringBuilder str = new StringBuilder();
        BufferedReader in = null;
        try {
            in = new BufferedReader(new FileReader(filePath));
            String s;
            try {
                while ((s = in.readLine()) != null)
                    str.append(s + '\n');
            } finally {
                in.close();
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return str.toString();
    }
}
