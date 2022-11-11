# Розробка мікросервісів
### лабораторна робота №1

---

### MoviesService - Spring Boot App (backend)
### create jar (optional)

```
cd MoviesService
```

```
mvnw package  
```

---

### containerizing
#### containers _dmirina/movies:1.5_ and _dmirina/client:1.5_ already exist on [hub.docker.com/repositories](http:/hub.docker.com/repositories "hub.docker.com/repositories"))

```
docker build -t <username>/movies:1.5 .
```

```
docker push <username>/movies:1.5
```

```
cd ..\client
```

```
docker build -t <username>/client:1.5 .
```

```
docker push <username>/client:1.5
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
minikube addons enable ingress
```

```
minikube addons list
```

```
kubectl apply -f ingress.yaml
```

```
minikube tunnel
```

### port-forwarding ingress (optional)

```
kubectl get pods --all-namespaces
```

|NAMESPACE|NAME|READY|STATUS|RESTARTS|AGE|
|----|----|----|----|----|----|
|...|...|...|...|...|...|
|ingress-nginx|ingress-nginx-controller-5959f988fd-lnq4q|1/1|Running|9 (25m ago)|7d19h|
|...|...|...|...|...|...|


```
kubectl port-forward ingress-nginx-controller-5959f988fd-lnq4q 5000:80 --namespace ingress-nginx
```

### minikube dashboard (optional)

```
minikube dashboard --url
```

![image](https://github.com/DmIrina/5-MD/blob/main/lab%201/image.jpeg)
