import os
import redis
import json
from textblob import TextBlob

REDIS_HOST = os.getenv("REDIS_HOST", "redis")
REDIS_PORT = int(os.getenv("REDIS_PORT", 6379))

r = redis.Redis(host=REDIS_HOST, port=REDIS_PORT, db=0)

pubsub = r.pubsub()
pubsub.subscribe("chat")

print(f"🔄 Worker Python en écoute sur Redis ({REDIS_HOST}:{REDIS_PORT})...")

for message in pubsub.listen():
    if message["type"] == "message":
        try:
            data = json.loads(message["data"])
            content = data.get("content", "")
            blob = TextBlob(content)
            sentiment = blob.sentiment.polarity
            print(f"📩 Message reçu: {content} | Sentiment: {sentiment:.2f}")

            enriched = {
                "senderId": data.get("senderId"),
                "receiverId": data.get("receiverId"),
                "content": content,
                "sentiment": sentiment
            }
            r.publish("chat-analyzed", json.dumps(enriched))
        except Exception as e:
            print("⚠️ Erreur de parsing:", e)
