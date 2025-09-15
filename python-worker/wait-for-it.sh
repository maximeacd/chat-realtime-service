#!/usr/bin/env bash
# wait-for-it.sh: attends qu'un service TCP soit disponible

set -e

host="$1"
port="$2"
shift 2
cmd="$@"

until nc -z "$host" "$port"; do
  echo "⏳ En attente de $host:$port..."
  sleep 2
done

echo "✅ $host:$port est disponible !"
exec $cmd
