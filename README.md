# 📡 Realtime Chat Service

![Java](https://img.shields.io/badge/Java-17-orange)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3-green)
![Docker](https://img.shields.io/badge/Docker-Ready-blue)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-15-blueviolet)
![Redis](https://img.shields.io/badge/Redis-PubSub-red)
![License](https://img.shields.io/badge/License-MIT-yellow)

---

## 🚀 Description

This project is a real-time chat application built with:

- Java + Spring Boot (WebSocket STOMP) for real-time communication

- Redis (Pub/Sub) for message broadcasting

- PostgreSQL for storing chat history

- Python Worker (optional) for message analysis (e.g., sentiment analysis)

The goal: showcase the ability to design and implement a scalable, distributed, real-time system integrating multiple services.

---

## 🛠️ Tech Stack

- Backend: Java 17, Spring Boot 3

- WebSocket STOMP: real-time communication

- Redis: Pub/Sub for message broadcasting

- PostgreSQL: persistence layer

- Python Worker: NLP micro-service (optional)

- Docker Compose: orchestration (Java + Redis + DB + Worker)

---

## 💬 Frontend (Demo)

A simple demo interface is included in the project to test the real-time chat features.  
You can find it at:  
`src/main/resources/static/index.html`

### Access

Once the backend is running (via `docker-compose up --build` or `mvn spring-boot:run`), open:

👉 [http://localhost:8081](http://localhost:8081)

### Features
- Join a public chat room by entering a username  
- Send and receive messages in real time via WebSocket (STOMP)  
- Simple and clean interface for testing and demonstration purposes

<img width="888" height="783" alt="image" src="https://github.com/user-attachments/assets/db72d3d7-0218-4b02-8dfa-518197f42757" />

<img width="1711" height="859" alt="image" src="https://github.com/user-attachments/assets/887cc8a2-a9ac-4f97-9446-ab971a641dd2" />

> 💡 This frontend is meant **for demonstration and testing only**, not for production use.

---

## ▶️ How to Run

### ⚙️ Requirements

- Docker + Docker Compose
- Maven (if you want to build locally without Docker)
- Java 17+ installed (if running outside Docker)

### 📦 Clone the repository

```bash
git clone https://github.com/your-github/realtime-chat-service.git
cd realtime-chat-service
```

### 🔥 Run with Docker Compose

```bash
docker-compose up --build
```

Services started:

- chat_app → Spring Boot app (port 8081)
- chat_postgres → PostgreSQL (port 5432)
- chat_redis → Redis (port 6379)
- chat_worker → Python Worker (optional)

---

## 💬 WebSocket Example

1. Connection URL:

```bash
ws://localhost:8081/ws-chat
```

2. Subscribe to a topic (STOMP example):

```bash
stompClient.subscribe('/topic/messages', (message) => {
    console.log("Message received: ", JSON.parse(message.body));
});
```

3. Send a message:

```bash
stompClient.send(
  "/app/chat",
  {},
  JSON.stringify({ senderId: 1, receiverId: 2, content: "Hello 👋" })
);
```

---

## 🎯 Outcome

This project demonstrates skills in:
- Building a real-time distributed system
- Connecting multiple services (Java, Redis, PostgreSQL, Python Worker)
- Dockerizing a full-stack backend
