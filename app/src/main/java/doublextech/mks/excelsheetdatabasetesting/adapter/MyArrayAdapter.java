package doublextech.mks.excelsheetdatabasetesting.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import doublextech.mks.excelsheetdatabasetesting.R;
import doublextech.mks.excelsheetdatabasetesting.model.MyDataModel;

public class MyArrayAdapter extends ArrayAdapter<MyDataModel> {

    List<MyDataModel> modelList;
    Context context;
    private LayoutInflater mInflater;

    // Constructors
    public MyArrayAdapter(Context context, List<MyDataModel> objects) {
        super(context, 0, objects);
        this.context = context;
        this.mInflater = LayoutInflater.from(context);
        modelList = objects;
    }

    @Override
    public MyDataModel getItem(int position) {
        return modelList.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder vh;
        if (convertView == null) {
            View view = mInflater.inflate(R.layout.layout_row_view, parent, false);
            vh = ViewHolder.create((RelativeLayout) view);
            view.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }

        MyDataModel item = getItem(position);

        vh.txtname.setText(item.getName());
        vh.txtrollno.setText(item.getRollno());
        vh.txtno.setText(item.getNo());
        vh.txtp1.setText(item.getP1());
        vh.txtp2.setText(item.getP2());
        vh.txtp3.setText(item.getP3());
        vh.txtp4.setText(item.getP4());
        vh.txtp5.setText(item.getP5());
        vh.txtEng.setText(item.getEng());
        vh.txthome.setText(item.getHome());
        vh.txtcopy.setText(item.getCopy());

        return vh.rootView;
    }



    private static class ViewHolder {
        public final RelativeLayout rootView;

        public final TextView txtno;
        public final TextView txtrollno;
        public final TextView txtname;
        public final TextView txtp1;
        public final TextView txtp2;
        public final TextView txtp3;
        public final TextView txtp4;
        public final TextView txtp5;
        public final TextView txtEng;
        public final TextView txthome;
        public final TextView txtcopy;

        private ViewHolder(RelativeLayout rootView, TextView textViewName, TextView textViewCountry, TextView txtname, TextView txtp1, TextView txtp2, TextView txtp3, TextView txtp4, TextView txtp5, TextView txtEng, TextView txthome, TextView txtcopy) {
            this.rootView = rootView;
            this.txtno = textViewName;
            this.txtrollno = textViewCountry;
            this.txtname = txtname;
            this.txtp1 = txtp1;
            this.txtp2 = txtp2;
            this.txtp3 = txtp3;
            this.txtp4 = txtp4;
            this.txtp5 = txtp5;
            this.txtEng = txtEng;
            this.txthome = txthome;
            this.txtcopy = txtcopy;
        }

        public static ViewHolder create(RelativeLayout rootView) {
            TextView txtno = (TextView) rootView.findViewById(R.id.txtno);
            TextView txtrollno = (TextView) rootView.findViewById(R.id.txtrollno);
            TextView txtname = (TextView) rootView.findViewById(R.id.txtname);
            TextView txtp1 = (TextView) rootView.findViewById(R.id.txtp1);
            TextView txtp2 = (TextView) rootView.findViewById(R.id.txtp2);
            TextView txtp3 = (TextView) rootView.findViewById(R.id.txtp3);
            TextView txtp4 = (TextView) rootView.findViewById(R.id.txtp4);
            TextView txtp5 = (TextView) rootView.findViewById(R.id.txtp5);
            TextView txtEng = (TextView) rootView.findViewById(R.id.txtEng);
            TextView txthome = (TextView) rootView.findViewById(R.id.txthome);
            TextView txtcopy = (TextView) rootView.findViewById(R.id.txtcopy);
            return new ViewHolder(rootView, txtno, txtrollno, txtname, txtp1, txtp2, txtp3, txtp4, txtp5, txtEng, txthome, txtcopy);
        }
    }
}