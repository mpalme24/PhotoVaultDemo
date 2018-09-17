package com.test.photovault;

import android.os.AsyncTask;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class fetchData extends AsyncTask<Void, Void, String> {
    String data = "";
    String dataParsed = "";
    String dataToShow;

    @Override
    public String doInBackground(Void... voids) {

        try {
            URL url = new URL("https://api.myjson.com/bins/kvf9c"); //Replace with API URL

            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            InputStream inputStream = httpURLConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String line = "";
            while (line != null) {
                line = bufferedReader.readLine();
                data = data + line;
            }


            String singleParsed = "Unable To Obtain Moon Information."; //Displays If Request To API Fails

            try {
                JSONObject jo = new JSONObject(data);
                JSONObject firstLocationAstronomy = jo
                        .getJSONArray("locations").getJSONObject(0)
                        .getJSONObject("astronomy");
                JSONObject firstDay = firstLocationAstronomy
                        .getJSONArray("objects").getJSONObject(0)
                        .getJSONArray("days").getJSONObject(0);
                singleParsed = firstDay.getString("moonphase");
            } catch (Exception e) {
            }

            return singleParsed;

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(String moonPhase) {

        //If Statements Changing Moon Icon

        if (moonPhase.toString().equals("waxingcrescent")){
            Tab2Fragment.moonIcon.setBackgroundResource(R.drawable.waxingcrescent); //Waxing Crescent
            moonPhase = "Waxing Crescent";
        }

        if (moonPhase.toString().equals("waninggibbous")){
            Tab2Fragment.moonIcon.setBackgroundResource(R.drawable.waninggibbous); //Waning Gibbous
            moonPhase = "Waning Gibbous";
        }

        if (moonPhase.toString().equals("waxinggibbous")){
            Tab2Fragment.moonIcon.setBackgroundResource(R.drawable.waxinggibbous); //Waxing Gibbous
            moonPhase = "Waxing Gibbous";
        }

        if (moonPhase.toString().equals("newmoon")){
            Tab2Fragment.moonIcon.setBackgroundResource(R.drawable.newmoon); //New Moon
            moonPhase = "New Moon";
        }

        if (moonPhase.toString().equals("firstquarter")){
            Tab2Fragment.moonIcon.setBackgroundResource(R.drawable.quartermoon); //Quarter Moon
            moonPhase = "First Quarter";
        }

        if (moonPhase.toString().equals("thirdquarter")){
            Tab2Fragment.moonIcon.setBackgroundResource(R.drawable.quartermoon); //Quarter Moon
            moonPhase = "Third Quarter";
        }

        if (moonPhase.toString().equals("fullmoon")){
            Tab2Fragment.moonIcon.setBackgroundResource(R.drawable.fullmoon); //Full Moon
            moonPhase = "Full Moon";
        }

        if (moonPhase.toString().equals("waningcrescent")){
            Tab2Fragment.moonIcon.setBackgroundResource(R.drawable.waxingcrescent); //Waning Crescent
            moonPhase = "Waning Crescent";
        }

        Tab2Fragment.fetcheddata.setText("Phase: " + moonPhase);

    }
}
