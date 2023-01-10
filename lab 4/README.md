# Розробка мікросервісів
#### лабораторна робота №4

---


## Images
#### MovieService - Spring Boot App (backend)

```
docker build --no-cache -t dmirina/cinemaservice:4.1 service
```

```
docker push dmirina/cinemaservice:4.1
```

#### TicketService - Spring Boot App (backend)

```
docker build --no-cache -t dmirina/ticketservice:4.1 service
```

```
docker push dmirina/ticketservice:4
```

#### Client - Front-end

```
docker build --no-cache -t dmirina/microservices-client:4.1 client
```

```
docker push dmirina/microservices-client:4.1
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
kubectl create namespace istio-system
```

```
helm repo add istio https://istio-release.storage.googleapis.com/charts
```

```
helm repo update
```

```
helm install istio-base istio/base -n istio-system
```

```
helm install istio-base istio/base -n istio-system
```

```
helm install istiod istio/istiod -n istio-system --wait
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
