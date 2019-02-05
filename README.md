# Hello world DB seeder app with Scala, Slick, SQLServer and Kubernetes

Don't forget to add `mssql-jdbc-7.2.0.jre11.jar` to your Classpath before running

### How to run locally

1. Install Docker, k8s and minikube
2. Run minikube: 
```minikube start --cpus=4 --memory=8192```
3. Apply mssql deployment
``` kubectl apply -f k8s/mssql/mssql.yaml```
4. Adjust host, port and auth settings in `src/main/resources/application.conf`.
Here you can check mssql host and port:
```minikube service mssql --url```
5. Example: run Seeder or Remover class from sbt or IDE