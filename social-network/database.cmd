
set containerName=mymysqlcontainer
set imageName=mymysqlimage

REM Sprawdzenie, czy kontener o podanej nazwie już istnieje

docker build -t mymysqlimage .

REM Tworzenie kontenera z obrazu
docker run -d --name %containerName% -p 3306:3306 %imageName%

REM Wyświetlenie listy uruchomionych kontenerów
docker ps