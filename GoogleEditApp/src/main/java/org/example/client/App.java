package org.example.client;

import org.example.manager.DocumentManager;
import org.example.model.Document;
import org.example.model.DocumentContents;

import java.util.Map;

public class App {

    public static void main(String[] args) {
        DocumentManager documentManager
                = new DocumentManager();

        //documentManager.processRequest(1L,1L,"testData1",true);


        // To Write to Doc
        documentManager.writeToDoc(1L,1L,"firstTestData");
        System.out.println(" Updated the Document................");

        Map<Long, Document> mp =
        documentManager.getDocIdMap();
        for(Map.Entry<Long, Document> content : mp.entrySet()){
           // content.getValue().getDocumentContents().getContent();
            System.out.println(" >> " + content.getValue().getDocumentContents().getContent());
        }

// second attempt:
        documentManager.writeToDoc(1L,1L," SecondTestData");
        System.out.println(" Updated the Document................");

         mp =
                documentManager.getDocIdMap();
        for(Map.Entry<Long, Document> content : mp.entrySet()){
            // content.getValue().getDocumentContents().getContent();
            System.out.println(" >> " + content.getValue().getDocumentContents().getContent());
        }



        // To Read from Data
        String docmentContent = documentManager.readFromDoc(1L,1L);
        System.out.println("-------------- > "+ docmentContent);
    }
}
