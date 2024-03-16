:: set EZYFOX_SERVER_HOME=
mvn -pl . clean install & ^
mvn -pl ezyfox-server-unity-example-common -Pexport clean install & ^
mvn -pl ezyfox-server-unity-example-app-api -Pexport clean install & ^
mvn -pl ezyfox-server-unity-example-app-entry -Pexport clean install & ^
mvn -pl ezyfox-server-unity-example-plugin -Pexport clean install & ^
copy ezyfox-server-unity-example-zone-settings.xml %EZYFOX_SERVER_HOME%/settings/zones/
