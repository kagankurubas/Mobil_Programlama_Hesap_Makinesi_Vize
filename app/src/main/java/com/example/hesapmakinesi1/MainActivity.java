package com.example.hesapmakinesi1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.mariuszgromada.math.mxparser.Expression;


public class MainActivity extends AppCompatActivity {
    EditText display;
    EditText display1;
    boolean ensonesitmi=true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        display=findViewById(R.id.yazi); //edittext tanımlandı.
        display1=findViewById(R.id.yazi);
        display.setShowSoftInputOnFocus(false); // Edit text kısmına tıkladığımızda klavye açılmayacak.
        if (getString(R.string.start).equals(display.getText().toString())){
            display.setText("");
        }
    }

    public void butontıklama(View view) {
        if (ensonesitmi){
            display.setText("");
            ensonesitmi=false;
        }
        switch (view.getId()){
            case R.id.clear         :   display.setText("")        ;      break;
            case R.id.parantezler   :   updateDisplay("(");     break;
            case R.id.parantezler1  :   updateDisplay(")");     break;
            case R.id.kare          :   updateDisplay("^");     break;
            case R.id.arti          :   updateDisplay("+");     break;
            case R.id.bolme         :   updateDisplay("/");     break;
            case R.id.carpma        :   updateDisplay("*");     break;
            case R.id.eksi          :   updateDisplay("-");     break;
            case R.id.bir           :   updateDisplay("1");     break;
            case R.id.iki           :   updateDisplay("2");     break;
            case R.id.uc            :   updateDisplay("3");     break;
            case R.id.dort          :   updateDisplay("4");     break;
            case R.id.bes           :   updateDisplay("5");     break;
            case R.id.alti          :   updateDisplay("6");     break;
            case R.id.yedi          :   updateDisplay("7");     break;
            case R.id.sekiz         :   updateDisplay("8");     break;
            case R.id.dokuz         :   updateDisplay("9");     break;
            case R.id.ucsifir       :   updateDisplayucsifir()      ;     break;
            case R.id.esittir       :   sonucuHesapla()             ;     break;
            case R.id.silme         :   silmeFonksiyonu()           ;     break;
            case R.id.ondalik       :   updateDisplay(".");     break;
            case R.id.sifir         :   updateDisplay("0");     break;
            case R.id.radyan        :   updateDisplaysin("rad(");      break;
            case R.id.mutlakdeger   :   updateDisplay("|");     break;
            case R.id.parantez      :   updateDisplay("(");     break;
            case R.id.parantez2     :   updateDisplay(")");     break;
            case R.id.xkare         :   updateDisplay("^2");    break;
            case R.id.bolum         :   updateDisplayikili("1/");      break;
            case R.id.cos           :   updateDisplaysin("cos(");      break;
            case R.id.esayi         :   updateDisplay("e");     break;
            case R.id.eussux        :   updateDisplayikili("e^");      break;
            case R.id.kok           :   updateDisplay("√");     break;
            case R.id.ln            :   updateDisplaysin("ln(");        break;
            case R.id.log           :   updateDisplaysin("log(");       break;
            case R.id.pi            :   updateDisplay("π");     break;
            case R.id.sin           :   updateDisplaysin("sin(");        break;
            case R.id.tan           :   updateDisplaysin("tan(");        break;
            case R.id.yuzde         :   updateDisplay("%");      break;

        }
    }

    private void updateDisplayikili(String s) {
        int imlecYeri = display.getSelectionStart();//Cursorun yeri belirlenir.
        if (getString(R.string.start).equals(display.getText().toString())){
            display.setText(s);
        }else {
            String eskiyazi = display.getText().toString();
            String solyazi = eskiyazi.substring(0,imlecYeri);//Cursor posisyonundan sol tarafı tutar.
            String sagyazi = eskiyazi.substring(imlecYeri);//Cursor posisyonundan sağ tarafı tutar.
            String yeniyazi = solyazi+s+sagyazi;
            display.setText(yeniyazi);
        }
        display.setSelection(imlecYeri+2);//Cursor pozisyonunun karakter eklendikten sonra doğru yerde olması için pozisyonu değiş
    }

    private void updateDisplaysin(String s) {
        int imlecYeri = display.getSelectionStart();//Cursorun yeri belirlenir.
        if (getString(R.string.start).equals(display.getText().toString())){
            display.setText(s);
        }else {
            String eskiyazi = display.getText().toString();
            String solyazi = eskiyazi.substring(0,imlecYeri);//Cursor posisyonundan sol tarafı tutar.
            String sagyazi = eskiyazi.substring(imlecYeri);//Cursor posisyonundan sağ tarafı tutar.
            String yeniyazi = solyazi+s+sagyazi;
            display.setText(yeniyazi);
        }
        display.setSelection(imlecYeri+4);//Cursor pozisyonunun karakter eklendikten sonra doğru yerde olması için pozisyonu değiş

    }

    private void silmeFonksiyonu() {
        int imlecYeri=display.getSelectionStart();
        if (imlecYeri>0){
            String eskiyazi = display.getText().toString();
            String solyazi = eskiyazi.substring(0,imlecYeri-1);//Cursor posisyonundan sol tarafı tutar ve soldan ilk karakteri eksiltir.
            String sagyazi = eskiyazi.substring(imlecYeri);//Cursor posisyonundan sağ tarafı tutar.
            String yeniyazi = solyazi+sagyazi;//Silme işlemi sonrası stringi yazar.
            display.setText(yeniyazi);
            display.setSelection(imlecYeri-1);//1 karakter silindiği için Cursor pozisyonunu bir sola çeker.
        }
    }

    private void sonucuHesapla() {
        Toast toast=new Toast(getApplicationContext());
        parantezsay();
        String yazi = display.getText().toString();
        Expression ifade = new Expression(yazi);
        String sonuc = String.valueOf(ifade.calculate()).toString();
        if (!sonuc.equals("NaN")){
            display.setText(sonuc);
            display.setSelection(sonuc.length());
        }else{
            Toast.makeText(MainActivity.this, "Hatalı Giriş.", Toast.LENGTH_SHORT).show();
        }
        ensonesitmi = true;
    }

    private void updateDisplayucsifir() {
        int imlecYeri = display.getSelectionStart();//Cursorun yeri belirlenir.
        if (getString(R.string.start).equals(display.getText().toString())){
            display.setText("000");
        }else {
            String eskiyazi = display.getText().toString();
            String solyazi = eskiyazi.substring(0,imlecYeri);//Cursor posisyonundan sol tarafı tutar.
            String sagyazi = eskiyazi.substring(imlecYeri);//Cursor posisyonundan sağ tarafı tutar.
            String yeniyazi = solyazi+"000"+sagyazi;//Cursor pozisyonuna girilen karakteri ekler.
            display.setText(yeniyazi);
        }
        display.setSelection(imlecYeri+3);//Cursor pozisyonunun karakter eklendikten sonra doğru yerde olması için pozisyonu değiştirir.
    }

    private void updateDisplay(String karakterEkle) {
        int imlecYeri = display.getSelectionStart();//Cursorun yeri belirlenir.
        if (getString(R.string.start).equals(display.getText().toString())){
            display.setText(karakterEkle);
        }else {
            String eskiyazi = display.getText().toString();
            String solyazi = eskiyazi.substring(0,imlecYeri);//Cursor posisyonundan sol tarafı tutar.
            String sagyazi = eskiyazi.substring(imlecYeri);//Cursor posisyonundan sağ tarafı tutar.
            String yeniyazi = solyazi+karakterEkle+sagyazi;
            display.setText(yeniyazi);
        }
        display.setSelection(imlecYeri+1);//Cursor pozisyonunun karakter eklendikten sonra doğru yerde olması için pozisyonu değiştirir.
    }

    private void parantezsay() {
        Toast toast=new Toast(getApplicationContext());
        String eskiyazi = display.getText().toString();
        int imlecYeri = display.getSelectionStart();
        int parantezSayisi = 0 ;
        for (int i=0;i<eskiyazi.length();i++){
            if(eskiyazi.substring(i,i+1).equalsIgnoreCase("(")) parantezSayisi++;
            if(eskiyazi.substring(i,i+1).equalsIgnoreCase(")")) parantezSayisi--;
        }

        //For döngüsü ile yazının karakterlerini kontrol eip parantezlerin sayısı alındı.
        if (parantezSayisi!=0){
            Toast.makeText(MainActivity.this, "Parantezler Hatalı Kullanıldı.", Toast.LENGTH_SHORT).show();
        }
        //Parantezler hatalı kullanıldıysa uyarı verir. Ekranda toast mesajı belirir.
    }

}