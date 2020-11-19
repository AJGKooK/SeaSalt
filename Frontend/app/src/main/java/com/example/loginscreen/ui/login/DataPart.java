package com.example.loginscreen.ui.login;

import androidx.appcompat.app.AppCompatActivity;

/**
 *  DataPart class to accept and set a String and byte[]
 */
public class DataPart extends AppCompatActivity {
    private String fileName;
    private byte[] content;
    private String type;

    /**
     * @param name
     * @param data
     */
    public DataPart(String name, byte[] data) {
        fileName = name;
        content = data;
    }

    /**
     * @return
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * @return
     */
    public byte[] getContent() {
        return content;
    }

    /**
     * @param content
     */
    public void setContent(byte[] content) {
        this.content = content;
    }

    /**
     * @return
     */
    public String getType() {
        return type;
    }
}