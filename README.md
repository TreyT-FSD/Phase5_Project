# Phase5_Project
Continuous Monitoring on Docker with ELK Stack.

# Overview
For this project I have used a hello world spring boot project as the application that will be getting built, deployed, and monitored with the ELK stack. The application is setup with a controller that can be reached at http://localhost:8085/. When this controller receives a request it logs a message with a timestamp. This message is pushed to logstash. The controller then returns a message to the browser with a timestamp.

Once this project was setup I pushed it to github (https://github.com/TreyT-FSD/Phase5_Project) and made several commits. I created a dockerfile to manage building the app in docker. I then created an image and pushed that to docker hub at https://hub.docker.com/repository/docker/tturley/phase5project.

Next, I created the docker-compose.yml file to take care of spinning up containers for the ELK stack and the springboot application. Finally, I created the pipeline in Jenkins to pull the code from Github and build it. Once the build completes additional docker and docker-compose commands fire off to spin up the application and the ELK stack.

# Applications and Technologies Used
- Docker
- Docker Compose
- Elasticsearch
- Logstash 
- Kibana
- Spring Boot application

# Link
- Docker Hub image of the SpringBoot application: https://hub.docker.com/repository/docker/tturley/phase5project
