package com.predposhitay.android.library.ui.contact;


import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.predposhitay.android.library.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DataAdapter extends BaseAdapter {

    List<Integer> colors = new ArrayList<>();
    Random random = new Random();
    private static final String[] mContacts = {
            "Центральная городская библиотека им. И.К.Калашникова",
            "Библиотека - филиал № 2",
            "Библиотека - филиал № 3",
            "Библиотека - филиал № 4",
            "Библиотека - филиал № 5",
            "Библиотека - филиал № 6 (детская библиотека)",
            "Библиотека - филиал № 9",
            "Библиотека - филиал № 10",
            "Библиотека - филиал № 12",
            "Библиотека - филиал № 13",
            "Библиотека - филиал № 15",
            "Библиотека - филиал № 16",
            "Библиотека - филиал № 17 (детская библиотека им. А.Гайдара)",
            "Библиотека - филиал № 18",
            "Библиотека - филиал № 19",
            "Библиотека - филиал № 20",
            "Библиотека - филиал № 21",
            "Библиотека - филиал № 24"
    };

    private static final String[] mAddres = {
            "г.Улан-Удэ, ул. Ленина, 17",
            "г.Улан-Удэ, мкр.Забайкальский, ул.Совхозная, 52",
            "г.Улан-Удэ, мкр.Загорск, ул.Столичная, 1",
            "г.Улан-Удэ, мкр.Стеклозавод, ул.Радикальцева, 5 а",
            "г.Улан-Удэ, мкр.Восточный, ул.Туполева, 4 ",
            "г.Улан-Удэ, мкр.Загорск, ул.Столичная, 1",
            "г.Улан-Удэ, ул.Московская, 4",
            "г.Улан-Удэ, ул. Жердева, 1",
            "г.Улан-Удэ, 113 мкр., д. 2",
            "г.Улан-Удэ, ул.Геологическая, д.15",
            "г.Улан-Удэ, мкр.Заречный, ул.Кабанская, 16",
            "г.Улан-Удэ, 20 кв. ул.Ключевская, 94-21",
            "г.Улан-Удэ, ул.Геологическая, 14",
            "г.Улан-Удэ, ул.8 Марта, 21",
            "г.Улан-Удэ, ул.Комсомольская, 23",
            "г.Улан-Удэ, мкр.Аэропорт, д. 7",
            "г.Улан-Удэ, мкр.Сокол, д. 3",
            "г.Улан-Удэ, проспект Строителей, 34 а"
    };




    private Context mContext;

    public DataAdapter(Context c) {
        mContext = c;
        colors.add(Color.argb(5,250,0,0));
        colors.add(R.color.color2);
        colors.add(R.color.color3);
        colors.add(R.color.color4);
        colors.add(R.color.color5);
    }

    public int getCount() {
        return mContacts.length;
    }

    public Object getItem(int position) {
        return mContacts[position];
    }

    public long getItemId(int position) {
        return position;
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View grid;

        if (convertView == null) {
            grid = new View(mContext);
            //LayoutInflater inflater = getLayoutInflater();
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
            grid = inflater.inflate(R.layout.item_contact, parent, false);

        } else {
            grid = (View) convertView;
        }

        LinearLayout back = (LinearLayout) grid.findViewById(R.id.back);


        switch (position %5){
            case 0:{
                back.setBackgroundColor(Color.GREEN);
                break;
            }
            case 1:{
                back.setBackgroundColor(Color.YELLOW);
                break;
            }
            case 2:{
                back.setBackgroundColor(Color.MAGENTA);
                break;
            }
            case 3:{
                back.setBackgroundColor(Color.RED);
                break;
            }
            case 4:{
                back.setBackgroundColor(Color.BLUE);
                break;
            }
        }
        TextView textView = (TextView) grid.findViewById(R.id.name);
        TextView textAddres = (TextView) grid.findViewById(R.id.addres);
        textView.setText(mContacts[position]);
        textAddres.setText(mAddres[position]);


        return grid;
    }



}
