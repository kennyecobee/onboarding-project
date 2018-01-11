package com.example.kennyr.onboardingproject.app;

public class App extends android.app.Application {

    private AppComponent component;

    @Override
    public void onCreate() {
        super.onCreate();

        AppModule module = new AppModule(this);
        component = DaggerAppComponent.builder().appModule(module).build();
        component.injectMembers(this);
    }

    public AppComponent getComponent() {
        return component;
    }
}
