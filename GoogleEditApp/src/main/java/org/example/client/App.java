package org.example.client;

import org.example.manager.DocumentManager;
import org.example.model.Document;
import org.example.model.DocumentContents;

import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class App {

    public static void main(String[] args) throws InterruptedException {
        DocumentManager documentManager
                = new DocumentManager();

        //documentManager.processRequest(1L,1L,"testData1",true);


        // To Write to Doc
      //  documentManager.writeToDoc(1L,1L,"firstTestData");
        ExecutorService executor = Executors.newFixedThreadPool(6);

        Runnable writer1 = () -> {
           // document.writeSection(0, "Hello");
            documentManager.writeToDoc(1L,1L,"Hello ");
            System.out.println("Written to section 0: Hello");
        };
        Runnable writer2 = () -> {
            //document.writeSection(1, "World");
            documentManager.writeToDoc(2L,1L,"Akhil");
            System.out.println("Written to section 1: World");
        };
        Runnable writer3 = () -> {
           // document.writeSection(2, "!!!");
            documentManager.writeToDoc(3L,1L,"Gupta");
            System.out.println("Written to section 2: !!!");
        };


        executor.execute(writer1);
        Thread.sleep(3000);

        executor.execute(writer2);
        Thread.sleep(3000);

        executor.execute(writer3);
        Thread.sleep(3000);

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
      //  String docmentContent = documentManager.readFromDoc(1L,1L);
      //  System.out.println("-------------- > "+ docmentContent);
    }
}
