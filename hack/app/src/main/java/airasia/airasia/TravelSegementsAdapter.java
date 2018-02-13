package airasia.airasia;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by CDL-DTY3 on 13/02/18.
 */

public class TravelSegementsAdapter extends ArrayAdapter<TravelSegments> implements View.OnClickListener {

    private ArrayList<TravelSegments> mtravelSegmentslist;
    Context mContext;

    private static class ViewHolder {
        TextView DepStationName;
        TextView ArrStationName;
        TextView FlightNumber;
    }

    public TravelSegementsAdapter(ArrayList<TravelSegments> trvelsegment, Context context) {
        super(context,R.layout.segment_template, trvelsegment);

        this.mtravelSegmentslist  =trvelsegment;
        this.mContext = context;
    }

    @Override
    public void onClick(View view) {
        int position=(Integer) view.getTag();
        Object object= getItem(position);
        TravelSegments dataModel=(TravelSegments) object;

        switch (view.getId())
        {
//            case R.id.item_info:
//                Snackbar.make(v, "Release date " +dataModel.getFeature(), Snackbar.LENGTH_LONG)
//                        .setAction("No action", null).show();
//                break;
        }
    }

    private int lastPosition = -1;

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        TravelSegments dataModel = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag

        final View result;

        if (convertView == null) {

            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.segment_template, parent, false);
            viewHolder.DepStationName = (TextView) convertView.findViewById(R.id.DepStationTxtView);
            viewHolder.ArrStationName = (TextView) convertView.findViewById(R.id.ArrStationTxtView);
            viewHolder.FlightNumber = (TextView) convertView.findViewById(R.id.FlightNoTxtView);

            result=convertView;

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
            result=convertView;
        }

        viewHolder.DepStationName.setText(dataModel.getStrDepartureStation());
        viewHolder.ArrStationName.setText(dataModel.getStrArivalStation());
        viewHolder.FlightNumber.setText(dataModel.getStrFlightNumber());
        //viewHolder.info.setOnClickListener(this);
        //viewHolder.info.setTag(position);
        // Return the completed view to render on screen
        return convertView;
    }
}
