package org.example.manager;

import org.example.model.Document;
import org.example.model.Role;
import org.example.model.User;

import java.util.*;

public class DocumentManager {



    Map<Long, Document> docIdMap;  // = new HashMap<>(); // Version to doc content Map
    Map<Long, Long> docVersionMap; // = new HashMap<>();

    public Map<Long, Document> getDocIdMap() {
        return docIdMap;
    }

    IEditStretegy editStretegy;

    public DocumentManager() {
        this.editStretegy = new SectionLevelStretegy();
        this.docVersionMap = new HashMap();
        this.docIdMap = new HashMap();

        this.docIdMap.put(1L, new Document());
    }

    // call from REST API
    // return the Version of Doc
    // How to pass DataType to Method of other than text as Image etc
    public Long writeToDoc(Long docId,Long userId,String data) {
        return  processRequest(docId,userId,data,true);

    }


    public String readFromDoc(Long docId,Long userId) {
        Long versionId =  processRequest(docId,userId,null, false); // READ
        return docIdMap.get(versionId).toString();
    }



    // Template patternt o check in sequence
    public Long processRequest(Long docId,Long userId,String data,boolean isWriteRequest){
        Long docVersionId = docVersionMap.get(docId);
        // Check if User is Auth TO WRITE BASED OF ROLE
        if(validateUserRoleAndPermission(docId,userId,isWriteRequest)){  // IS it tight Coupled.
            if(checkIfCanProceed(docId,userId,isWriteRequest)){
                // check if can edit as per stretegy
                // if yes than edit and update version
                // if no; mak wait until TTL to wait is Over (ALLOW RED ACCESS TILL THAN)

                //Append to doc content
                Document document = docIdMap.get(docId);
                String currentContent = document.getDocumentContents().getContent();
                currentContent +=data;
                document.setDocId(document.getDocId()+1); // MUST user AtomicInteger to overcome race conditions

                // update contents here:
                document.getDocumentContents().setContent(currentContent);
                return  document.getDocId();
            }
        }
        return docVersionId;
    }


    private boolean validateUserRoleAndPermission(Long docId,Long userId,boolean isWriteRequest) {
        // validate the role per Document
        Document document =docIdMap.get(docId);
        if(isWriteRequest) { // WritePermissionChevk
            document.getWriteingUsers().stream()
                    .filter(user -> user.getUserId().equals(userId)).findFirst().ifPresentOrElse(user -> {
                checkIfCanProceed(docId,user.getUserId(),isWriteRequest);
            },() -> {});
        }

        if(!isWriteRequest) { // REadPermissionCheck
            Optional<User> optionalUser =
            document.getReadingUsers().stream()
                    .filter(user -> user.getUserId().equals(userId))
                    .findAny();
            if(optionalUser.isPresent()){
                checkIfCanProceed(docId,userId,false);
            }else{
                return  false;
            }

        }

        return true;
    }

    private boolean checkIfCanProceed(Long docId,Long userId,boolean isWriteRequest){
        Document document =docIdMap.get(docId);
        if(isWriteRequest){
            if(document.getWriteingUsers().size()==10){
                System.out.println(" Ceiling for write reached......this provide Read Access");

                // TO DO: Implement inMemory cache to update Access Status onFLY
                document.getWriteingUsers().add(new User(Role.READ,userId));
                return false;
            }else{
                // how to avoid deduplication: if same USer made two double attempts
                 document.getWriteingUsers().add(new User(Role.WRITE,userId));
                    // update doc
                return  true;
            }
        }else{
            if(document.getReadingUsers().size()==10){
                System.out.println(" Ceiling for Read reached......");
            }else{
                // update doc
            }
        }


        return false;
    }
}
