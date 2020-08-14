package sg.edu.rp.soi.p12psc347;

import android.content.Intent;

import android.os.Bundle;

import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.RemoteInput;

public class Reply extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reply);
        CharSequence reply = null;
        Intent intent = getIntent();
        int id = intent.getIntExtra("id", -1);
        Bundle remoteInput = RemoteInput.getResultsFromIntent(intent);
        if (remoteInput != null) {
            reply = remoteInput.getCharSequence("status");

        }

        if (reply != null) {

            Log.d("asdsdsd", "onCreate: " + reply);

            if (reply == "Completed") {
                DBHelper db = new DBHelper(Reply.this);
                db.deleteTask(id);
                db.close();
            } else {
                Toast.makeText(Reply.this, "You have indicated: " + reply, Toast.LENGTH_SHORT).show();
            }

        }
    }
}
