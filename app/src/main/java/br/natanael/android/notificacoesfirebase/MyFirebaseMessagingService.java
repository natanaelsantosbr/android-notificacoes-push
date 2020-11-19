package br.natanael.android.notificacoesfirebase;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    @Override
    public void onMessageReceived(@NonNull RemoteMessage notificacao) {
        if(notificacao.getNotification() != null)
        {
            String titulo = notificacao.getNotification().getTitle();
            String corpo = notificacao.getNotification().getBody();

            enviarNotificacao(titulo, corpo);
        }
    }

    private void enviarNotificacao(String titulo, String corpo) {
        String canal = getString(R.string.default_notification_channel_id);
        Uri uriSom = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        Intent i = new Intent(this, NotificacoesActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, i, PendingIntent.FLAG_ONE_SHOT);

        //Criar notificação
        NotificationCompat.Builder notificacao = new NotificationCompat.Builder(this, canal)
                .setContentTitle(titulo)
                .setContentText(corpo)
                .setSmallIcon(R.drawable.ic_camera_black_24dp)
                .setSound(uriSom)
                .setAutoCancel(true)
                .setContentIntent(pendingIntent);

        //Recupera notificationManager
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        //Verifica versao do android a partir dooreo para configurar canal de notificacao
        if(Build.VERSION.SDK_INT  >= Build.VERSION_CODES.O)
        {
            NotificationChannel channel = new NotificationChannel(canal,"canal", NotificationManager.IMPORTANCE_DEFAULT);
            notificationManager.createNotificationChannel(channel);
        }

        //Envia notificacao
        notificationManager.notify(0, notificacao.build());
    }

    @Override
    public void onNewToken(@NonNull String s) {
        super.onNewToken(s);

        //Moto G
        //c3N-bmZ9SPyPXkU4IVoU91:APA91bEkXbdXh4HpUZ5xWMn4COr70YMWk1NBEt75dJ1OLj3GNWLROKqYYNskmT-h7yXE2en0xoNZv4qUBvaO9JacDFCBx_y6lZ4OKM2FKieEM7o0aOxjfjiFwmaPUycpjBtrX6YF8y5J

        //emulador 1
        //ev-0ReV3SNGdylmMeNYdja:APA91bEP2kWfuFnxMCd9rHBq6KGft3Bg6Ql62szrrMwquJ_9qz38fQBgRwmhNlc3-hQ1aJgFT_VJ8UPjWi6-0KgXDycykrcj8iUISLaA-qA2w4rU0pgWynClsao_mU1H727JIlbBKa3a


        Log.i("onNewToken", "onNewToken: " + s);
    }
}
