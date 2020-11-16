package com.ntersol.ntersolmusic;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.ntersol.ntersolmusic.data.SongTrackRaw;
import com.ntersol.ntersolmusic.data.SongTrackRemoteService;
import com.ntersol.ntersolmusic.data.SongTrackRemoteServiceProvider;
import com.ntersol.ntersolmusic.data.SongTrackRepository;
import com.ntersol.ntersolmusic.data.api.SongsConverter;
import com.ntersol.ntersolmusic.domain.SongTrackGateway;
import com.ntersol.ntersolmusic.domain.usecases.FetchSongTrackUseCase;

import java.util.List;
import java.util.Timer;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UseCasesProvider {

    private static Timer timer;

    private static SongTrackGateway songTrackGateway;
    private static FetchSongTrackUseCase fetchSongTrackUseCase;
    private static Retrofit retrofit;

    public static BuildType BUILD_TYPE;
    public static boolean IS_DEBUG = false;
    public static String APP_VERSION = "";
    private static boolean IS_UNINITIALIZED = true;

    public static enum BuildType {
        DEBUG("https://dev-api.cosmosapps.io"),
        PROD("https://dev-api.cosmosapps.io");

        String domain;

        BuildType(String domain) {
            this.domain = domain;
        }
    }

    public static void initialize(Context context) {
        if (IS_UNINITIALIZED) {
            IS_UNINITIALIZED = false;
            BuildType buildType = BuildType.DEBUG;

            boolean isDebuggable = (0 != (context.getApplicationInfo().flags & ApplicationInfo.FLAG_DEBUGGABLE));
            boolean isUAT = false;
            boolean isQA = false;
            try {
                PackageInfo pInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
                String version = pInfo.versionName;
                APP_VERSION = version;
                isQA = version.contains("QA");
                isUAT = version.contains("UAT");
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }

            if (!isDebuggable) {
                buildType = BuildType.PROD;
            }

            OkHttpClient okHttpClient = new OkHttpClient()
                    .newBuilder()
                    .connectTimeout(30, TimeUnit.SECONDS)
                    .build();

            Retrofit songTrackRetrofit = new Retrofit.Builder()
                    .client(okHttpClient)
                    .baseUrl(BuildType.PROD.domain)
                    .addConverterFactory(
                            GsonConverterFactory.create(
                                    new GsonBuilder()
                                            .registerTypeAdapter(
                                                    new TypeToken<List<SongTrackRaw>>() {
                                                    }.getType(),
                                                    new SongsConverter())
                                            .create()
                            )
                    )
                    .build();
            SongTrackRemoteService songTrackRemoteService = new SongTrackRemoteServiceProvider(
                    songTrackRetrofit
            );

            songTrackGateway = new SongTrackRepository(songTrackRemoteService);


        }


    }

    public static FetchSongTrackUseCase provideSongTrackUseCase() {
        if (fetchSongTrackUseCase == null)
            fetchSongTrackUseCase = new FetchSongTrackUseCase(
                    songTrackGateway
            );
        return fetchSongTrackUseCase;
    }

    public static Timer getTimerInstance() {
        if (timer == null) {
            timer = new Timer(false);
        }
        return timer;
    }
}
