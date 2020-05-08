# Additional Examples for Red Hat Fuse/Apache Camel training

## Voraussetzungen:
- (IDE)
- JDK8 für die Projekte
- JDK11 für das fabric8-plugin/openshift
- Maven
- git
- oc client

## Aufgabe 1:

Entwickelt den "client" im cxf-soap-Projekt so weiter, dass
er eine REST-Schnittstelle zum Zugriff auf eine der beiden
Methoden des SOAP-Services bereitstellt!


## Aufgabe 2:

Deployed das Projekt openshift-ocp mit fabric8:

```bash
$ mvn fabric8:deploy
```

Die URL könnt Ihr über __oc get route__ ermitteln.
Es wird die default-Antwort geliefert:
 
```bash
$ curl <URL>/camel/ping
hello
```

Zur Anpassung legen wir eine ConfigMap an und rollen neu aus:

```bash
$ oc create -f oc create -f src/test/resources/configmap.yml
$ oc rollout latest openshift-ocp
```
Ergebnis: keine Änderung.

Warum? Unser Pod darf nicht auf die ConfigMap zugreifen!
Um das zu beheben, müssen wir ihm mehr Rechte einräumen:

```bash
$ oc policy add-role-to-user view -z default
$ oc rollout latest openshift-ocp
```
Wenn der Rollout abgeschlossen ist, wird die
konfigurierte Nachricht in der Map zurückgeliefert werden.

Ihr könnt den Rollout tracken:

```bash
$ oc get pod -w
```

