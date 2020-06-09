# Additional Examples for Red Hat Fuse/Apache Camel training

## You need:
- (IDE)
- JDK8 for the projects
- JDK11 for fabric8-plugin/openshift
- Maven
- git
- oc client

## Task 1:

Develop the "client" in the cxf-soap project,
so that it provides a REST-Service to access one of
the two methods of the backend SOAP-Services!


## Aufgabe 2:

Deploy the openshift-ocp project using fabric8:

```bash
$ mvn fabric8:deploy
```

You can get the URL using __oc get route__ .
Using the url you can query the default answer:
 
```bash
$ curl <URL>/camel/ping
hello
```

For the configuration we create a ConfigMap and redeploy:

```bash
$ oc create -f src/test/resources/configmap.yml
$ oc rollout latest springboot-ocp
```
Result: no changes.
Why? Our pod is not allowed to access the ConfigMap!
We need to give it more privileges:

```bash
$ oc policy add-role-to-user view -z default
$ oc rollout latest springboot-ocp
```
After the rollout finishes the newly configured message will be delivered.
Ihr könnt den Rollout tracken:

```bash
$ oc get pod -w
```

