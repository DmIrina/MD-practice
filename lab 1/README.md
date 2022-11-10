# Розробка мікросервісів
### лабораторна робота №1

---

### MoviesService - Spring Boot App (backend)
### create jar

```
cd MoviesService
```

```
mvnw package  
```

---

### containerizing
#### containers _dmirina/movies:1.1_ and _dmirina/client:1.2_ already exist on [hub.docker.com/repositories](http:/hub.docker.com/repositories "hub.docker.com/repositories"))

```
docker build -t <username>/movies:1.1 .
```

```
docker push <username>/movies:1.1
```

```
cd ..\client
```

```
docker build -t <username>/client:1.2 .
```

```
docker push <username>/client:1.2
```

---

### Kubernetes

```
minikube start
```

```
kubectl apply -f deploy-client.yaml
```

```
kubectl apply -f deploy-movies.yaml
```

```
kubectl apply -f s-client.yaml
```

```
kubectl apply -f s-movies.yaml
```

```
kubectl apply -f ingress.yaml
```

```
kubectl get pods --all-namespaces
```

|NAMESPACE|NAME|READY|STATUS|RESTARTS|AGE|
|----|----|----|----|----|----|
|...|...|...|...|...|...|
|ingress-nginx|ingress-nginx-controller-5959f988fd-lnq4q|1/1|Running|9 (25m ago)|7d19h|
|...|...|...|...|...|...|



### forward ingress port 80 to localhost:5000 
#### [MoviesService](https://github.com/DmIrina/5-MD-lab1/blob/main/MoviesService/src/main/java/com/example/moviesservice/MovieController.java "MoviesService")
> @CrossOrigin(origins = [http://localhost:5000](http://localhost:5000 "localhost"))


```
kubectl port-forward ingress-nginx-controller-5959f988fd-lnq4q 5000:80 --namespace ingress-nginx
```
