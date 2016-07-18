package com.sdsu.cs646.lakshmi.restcall;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity implements AsyncResponse {

    private static final String urlString =  "http://www.washingtonpost.com/wp-srv/simulation/simulation_test.json";

    List<Data> listOfData = new ArrayList<Data>();
    String[] mobileArray = {"Android","IPhone","WindowsMobile","Blackberry","WebOS","Ubuntu","Windows7","Max OS X"};
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
       setContentView(R.layout.activity_list);
        new RetrieveData(this).execute(urlString);

     //   new RetrieveData().execute(urlString);

    }



    public void processFinish(List<Data> dataList)
    {

        listOfData.addAll(dataList);
        String[] dataArray = new String[listOfData.size()];

        for (int i=0; i<listOfData.size();i++)
        {
            dataArray[i] = listOfData.get(i).getDate();
        }

        ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.adaptor, dataArray);

        ListView listView = ( ListView) findViewById(R.id.mobile_list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {

            Data currentData = listOfData.get(position);
            String content = currentData.getContent();

                Intent intent = new Intent(ListActivity.this, DetailedViewActivity.class);

                intent.putExtra("content", content);


                startActivity(intent);



            }
        });

    }


    private class RetrieveData extends AsyncTask<String, Void, List<Data>>
    {

        public AsyncResponse delegate = null;

        public RetrieveData(AsyncResponse delegate){
            this.delegate = delegate;
        }

        protected List<Data> doInBackground(String... urls)
        {

            List<Data> dataList = new ArrayList<Data>();
            try {

                URL url = new URL(urls[0]);
                HttpURLConnection connection =
                        (HttpURLConnection)url.openConnection();

                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(connection.getInputStream()));
                StringBuffer json = new StringBuffer(1024);
                String tmp="";
                while((tmp=reader.readLine())!=null)
                    json.append(tmp).append("\n");
                reader.close();

                JSONObject data = new JSONObject(json.toString());


                Data dataObject = null;

                JSONArray jsonArray = data.getJSONArray("posts");
                for (int i=0; i<jsonArray.length(); i++)
                {
                    JSONObject jsonObj = jsonArray.getJSONObject(i);
                    dataObject = new Data();
                    String id = jsonObj.getString("id");
                    String title = jsonObj.getString("title");
                    String content = jsonObj.getString("content");
                    String date = jsonObj.getString("date");
                    dataObject.setTitle(title);
                    dataObject.setDate(date);
                    dataObject.setId(id);
                    dataObject.setContent(content);
                    dataList.add(dataObject);

                }
            } catch (Exception e)
            {

                return null;
            }

            return dataList;
        }

        protected void onPostExecute(List<Data> list)
        {
            delegate.processFinish(list);

        }
    }
}
