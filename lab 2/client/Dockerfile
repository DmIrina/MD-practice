# stage 1
FROM node:18.8.0-alpine as build
WORKDIR /app
COPY ./package.json ./
COPY ./package-lock.json ./

RUN npm install
COPY . .
RUN npm run build

# stage 2
FROM nginx:stable-alpine
COPY --from=build /app/build /usr/share/nginx/html

EXPOSE 80

CMD ["nginx", "-g", "daemon off;"]