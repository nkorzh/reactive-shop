# SD-4

## How to launch?
1. Go to `docker`, launch `docker-compose up -d`
2. Run `init.sql` in the database
3. Run application

## Sample create user request
```
curl -d '{"login":"sancho", "currencyName":"eur"}' -H "Content-Type: application/json" -X POST http://localhost:8080/users/create
```

## Create good request
```
curl -d '{"name":"Camomber 300g", "currency":"eur", "price": 5.0}' -H "Content-Type: application/json" -X POST http://localhost:8080/goods/create
```

Api key `***`
