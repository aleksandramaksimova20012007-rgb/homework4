# homework4
## Docker запуск

```bash
docker build -t collatz-parallel .
docker run --rm -v "${PWD}/results:/app/results" collatz-parallel
```
