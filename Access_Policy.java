import java.util.HashMap;

public class Access_Policy {
HashMap<String,String> ap=new HashMap<>();
public String getPolicy(String docId) {
	if(ap.containsKey(docId)) {
		return ap.get(docId);
	}
	return null;
}

public void setPolicy(String docId,String Policy) {
	if(!ap.containsKey(docId)) {
		 ap.put(docId, Policy);
		 System.out.print("Policy Added successfully !!! ");
	}
	else if(!ap.get(docId).equals(Policy)) {
		ap.put(docId, Policy);
		System.out.println("Policy Updated Successfully !!!");
	}
	
}

}
