# Run this Command on Terminal to Create the Docker Image
# > docker build -t react-bank:3.0 .
FROM node:18.17.0-alpine
WORKDIR /react-app
COPY package.json .
COPY package-lock.json .
RUN npm install
COPY . .
RUN npm run build
EXPOSE 3000
CMD ["npm", "start"]