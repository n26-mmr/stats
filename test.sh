#!/bin/bash
MAX_VALUE=100
for i in {1..10}; do
  v=$[RANDOM % MAX_VALUE]
  curl -i -XPOST -H "content-type: application/json" -d "{\"amount\": $v, \"timestamp\": $(date +%s999)}" localhost:8080/transactions
  curl localhost:8080/statistics
done
