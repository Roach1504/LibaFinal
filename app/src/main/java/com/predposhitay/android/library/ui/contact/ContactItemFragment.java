package com.predposhitay.android.library.ui.contact;


import android.annotation.SuppressLint;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.TextView;

import com.predposhitay.android.library.R;

@SuppressLint("ValidFragment")
public class ContactItemFragment extends Fragment {

    int num;
    @SuppressLint("ValidFragment")
    public ContactItemFragment(int num) {
        this.num = num;
    }

    private static final String[] mContacts = {
            "<b>Центральная городская библиотека им. И.К.Калашникова</b><br>" +
                    "670000, Республика Бурятия, г.Улан-Удэ, ул. Ленина, 17<br>"
                    + "<br>" +
                    "<b>Зимнее расписание с 01.09 по 31.05</b><br>" +
                    "С 9.00 до 19.00, суббота, воскресенье - с 10.00 до 18.00, санитарный день - последняя пятница месяца<br>" +
                    "<b>Летнее расписание с 01.06 по 31.08</b><br>С 9.00 до 19.00, суббота с 10.00 до 18.00, выходной-воскресенье, санитарный день-последняя пятница месяца<br>" +
                    "<br><b>Контакты:</b>" + "<br>" + "8(3012)215412 - Цыбенова Раиса Цыденовна - директор<br>" +
                    "8(3012)219947 - Жигмитов Евгений Бадмаевич - заместитель директора<br>" +
                    "Электронная почта: cbskalashnikov@yandex.ru<br>" +
                    "<a href = http://cbs-uu.ru>Сайт</a><br>" +
                    "<a href = https://vk.com/bibl_kalashnikov>Вконтакте</a>",
            "<b>Библиотека - филиал № 2</b><br>" +
                    "670014, Республика Бурятия, г. Улан-Удэ, мкр.Забайкальский, ул.Совхозная, 52<br>"
                    + "<br>" +
                    "<b>Зимнее расписание с 01.09 по 31.05</b><br>" +
                    "С 10.00 до 19.00, суббота, воскресенье - с 10.00 до 18.00.  Выходной- понедельник, санитарный день-последняя пятница месяца<br>" +
                    "<b>Летнее расписание с 01.06 по 31.08</b><br>С 09.00 до 18.00, выходной-суббота, воскресенье, санитарный день-последняя пятница месяца<br>" +
                    "<br><b>Контакты:</b>" + "<br>" + "8(3012)210191 - Орлова Ольга Владимировна - заведующий библиотекой<br>" +
                    "Электронная почта: filial2@cbs-ulan-ude.ru<br>",
            "<b>Библиотека - филиал № 3</b><br>" +
                    "670009, Республика Бурятия, г. Улан-Удэ, мкр.Загорск, ул.Столичная, 1<br>"
                    + "<br>" +
                    "<b>Зимнее расписание с 01.09 по 31.05</b><br>" +
                    "С 10.00 до 19.00, суббота - с 10.00 до 18.00. Выходной- воскресенье, санитарный день-последняя пятница месяца<br>" +
                    "<b>Летнее расписание с 01.06 по 31.08</b><br>" +
                    "С 09.00 до 18.00, выходной-воскресенье, суббота с 10.00 до 18.00, санитарный день-последняя пятница месяца<br>" +
                    "<br><b>Контакты:</b>" + "<br>" +
                    "8(3012)252075 - Соболева Норжин Норполовна Заведующий библиотекой<br>" +
                    "Электронная почта:" +
                    " filial3@cbs-ulan-ude.ru<br>",
            "<b>Библиотека - филиал № 4</b><br>" +
                    "670004, Республика Бурятия, г. Улан-Удэ, мкр.Стеклозавод, ул.Радикальцева, 5 а<br>"
                    + "<br>" +
                    "<b>Зимнее расписание с 01.09 по 31.05</b><br>" +
                    "С 10.00 до 19.00, суббота, воскресенье-с 10.00 до 18.00. Выходной-понедельник, санитарный день-последняя пятница месяца<br>" +
                    "<b>Летнее расписание с 01.06 по 31.08</b><br>" +
                    "С 09.00 до 18.00, выходной-воскресенье, суббота с 10.00 до 18.00, санитарный день-последняя пятница месяца<br>" +
                    "<br><b>Контакты:</b>" + "<br>" +
                    "8(3012)271132 - Гончарова Наталья Юрьевна Заведующий библиотекой<br>" +
                    "Электронная почта:" +
                    " filial4@cbs-ulan-ude.ru<br>",
            "<b>Библиотека - филиал № 5</b><br>" +
                    "670050, Республика Бурятия, г. Улан-Удэ, мкр.Восточный, ул.Туполева, 4 <br>"
                    + "<br>" +
                    "<b>Зимнее расписание с 01.09 по 31.05</b><br>" +
                    "С 10.00 до 19.00, суббота, воскресенье-с 10.00 до 18.00. Выходной-понедельник, санитарный день-последняя пятница месяца<br>" +
                    "<b>Летнее расписание с 01.06 по 31.08</b><br>" +
                    "С 09.00 до 18.00, выходной-воскресенье, суббота с 10.00 до 18.00, санитарный день-последняя пятница месяца<br>" +
                    "<br><b>Контакты:</b>" + "<br>" +
                    "8(3012)252080 - Гончарова Наталья Юрьевна Заведующий библиотекой<br>" +
                    "Электронная почта:" +
                    " filial5@cbs-ulan-ude.ru<br>",
            "<b>Библиотека - филиал № 6 (детская библиотека)</b><br>" +
                    "670009, Республика Бурятия, г. Улан-Удэ, мкр.Загорск, ул.Столичная, 1<br>"
                    + "<br>" +
                    "<b>Зимнее расписание с 01.09 по 31.05</b><br>" +
                    "С 9.00 до 18.00, воскресенье-с 10.00 до 18.00, выходной-суббота, санитарный день-последняя пятница месяца.<br>" +
                    "<b>Летнее расписание с 01.06 по 31.08</b><br>" +
                    "С 09.00 до 18.00, выходной-суббота, воскресенье, санитарный день-последняя пятница месяца<br>" +
                    "<br><b>Контакты:</b>" + "<br>" +
                    "8(3012)252074 - Понедько Ирина Федоровна Заведующий библиотекой<br>" +
                    "Электронная почта:" +
                    " filial6@cbs-ulan-ude.ru<br>",
            "<b>Библиотека - филиал № 9</b><br>" +
                    "670034, Республика Бурятия, г. Улан-Удэ, ул.Московская, 4<br>"
                    + "<br>" +
                    "<b>Зимнее расписание с 01.09 по 31.05</b><br>" +
                    "С 10.00 до 19.00, суббота, воскресенье  с 10.00 до 18.00. Выходной-понедельник, санитарный день-последняя пятница месяца<br>" +
                    "<b>Летнее расписание с 01.06 по 31.08</b><br>" +
                    "С 09.00 до 18.00, выходной-воскресенье, суббота с 10.00 до 18.00, санитарный день-последняя пятница месяца<br>" +
                    "<br><b>Контакты:</b>" + "<br>" +
                    "8(3012)441325 - Доржиева Оксана Викторовна Заведующий библиотекой<br>" +
                    "Электронная почта:" +
                    " filial9@cbs-ulan-ude.ru<br>",
            "<b>Библиотека - филиал № 10</b><br>" +
                    "670013, Республика Бурятия, г. Улан-Удэ, ул. Жердева, 134<br>"
                    + "<br>" +
                    "<b>Зимнее расписание с 01.09 по 31.05</b><br>" +
                    "С 10.00 до 19.00, суббота, воскресенье-с 10.00 до 18.00. Выходной-понедельник, санитарный день-последняя пятница месяца.<br>" +
                    "<b>Летнее расписание с 01.06 по 31.08</b><br>" +
                    "С 09.00 до 18.00, выходной-суббота, воскресенье, санитарный день-последняя пятница месяца<br>" +
                    "<br><b>Контакты:</b>" + "<br>" +
                    "8(3012)425993 - Нимаева Ольга Давидовна Заведующий библиотекой<br>" +
                    "Электронная почта:" +
                    " filial10@cbs-ulan-ude.ru<br>",
            "<b>Библиотека - филиал № 12</b>" +
                    "<br>670031, Республика Буятия, г. Улан-Удэ, 113 мкр., д. 2<br>" +
                    "<br><b>Зимнее расписание с 01.09 по 31.05</b><br>" +
                    "С 10.00 до 19.00, суббота, воскресенье-с 10.00 до 18.00. Выходной-понедельник, санитарный день-последняя пятница месяца.<br>" +
                    "<b>Летнее расписание с 01.06 по 31.08</b><br>" +
                    "С 09.00 до 18.00, выходной- воскресенье, суббота с 10.00 до 18.00, санитарный день-последняя пятница месяца<br><br><b >" +
                    "Контакты:</b><br>8(3012)550364 - Имидеева Антонина Сергеевна Заведующий библиотекой<br>Электронная почта: filial12@cbs-ulan-ude.ru<br>",
            "<b>Библиотека - филиал № 13</b>" +
                    "<br>670031, Республика Бурятия, г. Улан-Удэ, ул.Геологическая, д.15<br>" +
                    "<br><b>Зимнее расписание с 01.09 по 31.05</b><br>" +
                    "С 10.00 до 19.00, суббота, воскресенье-с 10.00 до 18.00. Выходной-понедельник, санитарный день-последняя пятница месяца.<br>" +
                    "<b>Летнее расписание с 01.06 по 31.08</b><br>" +
                    "С 09.00 до 18.00, выходной-воскресенье, суббота с 10.00 до 18.00, санитарный день-последняя пятница месяца<br><br><b >" +
                    "Контакты:</b><br>8(3012)231141 - Должикова Нина Валентиновна Заведующий библиотекой<br>Электронная почта: filial13@cbs-ulan-ude.ru<br>",
            "<b>Библиотека - филиал № 15</b><br>670023, Республика Бурятия, г. Улан-Удэ, мкр.Заречный, ул.Кабанская, 16<br><br><b>Зимнее расписание с 01.09 по 31.05</b><br>С 10.00 до 19.00, суббота, воскресенье-с 10.00 до 18.00. Выходной-понедельник, санитарный день-последняя пятница месяца.<br><b>Летнее расписание с 01.06 по 31.08</b><br>С 09.00 до 18.00, выходной-суббота, воскресенье, санитарный день-последняя пятница месяца<br><br><b                  >Контакты:</b><br>8(3012)224709 - Гундуева Саяна Батуевна Заведующий библиотекой<br>Электронная почта: filial15@cbs-ulan-ude.ru<br>",
            "<b>Библиотека - филиал № 16</b><br>670013, Республика Бурятия, г. Улан-Удэ, 20 кв. ул.Ключевская, 94-21<br><br><b   >Зимнее расписание с 01.09 по 31.05</b><br>С 10.00 до 19.00, суббота, воскресенье с 10.00 до 18.00. Выходной-понедельник, санитарный день-последняя пятница месяца.<br><b>Летнее расписание с 01.06 по 31.08</b><br>С 09.00 до 18.00, выходной-суббота, воскресенье, санитарный день-последняя пятница месяца<br><br><b                  >Контакты:</b><br>8(3012)410105 - Ходорова Ульяна Вильсоновна Заведующий библиотекой<br>Электронная почта: filial16@cbs-ulan-ude.ru<br>",
            "<b>Библиотека - филиал № 17 (детская библиотека им. А.Гайдара)</b><br>670031, Республика Бурятия, г. Улан-Удэ, ул.Геологическая, 14<br><br><b          >Зимнее расписание с 01.09 по 31.05</b><br>С 9.00 до 18.00, воскресенье с 10.00 до 18.00, выходной-суббота, санитарный день-последняя пятница месяца.<br><b              >Летнее расписание с 01.06 по 31.08</b><br>С 09.00 до 18.00, выходной-суббота, воскресенье с 10.00 до 18.00, санитарный день-последняя пятница месяца<br><br><b >Контакты:</b><br>8(3012)231921 - Васильева Елена Александровна Заведующий библиотекой<br>Электронная почта: filial17@cbs-ulan-ude.ru<br>",
            "<b>Библиотека - филиал № 18</b><br>670024, Республика Бурятия, г. Улан-Удэ, ул.8 Марта, 21<br><br><b                >Зимнее расписание с 01.09 по 31.05</b><br>С 9.00 до 18.00, суббота, воскресенье с 10.00 до 18.00. Выходной-понедельник, санитарный день-последняя пятница месяца.<br><b >Летнее расписание с 01.06 по 31.08</b><br>С 09.00 до 18.00, выходной- воскресенье, суббота с 10.00 до 18.00, санитарный день-последняя пятница месяца<br><br><b>Контакты:</b><br>8(3012)468267 - Цыренова Елена Семеновна Заведующий библиотекой<br>Электронная почта: filial18@cbs-ulan-ude.ru<br>",
            "<b>Библиотека - филиал № 19</b><br>670045, Республика Бурятия, г. Улан-Удэ, ул.Комсомольская, 23<br><br><b          >Зимнее расписание с 01.09 по 31.05</b><br>С 10.00 до 19.00, суббота, воскресенье с 10.00 до 18.00. Выходной-понедельник, санитарный день-последняя пятница месяца.<br><b>Летнее расписание с 01.06 по 31.08</b><br>С 09.00 до 18.00, выходной-воскресенье, суббота с 10.00 до 18.00, санитарный день-последняя пятница месяца<br><br><b >Контакты:</b><br>8(3012)270729 - Антонова Анастасия Евгеньевна Заведующий библиотекой<br>Электронная почта: filial19@cbs-ulan-ude.ru<br>",
            "<b>Библиотека - филиал № 20</b><br>670018, Республика Бурятия, г. Улан-Удэ, мкр.Аэропорт, д. 7<br><br><b            >Зимнее расписание с 01.09 по 31.05</b><br>С 9.00 до 18.00, суббота с 9.00 до 17.00, выходной- воскресенье, санитарный день-последняя пятница месяца.<br><b              >Летнее расписание с 01.06 по 31.08</b><br>С 09.00 до 18.00, выходной-суббота, воскресенье, санитарный день-последняя пятница месяца<br><br><b                  >Контакты:</b><br>8(3012)226685 - Федорова Татьяна Владимировна Заведующий библиотекой<br>Электронная почта: filial20@cbs-ulan-ude.ru<br>",
            "<b>Библиотека - филиал № 21</b><br>670018, Республика Бурятия, г. Улан-Удэ, мкр.Сокол, д. 3<br><br><b               >Зимнее расписание с 01.09 по 31.05</b><br>С 10.00 до 19.00, суббота, воскресенье-с 10.00 до 18.00. Выходной –понедельник, санитарный день-последняя пятница месяца<br><b>Летнее расписание с 01.06 по 31.08</b><br>С 09.00 до 18.00, выходной-суббота, воскресенье, санитарный день-последняя пятница месяца<br><br><b                  >Контакты:</b><br>8(3012)226692 - Козлова Татьяна Владимировна Заведующий библиотекой<br>Электронная почта: filial21@cbs-ulan-ude.ru<br>",
            "<b>Библиотека - филиал № 24</b><br>670042, Республика Бурятия, г. Улан-Удэ, проспект Строителей, 34 а<br><br><b     >Зимнее расписание с 01.09 по 31.05</b><br>С 10.00 до 19.00, суббота, воскресенье-с 10.00 до 18.00. Выходной-понедельник, санитарный день-последняя пятница месяца<br><b >Летнее расписание с 01.06 по 31.08</b><br>С 09.00 до 18.00, выходной-воскресенье, суббота с 10.00 до 18.00, санитарный день-последняя пятница месяца<br><br><b >Контакты:</b><br>8(3012)336711 - Брянская Ольга Петровна Заведующий библиотекой<br>Электронная почта: filial24@cbs-ulan-ude.ru<br>",
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.full_contact, container, false);
        WebView textView = (WebView) view.findViewById(R.id.text);
        Log.e("TEST",mContacts.length+", "+ num);
        textView.loadData(mContacts[num], "text/html; charset=UTF-8", "ru_RU");
        return view;
    }
}
