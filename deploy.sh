docker build Backend/ -t lomboker:lombokerbackend
docker build Frontend/ -t lomboker:lombokerfrontend
docker tag lomboker:lombokerbackend simmondobber/lombokerbackend
docker tag lomboker:lombokerfrontend simmondobber/lombokerfrontend
docker push simmondobber/lombokerbackend
docker push simmondobber/lombokerfrontend
docker-compose pull
docker-compose up