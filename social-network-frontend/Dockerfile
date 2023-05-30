FROM node:18-alpine as builder

WORKDIR /app

COPY ./social-network-frontend .

RUN npm ci 

RUN npm run build

# Bundle static assets with nginx
FROM nginx:1.24.0-alpine as production
ENV NODE_ENV production

COPY --from=builder /app/build /usr/share/nginx/html

# Add your nginx.conf
COPY social-network-frontend/nginx.conf /etc/nginx/conf.d/default.conf

EXPOSE 80
# Start nginx
CMD ["nginx", "-g", "daemon off;"]
