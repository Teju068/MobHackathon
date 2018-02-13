package airasia.airasia;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;


import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    ArrayList<TravelSegments> lstTravelSgements = null;
    ListAdapter lstAdapter;
    ListView lstView;

    private static  TravelSegementsAdapter travelSegementsAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lstView = (ListView) findViewById(R.id.listview);

        lstTravelSgements  = new ArrayList<TravelSegments>();

        loadTravelSegments();


        travelSegementsAdapter = new TravelSegementsAdapter(lstTravelSgements,getApplicationContext());

        lstView.setAdapter(travelSegementsAdapter);
//        lstView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//            }
//        });
    }


    private void loadTravelSegments(){

        try {

            BufferedReader br=new BufferedReader(new
                    InputStreamReader(getAssets().open("samplebooking.json")));

            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            String strcomplte = sb.toString();

            JSONObject jsonObject = new JSONObject(strcomplte);


                String strBookingStatus = (String) jsonObject.get("BookingStatus");
                System.out.println(strBookingStatus);

                String strPNR = (String) jsonObject.get("PNR");
                System.out.println(strPNR);

            String jsonarr = jsonObject.getJSONObject("BookingContacts").getString("BookingContact");

                JSONArray jsonArray = new JSONArray(jsonarr);
                JSONObject jsonObject1 = jsonArray.getJSONObject(0);
                String strTitle = (String) jsonObject1.getJSONObject("Name").getString("Title");
                System.out.println(strTitle);

                String strFirstName = (String) jsonObject1.getJSONObject("Name").getString("FirstName");
                System.out.println(strFirstName);

                String strMiddleName = (String) jsonObject1.getJSONObject("Name").getString("MiddleName");
                System.out.println(strMiddleName);

                String strLastName = (String) jsonObject1.getJSONObject("Name").getString("LastName");
                System.out.println(strLastName);


                String strTravelSegment = (String) jsonObject.getJSONObject("JourneyServices").getString("JourneyService");
                JSONArray jsonServices = new JSONArray(strTravelSegment);

                for (int j = 0; j < jsonServices.length(); j++) {
                    JSONObject jsonObject2 = jsonServices.getJSONObject(j);
                    JSONArray segmentArray = new JSONArray((String) jsonObject2.getJSONObject("Segments").getString("Segment"));

                    for (int i = 0; i < segmentArray.length(); i++) {
                        TravelSegments travelSegments = new TravelSegments();

                        JSONObject objJSON = segmentArray.getJSONObject(i);
                        travelSegments.setStrArivalStation(objJSON.getString("ArrivalStation"));
                        travelSegments.setStrDepartureStation(objJSON.getString("DepartureStation"));
                        travelSegments.setStrFlightNumber("Test Number");

                        lstTravelSgements.add(travelSegments);
                    }
                }




        }catch(Exception e1){
            e1.printStackTrace();
        }

    }
}
