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

### check if tool chain is correctly detected by running below command.
`./gradlew -q javatoolchain`
  
  if entries are not detected, we can add entries for example in windows registry, you can copy the existing content from previous registries.
    Computer\HKEY_LOCAL_MACHINE\SOFTWARE\JavaSoft\Java Development Kit\17
