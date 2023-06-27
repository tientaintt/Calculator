package hcmute.edu.vn.calculator;

import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;

import java.text.DecimalFormat;

public class MainActivity2 extends AppCompatActivity {
    Button soMot,soHai,soBa,soBon,soNam,soSau,soBay,soTam,soChin,phanTram,
            C,del,dauChia,dauNhan,dauTru,dauCong,so0,dauBang,dauCham;
    private static final int QR_SCAN_REQUEST_CODE = 100;
    TextView KQ,Cal;
    Boolean check=true;
    String phepTinh;
    Double so1,so2;
    ImageView qrcode;
    private boolean isScanning = false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        anhXa();
        qrcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (!isScanning) {
//                    isScanning = true;
//                    IntentIntegrator intentIntegrator = new IntentIntegrator(MainActivity2.this);
//                    intentIntegrator.setOrientationLocked(true);
//                    intentIntegrator.setPrompt("Scan a QR Code");
//                    intentIntegrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE);
//                    intentIntegrator.initiateScan();
//                }
                scanBarCode();
            }
        });
        soMot.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                phepTinh = KQ.getText().toString();
                KQ.setText(phepTinh + '1');

            }
        });
        soHai.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                phepTinh = KQ.getText().toString();
                KQ.setText(phepTinh + '2');

            }
        });
        soBa.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                phepTinh = KQ.getText().toString();
                KQ.setText(phepTinh + '3');

            }
        });
        soBon.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                phepTinh = KQ.getText().toString();
                KQ.setText(phepTinh + '4');

            }
        });
        soNam.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                phepTinh = KQ.getText().toString();
                KQ.setText(phepTinh + '5');

            }
        });
        soSau.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                phepTinh = KQ.getText().toString();
                KQ.setText(phepTinh + '6');

            }
        });
        soBay.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                phepTinh = KQ.getText().toString();
                KQ.setText(phepTinh + '7');

            }
        });
        soTam.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                phepTinh = KQ.getText().toString();
                KQ.setText(phepTinh + '8');

            }
        });
        soChin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                phepTinh = KQ.getText().toString();
                KQ.setText(phepTinh + '9');

            }
        });
        so0.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                phepTinh = KQ.getText().toString();
                KQ.setText(phepTinh + '0');

            }
        });
