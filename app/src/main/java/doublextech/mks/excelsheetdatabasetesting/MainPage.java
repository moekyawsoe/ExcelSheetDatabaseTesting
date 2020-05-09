package doublextech.mks.excelsheetdatabasetesting;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import doublextech.mks.excelsheetdatabasetesting.Post.PostData;

/**
 * Created by ADJ on 2/21/2017.
 */
public class MainPage extends AppCompatActivity{

    Button getData;
    Button sendData;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_page);

        sendData=(Button)findViewById(R.id.insertUser);
        getData=(Button)findViewById(R.id.viewUser);

        getData.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {


                Intent intent = new Intent(getApplicationContext(), UserList.class);
                startActivity(intent);

            }

        });
        sendData.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {


                Intent intent = new Intent(getApplicationContext(), PostData.class);
                    startActivity(intent);
            }

        });


    };



    }
