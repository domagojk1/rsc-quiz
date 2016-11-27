package com.egzepsn.rsc.rscapp.app;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.multidex.MultiDex;

import com.egzepsn.rsc.rscapp.enums.AppStateEnum;
import com.egzepsn.rsc.rscapp.models.Team;
import com.egzepsn.rsc.rscapp.models.User;
import com.egzepsn.rsc.rscapp.signalr.CustomMessage;
import com.egzepsn.rsc.rscapp.signalr.SignalRListener;
import com.facebook.FacebookSdk;
import com.facebook.LoggingBehavior;
import com.joanzapata.iconify.Iconify;
import com.joanzapata.iconify.fonts.MaterialModule;
import com.activeandroid.ActiveAndroid;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import microsoft.aspnet.signalr.client.Credentials;
import microsoft.aspnet.signalr.client.Platform;
import microsoft.aspnet.signalr.client.SignalRFuture;
import microsoft.aspnet.signalr.client.http.Request;
import microsoft.aspnet.signalr.client.http.android.AndroidPlatformComponent;
import microsoft.aspnet.signalr.client.hubs.HubConnection;
import microsoft.aspnet.signalr.client.hubs.HubProxy;
import microsoft.aspnet.signalr.client.hubs.SubscriptionHandler1;
import microsoft.aspnet.signalr.client.transport.ClientTransport;
import microsoft.aspnet.signalr.client.transport.ServerSentEventsTransport;

/**
 * Created by domagoj on 11/23/16.
 */

public class RSCApp extends Application {
    private static RSCApp instance;
    protected static AppStateEnum appState = AppStateEnum.NotSignedIn;
    protected static User loggedUser;
    private static ArrayList<Team> teams;

    public static RSCApp getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        startSignalR();
        instance = this;

        FacebookSdk.sdkInitialize(getApplicationContext());
        FacebookSdk.setIsDebugEnabled(true);
        FacebookSdk.addLoggingBehavior(LoggingBehavior.REQUESTS);
        Iconify.with(new MaterialModule());
        ActiveAndroid.initialize(this);
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    public SharedPreferences getPreferences() {
        return getPreferences();
    }

    public AppStateEnum getAppState() {
        return RSCApp.appState;
    }

    public void setAppState(AppStateEnum appState) {
        RSCApp.appState = appState;
    }

    public static User getLoggedUser() {
        return loggedUser;
    }

    public static void setLoggedUser(User loggedUser) {
        RSCApp.loggedUser = loggedUser;
    }

    public static ArrayList<Team> getTeams() {
        return teams;
    }

    public static void setTeams(ArrayList<Team> teams) {
        RSCApp.teams = teams;
    }

    private HubConnection mHubConnection;
    private HubProxy mHubProxy;

    private ArrayList<SignalRListener> signalRListeners = new ArrayList<>();

    public void addSignalRListener(SignalRListener listener) {
        signalRListeners.add(listener);
    }

    public void notifyListeners(Object object) {
        for (SignalRListener listener : signalRListeners) {
            listener.onHubMessageReceived(object);
        }
    }

    public void startSignalR() {
        Platform.loadPlatformComponent(new AndroidPlatformComponent());

        Credentials credentials = new Credentials() {
            @Override
            public void prepareRequest(Request request) {
                request.addHeader("User-Name", "Android");
            }
        };

        String serverUrl = "http://rsc2016quiz.azurewebsites.net/";
        mHubConnection = new HubConnection(serverUrl);
        mHubConnection.setCredentials(credentials);
        String SERVER_HUB_CHAT = "PostsHub";
        mHubProxy = mHubConnection.createHubProxy(SERVER_HUB_CHAT);
        ClientTransport clientTransport = new ServerSentEventsTransport(mHubConnection.getLogger());
        SignalRFuture<Void> signalRFuture = mHubConnection.start(clientTransport);

        try {
            signalRFuture.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            return;
        }

        //
        String CLIENT_METHOD_BROADAST_MESSAGE = "broadcastMessage";
        mHubProxy.on(CLIENT_METHOD_BROADAST_MESSAGE,
                new SubscriptionHandler1<CustomMessage>() {
                    @Override
                    public void run(final CustomMessage msg) {
                        final String finalMsg = msg.UserName + " says " + msg.Message;
                        notifyListeners(finalMsg);
                    }
                }
                , CustomMessage.class);

        String SendQuizList = "SendQuizList";
        mHubProxy.on(SendQuizList,
                new SubscriptionHandler1<CustomMessage>() {
                    @Override
                    public void run(final CustomMessage msg) {
                        final String finalMsg = msg.UserName + " says " + msg.Message;
                        notifyListeners(finalMsg);
                    }
                }
                , CustomMessage.class);

        String SendTeamList = "SendTeamList";
        mHubProxy.on(SendTeamList,
                new SubscriptionHandler1<CustomMessage>() {
                    @Override
                    public void run(final CustomMessage msg) {
                        final String finalMsg = msg.UserName + " says " + msg.Message;
                        notifyListeners(finalMsg);
                    }
                }
                , CustomMessage.class);

        String StartQuiz = "StartQuiz";
        mHubProxy.on(StartQuiz,
                new SubscriptionHandler1<CustomMessage>() {
                    @Override
                    public void run(final CustomMessage msg) {
                        final String finalMsg = msg.UserName + " says " + msg.Message;
                        notifyListeners(finalMsg);
                    }
                }
                , CustomMessage.class);

        String SendNextQuestion = "SendNextQuestion";
        mHubProxy.on(SendNextQuestion,
                new SubscriptionHandler1<CustomMessage>() {
                    @Override
                    public void run(final CustomMessage msg) {
                        final String finalMsg = msg.UserName + " says " + msg.Message;
                        // display Toast message

                    }
                }
                , CustomMessage.class);

        String Send = "Send";
        mHubProxy.on(SendNextQuestion,
                new SubscriptionHandler1<CustomMessage>() {
                    @Override
                    public void run(final CustomMessage msg) {
                        final String finalMsg = msg.UserName + " says " + msg.Message;
                        notifyListeners(msg);

                    }
                }
                , CustomMessage.class);


        sendMessage("Poruka");
    }

    public void sendMessage(String message) {
        String SERVER_METHOD_SEND = "Send";
        mHubProxy.invoke(SERVER_METHOD_SEND, message);
    }

    public void stopHub() {
        if (mHubConnection != null) {
            mHubConnection.stop();
        }
    }
}
