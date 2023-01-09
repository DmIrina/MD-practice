# Розробка мікросервісів
#### лабораторна робота №3

---


## Images
### MovieService - Spring Boot App (backend)

```
docker build --no-cache -t dmirina/microservices-service:3.2 service
```

```
docker push dmirina/microservices-service:2.1
```

#### Client - Front-end

```
docker build --no-cache -t dmirina/microservices-client:3.2 client
```

```
docker push dmirina/microservices-client:3.2
```

#### Migrations

```
docker build --no-cache -t dmirina/migrations:2.0 migrations
```

```
docker push dmirina/migrations:2.0
```

---

### Helm

```
choco install kubernetes-helm
```

```
minikube start
```

```
cd helm
```

```
helm dependency build
```

```
helm install helm-movie .
```

```
kubectl get all
```

```
helm uninstall helm-movie
```

```
minikube tunnel
```

---

### Minikube useful commands

```
minikube addons enable ingress
```

```
minikube addons list
```

```
minikube dashboard --url
```
