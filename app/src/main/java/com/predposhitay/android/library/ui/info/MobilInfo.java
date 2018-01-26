package com.predposhitay.android.library.ui.info;


import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.predposhitay.android.library.R;

public class MobilInfo extends Fragment{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.mobile_info_fragment,container,false);
//        String text = "\nВсем любителям книг в Улан-Удэ мобильное приложение городских библиотек. Бесплатное приложение для вашего телефона на Android станет прекрасным средством  интеллектуального отдыха, для поиска ваших любимых произведений.\n" +
//                "- Не успел дочитать интересную книгу? Уже надо нести в библиотеку? Теперь книгу можно продлить из любой точки города. \n" +
//                "- Следи за новинками книжного мира и новостями библиотек.\n" +
//                "- Есть возможность общения через \"Чат\" \n";
//        TextView textView = (TextView) view.findViewById(R.id.mobil_info);
//        textView.setText(text);
        return view;
    }
}


