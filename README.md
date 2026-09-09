<div align="center">

# 🤖 Saathi


### Your AI Coding Companion — Watches. Thinks. Speaks.

*Ek AI jo tumhare saath baithkar code karta hai — real-time suggestions, seedha voice mein.*

[![Java](https://img.shields.io/badge/Java-21-orange?style=for-the-badge&logo=openjdk)](https://openjdk.org/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-brightgreen?style=for-the-badge&logo=springboot)](https://spring.io/projects/spring-boot)
[![Ollama](https://img.shields.io/badge/Ollama-Local%20AI-black?style=for-the-badge)](https://ollama.com/)
[![License](https://img.shields.io/badge/License-MIT-blue?style=for-the-badge)](#license)

</div>

---

## 💡 What is Saathi?

**Saathi** (साथी — "companion") is a locally-running AI pair programmer for your Mac. It **watches** your Java files for changes, sends them to a **local LLM** for review, and **speaks** the suggestion back to you out loud — no cloud API costs, no data leaving your machine.

> Code karte waqt akela mehsoos mat karo — Saathi hamesha saath hai. 🎧

---

## ✨ Features

| Feature | Description |
|---|---|
| 📝 **Live File Watching** | Detects `.java` file saves in real time using Java's `WatchService` |
| 🧠 **Local AI Analysis** | Sends code to **Ollama (CodeLlama)** running fully offline — zero API cost |
| 🔊 **Voice Feedback** | Speaks suggestions aloud using macOS's built-in `say` command |
| 🔒 **100% Private** | Nothing leaves your machine — no cloud, no API keys, no billing surprises |
| ⚡ **Lightweight** | Built on Spring Boot — fast startup, minimal footprint |

---

## 🏗️ How It Works

```
   ┌─────────────────┐      ┌──────────────────┐      ┌─────────────────┐
   │   You save a     │      │   FileWatcher     │      │   OllamaService  │
   │   .java file     │ ───► │   detects change  │ ───► │   sends to LLM   │
   └─────────────────┘      └──────────────────┘      └─────────────────┘
                                                                  │
                                                                  ▼
   ┌─────────────────┐      ┌──────────────────┐      ┌─────────────────┐
   │   🔊 Saathi      │ ◄─── │   VoiceService     │ ◄─── │   Suggestion      │
   │   speaks it!     │      │   calls `say`      │      │   generated       │
   └─────────────────┘      └──────────────────┘      └─────────────────┘
```

---

## 🛠️ Tech Stack

- **Java 21**
- **Spring Boot 3.x** — core application framework
- **Ollama + CodeLlama 7B** — local LLM inference
- **macOS `say`** — native text-to-speech
- **Maven** — build & dependency management

---

## 🚀 Startup Guide

### Prerequisites

Make sure you have these installed:

```bash
brew install openjdk@21
brew install maven
brew install ollama
```

### 1️⃣ Clone the repo

```bash
git clone https://github.com/<your-username>/saathi.git
cd saathi
```

### 2️⃣ Start Ollama & pull the model

```bash
ollama serve
```

In a new terminal tab: 

```bash
ollama pull codellama:7b
```

### 3️⃣ Configure your watch folder

Open `src/main/resources/application.properties` and set the folder Saathi should watch:

```properties
saathi.watch.path=/Users/your-username/path-to-watch
```

> 💡 Tip: Use a dedicated test folder to avoid Saathi watching its own source code.

### 4️⃣ Run Saathi

```bash
./mvnw spring-boot:run
```

You should see:

```
Saathi is watching: /Users/your-username/path-to-watch
```

### 5️⃣ Test it

Open any `.java` file inside the watched folder, make a change, and **save**. Within a few seconds:

- ✅ Console prints Saathi's suggestion
- 🔊 Your Mac speaks it out loud

---

## 📂 Project Structure

```
saathi/
├── src/main/java/com/saathi/
│   ├── SaathiApplication.java     # Entry point
│   ├── FileWatcherService.java    # Watches for .java file changes
│   ├── OllamaService.java         # Talks to local Ollama LLM
│   └── VoiceService.java          # Text-to-speech via macOS `say`
├── src/main/resources/
│   └── application.properties     # Configuration
└── pom.xml
```

---

## 🗺️ Roadmap

- [x] File watching + AI suggestions
- [x] Voice output (TTS)
- [ ] Wake-word activation ("Hey Saathi")
- [ ] Speech-to-text (talk back to Saathi)
- [ ] Floating desktop widget UI
- [ ] Multi-language support (Python, JS)

---

## 🤝 Contributing

Suggestions and PRs welcome! This is a personal learning project exploring local-first AI tooling.

---

## 📄 License

MIT License — free to use, modify, and share.

---

<div align="center">

**Built with ☕ and late-night debugging by Mohit Yadav**

*Ek developer, ek Saathi!!.* 🚀

</div>
