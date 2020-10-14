package mk.ukim.finki.vvkn.stroopeffect.db;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Random;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;


public class ResultsFirebase {
    String id;
    public FirebaseDatabase database = FirebaseDatabase.getInstance();

    public ResultsFirebase() {
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        int length = 7;
        for(int i = 0; i < length; i++) {
            int index = random.nextInt(alphabet.length());
            char randomChar = alphabet.charAt(index);
            sb.append(randomChar);
        }
        id = sb.toString();
    }
    public String storedata(HashMap<String,HashMap> obj) {
        DatabaseReference myRef = database.getReference().getRoot().push();
        System.out.println(myRef.getKey());
        myRef.setValue(obj);
        return myRef.getKey();
    }
}