//        dauCham.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                    phepTinh = KQ.getText().toString();
//                    KQ.setText(phepTinh + '.');
//            }
//        });
        dauChia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { xuLyTinhToan('÷');}
        });
        dauNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                xuLyTinhToan('x');
            }
        });
        dauCong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                xuLyTinhToan('+');
            }
        });
        dauTru.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                xuLyTinhToan('-');
            }
        });

        dauBang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                xuLyTinhToan('=');
            }

        });

        phanTram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                xuLyTinhToan('%');
            }

        });

        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(KQ.getText().length()>0)
                    KQ.setText(KQ.getText().toString().substring(0, KQ.getText().toString().length() - 1));

            }
        });
        C.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                KQ.setText("");
                Cal.setText("");
            }
        });
    }
    public int tinhChuSoSauDauPhay(double x)
    {
        String formatted = Double.toString(x);
        int decimalPlaces = formatted.length() - formatted.indexOf('.') - 1; // tính số chữ số sau dấu phẩy
        return decimalPlaces;
    }

    public double lamTron(double so1, double so2, double ketqua)
    {

        int chuso;
        if(tinhChuSoSauDauPhay(so1)>tinhChuSoSauDauPhay(so2))
        {
            chuso=tinhChuSoSauDauPhay(so1);

        }
        else
            chuso=tinhChuSoSauDauPhay(so2);
        String form="#";
        for ( int i=1;i<chuso;i++)
            form+="#";
        DecimalFormat df = new DecimalFormat("#.####"+form); // sẽ giữ lại chuso chữ số sau dấu phẩy
        String formatted = df.format(ketqua);

        formatted=formatted.replace(',','.');

        return Double.parseDouble(formatted);
    }

    double percent;
    public void xuLyTinhToan(char dau) {
        double result = 0;//Lưu kết quả tính toán
        double so1=0;
        double so2=0;
        String kqText = KQ.getText().toString();
        String calText = Cal.getText().toString();
        if(dau=='%'){
            percent=1;
        }else {
            percent=0;
        }


        if (kqText.isEmpty()) {
            KQ.setText("0");

        }




        if (calText.isEmpty()) {
            result = Double.parseDouble(KQ.getText().toString());
            if(percent==1)
            {
                String phepTinh="0";
                Cal.setText(phepTinh);
                KQ.setText("0");
            }
            else {
                if (dau != '=') {

                    String phepTinh = Double.toString(result) + dau;
                    Cal.setText(phepTinh);
                    KQ.setText("");

                }
                else {
                    String phepTinh = Double.toString(result) + dau;
                    Cal.setText(phepTinh);

                    KQ.setText(Double.toString(result));
                }
            }

        }

        if (calText.isEmpty() != true && kqText.isEmpty() != true) {
            char lastChar = calText.charAt(calText.length() - 1);//dấu
            boolean signMul = calText.contains("x");
            boolean signDiv = calText.contains("÷");
            boolean signPlus = calText.contains("+");
            boolean signSub = calText.contains("-");
            boolean flag=true;


            if(signMul==false && signDiv==false && signPlus==false && signSub==false)
            {
                Cal.setText(KQ.getText().toString() +dau);
                //KQ.setText("");
            }
            else
            {

                //Để tránh nhân chia với 0 khi tính liên tiếp
                if(lastChar=='=' && dau!='=' && (signMul || signDiv))
                {

                    Cal.setText(KQ.getText().toString()+dau);
                    lastChar=dau;
                    KQ.setText("1");
                }
                else if(lastChar=='='&& dau!='=')
                {
                    Cal.setText(KQ.getText().toString()+dau);
                    //Cal.setText(KQ.getText().toString()+dau);
                    lastChar=dau;
                    KQ.setText("0");
                } else if (lastChar=='='&& dau=='=') {
                    KQ.setText("");
                    Cal.setText("");
                    flag=false;//Khi cờ tắt thì sẽ không thực hiện tính toán
                }
                if(flag){
                    so1 = Double.parseDouble(Cal.getText().toString().trim().substring(0, Cal.getText().toString().length() - 1));
                    so2 = Double.parseDouble(KQ.getText().toString());


                    if(percent==0)
                    {
                        if(signPlus)
                            result=so1+so2;
                        if(signSub)
                            result=so1-so2;
                        if(signMul)
                            result=so1*so2;
                        if(signDiv)
                            if(so2!=0)
                                result=so1/so2;
                            else
                                flag=false;
                    }
                    else {

                        if(signPlus)
                            result = so1+so2*so1/100;
                        else if(signSub)
                            result=so1-so2*so1/100;
                        if(signMul)
                            result=so1*so2/100;
                        if(signDiv)
                            if(so2!=0)
                                result=so1/(so2/100);
                            else
                                flag=false;
                    }
                    if(!flag)
                    {
                        Toast.makeText(MainActivity2.this,"Error",Toast.LENGTH_LONG).show();
                        KQ.setText("");
                    }
                    else {
                        result=lamTron(so1,so2,result);

                        if (dau != '=') {
                            if(dau =='%'){
                                String phepTinh = Double.toString(result);
                                Cal.setText("");
                                KQ.setText(phepTinh);
                            }else{
                                String phepTinh = Double.toString(result) + dau ;
                                Cal.setText(phepTinh);
                                KQ.setText("");
                            }
                        }
                        else {
                            String phepTinh = Double.toString(so1) +lastChar  + Double.toString(so2) + dau;
                            Cal.setText(phepTinh);
                            KQ.setText(String.format(String.valueOf(result)));
                        }

                    }
                }
            }

        }



    }
    public void anhXa()
    {
        soMot=findViewById(R.id.button4);//số 1
        soHai=findViewById(R.id.button15);//số 2
        soBa=findViewById(R.id.button16);//số 3
        soBon=findViewById(R.id.button3);//số 4
        soNam=findViewById(R.id.button12);//số 5
        soSau=findViewById(R.id.button13);//số 6
        soBay=findViewById(R.id.button2);//số 7
        soTam=findViewById(R.id.button9);//số 8
        soChin=findViewById(R.id.button10);//số 9
        phanTram=findViewById(R.id.btnphantram);//phép phần trăm
        C=findViewById(R.id.button5);//Xóa màn hình
        del=findViewById(R.id.button6);//Xóa 1 số
        dauChia=findViewById(R.id.button7);//dấu chia
        dauNhan=findViewById(R.id.button11);//dấu nhân
        dauTru=findViewById(R.id.button14);//dấu trừ
        dauCong=findViewById(R.id.button17);//dấu cộng
        so0=findViewById(R.id.button18);//số 0
        dauBang=findViewById(R.id.button19);//dấu bằng
//        dauCham=findViewById(R.id.button8);//dấu Chấm
        qrcode=findViewById(R.id.imageView2);
        KQ=findViewById(R.id.textView4);//Hiện kết quả
        Cal=findViewById(R.id.textView3);//Hiện phép toán
    }
//
    public void scanBarCode()
    {



            ScanOptions options=new ScanOptions();
            options.setPrompt("Volumn up to flash on");
            options.setBeepEnabled(true);
            options.setOrientationLocked(true);
            options.setDesiredBarcodeFormats(ScanOptions.ALL_CODE_TYPES);
        options.setCaptureActivity(CaptureAct.class);

        barLaucher.launch(options);



    }
    ActivityResultLauncher<ScanOptions> barLaucher=registerForActivityResult(new ScanContract(),result -> {
        if(KQ.getText().toString().trim().isEmpty())
        {
            KQ.setText("0");
        }
        if(result.getContents()!=null){
            double qrCodeValue = Double.parseDouble(result.getContents());
            double currentValue = Double.parseDouble(KQ.getText().toString());
            double newValue = currentValue + qrCodeValue;
            KQ.setText(String.valueOf(newValue));
            scanBarCode();
        }

    });

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        IntentResult intentResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
//        if (intentResult != null) {
//            String contents = intentResult.getContents();
//            if(KQ.getText().toString().trim().isEmpty())
//            {
//                KQ.setText("0");
//            }
//            if (contents != null) {
//
//                double qrCodeValue = Double.parseDouble(contents);
//                double currentValue = Double.parseDouble(KQ.getText().toString());
//                double newValue = currentValue + qrCodeValue;
//                KQ.setText(String.valueOf(newValue));
//                IntentIntegrator intentIntegrator = new IntentIntegrator(MainActivity2.this);
//                intentIntegrator.setOrientationLocked(true);
//                intentIntegrator.setPrompt("Scan a QR Code");
//                intentIntegrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE);
//                intentIntegrator.initiateScan();
//            }
//        } else {
//            super.onActivityResult(requestCode, resultCode, data);
//        }
//        // Tắt cờ quét QR code sau khi xử lý kết quả
//        isScanning = false;
//    }
}


