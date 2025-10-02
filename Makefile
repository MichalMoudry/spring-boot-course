create_local_db:
	docker run -d --name tododb -p 5432:5432 -e POSTGRES_USER=root -e POSTGRES_PASSWORD=root -e POSTGRES_DB=tododb postgres:latest

migrate_local_db:
	migrate -path "./database/migrations" -database "postgres://root:root@localhost:5432/data-persistence?sslmode=disable" up