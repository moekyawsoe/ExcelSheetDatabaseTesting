

package doublextech.mks.excelsheetdatabasetesting;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import doublextech.mks.excelsheetdatabasetesting.adapter.MyArrayAdapter;
import doublextech.mks.excelsheetdatabasetesting.model.MyDataModel;
import doublextech.mks.excelsheetdatabasetesting.parser.JSONParser;
import doublextech.mks.excelsheetdatabasetesting.util.InternetConnection;
import doublextech.mks.excelsheetdatabasetesting.util.Keys;

public class UserList extends AppCompatActivity {

    private ListView listView;
    private ArrayList<MyDataModel> list;
    private MyArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_list);


        /**
         * Array List for Binding Data from JSON to this List
         */
        list = new ArrayList<>();
        /**
         * Binding that List to Adapter
         */
        adapter = new MyArrayAdapter(this, list);

        /**
         * Getting List and Setting List Adapter
         */
        listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Snackbar.make(findViewById(R.id.parentLayout), list.get(position).getName() + " => " + list.get(position).getCountry(), Snackbar.LENGTH_LONG).show();
            }
        });

        /**
         * Just to know onClick and Printing Hello Toast in Center.
         */
        Toast toast = Toast.makeText(getApplicationContext(), "Click on FloatingActionButton to Load JSON", Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();

        /** Download datas on load **/

        if (InternetConnection.checkConnection(getApplicationContext())) {
            new GetDataTask().execute();
        } else {
            View viewr = null;
            Snackbar.make(viewr, "Internet Connection Not Available", Snackbar.LENGTH_LONG).show();
        }

    }

    /**
     * Creating Get Data Task for Getting Data From Web
     */
    class GetDataTask extends AsyncTask<Void, Void, Void> {

        ProgressDialog dialog;
        int jIndex;
        int x;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            /**
             * Progress Dialog for User Interaction
             */

            x=list.size();

            if(x==0)
                jIndex=0;
            else
                jIndex=x;

            dialog = new ProgressDialog(UserList.this);
            dialog.setTitle("Hey Wait Please..."+x);
            dialog.setMessage("I am getting your JSON");
            dialog.show();
        }

        @Nullable
        @Override
        protected Void doInBackground(Void... params) {
            JSONObject jsonObject = JSONParser.getDataFromWeb();

            try {
                if (jsonObject != null) {
                    if(jsonObject.length() > 0) {
                        JSONArray array = jsonObject.getJSONArray(Keys.KEY_CONTACTS);
                        int lenArray = array.length();
                        if(lenArray > 0) {
                            for( ; jIndex < lenArray; jIndex++) {
                                MyDataModel model = new MyDataModel();
                                JSONObject innerObject = array.getJSONObject(jIndex);
                                String name = innerObject.getString(Keys.KEY_NAME);
                                String no = innerObject.getString(Keys.KEY_NO);
                                String rollno = innerObject.getString(Keys.KEY_ROLL);
                                String p1 = innerObject.getString(Keys.KEY_P1);
                                String p2 = innerObject.getString(Keys.KEY_P2);
                                String p3 = innerObject.getString(Keys.KEY_P3);
                                String p4 = innerObject.getString(Keys.KEY_P4);
                                String p5 = innerObject.getString(Keys.KEY_P5);
                                String eng = innerObject.getString(Keys.KEY_ENG);
                                String home = innerObject.getString(Keys.KEY_HOME);
                                String copy = innerObject.getString(Keys.KEY_COPY);
//                                String image = innerObject.getString(Keys.KEY_IMAGE);
                                //JSONObject phoneObject = innerObject.getJSONObject(Keys.KEY_PHONE);
                                //String phone = phoneObject.getString(Keys.KEY_MOBILE);

                                model.setName(name);
                                model.setNo(no);
                                model.setRollno(rollno);
                                model.setP1(p1);
                                model.setP2(p2);
                                model.setP3(p3);
                                model.setP4(p4);
                                model.setP5(p5);
                                model.setEng(eng);
                                model.setHome(home);
                                model.setCopy(copy);
  //                              model.setImage(image);
                                list.add(model);
                            }
                        }
                    }
                } else {

                }
            } catch (JSONException je) {
                Log.i(JSONParser.TAG, "" + je.getLocalizedMessage());
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            dialog.dismiss();
            if(list.size() > 0) {
                adapter.notifyDataSetChanged();
            } else {
                Snackbar.make(findViewById(R.id.parentLayout), "No Data Found", Snackbar.LENGTH_LONG).show();
            }
        }
    }
}