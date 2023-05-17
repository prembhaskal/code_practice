## pre-requisites
  - install gradle
  - install java17

## build steps
  - ./gradlew build

### gradle stuff
  you might have to add proxy in your $USER_HOME/.gradle/gradle.properties, if you are behind a proxy.
```
systemProp.http.proxyHost=host
systemProp.http.proxyPort=port
systemProp.https.proxyHost=host
systemProp.https.proxyPort=port
```