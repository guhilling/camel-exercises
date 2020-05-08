# Additional Examples for Red Hat Fuse/Apache Camel training

Voraussetzungen:
- (IDE)
- JDK8
- Maven
- git
- oc client

Aufgabe 1:

Entwickelt den "client" im cxf-soap-Projekt so weiter, dass
er eine REST-Schnittstelle zum Zugriff auf eine der beiden
Methoden des SOAP-Services bereitstellt!


Aufgabe 2:

Deployed das Projekt openshift-ocp mit fabric8:

- mvn fabric8:deploy

curl springboot-ocp-gunnar-demo.apps.ocp-ejkypoyqorxzmol200504.do280.rht-eu.nextcle.com/camel/ping

sollte "hello" liefern.

Verwendung der Configmap:

- ConfigMap anlegen: 

    oc create -f oc create -f src/test/resources/configmap.yml
- redeploy:

oc rollout latest openshift-ocp

- Ergebnis: kein Änderung. Kein Zugriff auf die ConfigMap möglich (Log beachten!)

oc policy add-role-to-user view -z default

- Hiermit ist die Berechtigung erteilt (eigentlich zu viel...)

oc rollout latest openshift-ocp

- Ergebnis: Die konfigurierte Nachricht in der Map sollte beim ping zurückgeliefert werden.

