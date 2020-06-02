package com.example.mysaavndownloader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

class copy {
    copy(File paramFile1, File paramFile2) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(paramFile1);
        FileOutputStream fileOutputStream = new FileOutputStream(paramFile2);
        byte[] arrayOfByte = new byte[1024];
        while (true) {
            int i = fileInputStream.read(arrayOfByte);
            if (i <= 0) {
                fileInputStream.close();
                fileOutputStream.close();
                return;
            }
            fileOutputStream.write(arrayOfByte, 0, i);
        }
    }
}
