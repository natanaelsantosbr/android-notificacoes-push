package br.natanael.android.notificacoesfirebase.api;

import br.natanael.android.notificacoesfirebase.model.NotificacaoDados;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface NotificacaoService {

    @Headers({
            "Authorization:key=AAAAoJqvYYU:APA91bENFg3nDvoHYAReKi9zDktzffuFyCh4hYjjfNnbTBr0vhoD3ifyqyhTcHxlOIeyXIeddR2E6fxge7dy0BvkQkb39QuKux6G89CdFPtXiDYoPHso0HF8CF8kb7C7vq_Ri8Diqx2M",
            "Content-Type:application/json"
    })
    @POST("send")
    Call<NotificacaoDados> salvarNotificacao(@Body NotificacaoDados notificacaoDados);
}
