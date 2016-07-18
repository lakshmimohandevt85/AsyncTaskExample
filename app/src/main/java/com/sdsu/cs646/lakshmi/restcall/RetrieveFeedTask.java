package com.sdsu.cs646.lakshmi.restcall;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONObject;
import org.xml.sax.InputSource;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lakshmimohandev on 7/6/16.
 */
public class RetrieveFeedTask extends AsyncTask<String, Void, List<Data>> {

    private Exception exception;

    protected List<Data> doInBackground(String... urls) {

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
            for (int i=0; i<jsonArray.length(); i++) {
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
        } catch (Exception e) {
            this.exception = e;
            return null;
        }

        return dataList;
    }

    protected void onPostExecute(Data feed) {
        // TODO: check this.exception
        // TODO: do something with the feed
    }
}
