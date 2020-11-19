package br.natanael.android.notificacoesfirebase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.google.firebase.iid.internal.FirebaseInstanceIdInternal;
import com.google.firebase.messaging.FirebaseMessaging;

import br.natanael.android.notificacoesfirebase.api.NotificacaoService;
import br.natanael.android.notificacoesfirebase.model.Notificacao;
import br.natanael.android.notificacoesfirebase.model.NotificacaoDados;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private Retrofit retrofit;
    private String baseUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        baseUrl = "https://fcm.googleapis.com/fcm/";

        retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        FirebaseMessaging.getInstance().subscribeToTopic("alemao");

    }

    public  void enviarNotificacao(View view)
    {
        String to = "/token/c3N-bmZ9SPyPXkU4IVoU91:APA91bEkXbdXh4HpUZ5xWMn4COr70YMWk1NBEt75dJ1OLj3GNWLROKqYYNskmT-h7yXE2en0xoNZv4qUBvaO9JacDFCBx_y6lZ4OKM2FKieEM7o0aOxjfjiFwmaPUycpjBtrX6YF8y5J";
        Notificacao notificacao = new Notificacao("Titulo da notificacao","Corpo da notificacao");

        NotificacaoDados notificacaoDados = new NotificacaoDados(to, notificacao);

        NotificacaoService notificacaoService = retrofit.create(NotificacaoService.class);
        Call<NotificacaoDados> call = notificacaoService.salvarNotificacao(notificacaoDados);

        call.enqueue(new Callback<NotificacaoDados>() {
            @Override
            public void onResponse(Call<NotificacaoDados> call, Response<NotificacaoDados> response) {
                if(response.isSuccessful())
                {
                    Toast.makeText(MainActivity.this, "codigo: " + response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<NotificacaoDados> call, Throwable t) {

            }
        });

    }


}
