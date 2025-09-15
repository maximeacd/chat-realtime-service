FROM python:3.11-slim
WORKDIR /app
RUN apt-get update && apt-get install -y netcat-openbsd && rm -rf /var/lib/apt/lists/*
COPY requirements.txt .
RUN pip install --no-cache-dir -r requirements.txt
COPY ../wait-for-it.sh /wait-for-it.sh
RUN chmod +x /wait-for-it.sh
COPY worker.py .
CMD ["/wait-for-it.sh", "redis", "6379", "--", "python", "worker.py"]