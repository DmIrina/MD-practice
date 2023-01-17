# Розробка мікросервісів
#### лабораторна робота №5

---


## Images
#### CinemaService - Spring Boot App (back end)

```
docker build --no-cache -t dmirina/cinemaservice:5.2 services/CinemaService
```

```
docker push dmirina/cinemaservice:5.2
```

#### FilmService - Spring Boot App (back end)

```
docker build --no-cache -t dmirina/filmservice:5.2 services/FilmService
```

```
docker push dmirina/filmservice:5.2
```

#### MessageService - Spring Boot App (back end)

```
docker build --no-cache -t dmirina/messageservice:5.1 services/MessageService
```

```
docker push dmirina/messageservice:5.1
```

#### Migrations

```
docker build --no-cache -t dmirina/migrations:2.1 migrations
```

```
docker push dmirina/migrations:2.1
```

#### client - React App (front end)

```
docker build --no-cache -t dmirina/client:5.1 client
```

```
docker push dmirina/client:5.1
```

---

```
choco install kubernetes-helm
```

```
minikube start
```

```
helm repo update
```



```
cd helm
```

```
kubectl get all
```

```
helm dependency build
```

```
helm install helm-cinema .
```

```
helm uninstall helm-cinema
```

```
minikube tunnel
```

---

localhost/api/session/1


localhost/api/movie/1

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