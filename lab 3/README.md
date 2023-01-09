# Розробка мікросервісів
#### лабораторна робота №3

---


## Images
### MovieService - Spring Boot App (backend)

```
docker build -t dmirina/movie:2.1 service
```

```
docker push dmirina/movie:2.1
```

#### Client - Front-end

```
docker build -t dmirina/client:2.4 client
```

```
docker push dmirina/client:2.4
```

#### Migrations

```
docker build -t dmirina/migrations:2.0 migrations
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