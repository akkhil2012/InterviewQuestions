package org.example.model;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DocumentContents {
    private String content;

    private final String[] sections;
    private final Lock[] locks;

    public DocumentContents(int numSections){
        sections = new String[numSections];
        locks = new ReentrantLock[numSections];
        for(int i=0;i<numSections;i++){
            sections[i] = "";
            locks[i] = new ReentrantLock();
        }
    }


    public void writeSection(int section,String content){
        locks[section].lock();
        try {
            sections[section] = content;
        }finally {
            locks[section].unlock();
        }
    }


    public String readSection(int section){

          return "";
    }



    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
