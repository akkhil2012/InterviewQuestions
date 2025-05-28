package org.example.model;

import java.util.HashSet;
import java.util.Set;

public class Document {

    private String title;

    public Long getDocId() {
        return docId;
    }

    public void setDocId(Long docId) {
        this.docId = docId;
    }

    private Long docId;
    private DocumentContents documentContents;

    public DocumentContents getDocumentContents() {
        return documentContents;
    }

    public Set<User> getWriteingUsers() {
        return writeingUsers;
    }

    public Set<User> getReadingUsers() {
        return readingUsers;
    }

    Set<User> writeingUsers;
    Set<User> readingUsers;

    public void setWriteingUsers(Set<User> writeingUsers) {
        this.writeingUsers = writeingUsers;
    }

    public void setReadingUsers(Set<User> readingUsers) {
        this.readingUsers = readingUsers;
    }

    public void setDocumentContents(DocumentContents documentContents) {
        this.documentContents = documentContents;
    }

    public Document() {
        this.writeingUsers = new HashSet<>();
        this.docId = 1L;
        this.readingUsers = new HashSet<>();
        this.documentContents = new DocumentContents();
    }

    public Document(String title, Long docId, DocumentContents documentContents) {
        this.title = title;
    }

}
